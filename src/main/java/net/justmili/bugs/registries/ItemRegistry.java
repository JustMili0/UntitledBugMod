package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.justmili.bugs.content.item.BugNet;
import net.justmili.bugs.content.item.SnailSlimeBottle;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class ItemRegistry {
    public static void init() {}

    public static final Item
        COPPER_SHEARS, BUG_NET, SILK, WHITE_SILK, LIGHT_GRAY_SILK, GRAY_SILK, BLACK_SILK,
        BROWN_SILK, RED_SILK, ORANGE_SILK, YELLOW_SILK,
        LIME_SILK, GREEN_SILK, CYAN_SILK, LIGHT_BLUE_SILK, BLUE_SILK,
        PURPLE_SILK, MAGENTA_SILK, PINK_SILK,
        WHITE_SILK_CARPET, LIGHT_GRAY_SILK_CARPET, GRAY_SILK_CARPET, BLACK_SILK_CARPET,
        BROWN_SILK_CARPET, RED_SILK_CARPET, ORANGE_SILK_CARPET, YELLOW_SILK_CARPET,
        LIME_SILK_CARPET, GREEN_SILK_CARPET, CYAN_SILK_CARPET, LIGHT_BLUE_SILK_CARPET, BLUE_SILK_CARPET,
        PURPLE_SILK_CARPET, MAGENTA_SILK_CARPET, PINK_SILK_CARPET, SNAIL_SLIME, SNAIL_SLIME_BOTTLE, SNAIL_SLIME_BLOCK;

    static {
        COPPER_SHEARS = registerItem("copper_shears", p -> new ShearsItem(p.durability(126).component(DataComponents.TOOL, ShearsItem.createToolProperties())));
        BUG_NET = registerItem("bug_net", BugNet::new);
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

        WHITE_SILK_CARPET = registerBlockItem("white_silk_carpet", BlockRegistry.WHITE_SILK_CARPET);
        LIGHT_GRAY_SILK_CARPET = registerBlockItem("light_gray_silk_carpet", BlockRegistry.LIGHT_GRAY_SILK_CARPET);
        GRAY_SILK_CARPET = registerBlockItem("gray_silk_carpet", BlockRegistry.GRAY_SILK_CARPET);
        BLACK_SILK_CARPET = registerBlockItem("black_silk_carpet", BlockRegistry.BLACK_SILK_CARPET);
        BROWN_SILK_CARPET = registerBlockItem("brown_silk_carpet", BlockRegistry.BROWN_SILK_CARPET);
        RED_SILK_CARPET = registerBlockItem("red_silk_carpet", BlockRegistry.RED_SILK_CARPET);
        ORANGE_SILK_CARPET = registerBlockItem("orange_silk_carpet", BlockRegistry.ORANGE_SILK_CARPET);
        YELLOW_SILK_CARPET = registerBlockItem("yellow_silk_carpet", BlockRegistry.YELLOW_SILK_CARPET);
        LIME_SILK_CARPET = registerBlockItem("lime_silk_carpet", BlockRegistry.LIME_SILK_CARPET);
        GREEN_SILK_CARPET = registerBlockItem("green_silk_carpet", BlockRegistry.GREEN_SILK_CARPET);
        CYAN_SILK_CARPET = registerBlockItem("cyan_silk_carpet", BlockRegistry.CYAN_SILK_CARPET);
        LIGHT_BLUE_SILK_CARPET = registerBlockItem("light_blue_silk_carpet", BlockRegistry.LIGHT_BLUE_SILK_CARPET);
        BLUE_SILK_CARPET = registerBlockItem("blue_silk_carpet", BlockRegistry.BLUE_SILK_CARPET);
        PURPLE_SILK_CARPET = registerBlockItem("purple_silk_carpet", BlockRegistry.PURPLE_SILK_CARPET);
        MAGENTA_SILK_CARPET = registerBlockItem("magenta_silk_carpet", BlockRegistry.MAGENTA_SILK_CARPET);
        PINK_SILK_CARPET = registerBlockItem("pink_silk_carpet", BlockRegistry.PINK_SILK_CARPET);

        SNAIL_SLIME = registerItem("snail_slime", Item::new);
        SNAIL_SLIME_BOTTLE = registerItem("snail_slime_bottle", SnailSlimeBottle::new);
        SNAIL_SLIME_BLOCK = registerBlockItem("snail_slime_block", BlockRegistry.SNAIL_SLIME_BLOCK);
    }

    private static Item registerBlockItem(String name, Block block) {
        var id = BugMod.asId(name);
        return Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block,
            new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, id))));
    }
    private static Item registerBlockItemWithCustomItemName(String name, Block block) {
        var id = BugMod.asId(name);
        return Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block,
            new Item.Properties().useItemDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, id))));
    }
    private static Item registerItem(String name, Function<Item.Properties, Item> item) {
        var id = BugMod.asId(name);
        return Registry.register(BuiltInRegistries.ITEM, id,
            item.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }
}
