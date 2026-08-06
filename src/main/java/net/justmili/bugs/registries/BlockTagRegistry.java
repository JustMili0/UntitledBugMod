package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BlockTagRegistry {
    public static final TagKey<Block> SILK_BLOCKS, SILK_CARPETS;

    static {
        SILK_BLOCKS = create("silk");
        SILK_CARPETS = create("silk_carpets");
    }

    private static TagKey<Block> create(String id) {
        return TagKey.create(Registries.BLOCK, BugMod.asId(id));
    }
}
