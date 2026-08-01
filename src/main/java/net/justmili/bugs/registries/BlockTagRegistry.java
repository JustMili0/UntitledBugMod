package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BlockTagRegistry {
    public static final TagKey<Block> SILK_BLOCKS = create(BugMod.asId("silk"));
    public static final TagKey<Block> SILK_CARPETS = create(BugMod.asId("silk_carpets"));

    private static TagKey<Block> create(Identifier id) {
        return TagKey.create(Registries.BLOCK, id);
    }
}
