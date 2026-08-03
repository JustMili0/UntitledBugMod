package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.justmili.bugs.content.block.SnailSlimeBlock;
import net.justmili.bugs.content.block.SnailSlimeTrail;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.TripWireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class BlockRegistry {
    public static void init() {}

    public static final Block
        SILK_TRIPWIRE, WHITE_SILK, LIGHT_GRAY_SILK, GRAY_SILK, BLACK_SILK,
        BROWN_SILK, RED_SILK, ORANGE_SILK, YELLOW_SILK,
        LIME_SILK, GREEN_SILK, CYAN_SILK, LIGHT_BLUE_SILK, BLUE_SILK,
        PURPLE_SILK, MAGENTA_SILK, PINK_SILK,
        WHITE_SILK_CARPET, LIGHT_GRAY_SILK_CARPET, GRAY_SILK_CARPET, BLACK_SILK_CARPET,
        BROWN_SILK_CARPET, RED_SILK_CARPET, ORANGE_SILK_CARPET, YELLOW_SILK_CARPET,
        LIME_SILK_CARPET, GREEN_SILK_CARPET, CYAN_SILK_CARPET, LIGHT_BLUE_SILK_CARPET, BLUE_SILK_CARPET,
        PURPLE_SILK_CARPET, MAGENTA_SILK_CARPET, PINK_SILK_CARPET, SNAIL_TRAIL, SNAIL_SLIME_BLOCK;

    static {
        SILK_TRIPWIRE = copyAndRegister("silk_tripwire", Blocks.TRIPWIRE, p -> new TripWireBlock(Blocks.TRIPWIRE_HOOK, p));

        WHITE_SILK = copyWoolAndRegister("white_silk", Blocks.WHITE_WOOL);
        LIGHT_GRAY_SILK = copyWoolAndRegister("light_gray_silk", Blocks.LIGHT_GRAY_WOOL);
        GRAY_SILK = copyWoolAndRegister("gray_silk", Blocks.GRAY_WOOL);
        BLACK_SILK = copyWoolAndRegister("black_silk", Blocks.BLACK_WOOL);
        BROWN_SILK = copyWoolAndRegister("brown_silk", Blocks.BROWN_WOOL);
        RED_SILK = copyWoolAndRegister("red_silk", Blocks.RED_WOOL);
        ORANGE_SILK = copyWoolAndRegister("orange_silk", Blocks.ORANGE_WOOL);
        YELLOW_SILK = copyWoolAndRegister("yellow_silk", Blocks.YELLOW_WOOL);
        LIME_SILK = copyWoolAndRegister("lime_silk", Blocks.LIME_WOOL);
        GREEN_SILK = copyWoolAndRegister("green_silk", Blocks.GREEN_WOOL);
        CYAN_SILK = copyWoolAndRegister("cyan_silk", Blocks.CYAN_WOOL);
        LIGHT_BLUE_SILK = copyWoolAndRegister("light_blue_silk", Blocks.LIGHT_BLUE_WOOL);
        BLUE_SILK = copyWoolAndRegister("blue_silk", Blocks.BLUE_WOOL);
        PURPLE_SILK = copyWoolAndRegister("purple_silk", Blocks.PURPLE_WOOL);
        MAGENTA_SILK = copyWoolAndRegister("magenta_silk", Blocks.MAGENTA_WOOL);
        PINK_SILK = copyWoolAndRegister("pink_silk", Blocks.PINK_WOOL);

        WHITE_SILK_CARPET = copyCarpetAndRegister("white_silk_carpet", Blocks.WHITE_CARPET);
        LIGHT_GRAY_SILK_CARPET = copyCarpetAndRegister("light_gray_silk_carpet", Blocks.LIGHT_GRAY_CARPET);
        GRAY_SILK_CARPET = copyCarpetAndRegister("gray_silk_carpet", Blocks.GRAY_CARPET);
        BLACK_SILK_CARPET = copyCarpetAndRegister("black_silk_carpet", Blocks.BLACK_CARPET);
        BROWN_SILK_CARPET = copyCarpetAndRegister("brown_silk_carpet", Blocks.BROWN_CARPET);
        RED_SILK_CARPET = copyCarpetAndRegister("red_silk_carpet", Blocks.RED_CARPET);
        ORANGE_SILK_CARPET = copyCarpetAndRegister("orange_silk_carpet", Blocks.ORANGE_CARPET);
        YELLOW_SILK_CARPET = copyCarpetAndRegister("yellow_silk_carpet", Blocks.YELLOW_CARPET);
        LIME_SILK_CARPET = copyCarpetAndRegister("lime_silk_carpet", Blocks.LIME_CARPET);
        GREEN_SILK_CARPET = copyCarpetAndRegister("green_silk_carpet", Blocks.GREEN_CARPET);
        CYAN_SILK_CARPET = copyCarpetAndRegister("cyan_silk_carpet", Blocks.CYAN_CARPET);
        LIGHT_BLUE_SILK_CARPET = copyCarpetAndRegister("light_blue_silk_carpet", Blocks.LIGHT_BLUE_CARPET);
        BLUE_SILK_CARPET = copyCarpetAndRegister("blue_silk_carpet", Blocks.BLUE_CARPET);
        PURPLE_SILK_CARPET = copyCarpetAndRegister("purple_silk_carpet", Blocks.PURPLE_CARPET);
        MAGENTA_SILK_CARPET = copyCarpetAndRegister("magenta_silk_carpet", Blocks.MAGENTA_CARPET);
        PINK_SILK_CARPET = copyCarpetAndRegister("pink_silk_carpet", Blocks.PINK_CARPET);

        SNAIL_TRAIL = register("snail_slime_trail", SnailSlimeTrail::new);
        SNAIL_SLIME_BLOCK = register("snail_slime_block", SnailSlimeBlock::new);
    }

    private static Block copyWoolAndRegister(String name, Block blockToCopy) {
        return copyAndRegister(name, blockToCopy, Block::new);
    }
    private static Block copyCarpetAndRegister(String name, Block blockToCopy) {
        return copyAndRegister(name, blockToCopy, CarpetBlock::new);
    }
    private static Block copyAndRegister(String name, Block blockToCopy, Function<BlockBehaviour.Properties, Block> block) {
        var id = BugMod.asId(name);
        var properties = BlockBehaviour.Properties.ofFullCopy(blockToCopy).setId(ResourceKey.create(Registries.BLOCK, id));
        return Registry.register(BuiltInRegistries.BLOCK, id, block.apply(properties));
    }
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> block) {
        var id = BugMod.asId(name);
        return Registry.register(BuiltInRegistries.BLOCK, id,
            block.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, id))));
    }

    public static Block[] getSilkBlocks() {
        return new Block[] {
            SILK_TRIPWIRE, WHITE_SILK, LIGHT_GRAY_SILK, GRAY_SILK, BLACK_SILK,
            BROWN_SILK, RED_SILK, ORANGE_SILK, YELLOW_SILK,
            LIME_SILK, GREEN_SILK, CYAN_SILK, LIGHT_BLUE_SILK, BLUE_SILK,
            PURPLE_SILK, MAGENTA_SILK, PINK_SILK,
            WHITE_SILK_CARPET, LIGHT_GRAY_SILK_CARPET, GRAY_SILK_CARPET, BLACK_SILK_CARPET,
            BROWN_SILK_CARPET, RED_SILK_CARPET, ORANGE_SILK_CARPET, YELLOW_SILK_CARPET,
            LIME_SILK_CARPET, GREEN_SILK_CARPET, CYAN_SILK_CARPET, LIGHT_BLUE_SILK_CARPET, BLUE_SILK_CARPET,
            PURPLE_SILK_CARPET, MAGENTA_SILK_CARPET, PINK_SILK_CARPET
        };
    }
}
