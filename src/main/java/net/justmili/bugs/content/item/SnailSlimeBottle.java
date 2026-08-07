package net.justmili.bugs.content.item;

import net.justmili.bugs.registries.BlockRegistry;
import net.justmili.libs.v1.utils.common.EntityUtil;
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

        EntityUtil.consumeHeldWithResult(player, context.getHand(), Items.GLASS_BOTTLE, false);

        return result;
    }
}
