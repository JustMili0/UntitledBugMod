package net.justmili.bugs.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.justmili.bugs.registries.BlockRegistry;
import net.justmili.bugs.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class BugsBlockLootProvider extends FabricBlockLootSubProvider {
    public BugsBlockLootProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    public void generate() {
        for (var block : BlockRegistry.getSilkBlocks()) {
            dropSelf(block);
        }
        dropOther(BlockRegistry.HONEY_CLUMP, ItemRegistry.HONEY_CLUMP);
        dropOther(BlockRegistry.SNAIL_SLIME_TRAIL, ItemRegistry.SNAIL_SLIME);
        dropSelf(BlockRegistry.SNAIL_SLIME_BLOCK);
    }
}
