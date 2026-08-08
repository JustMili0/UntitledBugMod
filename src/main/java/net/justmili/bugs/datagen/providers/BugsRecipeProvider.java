package net.justmili.bugs.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.bugs.registries.ItemTagRegistry;
import net.justmili.libs.v1.utils.common.datagen.DatagenDataUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

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

                var building = RecipeCategory.BUILDING_BLOCKS;
                var decoration = RecipeCategory.DECORATIONS;
                var redstone = RecipeCategory.REDSTONE;
                var misc = RecipeCategory.MISC;

                shaped(decoration, ItemRegistry.COPPER_SHEARS, 1)
                    .define('#', Items.COPPER_INGOT)
                    .pattern(" #")
                    .pattern("# ")
                    .unlockedBy("has_copper", has(Items.COPPER_INGOT))
                    .save(output, newRecipeKey("copper_shears"));

                shaped(decoration, ItemRegistry.BUG_NET, 3)
                    .define('/', Items.STICK)
                    .define('@', ItemRegistry.SILK)
                    .pattern("  /")
                    .pattern(" /@")
                    .pattern("/@@")
                    .unlockedBy("has_silk", has(ItemRegistry.SILK))
                    .save(output, newRecipeKey("bug_net"));

                // White Silk (base)
                gen.shaped2x2(building, ItemRegistry.SILK, ItemRegistry.WHITE_SILK, 1);

                // Carpets
                gen.carpet(ItemRegistry.WHITE_SILK, ItemRegistry.WHITE_SILK_CARPET);
                gen.carpet(ItemRegistry.LIGHT_GRAY_SILK, ItemRegistry.LIGHT_GRAY_SILK_CARPET);
                gen.carpet(ItemRegistry.GRAY_SILK, ItemRegistry.GRAY_SILK_CARPET);
                gen.carpet(ItemRegistry.BLACK_SILK, ItemRegistry.BLACK_SILK_CARPET);
                gen.carpet(ItemRegistry.BROWN_SILK, ItemRegistry.BROWN_SILK_CARPET);
                gen.carpet(ItemRegistry.RED_SILK, ItemRegistry.RED_SILK_CARPET);
                gen.carpet(ItemRegistry.ORANGE_SILK, ItemRegistry.ORANGE_SILK_CARPET);
                gen.carpet(ItemRegistry.YELLOW_SILK, ItemRegistry.YELLOW_SILK_CARPET);
                gen.carpet(ItemRegistry.LIME_SILK, ItemRegistry.LIME_SILK_CARPET);
                gen.carpet(ItemRegistry.GREEN_SILK, ItemRegistry.GREEN_SILK_CARPET);
                gen.carpet(ItemRegistry.CYAN_SILK, ItemRegistry.CYAN_SILK_CARPET);
                gen.carpet(ItemRegistry.LIGHT_BLUE_SILK, ItemRegistry.LIGHT_BLUE_SILK_CARPET);
                gen.carpet(ItemRegistry.BLUE_SILK, ItemRegistry.BLUE_SILK_CARPET);
                gen.carpet(ItemRegistry.PURPLE_SILK, ItemRegistry.PURPLE_SILK_CARPET);
                gen.carpet(ItemRegistry.MAGENTA_SILK, ItemRegistry.MAGENTA_SILK_CARPET);
                gen.carpet(ItemRegistry.PINK_SILK, ItemRegistry.PINK_SILK_CARPET);

                // Dyed Silks
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.WHITE_DYE, ItemRegistry.WHITE_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.LIGHT_GRAY_DYE, ItemRegistry.LIGHT_GRAY_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.GRAY_DYE, ItemRegistry.GRAY_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.BLACK_DYE, ItemRegistry.BLACK_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.BROWN_DYE, ItemRegistry.BROWN_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.RED_DYE, ItemRegistry.RED_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.ORANGE_DYE, ItemRegistry.ORANGE_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.YELLOW_DYE, ItemRegistry.YELLOW_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.LIME_DYE, ItemRegistry.LIME_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.GREEN_DYE, ItemRegistry.GREEN_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.CYAN_DYE, ItemRegistry.CYAN_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.LIGHT_BLUE_DYE, ItemRegistry.LIGHT_BLUE_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.BLUE_DYE, ItemRegistry.BLUE_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.PURPLE_DYE, ItemRegistry.PURPLE_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.MAGENTA_DYE, ItemRegistry.MAGENTA_SILK);
                gen.dyeItem(ItemTagRegistry.SILK_BLOCKS, Items.PINK_DYE, ItemRegistry.PINK_SILK);

                // Dyed Silk Carpets
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.WHITE_DYE, ItemRegistry.WHITE_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.LIGHT_GRAY_DYE, ItemRegistry.LIGHT_GRAY_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.GRAY_DYE, ItemRegistry.GRAY_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.BLACK_DYE, ItemRegistry.BLACK_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.BROWN_DYE, ItemRegistry.BROWN_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.RED_DYE, ItemRegistry.RED_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.ORANGE_DYE, ItemRegistry.ORANGE_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.YELLOW_DYE, ItemRegistry.YELLOW_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.LIME_DYE, ItemRegistry.LIME_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.GREEN_DYE, ItemRegistry.GREEN_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.CYAN_DYE, ItemRegistry.CYAN_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.LIGHT_BLUE_DYE, ItemRegistry.LIGHT_BLUE_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.BLUE_DYE, ItemRegistry.BLUE_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.PURPLE_DYE, ItemRegistry.PURPLE_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.MAGENTA_DYE, ItemRegistry.MAGENTA_SILK_CARPET);
                gen.dyeItem(ItemTagRegistry.SILK_CARPETS, Items.PINK_DYE, ItemRegistry.PINK_SILK_CARPET);

                // Snail Slime
                gen.shaped2x2(RecipeCategory.REDSTONE, ItemRegistry.SNAIL_SLIME_BOTTLE, ItemRegistry.SNAIL_SLIME_BLOCK, 1);
                gen.shapeless(misc, ItemRegistry.SNAIL_SLIME, ItemRegistry.SNAIL_SLIME_BOTTLE);
                gen.shapeless(misc, ItemRegistry.SNAIL_SLIME_BOTTLE, ItemRegistry.SNAIL_SLIME, Items.GLASS_BOTTLE);

                gen.shapeless(misc, Items.MAGMA_CREAM, Items.BLAZE_POWDER, ItemRegistry.SNAIL_SLIME);
                shaped(redstone, Items.STICKY_PISTON)
                    .define('O', ItemRegistry.SNAIL_SLIME)
                    .define('#', Items.PISTON)
                    .pattern("O")
                    .pattern("#")
                    .unlockedBy("has_snail_slime", has(ItemRegistry.SNAIL_SLIME))
                    .save(output, newRecipeKey("sticky_piston_from_snail_slime"));

                // Snail Slime + Water Bottle = Weakness
                // Silk + Awkward potion = Weaving
            }
        };
    }

    @Override
    public String getName() {
        return "Recipes";
    }

    public ResourceKey<Recipe<?>> newRecipeKey(String id) {
        return ResourceKey.create(Registries.RECIPE, BugMod.asId(id));
    }
}
