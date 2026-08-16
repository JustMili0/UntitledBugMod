package net.justmili.bugs.datagen.providers.tags;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.justmili.bugs.registries.BlockRegistry;
import net.justmili.bugs.registries.tags.BlockTagRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class BugsBlockTagProvider extends IntrinsicHolderTagsProvider<Block> {
    public BugsBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, Registries.BLOCK, future, block -> BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(BlockTags.WALL_POST_OVERRIDE).add(BlockRegistry.SILK_TRIPWIRE);
        
        this.tag(ConventionalBlockTags.DYED)
            .add(BlockRegistry.WHITE_SILK, BlockRegistry.LIGHT_GRAY_SILK, BlockRegistry.GRAY_SILK, BlockRegistry.BLACK_SILK,
                BlockRegistry.BROWN_SILK, BlockRegistry.RED_SILK, BlockRegistry.ORANGE_SILK, BlockRegistry.YELLOW_SILK,
                BlockRegistry.LIME_SILK, BlockRegistry.GREEN_SILK, BlockRegistry.CYAN_SILK, BlockRegistry.LIGHT_BLUE_SILK, BlockRegistry.BLUE_SILK,
                BlockRegistry.PURPLE_SILK, BlockRegistry.MAGENTA_SILK, BlockRegistry.PINK_SILK,
                BlockRegistry.WHITE_SILK_CARPET, BlockRegistry.LIGHT_GRAY_SILK_CARPET, BlockRegistry.GRAY_SILK_CARPET, BlockRegistry.BLACK_SILK_CARPET,
                BlockRegistry.BROWN_SILK_CARPET, BlockRegistry.RED_SILK_CARPET, BlockRegistry.ORANGE_SILK_CARPET, BlockRegistry.YELLOW_SILK_CARPET,
                BlockRegistry.LIME_SILK_CARPET, BlockRegistry.GREEN_SILK_CARPET, BlockRegistry.CYAN_SILK_CARPET, BlockRegistry.LIGHT_BLUE_SILK_CARPET, BlockRegistry.BLUE_SILK_CARPET,
                BlockRegistry.PURPLE_SILK_CARPET, BlockRegistry.MAGENTA_SILK_CARPET, BlockRegistry.PINK_SILK_CARPET);
        this.tag(ConventionalBlockTags.WHITE_DYED).add(BlockRegistry.WHITE_SILK, BlockRegistry.WHITE_SILK_CARPET);
        this.tag(ConventionalBlockTags.LIGHT_GRAY_DYED).add(BlockRegistry.LIGHT_GRAY_SILK, BlockRegistry.LIGHT_GRAY_SILK_CARPET);
        this.tag(ConventionalBlockTags.GRAY_DYED).add(BlockRegistry.GRAY_SILK, BlockRegistry.GRAY_SILK_CARPET);
        this.tag(ConventionalBlockTags.BLACK_DYED).add(BlockRegistry.BLACK_SILK, BlockRegistry.BLACK_SILK_CARPET);
        this.tag(ConventionalBlockTags.BROWN_DYED).add(BlockRegistry.BROWN_SILK, BlockRegistry.BROWN_SILK_CARPET);
        this.tag(ConventionalBlockTags.RED_DYED).add(BlockRegistry.RED_SILK, BlockRegistry.RED_SILK_CARPET);
        this.tag(ConventionalBlockTags.ORANGE_DYED).add(BlockRegistry.ORANGE_SILK, BlockRegistry.ORANGE_SILK_CARPET);
        this.tag(ConventionalBlockTags.YELLOW_DYED).add(BlockRegistry.YELLOW_SILK, BlockRegistry.YELLOW_SILK_CARPET);
        this.tag(ConventionalBlockTags.LIME_DYED).add(BlockRegistry.LIME_SILK, BlockRegistry.LIME_SILK_CARPET);
        this.tag(ConventionalBlockTags.GREEN_DYED).add(BlockRegistry.GREEN_SILK, BlockRegistry.GREEN_SILK_CARPET);
        this.tag(ConventionalBlockTags.CYAN_DYED).add(BlockRegistry.CYAN_SILK, BlockRegistry.CYAN_SILK_CARPET);
        this.tag(ConventionalBlockTags.LIGHT_BLUE_DYED).add(BlockRegistry.LIGHT_BLUE_SILK, BlockRegistry.LIGHT_BLUE_SILK_CARPET);
        this.tag(ConventionalBlockTags.BLUE_DYED).add(BlockRegistry.BLUE_SILK, BlockRegistry.BLUE_SILK_CARPET);
        this.tag(ConventionalBlockTags.PURPLE_DYED).add(BlockRegistry.PURPLE_SILK, BlockRegistry.PURPLE_SILK_CARPET);
        this.tag(ConventionalBlockTags.MAGENTA_DYED).add(BlockRegistry.MAGENTA_SILK, BlockRegistry.MAGENTA_SILK_CARPET);
        this.tag(ConventionalBlockTags.PINK_DYED).add(BlockRegistry.PINK_SILK, BlockRegistry.PINK_SILK_CARPET);

        this.tag(BlockTagRegistry.SILK_BLOCKS)
            .add(BlockRegistry.WHITE_SILK, BlockRegistry.LIGHT_GRAY_SILK, BlockRegistry.GRAY_SILK, BlockRegistry.BLACK_SILK,
                BlockRegistry.BROWN_SILK, BlockRegistry.RED_SILK, BlockRegistry.ORANGE_SILK, BlockRegistry.YELLOW_SILK,
                BlockRegistry.LIME_SILK, BlockRegistry.GREEN_SILK, BlockRegistry.CYAN_SILK, BlockRegistry.LIGHT_BLUE_SILK, BlockRegistry.BLUE_SILK,
                BlockRegistry.PURPLE_SILK, BlockRegistry.MAGENTA_SILK, BlockRegistry.PINK_SILK);
        this.tag(BlockTagRegistry.SILK_CARPETS)
            .add(BlockRegistry.WHITE_SILK_CARPET, BlockRegistry.LIGHT_GRAY_SILK_CARPET, BlockRegistry.GRAY_SILK_CARPET, BlockRegistry.BLACK_SILK_CARPET,
                BlockRegistry.BROWN_SILK_CARPET, BlockRegistry.RED_SILK_CARPET, BlockRegistry.ORANGE_SILK_CARPET, BlockRegistry.YELLOW_SILK_CARPET,
                BlockRegistry.LIME_SILK_CARPET, BlockRegistry.GREEN_SILK_CARPET, BlockRegistry.CYAN_SILK_CARPET, BlockRegistry.LIGHT_BLUE_SILK_CARPET, BlockRegistry.BLUE_SILK_CARPET,
                BlockRegistry.PURPLE_SILK_CARPET, BlockRegistry.MAGENTA_SILK_CARPET, BlockRegistry.PINK_SILK_CARPET);

        this.tag(BlockTags.DAMPENS_VIBRATIONS)
            .add(BlockRegistry.WHITE_SILK, BlockRegistry.LIGHT_GRAY_SILK, BlockRegistry.GRAY_SILK, BlockRegistry.BLACK_SILK,
                BlockRegistry.BROWN_SILK, BlockRegistry.RED_SILK, BlockRegistry.ORANGE_SILK, BlockRegistry.YELLOW_SILK,
                BlockRegistry.LIME_SILK, BlockRegistry.GREEN_SILK, BlockRegistry.CYAN_SILK, BlockRegistry.LIGHT_BLUE_SILK, BlockRegistry.BLUE_SILK,
                BlockRegistry.PURPLE_SILK, BlockRegistry.MAGENTA_SILK, BlockRegistry.PINK_SILK);
        this.tag(BlockTags.DAMPENS_VIBRATIONS)
            .add(BlockRegistry.WHITE_SILK_CARPET, BlockRegistry.LIGHT_GRAY_SILK_CARPET, BlockRegistry.GRAY_SILK_CARPET, BlockRegistry.BLACK_SILK_CARPET,
                BlockRegistry.BROWN_SILK_CARPET, BlockRegistry.RED_SILK_CARPET, BlockRegistry.ORANGE_SILK_CARPET, BlockRegistry.YELLOW_SILK_CARPET,
                BlockRegistry.LIME_SILK_CARPET, BlockRegistry.GREEN_SILK_CARPET, BlockRegistry.CYAN_SILK_CARPET, BlockRegistry.LIGHT_BLUE_SILK_CARPET, BlockRegistry.BLUE_SILK_CARPET,
                BlockRegistry.PURPLE_SILK_CARPET, BlockRegistry.MAGENTA_SILK_CARPET, BlockRegistry.PINK_SILK_CARPET);

        this.tag(BlockTags.OCCLUDES_VIBRATION_SIGNALS)
            .add(BlockRegistry.WHITE_SILK, BlockRegistry.LIGHT_GRAY_SILK, BlockRegistry.GRAY_SILK, BlockRegistry.BLACK_SILK,
                BlockRegistry.BROWN_SILK, BlockRegistry.RED_SILK, BlockRegistry.ORANGE_SILK, BlockRegistry.YELLOW_SILK,
                BlockRegistry.LIME_SILK, BlockRegistry.GREEN_SILK, BlockRegistry.CYAN_SILK, BlockRegistry.LIGHT_BLUE_SILK, BlockRegistry.BLUE_SILK,
                BlockRegistry.PURPLE_SILK, BlockRegistry.MAGENTA_SILK, BlockRegistry.PINK_SILK);
        this.tag(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
            .add(BlockRegistry.WHITE_SILK_CARPET, BlockRegistry.LIGHT_GRAY_SILK_CARPET, BlockRegistry.GRAY_SILK_CARPET, BlockRegistry.BLACK_SILK_CARPET,
                BlockRegistry.BROWN_SILK_CARPET, BlockRegistry.RED_SILK_CARPET, BlockRegistry.ORANGE_SILK_CARPET, BlockRegistry.YELLOW_SILK_CARPET,
                BlockRegistry.LIME_SILK_CARPET, BlockRegistry.GREEN_SILK_CARPET, BlockRegistry.CYAN_SILK_CARPET, BlockRegistry.LIGHT_BLUE_SILK_CARPET, BlockRegistry.BLUE_SILK_CARPET,
                BlockRegistry.PURPLE_SILK_CARPET, BlockRegistry.MAGENTA_SILK_CARPET, BlockRegistry.PINK_SILK_CARPET);
    }
}
