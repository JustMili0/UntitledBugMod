package net.justmili.libs.v1.utils.common;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EntityUtil {
    // Player
    public static void consumeHeldWithResult(Player player, InteractionHand hand, Item result, boolean shrinkStack) {
        var stack = player.getItemInHand(hand);
        var item = new ItemStack(result);

        if (shrinkStack) stack.shrink(1);
        if (stack.isEmpty()) {
            player.setItemInHand(hand, item);
        } else if (!player.getInventory().add(item)) {
            player.drop(item, false);
        }
    }
}