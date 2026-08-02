package net.justmili.bugs.content.block;

import net.justmili.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SnailSlimeTrail extends Block {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH, EAST = BlockStateProperties.EAST,
        SOUTH = BlockStateProperties.SOUTH, WEST = BlockStateProperties.WEST;
    private static final VoxelShape SHAPE_NSEW = Block.box(3, 0, 3, 13, 1, 13);

    public SnailSlimeTrail(Properties properties) {
        super(properties.instabreak().noCollision().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.SLIME_BLOCK)
            .isSuffocating(BlockBehaviorUtil::never).isViewBlocking(BlockBehaviorUtil::never).isValidSpawn(BlockBehaviorUtil::never));
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE_NSEW;
    }

    private boolean shouldConnectTo(BlockState neighborState) {
        return neighborState.is(this);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return this.defaultBlockState()
            .setValue(NORTH, shouldConnectTo(level.getBlockState(pos.north())))
            .setValue(EAST, shouldConnectTo(level.getBlockState(pos.east())))
            .setValue(SOUTH, shouldConnectTo(level.getBlockState(pos.south())))
            .setValue(WEST, shouldConnectTo(level.getBlockState(pos.west())));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
                                     Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (!directionToNeighbour.getAxis().isHorizontal()) {
            return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
        }
        BooleanProperty property = switch (directionToNeighbour) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> throw new IllegalStateException("Unreachable: " + directionToNeighbour);
        };
        return state.setValue(property, shouldConnectTo(neighbourState));
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case CLOCKWISE_180 -> state
                .setValue(NORTH, state.getValue(SOUTH)).setValue(EAST, state.getValue(WEST))
                .setValue(SOUTH, state.getValue(NORTH)).setValue(WEST, state.getValue(EAST));
            case CLOCKWISE_90 -> state
                .setValue(NORTH, state.getValue(WEST)).setValue(EAST, state.getValue(NORTH))
                .setValue(SOUTH, state.getValue(EAST)).setValue(WEST, state.getValue(SOUTH));
            case COUNTERCLOCKWISE_90 -> state
                .setValue(NORTH, state.getValue(EAST)).setValue(EAST, state.getValue(SOUTH))
                .setValue(SOUTH, state.getValue(WEST)).setValue(WEST, state.getValue(NORTH));
            default -> state;
        };
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case LEFT_RIGHT -> state.setValue(NORTH, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(NORTH));
            case FRONT_BACK -> state.setValue(EAST, state.getValue(WEST)).setValue(WEST, state.getValue(EAST));
            default -> super.mirror(state, mirror);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST);
    }
}