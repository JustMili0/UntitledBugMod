package net.justmili.bugs.content.block;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class SnailSlimeBlock extends SlimeBlock {
    public SnailSlimeBlock(Properties properties) {
        super(properties.friction(0.98f).sound(SoundType.SLIME_BLOCK).mapColor(MapColor.SAND));
    }

    @Override
    public void updateEntityMovementAfterFallOn(BlockGetter level, Entity entity) {
        // Nothin'
    }
}
