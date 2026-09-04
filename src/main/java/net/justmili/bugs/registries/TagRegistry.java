package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class TagRegistry {
    public static final TagKey<Block> SILK_BLOCKS, SILK_CARPETS;
    public static final TagKey<Item> SILK_BLOCK_ITEMS, SILK_CARPET_ITEMS, BUG_NET_REPAIRABLES;
    public static final TagKey<EntityType<?>> BUG_NET_CATCHABLE, BUGS, INSECTS, HOSTILE_INSECTS, BEETLES, MOLLUSKS;

    static {
        SILK_BLOCKS = block("silk");
        SILK_CARPETS = block("silk_carpets");

        SILK_BLOCK_ITEMS = item("silk");
        SILK_CARPET_ITEMS = item("silk_carpets");
        BUG_NET_REPAIRABLES = item("bug_net_repairables");

        BUG_NET_CATCHABLE = entityType("bug_net_catchable");
        BUGS = entityType("bugs"); // Millipede, Stick Bug, Leaf Bug
        INSECTS = entityType("insects"); // Bee, Ladybug, Moth, Butterfly, Firefly, Ant, Silverfish, Endermite, Spiders, Mantis
        HOSTILE_INSECTS = entityType("hostile_insects"); // Silverfish, Endermite, Spiders, Mantis
        BEETLES = entityType("beetles"); // Ladybug
        MOLLUSKS = entityType("mollusks"); // Snail
    }

    private static <T> TagKey<T> create(ResourceKey<? extends Registry<T>> registries, String path) {
        return TagKey.create(registries, BugMod.asId(path));
    }

    private static TagKey<Block> block(String path) {
        return create(Registries.BLOCK, path);
    }

    private static TagKey<Item> item(String path) {
        return create(Registries.ITEM, path);
    }

    private static TagKey<Biome> biome(String path) {
        return create(Registries.BIOME, path);
    }

    private static TagKey<EntityType<?>> entityType(String path) {
        return create(Registries.ENTITY_TYPE, path);
    }

    private static TagKey<Enchantment> enchant(String path) {
        return create(Registries.ENCHANTMENT, path);
    }
}
