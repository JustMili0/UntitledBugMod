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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemModelResolver.class)
public abstract class ItemModelResolverMixin {

    @ModifyReceiver(
        method = "appendItemLayers(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/ItemOwner;I)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/item/ItemModel;update(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/item/ItemModelResolver;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/entity/ItemOwner;I)V"))
    private ItemModel applyRenderOverride(ItemModel model, ItemStackRenderState renderState, ItemStack stack, ItemModelResolver resolver,
                                          ItemDisplayContext displayContext, ClientLevel level, ItemOwner owner, int seed) {
        ItemRendererUtil.Entry override = ItemRendererUtil.getOverrideItem(stack.getItem());
        if (override == null) return model;
        if (stack.getItem() != override.item()) return model;

        ItemRendererUtil.ItemDisplay mapped = corelibs$toItemDisplay(displayContext);
        if (mapped != null && override.displayAt().contains(mapped)) {
            return resolver.getItemModel(override.otherItemModel());
        }

        return model;
    }

    @Unique
    private static ItemRendererUtil.ItemDisplay corelibs$toItemDisplay(ItemDisplayContext context) {
        return switch (context) {
            case GUI -> ItemRendererUtil.ItemDisplay.GUI;
            case GROUND -> ItemRendererUtil.ItemDisplay.ON_GROUND;
            case FIXED -> ItemRendererUtil.ItemDisplay.FIXED;
            case ON_SHELF -> ItemRendererUtil.ItemDisplay.ON_SHELF;
            case HEAD -> ItemRendererUtil.ItemDisplay.HEAD;
            case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> ItemRendererUtil.ItemDisplay.FIRST_PERSON;
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND -> ItemRendererUtil.ItemDisplay.THIRD_PERSON;
            default -> null;
        };
    }
}