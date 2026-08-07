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

                var silkBlocks = ItemTagRegistry.SILK_BLOCKS;
                var silkCarpets = ItemTagRegistry.SILK_CARPETS;

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

                gen.shaped2x2(building, ItemRegistry.SILK, ItemRegistry.WHITE_SILK, 1);

                shapeless(building, ItemRegistry.WHITE_SILK)
                    .requires(silkBlocks).requires(Items.WHITE_DYE)
                    .unlockedBy("has_white_dye", has(Items.WHITE_DYE))
                    .save(output, newRecipeKey("white_silk_from_dying"));
                shapeless(building, ItemRegistry.LIGHT_GRAY_SILK)
                    .requires(silkBlocks).requires(Items.LIGHT_GRAY_DYE)
                    .unlockedBy("has_light_gray_dye", has(Items.LIGHT_GRAY_DYE))
                    .save(output, newRecipeKey("light_gray_silk_from_dying"));
                shapeless(building, ItemRegistry.GRAY_SILK)
                    .requires(silkBlocks).requires(Items.GRAY_DYE)
                    .unlockedBy("has_gray_dye", has(Items.GRAY_DYE))
                    .save(output, newRecipeKey("gray_silk_from_dying"));
                shapeless(building, ItemRegistry.BLACK_SILK)
                    .requires(silkBlocks).requires(Items.BLACK_DYE)
                    .unlockedBy("has_black_dye", has(Items.BLACK_DYE))
                    .save(output, newRecipeKey("black_silk_from_dying"));
                shapeless(building, ItemRegistry.BROWN_SILK)
                    .requires(silkBlocks).requires(Items.BROWN_DYE)
                    .unlockedBy("has_brown_dye", has(Items.BROWN_DYE))
                    .save(output, newRecipeKey("brown_silk_from_dying"));
                shapeless(building, ItemRegistry.RED_SILK)
                    .requires(silkBlocks).requires(Items.RED_DYE)
                    .unlockedBy("has_red_dye", has(Items.RED_DYE))
                    .save(output, newRecipeKey("red_silk_from_dying"));
                shapeless(building, ItemRegistry.ORANGE_SILK)
                    .requires(silkBlocks).requires(Items.ORANGE_DYE)
                    .unlockedBy("has_orange_dye", has(Items.ORANGE_DYE))
                    .save(output, newRecipeKey("orange_silk_from_dying"));
                shapeless(building, ItemRegistry.YELLOW_SILK)
                    .requires(silkBlocks).requires(Items.YELLOW_DYE)
                    .unlockedBy("has_yellow_dye", has(Items.YELLOW_DYE))
                    .save(output, newRecipeKey("yellow_silk_from_dying"));
                shapeless(building, ItemRegistry.LIME_SILK)
                    .requires(silkBlocks).requires(Items.LIME_DYE)
                    .unlockedBy("has_lime_dye", has(Items.LIME_DYE))
                    .save(output, newRecipeKey("lime_silk_from_dying"));
                shapeless(building, ItemRegistry.GREEN_SILK)
                    .requires(silkBlocks).requires(Items.GREEN_DYE)
                    .unlockedBy("has_green_dye", has(Items.GREEN_DYE))
                    .save(output, newRecipeKey("green_silk_from_dying"));
                shapeless(building, ItemRegistry.CYAN_SILK)
                    .requires(silkBlocks).requires(Items.CYAN_DYE)
                    .unlockedBy("has_cyan_dye", has(Items.CYAN_DYE))
                    .save(output, newRecipeKey("cyan_silk_from_dying"));
                shapeless(building, ItemRegistry.LIGHT_BLUE_SILK)
                    .requires(silkBlocks).requires(Items.LIGHT_BLUE_DYE)
                    .unlockedBy("has_light_blue_dye", has(Items.LIGHT_BLUE_DYE))
                    .save(output, newRecipeKey("light_blue_silk_from_dying"));
                shapeless(building, ItemRegistry.BLUE_SILK)
                    .requires(silkBlocks).requires(Items.BLUE_DYE)
                    .unlockedBy("has_blue_dye", has(Items.BLUE_DYE))
                    .save(output, newRecipeKey("blue_silk_from_dying"));
                shapeless(building, ItemRegistry.PURPLE_SILK)
                    .requires(silkBlocks).requires(Items.PURPLE_DYE)
                    .unlockedBy("has_purple_dye", has(Items.PURPLE_DYE))
                    .save(output, newRecipeKey("purple_silk_from_dying"));
                shapeless(building, ItemRegistry.MAGENTA_SILK)
                    .requires(silkBlocks).requires(Items.MAGENTA_DYE)
                    .unlockedBy("has_magenta_dye", has(Items.MAGENTA_DYE))
                    .save(output, newRecipeKey("magenta_silk_from_dying"));
                shapeless(building, ItemRegistry.PINK_SILK)
                    .requires(silkBlocks).requires(Items.PINK_DYE)
                    .unlockedBy("has_pink_dye", has(Items.PINK_DYE))
                    .save(output, newRecipeKey("pink_silk_from_dying"));

                shaped(decoration, ItemRegistry.WHITE_SILK_CARPET, 3)
                    .define('#', ItemRegistry.WHITE_SILK)
                    .pattern("##")
                    .unlockedBy("has_white_silk", has(ItemRegistry.WHITE_SILK))
                    .save(output, newRecipeKey("white_silk_carpet"));

                shaped(decoration, ItemRegistry.LIGHT_GRAY_SILK_CARPET, 3)
                    .define('#', ItemRegistry.LIGHT_GRAY_SILK)
                    .pattern("##")
                    .unlockedBy("has_light_gray_silk", has(ItemRegistry.LIGHT_GRAY_SILK))
                    .save(output, newRecipeKey("light_gray_silk_carpet"));

                shaped(decoration, ItemRegistry.GRAY_SILK_CARPET, 3)
                    .define('#', ItemRegistry.GRAY_SILK)
                    .pattern("##")
                    .unlockedBy("has_gray_silk", has(ItemRegistry.GRAY_SILK))
                    .save(output, newRecipeKey("gray_silk_carpet"));

                shaped(decoration, ItemRegistry.BLACK_SILK_CARPET, 3)
                    .define('#', ItemRegistry.BLACK_SILK)
                    .pattern("##")
                    .unlockedBy("has_black_silk", has(ItemRegistry.BLACK_SILK))
                    .save(output, newRecipeKey("black_silk_carpet"));

                shaped(decoration, ItemRegistry.BROWN_SILK_CARPET, 3)
                    .define('#', ItemRegistry.BROWN_SILK)
                    .pattern("##")
                    .unlockedBy("has_brown_silk", has(ItemRegistry.BROWN_SILK))
                    .save(output, newRecipeKey("brown_silk_carpet"));

                shaped(decoration, ItemRegistry.RED_SILK_CARPET, 3)
                    .define('#', ItemRegistry.RED_SILK)
                    .pattern("##")
                    .unlockedBy("has_red_silk", has(ItemRegistry.RED_SILK))
                    .save(output, newRecipeKey("red_silk_carpet"));

                shaped(decoration, ItemRegistry.ORANGE_SILK_CARPET, 3)
                    .define('#', ItemRegistry.ORANGE_SILK)
                    .pattern("##")
                    .unlockedBy("has_orange_silk", has(ItemRegistry.ORANGE_SILK))
                    .save(output, newRecipeKey("orange_silk_carpet"));

                shaped(decoration, ItemRegistry.YELLOW_SILK_CARPET, 3)
                    .define('#', ItemRegistry.YELLOW_SILK)
                    .pattern("##")
                    .unlockedBy("has_yellow_silk", has(ItemRegistry.YELLOW_SILK))
                    .save(output, newRecipeKey("yellow_silk_carpet"));

                shaped(decoration, ItemRegistry.LIME_SILK_CARPET, 3)
                    .define('#', ItemRegistry.LIME_SILK)
                    .pattern("##")
                    .unlockedBy("has_lime_silk", has(ItemRegistry.LIME_SILK))
                    .save(output, newRecipeKey("lime_silk_carpet"));

                shaped(decoration, ItemRegistry.GREEN_SILK_CARPET, 3)
                    .define('#', ItemRegistry.GREEN_SILK)
                    .pattern("##")
                    .unlockedBy("has_green_silk", has(ItemRegistry.GREEN_SILK))
                    .save(output, newRecipeKey("green_silk_carpet"));

                shaped(decoration, ItemRegistry.CYAN_SILK_CARPET, 3)
                    .define('#', ItemRegistry.CYAN_SILK)
                    .pattern("##")
                    .unlockedBy("has_cyan_silk", has(ItemRegistry.CYAN_SILK))
                    .save(output, newRecipeKey("cyan_silk_carpet"));

                shaped(decoration, ItemRegistry.LIGHT_BLUE_SILK_CARPET, 3)
                    .define('#', ItemRegistry.LIGHT_BLUE_SILK)
                    .pattern("##")
                    .unlockedBy("has_light_blue_silk", has(ItemRegistry.LIGHT_BLUE_SILK))
                    .save(output, newRecipeKey("light_blue_silk_carpet"));

                shaped(decoration, ItemRegistry.BLUE_SILK_CARPET, 3)
                    .define('#', ItemRegistry.BLUE_SILK)
                    .pattern("##")
                    .unlockedBy("has_blue_silk", has(ItemRegistry.BLUE_SILK))
                    .save(output, newRecipeKey("blue_silk_carpet"));

                shaped(decoration, ItemRegistry.PURPLE_SILK_CARPET, 3)
                    .define('#', ItemRegistry.PURPLE_SILK)
                    .pattern("##")
                    .unlockedBy("has_purple_silk", has(ItemRegistry.PURPLE_SILK))
                    .save(output, newRecipeKey("purple_silk_carpet"));

                shaped(decoration, ItemRegistry.MAGENTA_SILK_CARPET, 3)
                    .define('#', ItemRegistry.MAGENTA_SILK)
                    .pattern("##")
                    .unlockedBy("has_magenta_silk", has(ItemRegistry.MAGENTA_SILK))
                    .save(output, newRecipeKey("magenta_silk_carpet"));

                shaped(decoration, ItemRegistry.PINK_SILK_CARPET, 3)
                    .define('#', ItemRegistry.PINK_SILK)
                    .pattern("##")
                    .unlockedBy("has_pink_silk", has(ItemRegistry.PINK_SILK))
                    .save(output, newRecipeKey("pink_silk_carpet"));

                shapeless(decoration, ItemRegistry.WHITE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.WHITE_DYE)
                    .unlockedBy("has_white_dye", has(Items.WHITE_DYE))
                    .save(output, newRecipeKey("white_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.LIGHT_GRAY_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.LIGHT_GRAY_DYE)
                    .unlockedBy("has_light_gray_dye", has(Items.LIGHT_GRAY_DYE))
                    .save(output, newRecipeKey("light_gray_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.GRAY_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.GRAY_DYE)
                    .unlockedBy("has_gray_dye", has(Items.GRAY_DYE))
                    .save(output, newRecipeKey("gray_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.BLACK_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.BLACK_DYE)
                    .unlockedBy("has_black_dye", has(Items.BLACK_DYE))
                    .save(output, newRecipeKey("black_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.BROWN_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.BROWN_DYE)
                    .unlockedBy("has_brown_dye", has(Items.BROWN_DYE))
                    .save(output, newRecipeKey("brown_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.RED_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.RED_DYE)
                    .unlockedBy("has_red_dye", has(Items.RED_DYE))
                    .save(output, newRecipeKey("red_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.ORANGE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.ORANGE_DYE)
                    .unlockedBy("has_orange_dye", has(Items.ORANGE_DYE))
                    .save(output, newRecipeKey("orange_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.YELLOW_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.YELLOW_DYE)
                    .unlockedBy("has_yellow_dye", has(Items.YELLOW_DYE))
                    .save(output, newRecipeKey("yellow_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.LIME_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.LIME_DYE)
                    .unlockedBy("has_lime_dye", has(Items.LIME_DYE))
                    .save(output, newRecipeKey("lime_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.GREEN_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.GREEN_DYE)
                    .unlockedBy("has_green_dye", has(Items.GREEN_DYE))
                    .save(output, newRecipeKey("green_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.CYAN_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.CYAN_DYE)
                    .unlockedBy("has_cyan_dye", has(Items.CYAN_DYE))
                    .save(output, newRecipeKey("cyan_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.LIGHT_BLUE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.LIGHT_BLUE_DYE)
                    .unlockedBy("has_light_blue_dye", has(Items.LIGHT_BLUE_DYE))
                    .save(output, newRecipeKey("light_blue_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.BLUE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.BLUE_DYE)
                    .unlockedBy("has_blue_dye", has(Items.BLUE_DYE))
                    .save(output, newRecipeKey("blue_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.PURPLE_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.PURPLE_DYE)
                    .unlockedBy("has_purple_dye", has(Items.PURPLE_DYE))
                    .save(output, newRecipeKey("purple_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.MAGENTA_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.MAGENTA_DYE)
                    .unlockedBy("has_magenta_dye", has(Items.MAGENTA_DYE))
                    .save(output, newRecipeKey("magenta_silk_carpet_from_dying"));
                shapeless(decoration, ItemRegistry.PINK_SILK_CARPET)
                    .requires(silkCarpets).requires(Items.PINK_DYE)
                    .unlockedBy("has_pink_dye", has(Items.PINK_DYE))
                    .save(output, newRecipeKey("pink_silk_carpet_from_dying"));

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
