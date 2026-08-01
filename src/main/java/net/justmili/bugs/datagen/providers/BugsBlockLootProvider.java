package net.justmili.bugs.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class BugsBlockLootProvider extends FabricBlockLootSubProvider {
    public BugsBlockLootProvider(FabricPackOutput dataOut, CompletableFuture<HolderLookup.Provider> future) {
        super(dataOut, future);
    }

    @Override
    public void generate() {
    }
}
