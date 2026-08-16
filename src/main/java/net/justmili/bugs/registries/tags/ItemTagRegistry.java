package net.justmili.bugs.registries.tags;

import net.justmili.bugs.BugMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTagRegistry {
    public static final TagKey<Item> SILK_BLOCKS, SILK_CARPETS, BUG_NET_REPAIRABLES;

    static {
        SILK_BLOCKS = create("silk");
        SILK_CARPETS = create("silk_carpets");
        BUG_NET_REPAIRABLES = create("bug_net_repairables");
    }

    private static TagKey<Item> create(String id) {
        return TagKey.create(Registries.ITEM, BugMod.asId(id));
    }
}
