package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class ItemRegistry {
    public static void init() {}

    public static final Item
        SILK, WHITE_SILK, LIGHT_GRAY_SILK, GRAY_SILK, BLACK_SILK,
        BROWN_SILK, RED_SILK, ORANGE_SILK, YELLOW_SILK,
        LIME_SILK, GREEN_SILK, CYAN_SILK, LIGHT_BLUE_SILK, BLUE_SILK,
        PURPLE_SILK, MAGENTA_SILK, PINK_SILK;

    static {
        SILK = registerBlockItemWithCustomItemName("silk", BlockRegistry.SILK_TRIPWIRE);
        WHITE_SILK = registerBlockItem("white_silk", BlockRegistry.WHITE_SILK);
        LIGHT_GRAY_SILK = registerBlockItem("light_gray_silk", BlockRegistry.LIGHT_GRAY_SILK);
        GRAY_SILK = registerBlockItem("gray_silk", BlockRegistry.GRAY_SILK);
        BLACK_SILK = registerBlockItem("black_silk", BlockRegistry.BLACK_SILK);
        BROWN_SILK = registerBlockItem("brown_silk", BlockRegistry.BROWN_SILK);
        RED_SILK = registerBlockItem("red_silk", BlockRegistry.RED_SILK);
        ORANGE_SILK = registerBlockItem("orange_silk", BlockRegistry.ORANGE_SILK);
        YELLOW_SILK = registerBlockItem("yellow_silk", BlockRegistry.YELLOW_SILK);
        LIME_SILK = registerBlockItem("lime_silk", BlockRegistry.LIME_SILK);
        GREEN_SILK = registerBlockItem("green_silk", BlockRegistry.GREEN_SILK);
        CYAN_SILK = registerBlockItem("cyan_silk", BlockRegistry.CYAN_SILK);
        LIGHT_BLUE_SILK = registerBlockItem("light_blue_silk", BlockRegistry.LIGHT_BLUE_SILK);
        BLUE_SILK = registerBlockItem("blue_silk", BlockRegistry.BLUE_SILK);
        PURPLE_SILK = registerBlockItem("purple_silk", BlockRegistry.PURPLE_SILK);
        MAGENTA_SILK = registerBlockItem("magenta_silk", BlockRegistry.MAGENTA_SILK);
        PINK_SILK = registerBlockItem("pink_silk", BlockRegistry.PINK_SILK);
    }

    private static Item registerBlockItem(String name, Block block) {
        var id = BugMod.asResource(name);
        return Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block,
            new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, id))));
    }
    private static Item registerBlockItemWithCustomItemName(String name, Block block) {
        var id = BugMod.asResource(name);
        return Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block,
            new Item.Properties().useItemDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, id))));
    }
    private static Item registerItem(String name, Function<Item.Properties, Item> item) {
        var id = BugMod.asResource(name);
        return Registry.register(BuiltInRegistries.ITEM, id,
            item.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }
}
