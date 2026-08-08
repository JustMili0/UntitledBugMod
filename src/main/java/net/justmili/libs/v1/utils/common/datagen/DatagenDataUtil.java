package net.justmili.libs.v1.utils.common.datagen;

import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.libs.v1.utils.common.ResourceUtil;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
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

    private ResourceKey<Recipe<?>> parseOutput(Item item) {
        return newRecipeKey(getItemName(item));
    }

    private ResourceKey<Recipe<?>> parseOutputFrom(Item input, Item output) {
        return newRecipeKey(getItemName(output) + "_from_" + getItemName(input));
    }

    private ResourceKey<Recipe<?>> parseOutputTyped(Item input, Item output, String processType) {
        return newRecipeKey(getItemName(output) + "_from_" + getItemName(input) + processType);
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
            .save(writer, newRecipeKey(getItemName(output) + "_from_" + inputNames));
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
            .save(writer, parseOutput(output));
    }

    public void shaped3x3(RecipeCategory category, Item material, Item output, int outCount) {
        ShapedRecipeBuilder.shaped(itemRegistry(), category, output, outCount)
            .define('#', material)
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(output));
    }

    public void planks(Item log, Item planks) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.BUILDING_BLOCKS, planks, 4)
            .requires(log)
            .unlockedBy(getHasName(log), has(log))
            .save(writer, parseOutput(planks));
    }
    public void planksFromLogs(Item log, Item planks) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.BUILDING_BLOCKS, planks, 4)
            .requires(log)
            .unlockedBy(getHasName(log), has(log))
            .save(writer, parseOutputFrom(log, planks));
    }
    public void planksFromWood(Item wood, Item planks) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.BUILDING_BLOCKS, planks, 4)
            .requires(wood)
            .unlockedBy(getHasName(wood), has(wood))
            .save(writer, parseOutputFrom(wood, planks));
    }

    public void stairs(Item material, Item stairs) {
        stairBuilder(stairs, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(stairs));
    }

    public void slab(Item material, Item slab) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(slab));
    }

    public void fence(Item material, Item fence) {
        fenceBuilder(fence, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(fence));
    }

    public void fenceGate(Item material, Item fenceGate) {
        fenceGateBuilder(fenceGate, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(fenceGate));
    }

    public void door(Item material, Item door) {
        doorBuilder(door, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(door));
    }

    public void trapdoor(Item material, Item trapdoor) {
        trapdoorBuilder(trapdoor, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(trapdoor));
    }

    public void wall(Item material, Item wall) {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wall, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(wall));
    }

    public void bars(Item material, Item bars) {
        ShapedRecipeBuilder.shaped(itemRegistry(), RecipeCategory.DECORATIONS, bars, 16)
            .define('#', material)
            .pattern("###")
            .pattern("###")
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(bars));
    }

    public void pressurePlate(Item material, Item pressurePlate) {
        pressurePlateBuilder(RecipeCategory.REDSTONE, pressurePlate, Ingredient.of(material))
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(pressurePlate));
    }

    public void button(Item material, Item button) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.REDSTONE, button)
            .requires(material)
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(button));
    }

    public void carpet(Item material, Item carpet) {
        shaped(RecipeCategory.DECORATIONS, carpet, 3)
            .define('#', material)
            .pattern("##")
            .unlockedBy(getHasName(material), has(material))
            .save(writer, parseOutput(carpet));
    }

    public void dyeItem(Item item, Item dye, Item dyed) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.MISC, dyed)
            .requires(item).requires(dye)
            .unlockedBy(getHasName(dye), has(dye))
            .save(writer, newRecipeKey(getItemName(dyed) + "_from_dying"));
    }

    public void dyeItem(TagKey<Item> items, Item dye, Item dyed) {
        ShapelessRecipeBuilder.shapeless(itemRegistry(), RecipeCategory.MISC, dyed)
            .requires(items).requires(dye)
            .unlockedBy(getHasName(dye), has(dye))
            .save(writer, newRecipeKey(getItemName(dyed) + "_from_dying"));
    }

    public void smelt(Item input, Item output, CookingBookCategory bookCategory, float exp, int cookingTime) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.MISC, bookCategory, output, exp, cookingTime)
            .unlockedBy(getHasName(input), has(input))
            .save(writer, parseOutputTyped(input, output, "_smelting"));
    }

    public void smelt(Item input, Item output, CookingBookCategory bookCategory, float exp) {
        smelt(input, output, bookCategory, exp, 200);
    }

    public void blast(Item input, Item output, CookingBookCategory bookCategory, float exp, int cookingTime) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), RecipeCategory.MISC, bookCategory, output, exp, cookingTime)
            .unlockedBy(getHasName(input), has(input))
            .save(writer, parseOutputTyped(input, output, "_blasting"));
    }

    public void blast(Item input, Item output, CookingBookCategory bookCategory, float exp) {
        blast(input, output, bookCategory, exp, 100);
    }

    public void smoke(Item input, Item output, float exp, int cookingTime) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.MISC, output, exp, cookingTime)
            .unlockedBy(getHasName(input), has(input))
            .save(writer, parseOutputTyped(input, output, "_smoking"));
    }

    public void smoke(Item input, Item output, float exp) {
        smoke(input, output, exp, 100);
    }

    public void cut(Item input, Item output, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, output, resultCount)
            .unlockedBy(getHasName(input), has(input))
            .save(writer, parseOutputTyped(input, output, "_stonecutting"));
    }

    public void cut(Item input, Item output) {
        cut(input, output, 1);
    }

    public void smithing(Item base, Item addition, Item template, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(base), Ingredient.of(addition), category, result)
            .unlocks(getHasName(addition), has(addition))
            .save(writer, parseOutputTyped(base, result, "_smithing"));
    }

    @Override
    public void buildRecipes() {

    }
}