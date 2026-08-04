package net.justmili.libs.mixin;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.justmili.libs.v1.utils.client.ItemRendererUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemModelResolver.class)
public abstract class ItemModelResolverMixin {

    @ModifyReceiver(
        method = "appendItemLayers(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/ItemOwner;I)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/item/ItemModel;update(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/item/ItemModelResolver;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/entity/ItemOwner;I)V"))
    private ItemModel applyRenderOverride(ItemModel model, ItemStackRenderState renderState, ItemStack stack, ItemModelResolver resolver,
                                          ItemDisplayContext displayContext, ClientLevel level, ItemOwner owner, int seed) {
        var override = ItemRendererUtil.getOverrideItem(stack.getItem());
        if (override == null) return model;
        if (stack.getItem() != override.item()) return model;

        var mapped = ItemRendererUtil.toItemDisplay(displayContext);
        if (mapped != null && override.displayAt().contains(mapped)) {
            return resolver.getItemModel(override.otherItemModel());
        }

        return model;
    }
}