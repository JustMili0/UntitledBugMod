package net.justmili.bugs.content.item;

import net.justmili.bugs.registries.BlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;

public class SnailSlimeBottle extends BlockItem {

    public SnailSlimeBottle(Properties properties) {
        super(BlockRegistry.SNAIL_TRAIL, properties.stacksTo(16));
    }

    @Override
    public ItemStackTemplate getCraftingRemainder(ItemStack stack) {
        return new ItemStackTemplate(Items.GLASS_BOTTLE);
    }
}
