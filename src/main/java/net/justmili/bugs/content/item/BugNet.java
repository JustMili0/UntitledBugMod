package net.justmili.bugs.content.item;

import net.justmili.bugs.registries.ItemTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BugNet extends Item {
    public static DataComponentType<CustomData> BUG_NET_ENTITY_DATA;
    public BugNet(Properties properties) {
        super(properties.durability(96).enchantable(14).repairable(ItemTagRegistry.BUG_NET_REPAIRABLES));
    }

    // TODO: add insect catching mechanic
    // Can only store one bug. LC to catch, Shift+LC to release
    // For hostile insects weakness is required (e.g. for silverfish, endermites)
    // Item textures and handheld model changes depending on what bug is stored
    // If the bug isn't present in ones that have a custom model, it will default to a random model

    // Bugs to do (indev): Bees, Silverfish, Endermites, Spiders (use entity tag if spiders have one)
    // Will add all the mod's bugs once they're implemented

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
