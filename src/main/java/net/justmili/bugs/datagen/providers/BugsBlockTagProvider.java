package net.justmili.bugs.datagen.providers;

import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.BlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class BugsBlockTagProvider extends IntrinsicHolderTagsProvider<Block> {
    public static final TagKey<Block> SILK_BLOCKS = blockTag(BugMod.asResource("silk"));
    public static final TagKey<Block> SILK_CARPETS = blockTag(BugMod.asResource("silk_carpets"));

    private static TagKey<Block> blockTag(Identifier id) {
        return TagKey.create(Registries.BLOCK, id);
    }

    public BugsBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, Registries.BLOCK, future, block -> BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(SILK_BLOCKS)
            .add(BlockRegistry.WHITE_SILK, BlockRegistry.LIGHT_GRAY_SILK, BlockRegistry.GRAY_SILK, BlockRegistry.BLACK_SILK,
                BlockRegistry.BROWN_SILK, BlockRegistry.RED_SILK, BlockRegistry.ORANGE_SILK, BlockRegistry.YELLOW_SILK,
                BlockRegistry.LIME_SILK, BlockRegistry.GREEN_SILK, BlockRegistry.CYAN_SILK, BlockRegistry.LIGHT_BLUE_SILK, BlockRegistry.BLUE_SILK,
                BlockRegistry.PURPLE_SILK, BlockRegistry.MAGENTA_SILK, BlockRegistry.PINK_SILK);

        this.tag(SILK_CARPETS)
            .add(BlockRegistry.WHITE_SILK_CARPET, BlockRegistry.LIGHT_GRAY_SILK_CARPET, BlockRegistry.GRAY_SILK_CARPET, BlockRegistry.BLACK_SILK_CARPET,
                BlockRegistry.BROWN_SILK_CARPET, BlockRegistry.RED_SILK_CARPET, BlockRegistry.ORANGE_SILK_CARPET, BlockRegistry.YELLOW_SILK_CARPET,
                BlockRegistry.LIME_SILK_CARPET, BlockRegistry.GREEN_SILK_CARPET, BlockRegistry.CYAN_SILK_CARPET, BlockRegistry.LIGHT_BLUE_SILK_CARPET, BlockRegistry.BLUE_SILK_CARPET,
                BlockRegistry.PURPLE_SILK_CARPET, BlockRegistry.MAGENTA_SILK_CARPET, BlockRegistry.PINK_SILK_CARPET);
    }
}
