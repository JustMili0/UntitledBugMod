package net.justmili.bugs.datagen.providers;

import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.BlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class BugsBlockTagProvider extends IntrinsicHolderTagsProvider<Block> {
    public static final TagKey<Block> SILK_BLOCKS = TagKey.create(Registries.BLOCK, BugMod.asResource("silk"));

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
    }
}
