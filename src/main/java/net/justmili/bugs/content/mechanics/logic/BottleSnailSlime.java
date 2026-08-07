package net.justmili.bugs.content.mechanics.logic;

import net.justmili.bugs.registries.BlockRegistry;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.libs.v1.utils.common.EntityUtil;
import net.justmili.libs.v1.utils.common.LevelUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class BottleSnailSlime {
    public static InteractionResult onBlockClicked(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        var blockPos = hitResult.getBlockPos();
        var block = level.getBlockState(blockPos);
        var stack = player.getItemInHand(hand);

        if (block.is(BlockRegistry.SNAIL_SLIME_TRAIL) && stack.is(Items.GLASS_BOTTLE)) {
            LevelUtil.setAir(level, blockPos); // Set to air
            EntityUtil.consumeHeldWithResult(player, hand, ItemRegistry.SNAIL_SLIME_BOTTLE, true);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
