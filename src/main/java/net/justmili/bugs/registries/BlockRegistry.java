package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class BlockRegistry {
    public static void init() {}

    public static final Block
        WHITE_SILK, LIGHT_GRAY_SILK, GRAY_SILK, BLACK_SILK,
        BROWN_SILK, RED_SILK, ORANGE_SILK, YELLOW_SILK,
        LIME_SILK, GREEN_SILK, CYAN_SILK, LIGHT_BLUE_SILK, BLUE_SILK,
        PURPLE_SILK, MAGENTA_SILK, PINK_SILK, SILK_TRIPWIRE;

    static {
        WHITE_SILK = copyAndRegister("white_silk", Blocks.WHITE_WOOL);
        LIGHT_GRAY_SILK = copyAndRegister("light_gray_silk", Blocks.LIGHT_GRAY_WOOL);
        GRAY_SILK = copyAndRegister("gray_silk", Blocks.GRAY_WOOL);
        BLACK_SILK = copyAndRegister("black_silk", Blocks.BLACK_WOOL);
        BROWN_SILK = copyAndRegister("brown_silk", Blocks.BROWN_WOOL);
        RED_SILK = copyAndRegister("red_silk", Blocks.RED_WOOL);
        ORANGE_SILK = copyAndRegister("orange_silk", Blocks.ORANGE_WOOL);
        YELLOW_SILK = copyAndRegister("yellow_silk", Blocks.YELLOW_WOOL);
        LIME_SILK = copyAndRegister("lime_silk", Blocks.LIME_WOOL);
        GREEN_SILK = copyAndRegister("green_silk", Blocks.GREEN_WOOL);
        CYAN_SILK = copyAndRegister("cyan_silk", Blocks.CYAN_WOOL);
        LIGHT_BLUE_SILK = copyAndRegister("light_blue_silk", Blocks.LIGHT_BLUE_WOOL);
        BLUE_SILK = copyAndRegister("blue_silk", Blocks.BLUE_WOOL);
        PURPLE_SILK = copyAndRegister("purple_silk", Blocks.PURPLE_WOOL);
        MAGENTA_SILK = copyAndRegister("magenta_silk", Blocks.MAGENTA_WOOL);
        PINK_SILK = copyAndRegister("pink_silk", Blocks.PINK_WOOL);
        SILK_TRIPWIRE = copyAndRegister("silk_tripwire", Blocks.TRIPWIRE);
    }

    private static Block copyAndRegister(String name, Block blockToCopy) {
        return register(name, BlockBehaviour.Properties.ofFullCopy(blockToCopy));
    }
    private static Block register(String name, BlockBehaviour.Properties properties) {
        var id = BugMod.asResource(name);
        return Registry.register(BuiltInRegistries.BLOCK, id,
            new Block(properties.setId(ResourceKey.create(Registries.BLOCK, id))));
    }
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> block) {
        var id = BugMod.asResource(name);
        return Registry.register(BuiltInRegistries.BLOCK, id,
            block.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, id))));
    }

    public static Block[] getBlocks() {
        return new Block[] {
            SILK_TRIPWIRE, WHITE_SILK, LIGHT_GRAY_SILK, GRAY_SILK, BLACK_SILK,
            BROWN_SILK, RED_SILK, ORANGE_SILK, YELLOW_SILK,
            LIME_SILK, GREEN_SILK, CYAN_SILK, LIGHT_BLUE_SILK, BLUE_SILK,
            PURPLE_SILK, MAGENTA_SILK, PINK_SILK
        };
    }
}
