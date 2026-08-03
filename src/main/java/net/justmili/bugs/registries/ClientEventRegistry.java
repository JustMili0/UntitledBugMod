package net.justmili.bugs.registries;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.justmili.bugs.content.mechanics.client.logic.UpdateBugNetModels;

public class ClientEventRegistry {
    public static void register() {
        ClientTickEvents.START_CLIENT_TICK.register(UpdateBugNetModels::onClientTick);
    }
}
