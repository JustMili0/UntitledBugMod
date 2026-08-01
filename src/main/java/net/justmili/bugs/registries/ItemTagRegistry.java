package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTagRegistry {
    public static final TagKey<Item> SILK_BLOCKS = create(BugMod.asId("silk"));
    public static final TagKey<Item> SILK_CARPETS = create(BugMod.asId("silk_carpets"));

    private static TagKey<Item> create(Identifier id) {
        return TagKey.create(Registries.ITEM, id);
    }
}
