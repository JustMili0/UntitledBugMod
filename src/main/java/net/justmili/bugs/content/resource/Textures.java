package net.justmili.bugs.content.resource;

import net.justmili.bugs.BugMod;
import net.minecraft.resources.Identifier;

public class Textures {
    public static final Identifier DEFAULT_2D = BugMod.asId("bug_net_flat");
    public static final Identifier BEE_3D = id3D("bee"), BEE_2D = id2D("bee");
    public static final Identifier SILVERFISH_3D = id3D("silverfish"), SILVERFISH_2D = id2D("silverfish");
    public static final Identifier ENDERMITE_3D = id3D("endermite"), ENDERMITE_2D = id2D("endermite");
    public static final Identifier SPIDER_3D = id3D("spider"), SPIDER_2D = id2D("spider");
    public static final Identifier CAVE_SPIDER_3D = id3D("cave_spider"), CAVE_SPIDER_2D = id2D("cave_spider");

    private static Identifier id3D(String entityId) {
        return BugMod.asId("bug_net_" + entityId);
    }
    private static Identifier id2D(String entityId) {
        return BugMod.asId("bug_net_" + entityId + "_flat");
    }
}
