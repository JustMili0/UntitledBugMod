package net.justmili.bugs.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.BlockRegistry;
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
                var silkBlocks = ItemTagRegistry.SILK_BLOCKS;
                var silkCarpets = ItemTagRegistry.SILK_CARPETS;
                var building = RecipeCategory.BUILDING_BLOCKS;
                var decoration = RecipeCategory.DECORATIONS;

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

                gen.shaped2x2(building, ItemRegistry.SILK, ItemRegistry.WHITE_SILK, 1);

                shapeless(building, BlockRegistry.WHITE_SILK)
                    .requires(silkBlocks).requires(Items.WHITE_DYE)
                    .unlockedBy("has_white_dye", has(Items.WHITE_DYE))
                    .save(output, newRecipeKey("white_silk_from_dying"));
                shapeless(building, BlockRegistry.LIGHT_GRAY_SILK)
                    .requires(silkBlocks).requires(Items.LIGHT_GRAY_DYE)
                    .unlockedBy("has_light_gray_dye", has(Items.LIGHT_GRAY_DYE))
                    .save(output, newRecipeKey("light_gray_silk_from_dying"));
                shapeless(building, BlockRegistry.GRAY_SILK)
                    .requires(silkBlocks).requires(Items.GRAY_DYE)
                    .unlockedBy("has_gray_dye", has(Items.GRAY_DYE))
                    .save(output, newRecipeKey("gray_silk_from_dying"));
                shapeless(building, BlockRegistry.BLACK_SILK)
                    .requires(silkBlocks).requires(Items.BLACK_DYE)
                    .unlockedBy("has_black_dye", has(Items.BLACK_DYE))
                    .save(output, newRecipeKey("black_silk_from_dying"));
                shapeless(building, BlockRegistry.BROWN_SILK)
                    .requires(silkBlocks).requires(Items.BROWN_DYE)
                    .unlockedBy("has_brown_dye", has(Items.BROWN_DYE))
                    .save(output, newRecipeKey("brown_silk_from_dying"));
                shapeless(building, BlockRegistry.RED_SILK)
                    .requires(silkBlocks).requires(Items.RED_DYE)
                    .unlockedBy("has_red_dye", has(Items.RED_DYE))
                    .save(output, newRecipeKey("red_silk_from_dying"));
                shapeless(building, BlockRegistry.ORANGE_SILK)
                    .requires(silkBlocks).requires(Items.ORANGE_DYE)
                    .unlockedBy("has_orange_dye", has(Items.ORANGE_DYE))
                    .save(output, newRecipeKey("orange_silk_from_dying"));
                shapeless(building, BlockRegistry.YELLOW_SILK)
                    .requires(silkBlocks).requires(Items.YELLOW_DYE)
                    .unlockedBy("has_yellow_dye", has(Items.YELLOW_DYE))
                    .save(output, newRecipeKey("yellow_silk_from_dying"));
                shapeless(building, BlockRegistry.LIME_SILK)
                    .requires(silkBlocks).requires(Items.LIME_DYE)
                    .unlockedBy("has_lime_dye", has(Items.LIME_DYE))
                    .save(output, newRecipeKey("lime_silk_from_dying"));
                shapeless(building, BlockRegistry.GREEN_SILK)
                    .requires(silkBlocks).requires(Items.GREEN_DYE)
                    .unlockedBy("has_green_dye", has(Items.GREEN_DYE))
                    .save(output, newRecipeKey("green_silk_from_dying"));
                shapeless(building, BlockRegistry.CYAN_SILK)
                    .requires(silkBlocks).requires(Items.CYAN_DYE)
                    .unlockedBy("has_cyan_dye", has(Items.CYAN_DYE))
                    .save(output, newRecipeKey("cyan_silk_from_dying"));
                shapeless(building, BlockRegistry.LIGHT_BLUE_SILK)
                    .requires(silkBlocks).requires(Items.LIGHT_BLUE_DYE)
                    .unlockedBy("has_light_blue_dye", has(Items.LIGHT_BLUE_DYE))
                    .save(output, newRecipeKey("light_blue_silk_from_dying"));
                shapeless(building, BlockRegistry.BLUE_SILK)
                    .requires(silkBlocks).requires(Items.BLUE_DYE)
                    .unlockedBy("has_blue_dye", has(Items.BLUE_DYE))
                    .save(output, newRecipeKey("blue_silk_from_dying"));
                shapeless(building, BlockRegistry.PURPLE_SILK)
                    .requires(silkBlocks).requires(Items.PURPLE_DYE)
                    .unlockedBy("has_purple_dye", has(Items.PURPLE_DYE))
                    .save(output, newRecipeKey("purple_silk_from_dying"));
                shapeless(building, BlockRegistry.MAGENTA_SILK)
                    .requires(silkBlocks).requires(Items.MAGENTA_DYE)
                    .unlockedBy("has_magenta_dye", has(Items.MAGENTA_DYE))
                    .save(output, newRecipeKey("magenta_silk_from_dying"));
                shapeless(building, BlockRegistry.PINK_SILK)
                    .requires(silkBlocks).requires(Items.PINK_DYE)
                    .unlockedBy("has_pink_dye", has(Items.PINK_DYE))
                    .save(output, newRecipeKey("pink_silk_from_dying"));

                shaped(decoration, BlockRegistry.WHITE_SILK_CARPET, 3)
                    .define('#', BlockRegistry.WHITE_SILK)
                    .pattern("##")
                    .unlockedBy("has_white_silk", has(BlockRegistry.WHITE_SILK))
                    .save(output, newRecipeKey("white_silk_carpet"));

                shaped(decoration, BlockRegistry.LIGHT_GRAY_SILK_CARPET, 3)
                    .define('#', BlockRegistry.LIGHT_GRAY_SILK)
                    .pattern("##")
                    .unlockedBy("has_light_gray_silk", has(BlockRegistry.LIGHT_GRAY_SILK))
                    .save(output, newRecipeKey("light_gray_silk_carpet"));

                shaped(decoration, BlockRegistry.GRAY_SILK_CARPET, 3)
                    .define('#', BlockRegistry.GRAY_SILK)
                    .pattern("##")
                    .unlockedBy("has_gray_silk", has(BlockRegistry.GRAY_SILK))
                    .save(output, newRecipeKey("gray_silk_carpet"));

                shaped(decoration, BlockRegistry.BLACK_SILK_CARPET, 3)
                    .define('#', BlockRegistry.BLACK_SILK)
                    .pattern("##")
                    .unlockedBy("has_black_silk", has(BlockRegistry.BLACK_SILK))
                    .save(output, newRecipeKey("black_silk_carpet"));

                shaped(decoration, BlockRegistry.BROWN_SILK_CARPET, 3)
                    .define('#', BlockRegistry.BROWN_SILK)
                    .pattern("##")
                    .unlockedBy("has_brown_silk", has(BlockRegistry.BROWN_SILK))
                    .save(output, newRecipeKey("brown_silk_carpet"));

                shaped(decoration, BlockRegistry.RED_SILK_CARPET, 3)
                    .define('#', BlockRegistry.RED_SILK)
                    .pattern("##")
                    .unlockedBy("has_red_silk", has(BlockRegistry.RED_SILK))
                    .save(output, newRecipeKey("red_silk_carpet"));

                shaped(decoration, BlockRegistry.ORANGE_SILK_CARPET, 3)
                    .define('#', BlockRegistry.ORANGE_SILK)
                    .pattern("##")
                    .unlockedBy("has_orange_silk", has(BlockRegistry.ORANGE_SILK))
                    .save(output, newRecipeKey("orange_silk_carpet"));

                shaped(decoration, BlockRegistry.YELLOW_SILK_CARPET, 3)
                    .define('#', BlockRegistry.YELLOW_SILK)
                    .pattern("##")
                    .unlockedBy("has_yellow_silk", has(BlockRegistry.YELLOW_SILK))
                    .save(output, newRecipeKey("yellow_silk_carpet"));

                shaped(decoration, BlockRegistry.LIME_SILK_CARPET, 3)
                    .define('#', BlockRegistry.LIME_SILK)
                    .pattern("##")
                    .unlockedBy("has_lime_silk", has(BlockRegistry.LIME_SILK))
                    .save(output, newRecipeKey("lime_silk_carpet"));

                shaped(decoration, BlockRegistry.GREEN_SILK_CARPET, 3)
                    .define('#', BlockRegistry.GREEN_SILK)
                    .pattern("##")
                    .unlockedBy("has_green_silk", has(BlockRegistry.GREEN_SILK))
                    .save(output, newRecipeKey("green_silk_carpet"));

                shaped(decoration, BlockRegistry.CYAN_SILK_CARPET, 3)
                    .define('#', BlockRegistry.CYAN_SILK)
                    .pattern("##")
                    .unlockedBy("has_cyan_silk", has(BlockRegistry.CYAN_SILK))
                    .save(output, newRecipeKey("cyan_silk_carpet"));

                shaped(decoration, BlockRegistry.LIGHT_BLUE_SILK_CARPET, 3)
                    .define('#', BlockRegistry.LIGHT_BLUE_SILK)
                    .pattern("##")
                    .unlockedBy("has_light_blue_silk", has(BlockRegistry.LIGHT_BLUE_SILK))
                    .save(output, newRecipeKey("light_blue_silk_carpet"));

                shaped(decoration, BlockRegistry.BLUE_SILK_CARPET, 3)
                    .define('#', BlockRegistry.BLUE_SILK)
                    .pattern("##")
                    .unlockedBy("has_blue_silk", has(BlockRegistry.BLUE_SILK))
                    .save(output, newRecipeKey("blue_silk_carpet"));

                shaped(decoration, BlockRegistry.PURPLE_SILK_CARPET, 3)
                    .define('#', BlockRegistry.PURPLE_SILK)
                    .pattern("##")
                    .unlockedBy("has_purple_silk", has(BlockRegistry.PURPLE_SILK))
                    .save(output, newRecipeKey("purple_silk_carpet"));

                shaped(decoration, BlockRegistry.MAGENTA_SILK_CARPET, 3)
                    .define('#', BlockRegistry.MAGENTA_SILK)
                    .pattern("##")
                    .unlockedBy("has_magenta_silk", has(BlockRegistry.MAGENTA_SILK))
                    .save(output, newRecipeKey("magenta_silk_carpet"));

                shaped(decoration, BlockRegistry.PINK_SILK_CARPET, 3)
                    .define('#', BlockRegistry.PINK_SILK)
                    .pattern("##")
                    .unlockedBy("has_pink_silk", has(BlockRegistry.PINK_SILK))
                    .save(output, newRecipeKey("pink_silk_carpet"));

                shapeless(decoration, BlockRegistry.WHITE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.WHITE_DYE)
                    .unlockedBy("has_white_dye", has(Items.WHITE_DYE))
                    .save(output, newRecipeKey("white_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.LIGHT_GRAY_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.LIGHT_GRAY_DYE)
                    .unlockedBy("has_light_gray_dye", has(Items.LIGHT_GRAY_DYE))
                    .save(output, newRecipeKey("light_gray_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.GRAY_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.GRAY_DYE)
                    .unlockedBy("has_gray_dye", has(Items.GRAY_DYE))
                    .save(output, newRecipeKey("gray_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.BLACK_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.BLACK_DYE)
                    .unlockedBy("has_black_dye", has(Items.BLACK_DYE))
                    .save(output, newRecipeKey("black_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.BROWN_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.BROWN_DYE)
                    .unlockedBy("has_brown_dye", has(Items.BROWN_DYE))
                    .save(output, newRecipeKey("brown_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.RED_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.RED_DYE)
                    .unlockedBy("has_red_dye", has(Items.RED_DYE))
                    .save(output, newRecipeKey("red_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.ORANGE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.ORANGE_DYE)
                    .unlockedBy("has_orange_dye", has(Items.ORANGE_DYE))
                    .save(output, newRecipeKey("orange_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.YELLOW_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.YELLOW_DYE)
                    .unlockedBy("has_yellow_dye", has(Items.YELLOW_DYE))
                    .save(output, newRecipeKey("yellow_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.LIME_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.LIME_DYE)
                    .unlockedBy("has_lime_dye", has(Items.LIME_DYE))
                    .save(output, newRecipeKey("lime_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.GREEN_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.GREEN_DYE)
                    .unlockedBy("has_green_dye", has(Items.GREEN_DYE))
                    .save(output, newRecipeKey("green_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.CYAN_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.CYAN_DYE)
                    .unlockedBy("has_cyan_dye", has(Items.CYAN_DYE))
                    .save(output, newRecipeKey("cyan_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.LIGHT_BLUE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.LIGHT_BLUE_DYE)
                    .unlockedBy("has_light_blue_dye", has(Items.LIGHT_BLUE_DYE))
                    .save(output, newRecipeKey("light_blue_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.BLUE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.BLUE_DYE)
                    .unlockedBy("has_blue_dye", has(Items.BLUE_DYE))
                    .save(output, newRecipeKey("blue_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.PURPLE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.PURPLE_DYE)
                    .unlockedBy("has_purple_dye", has(Items.PURPLE_DYE))
                    .save(output, newRecipeKey("purple_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.MAGENTA_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.MAGENTA_DYE)
                    .unlockedBy("has_magenta_dye", has(Items.MAGENTA_DYE))
                    .save(output, newRecipeKey("magenta_silk_carpet_from_dying"));
                shapeless(decoration, BlockRegistry.PINK_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.PINK_DYE)
                    .unlockedBy("has_pink_dye", has(Items.PINK_DYE))
                    .save(output, newRecipeKey("pink_silk_carpet_from_dying"));

                gen.shaped2x2(RecipeCategory.REDSTONE, ItemRegistry.SNAIL_SLIME_BOTTLE, ItemRegistry.SNAIL_SLIME_BLOCK, 1);
                gen.shapeless(RecipeCategory.MISC, ItemRegistry.SNAIL_SLIME, ItemRegistry.SNAIL_SLIME_BOTTLE);
                gen.shapeless(RecipeCategory.MISC, ItemRegistry.SNAIL_SLIME_BOTTLE, ItemRegistry.SNAIL_SLIME, Items.GLASS_BOTTLE);

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
