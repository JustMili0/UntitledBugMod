package net.justmili.bugs.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.bugs.registries.ItemRegistry;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Mixin(ItemPredicate.class)
public class ItemPredicateMixin {
    @Shadow
    @Final
    private MinMaxBounds.Ints count;

    @Shadow
    @Final
    private DataComponentMatchers components;

    @Shadow
    @Final
    private Optional<HolderSet<Item>> items;

    @ModifyReturnValue(method = "test(Lnet/minecraft/world/item/ItemInstance;)Z", at = @At("RETURN"))
    private boolean checkCopperShears(boolean original, ItemInstance stack) {
        if (original) return true;
        if (!count.isAny() || !components.isEmpty()) return false;

        var items = this.items.orElse(null);
        if (items == null || !items.isBound() || items.size() != 1 || items.get(0).value() != Items.SHEARS) return false;

        return stack.is(ItemRegistry.COPPER_SHEARS);
    }
}