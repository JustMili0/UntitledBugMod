package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class TabRegistry {
    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB = ResourceKey.create(
        Registries.CREATIVE_MODE_TAB, BugMod.asId("content"));

    public static void init() {
        Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB,
            CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0)
                .title(Component.translatable(CREATIVE_TAB.identifier().toLanguageKey("item_group")))
                .icon(() -> new ItemStack(ItemRegistry.SILK)) // TODO: Change later to moth spawn egg
                .displayItems((params, output) -> {
                    output.accept(ItemRegistry.COPPER_SHEARS);
                    output.accept(ItemRegistry.BUG_NET);
                    for (Block block : BlockRegistry.getSilkBlocks()) {
                        output.accept(block);
                    }
                    output.accept(ItemRegistry.SNAIL_SLIME);
                    output.accept(ItemRegistry.SNAIL_SLIME_BOTTLE);
                    output.accept(ItemRegistry.SNAIL_SLIME_BLOCK);
                })
                .build()
        );
    }
}
