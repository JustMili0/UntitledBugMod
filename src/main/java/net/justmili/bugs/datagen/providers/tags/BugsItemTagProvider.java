package net.justmili.bugs.datagen.providers.tags;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.bugs.registries.tags.ItemTagRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class BugsItemTagProvider extends IntrinsicHolderTagsProvider<Item> {
    public BugsItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, Registries.ITEM, future, item -> BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ConventionalItemTags.SHEAR_TOOLS)
            .add(ItemRegistry.COPPER_SHEARS);

        this.tag(ItemTagRegistry.BUG_NET_REPAIRABLES)
            .add(Items.BAMBOO, ItemRegistry.SILK, Items.SCAFFOLDING); // Funny, scaffolding

        this.tag(ConventionalItemTags.STRINGS)
            .add(ItemRegistry.SILK);

        this.tag(ItemTagRegistry.SILK_BLOCKS)
            .add(ItemRegistry.WHITE_SILK, ItemRegistry.LIGHT_GRAY_SILK, ItemRegistry.GRAY_SILK, ItemRegistry.BLACK_SILK,
                ItemRegistry.BROWN_SILK, ItemRegistry.RED_SILK, ItemRegistry.ORANGE_SILK, ItemRegistry.YELLOW_SILK,
                ItemRegistry.LIME_SILK, ItemRegistry.GREEN_SILK, ItemRegistry.CYAN_SILK, ItemRegistry.LIGHT_BLUE_SILK, ItemRegistry.BLUE_SILK,
                ItemRegistry.PURPLE_SILK, ItemRegistry.MAGENTA_SILK, ItemRegistry.PINK_SILK);

        this.tag(ItemTagRegistry.SILK_CARPETS)
            .add(ItemRegistry.WHITE_SILK_CARPET, ItemRegistry.LIGHT_GRAY_SILK_CARPET, ItemRegistry.GRAY_SILK_CARPET, ItemRegistry.BLACK_SILK_CARPET,
                ItemRegistry.BROWN_SILK_CARPET, ItemRegistry.RED_SILK_CARPET, ItemRegistry.ORANGE_SILK_CARPET, ItemRegistry.YELLOW_SILK_CARPET,
                ItemRegistry.LIME_SILK_CARPET, ItemRegistry.GREEN_SILK_CARPET, ItemRegistry.CYAN_SILK_CARPET, ItemRegistry.LIGHT_BLUE_SILK_CARPET, ItemRegistry.BLUE_SILK_CARPET,
                ItemRegistry.PURPLE_SILK_CARPET, ItemRegistry.MAGENTA_SILK_CARPET, ItemRegistry.PINK_SILK_CARPET);
    }
}
