package net.justmili.bugs.registries.tags;

import net.justmili.bugs.BugMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class EntityTypeTagRegistry {
    public static final TagKey<EntityType<?>> BUG_NET_CATCHABLE, BUGS, INSECTS, HOSTILE_INSECTS, BEETLES, MOLLUSKS;

    static {
        BUG_NET_CATCHABLE = create("bug_net_catchable");
        BUGS = create("bugs"); // Millipede, Stick Bug, Leaf Bug
        INSECTS = create("insects"); // Bee, Ladybug, Moth, Butterfly, Firefly, Ant, Silverfish, Endermite, Spiders, Mantis
        HOSTILE_INSECTS = create("hostile_insects"); // Silverfish, Endermite, Spiders, Mantis
        BEETLES = create("beetles"); // Ladybug
        MOLLUSKS = create("mollusks"); // Snail
    }

    private static TagKey<EntityType<?>> create(String id) {
        return TagKey.create(Registries.ENTITY_TYPE, BugMod.asId(id));
    }
}
