package net.justmili.bugs.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.libs.v1.utils.common.DatagenDataUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class BugsRecipeProvider extends FabricRecipeProvider {
    public BugsRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput writer) {
        return new RecipeProvider(provider, writer) {
            @Override
            public void buildRecipes() {
                var gen = new DatagenDataUtil(provider, BugMod.MODID, writer);

                gen.shaped2x2(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.SILK, ItemRegistry.WHITE_SILK, 1);
            }
        };
    }

    @Override
    public String getName() {
        return "UntitledBugsModRecipeProvider";
    }
}
