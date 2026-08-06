package net.justmili.bugs.registries;

import net.justmili.bugs.BugMod;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.component.CustomData;

public class DataComponentRegistry {
    public static DataComponentType<CustomData> BUG_NET_ENTITY_DATA;

    public static void init() {
        BUG_NET_ENTITY_DATA = register("bug_net_entity_data");
    }

    private static DataComponentType<CustomData> register(String name) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, BugMod.asId(name), DataComponentType.<CustomData>builder()
            .persistent(CustomData.CODEC).networkSynchronized(ByteBufCodecs.COMPOUND_TAG.map(CustomData::of, CustomData::copyTag)).build());
    }
}
