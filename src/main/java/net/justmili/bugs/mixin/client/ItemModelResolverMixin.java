package net.justmili.bugs.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.justmili.bugs.content.item.BugNet;
import net.justmili.libs.v1.utils.client.ItemRendererUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.EnumSet;
import java.util.Set;

import static net.justmili.bugs.content.resource.Textures.*;

@Mixin(ItemModelResolver.class)
public abstract class ItemModelResolverMixin {

    @Unique
    private static final Set<ItemRendererUtil.ItemDisplay> bugmod$RENDER_2D = EnumSet.of(
        ItemRendererUtil.ItemDisplay.GUI,
        ItemRendererUtil.ItemDisplay.FIXED,
        ItemRendererUtil.ItemDisplay.ON_SHELF,
        ItemRendererUtil.ItemDisplay.ON_GROUND
    );
    private static final Set<ItemRendererUtil.ItemDisplay> bugmod$RENDER_3D = EnumSet.of(
        ItemRendererUtil.ItemDisplay.FIRST_PERSON,
        ItemRendererUtil.ItemDisplay.THIRD_PERSON,
        ItemRendererUtil.ItemDisplay.HEAD
    );

    @ModifyReceiver(
        method = "appendItemLayers(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/ItemOwner;I)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/item/ItemModel;update(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/item/ItemModelResolver;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/entity/ItemOwner;I)V"))
    private ItemModel applyRenderOverride(ItemModel model, ItemStackRenderState renderState, ItemStack stack, ItemModelResolver resolver,
                                          ItemDisplayContext displayContext, ClientLevel level, ItemOwner owner, int seed) {
        if (stack.getItem() instanceof BugNet) {
            return bugmod$resolveBugNetModel(model, stack, resolver, displayContext);
        }

        var override = ItemRendererUtil.getOverrideItem(stack.getItem());
        if (override == null) return model;
        if (stack.getItem() != override.item()) return model;

        var mapped = ItemRendererUtil.toItemDisplay(displayContext);
        if (mapped != null && override.displayAt().contains(mapped)) {
            return resolver.getItemModel(override.otherItemModel());
        }

        return model;
    }

    @Unique
    private ItemModel bugmod$resolveBugNetModel(ItemModel defaultModel, ItemStack stack, ItemModelResolver resolver, ItemDisplayContext displayContext) {
        var mapped = ItemRendererUtil.toItemDisplay(displayContext);
        if (mapped == null) return defaultModel;

        boolean is2D = bugmod$RENDER_2D.contains(mapped);
        if (!is2D && !bugmod$RENDER_3D.contains(mapped)) return defaultModel;

        String caughtId = BugNet.getEntityIdentifierString(stack);

        Identifier resolved = switch (caughtId) {
            case "minecraft:bee" -> is2D ? BEE_2D : BEE_3D;
            case "minecraft:silverfish" -> is2D ? SILVERFISH_2D : SILVERFISH_3D;
            case "minecraft:endermite" -> is2D ? ENDERMITE_2D : ENDERMITE_3D;
            case "minecraft:spider" -> is2D ? SPIDER_2D : SPIDER_3D;
            case "minecraft:cave_spider" -> is2D ? CAVE_SPIDER_2D : CAVE_SPIDER_3D;
            default -> null;
        };

        if (resolved != null) return resolver.getItemModel(resolved);
        return is2D ? resolver.getItemModel(DEFAULT_2D) : defaultModel;
    }
}