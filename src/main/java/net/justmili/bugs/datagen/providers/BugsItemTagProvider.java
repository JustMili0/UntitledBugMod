package net.justmili.bugs.datagen.providers;

import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.libs.v1.utils.common.ResourceUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class BugsItemTagProvider extends IntrinsicHolderTagsProvider<Item> {
    public static final TagKey<Item> SILK_BLOCKS = itemTag(BugMod.asResource("silk"));
    public static final TagKey<Item> SILK_CARPETS = itemTag(BugMod.asResource("silk_carpets"));
    public static final TagKey<Item> COMMON_STRING = itemTag(ResourceUtil.asCommon("strings"));
    public static final TagKey<Item> COMMON_SHEARS = itemTag(ResourceUtil.asCommon("tools/shears"));

    private static TagKey<Item> itemTag(Identifier id) {
        return TagKey.create(Registries.ITEM, id);
    }

    public BugsItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, Registries.ITEM, future, item -> BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(COMMON_SHEARS)
            .add(ItemRegistry.COPPER_SHEARS);

        this.tag(COMMON_STRING)
            .add(ItemRegistry.SILK);

        this.tag(SILK_BLOCKS)
            .add(ItemRegistry.WHITE_SILK, ItemRegistry.LIGHT_GRAY_SILK, ItemRegistry.GRAY_SILK, ItemRegistry.BLACK_SILK,
                ItemRegistry.BROWN_SILK, ItemRegistry.RED_SILK, ItemRegistry.ORANGE_SILK, ItemRegistry.YELLOW_SILK,
                ItemRegistry.LIME_SILK, ItemRegistry.GREEN_SILK, ItemRegistry.CYAN_SILK, ItemRegistry.LIGHT_BLUE_SILK, ItemRegistry.BLUE_SILK,
                ItemRegistry.PURPLE_SILK, ItemRegistry.MAGENTA_SILK, ItemRegistry.PINK_SILK);

        this.tag(SILK_CARPETS)
            .add(ItemRegistry.WHITE_SILK_CARPET, ItemRegistry.LIGHT_GRAY_SILK_CARPET, ItemRegistry.GRAY_SILK_CARPET, ItemRegistry.BLACK_SILK_CARPET,
                ItemRegistry.BROWN_SILK_CARPET, ItemRegistry.RED_SILK_CARPET, ItemRegistry.ORANGE_SILK_CARPET, ItemRegistry.YELLOW_SILK_CARPET,
                ItemRegistry.LIME_SILK_CARPET, ItemRegistry.GREEN_SILK_CARPET, ItemRegistry.CYAN_SILK_CARPET, ItemRegistry.LIGHT_BLUE_SILK_CARPET, ItemRegistry.BLUE_SILK_CARPET,
                ItemRegistry.PURPLE_SILK_CARPET, ItemRegistry.MAGENTA_SILK_CARPET, ItemRegistry.PINK_SILK_CARPET);
    }
}
