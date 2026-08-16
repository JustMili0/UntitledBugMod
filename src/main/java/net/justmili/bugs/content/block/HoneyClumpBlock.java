package net.justmili.bugs.content.block;

import net.justmili.bugs.registries.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HoneyClumpBlock extends Block {
    public static final int MIN_CLUMPS = 1, MAX_CLUMPS = 3;
    public static final IntegerProperty CLUMPS_NORTH, CLUMPS_SOUTH, CLUMPS_EAST, CLUMPS_WEST, CLUMPS_UP, CLUMPS_DOWN;
    private static final VoxelShape SHAPE_DOWN, SHAPE_UP, SHAPE_NORTH, SHAPE_SOUTH, SHAPE_EAST, SHAPE_WEST;

    public HoneyClumpBlock(Properties properties) {
        super(properties.mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.HONEY_BLOCK).pushReaction(PushReaction.DESTROY).replaceable().noCollision());
        var defaultState = this.stateDefinition.any();
        for (Direction direction : Direction.values()) defaultState = defaultState.setValue(faceProperty(direction), 0);
        this.registerDefaultState(defaultState);
    }

    static {
        CLUMPS_NORTH = IntegerProperty.create("clumps_north", 0, MAX_CLUMPS);
        CLUMPS_SOUTH = IntegerProperty.create("clumps_south", 0, MAX_CLUMPS);
        CLUMPS_EAST = IntegerProperty.create("clumps_east", 0, MAX_CLUMPS);
        CLUMPS_WEST = IntegerProperty.create("clumps_west", 0, MAX_CLUMPS);
        CLUMPS_UP = IntegerProperty.create("clumps_up", 0, MAX_CLUMPS);
        CLUMPS_DOWN = IntegerProperty.create("clumps_down", 0, MAX_CLUMPS);

        SHAPE_DOWN = box(0, 0, 0, 16, 1, 16);
        SHAPE_UP = box(0, 15, 0, 16, 16, 16);
        SHAPE_NORTH = box(0, 0, 0, 16, 16, 1);
        SHAPE_SOUTH = box(0, 0, 15, 16, 16, 16);
        SHAPE_EAST = box(15, 0, 0, 16, 16, 16);
        SHAPE_WEST = box(0, 0, 0, 1, 16, 16);
    }

    public static IntegerProperty faceProperty(Direction direction) {
        return switch (direction) {
            case NORTH -> CLUMPS_NORTH;
            case SOUTH -> CLUMPS_SOUTH;
            case EAST -> CLUMPS_EAST;
            case WEST -> CLUMPS_WEST;
            case UP -> CLUMPS_UP;
            case DOWN -> CLUMPS_DOWN;
        };
    }

    private static VoxelShape shapeFor(Direction direction) {
        return switch (direction) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            case UP -> SHAPE_UP;
            case DOWN -> SHAPE_DOWN;
        };
    }

    public static boolean hasAnyFace(BlockState state) {
        for (Direction direction : Direction.values()) if (state.getValue(faceProperty(direction)) > 0) return true;
        return false;
    }

    private static boolean canAttachTo(LevelReader level, Direction direction, BlockPos neighborPos, BlockState neighborState) {
        return neighborState.isFaceSturdy(level, neighborPos, direction.getOpposite());
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (state.getValue(faceProperty(direction)) > 0) {
                var neighborPos = pos.relative(direction);
                if (canAttachTo(level, direction, neighborPos, level.getBlockState(neighborPos))) return true;
            }
        }
        return false;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
                                     Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        var changedProperty = faceProperty(directionToNeighbour);
        if (state.getValue(changedProperty) > 0 && !canAttachTo(level, directionToNeighbour, neighbourPos, neighbourState)) {
            state = state.setValue(changedProperty, 0);
        }
        return hasAnyFace(state) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        if (context.getItemInHand().is(ItemRegistry.HONEY_CLUMP) && !context.isSecondaryUseActive()) {
            var property = faceProperty(context.getClickedFace());
            return state.getValue(property) < MAX_CLUMPS;
        }
        return super.canBeReplaced(state, context);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var existing = context.getLevel().getBlockState(context.getClickedPos());
        var supportDirection = context.getClickedFace().getOpposite();

        if (existing.is(this)) {
            var property = faceProperty(supportDirection);
            int current = existing.getValue(property);
            if (current >= MAX_CLUMPS) return null;
            return existing.setValue(property, current + 1);
        }

        var level = context.getLevel();
        var pos = context.getClickedPos();
        var supportPos = pos.relative(supportDirection);
        if (!canAttachTo(level, supportDirection, supportPos, level.getBlockState(supportPos))) return null;

        return this.defaultBlockState().setValue(faceProperty(supportDirection), MIN_CLUMPS);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, Fluid fluid) {
        return fluid.is(FluidTags.WATER);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        var shape = Shapes.empty();
        for (Direction direction : Direction.values()) {
            if (state.getValue(faceProperty(direction)) > 0) shape = Shapes.or(shape, shapeFor(direction));
        }
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLUMPS_NORTH, CLUMPS_SOUTH, CLUMPS_EAST, CLUMPS_WEST, CLUMPS_UP, CLUMPS_DOWN);
    }
}