package net.justmili.bugs.client;

import net.fabricmc.api.ClientModInitializer;
import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.ClientEventRegistry;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.libs.v1.utils.client.ItemRendererUtil;

public class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientEventRegistry.register();
    }
}
