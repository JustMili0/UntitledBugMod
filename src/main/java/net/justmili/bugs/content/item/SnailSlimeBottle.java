package net.justmili.bugs.content.item;

import net.justmili.bugs.registries.BlockRegistry;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;

public class SnailSlimeBottle extends BlockItem {

    public SnailSlimeBottle(Properties properties) {
        super(BlockRegistry.SNAIL_TRAIL, properties.stacksTo(16));
    }

    @Override
    public ItemStackTemplate getCraftingRemainder(ItemStack stack) {
        return new ItemStackTemplate(Items.GLASS_BOTTLE);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var result = super.useOn(context);
        if (!result.consumesAction()) return result;

        var player = context.getPlayer();
        if (player == null || player.getAbilities().instabuild) return result;

        var stack = context.getItemInHand();
        var bottle = new ItemStack(Items.GLASS_BOTTLE);

        stack.shrink(1);
        if (stack.isEmpty()) {
            context.getPlayer().setItemInHand(context.getHand(), bottle);
        } else if (!player.getInventory().add(bottle)) {
            player.drop(bottle, false);
        }

        return result;
    }
}
