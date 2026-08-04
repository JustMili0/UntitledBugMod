package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class EntityTypeTagRegistry {
    public static final TagKey<EntityType<?>> BUGS = create("bugs"); // Millipede, Stick Bug, Leaf Bug
    public static final TagKey<EntityType<?>> INSECTS = create("insects"); // Bee, Ladybug, Moth, Butterfly, Firefly, Ant, Silverfish, Endermite, Spiders, Mantis
    public static final TagKey<EntityType<?>> HOSTILE_INSECTS = create("hostile_insects"); // Silverfish, Endermite, Spiders, Mantis
    public static final TagKey<EntityType<?>> BEETLES = create("beetles"); // Ladybug
    public static final TagKey<EntityType<?>> MOLLUSKS = create("mollusks"); // Snail

    private static TagKey<EntityType<?>> create(String id) {
        return TagKey.create(Registries.ENTITY_TYPE, BugMod.asId(id));
    }
}
