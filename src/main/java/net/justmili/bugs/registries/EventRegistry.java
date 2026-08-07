package net.justmili.bugs.registries;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.justmili.bugs.content.mechanics.logic.BottleSnailSlime;

public class EventRegistry {
    public static void init() {
        UseBlockCallback.EVENT.register(BottleSnailSlime::onBlockClicked);
    }
}
