package net.justmili.bugs.content.block;

import net.justmili.bugs.registries.ItemRegistry;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class HoneyClumpBlock extends MultifaceBlock {
    public static final int MIN_CLUMPS = 1, MAX_CLUMPS = 3;
    public static final IntegerProperty CLUMPS;

    public HoneyClumpBlock(Properties properties) {
        super(properties.mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.HONEY_BLOCK).pushReaction(PushReaction.DESTROY).replaceable().noCollision());
        this.registerDefaultState(this.defaultBlockState().setValue(CLUMPS, MIN_CLUMPS));
    }

    static {
        CLUMPS = IntegerProperty.create("clumps", MIN_CLUMPS, MAX_CLUMPS);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        if (context.getItemInHand().is(ItemRegistry.HONEY_CLUMP)) return state.getValue(CLUMPS) < MAX_CLUMPS;
        return super.canBeReplaced(state, context);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var existing = context.getLevel().getBlockState(context.getClickedPos());
        if (existing.is(this)) return existing.cycle(CLUMPS);

        var state = super.getStateForPlacement(context);
        return state == null? null : state.setValue(CLUMPS, MIN_CLUMPS);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, Fluid fluid) {
        return fluid.is(FluidTags.WATER);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CLUMPS);
    }
}