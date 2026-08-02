package net.justmili.libs.v1.utils.client;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemRendererUtil {
    public enum ItemDisplay { FIRST_PERSON, THIRD_PERSON, HEAD, GUI, FIXED, ON_SHELF, ON_GROUND }
    public record Entry(Item item, Identifier otherItemModel, List<ItemDisplay> displayAt) {}
    private static final Set<Entry> OVERRIDES = new HashSet<>();

    public static void renderAsOther(Item item, Identifier otherItemModel, ItemDisplay... displayAt) {
        OVERRIDES.add(new Entry(item, otherItemModel, List.of(displayAt)));
    }

    public static Entry getOverrideItem(Item item) {
        for (Entry entry : OVERRIDES) {
            if (entry.item() == item) return entry;
        }
        return null;
    }
}