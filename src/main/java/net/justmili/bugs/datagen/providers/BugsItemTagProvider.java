package net.justmili.bugs.datagen.providers;

import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class BugsItemTagProvider extends IntrinsicHolderTagsProvider<Item> {
    public static final TagKey<Item> SILK_BLOCKS = TagKey.create(Registries.ITEM, BugMod.asResource("silk"));

    public BugsItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, Registries.ITEM, future, item -> BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(SILK_BLOCKS)
            .add(ItemRegistry.WHITE_SILK, ItemRegistry.LIGHT_GRAY_SILK, ItemRegistry.GRAY_SILK, ItemRegistry.BLACK_SILK,
                ItemRegistry.BROWN_SILK, ItemRegistry.RED_SILK, ItemRegistry.ORANGE_SILK, ItemRegistry.YELLOW_SILK,
                ItemRegistry.LIME_SILK, ItemRegistry.GREEN_SILK, ItemRegistry.CYAN_SILK, ItemRegistry.LIGHT_BLUE_SILK, ItemRegistry.BLUE_SILK,
                ItemRegistry.PURPLE_SILK, ItemRegistry.MAGENTA_SILK, ItemRegistry.PINK_SILK);
    }
}
