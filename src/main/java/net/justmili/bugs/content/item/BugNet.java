package net.justmili.bugs.content.item;

import net.justmili.bugs.registries.ItemTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BugNet extends Item {
    public BugNet(Properties properties) {
        super(properties.durability(96).enchantable(14).repairable(ItemTagRegistry.BUG_NET_REPAIRABLES));
    }

    // TODO: add insect catching mechanic
    // For hostile insects weakness is required (e.g. for silverfish, endermites)
    // Can only store one bug. LC to catch, Shift+LC to release
    // Item texture changes depending on what bug is stored

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.swing(hand);
        return InteractionResult.PASS;
    }

    @Override
    public boolean canDestroyBlock(ItemStack itemStack, BlockState state, Level level, BlockPos pos, LivingEntity user) {
        if (!(user instanceof Player player)) return false;
        return player.gameMode().isSurvival(); // TODO: Should not be able to destroy blocks if Net contains entities
    }
}
