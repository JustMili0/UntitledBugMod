package net.justmili.libs.v1.utils.common.datagen;

import net.justmili.libs.v1.utils.common.ResourceUtil;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DatagenDataUtil extends RecipeProvider {
    public String modId;
    public RecipeOutput writer;
    
    public DatagenDataUtil(HolderLookup.Provider provider, String modId, RecipeOutput writer) {
        this.modId = modId;
        this.writer = writer;
        super(provider, writer);
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> has(Item item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }
    public ResourceKey<Recipe<?>> newRecipeKey(String id) {
        return ResourceKey.create(Registries.RECIPE, ResourceUtil.parse(modId, id));
    }
    private HolderGetter<Item> itemRegistry() {
        return registries.lookupOrThrow(Registries.ITEM);
    }

    public void shapeless(RecipeCategory category, Item output, int outCount, Item... inputs) {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(itemRegistry(), category, output, outCount);
        for (Item input : inputs) builder.requires(input);
        String inputNames = Arrays.stream(inputs)
            .map(RecipeProvider::getItemName)
            .collect(Collectors.joining("_and_"));
        builder.unlockedBy(getHasName(inputs[0]), has(inputs[0]))
            .save(writer, newRecipeKey(getItemName(output)+"_from_"+inputNames));
    }

    public void shapeless(RecipeCategory category, Item output, Item... inputs) {
        shapeless(category, output, 1, inputs);
    }

    public void shaped2x2(RecipeCategory category, Item material, Item output, int outCount) {
        ShapedRecipeBuilder.shaped(itemRegistry(), category, output, outCount)
            .define('#', material)
            .pattern("##")
            .pattern("##")
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(output)));
    }

    public void shaped3x3(RecipeCategory category, Item material, Item output, int outCount) {
        ShapedRecipeBuilder.shaped(itemRegistry(), category, output, outCount)
            .define('#', material)
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(output)));
    }

    public void planks(Item log, Item planks) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.BUILDING_BLOCKS, planks, 4)
            .requires(log)
            .unlockedBy(getHasName(log), has(log))
            .save(writer, newRecipeKey(getItemName(planks)));
    }
    public void planksFromLogs(Item log, Item planks) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.BUILDING_BLOCKS, planks, 4)
            .requires(log)
            .unlockedBy(getHasName(log), has(log))
            .save(writer, newRecipeKey(getItemName(planks) + "_from_" + getItemName(log)));
    }
    public void planksFromWood(Item wood, Item planks) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.BUILDING_BLOCKS, planks, 4)
            .requires(wood)
            .unlockedBy(getHasName(wood), has(wood))
            .save(writer, newRecipeKey(getItemName(planks) + "_from_" + getItemName(wood)));
    }

    public void stairs(Item material, Item stairs) {
        stairBuilder(stairs, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(stairs)));
    }

    public void slab(Item material, Item slab) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(slab)));
    }

    public void fence(Item material, Item fence) {
        fenceBuilder(fence, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(fence)));
    }

    public void fenceGate(Item material, Item fenceGate) {
        fenceGateBuilder(fenceGate, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(fenceGate)));
    }

    public void door(Item material, Item door) {
        doorBuilder(door, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(door)));
    }

    public void trapdoor(Item material, Item trapdoor) {
        trapdoorBuilder(trapdoor, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(trapdoor)));
    }

    public void wall(Item material, Item wall) {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wall, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(wall)));
    }

    public void bars(Item material, Item bars) {
        ShapedRecipeBuilder.shaped(itemRegistry(), RecipeCategory.DECORATIONS, bars, 16)
            .define('#', material)
            .pattern("###")
            .pattern("###")
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(bars)));
    }

    public void pressurePlate(Item material, Item pressurePlate) {
        pressurePlateBuilder(RecipeCategory.REDSTONE, pressurePlate, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(pressurePlate)));
    }

    public void button(Item material, Item button) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.REDSTONE, button)
            .requires(material)
            .unlockedBy(getHasName(material), has(material))
            .save(writer, newRecipeKey(getItemName(button)));
    }

    public void smelt(Item input, Item output, CookingBookCategory bookCategory, float exp, int cookingTime) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.MISC, bookCategory, output, exp, cookingTime)
            .unlockedBy(getHasName(input), has(input))
            .save(writer, newRecipeKey(getItemName(output)+"_from_"+getItemName(input)+"_smelting"));
    }

    public void smelt(Item input, Item output, CookingBookCategory bookCategory, float exp) {
        smelt(input, output, bookCategory, exp, 200);
    }

    public void blast(Item input, Item output, CookingBookCategory bookCategory, float exp, int cookingTime) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), RecipeCategory.MISC, bookCategory, output, exp, cookingTime)
            .unlockedBy(getHasName(input), has(input))
            .save(writer, newRecipeKey(getItemName(output)+"_from_"+getItemName(input)+"_blasting"));
    }

    public void blast(Item input, Item output, CookingBookCategory bookCategory,  float exp) {
        blast(input, output, bookCategory, exp, 100);
    }

    public void smoke(Item input, Item output, float exp, int cookingTime) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.MISC, output, exp, cookingTime)
            .unlockedBy(getHasName(input), has(input))
            .save(writer, newRecipeKey(getItemName(output)+"_from_"+getItemName(input)+"_smoking"));
    }

    public void smoke(Item input, Item output, float exp) {
        smoke(input, output, exp, 100);
    }

    public void cut(Item input, Item output, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, output, resultCount)
            .unlockedBy(getHasName(input), has(input))
            .save(writer, newRecipeKey(getItemName(output)+"_from_"+getItemName(input)+"_stonecutting"));
    }

    public void cut(Item input, Item output) {
        cut(input, output, 1);
    }

    public void smithing(Item base, Item addition, Item template, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(base), Ingredient.of(addition), category, result)
            .unlocks(getHasName(addition), has(addition))
            .save(writer, newRecipeKey(getItemName(result)+"_from_"+getItemName(base)+"_smithing"));
    }

    @Override
    public void buildRecipes() {
        
    }
}