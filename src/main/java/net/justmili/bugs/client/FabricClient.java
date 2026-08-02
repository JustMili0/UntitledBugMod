package net.justmili.bugs.client;

import net.fabricmc.api.ClientModInitializer;
import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.libs.v1.utils.client.ItemRendererUtil;

public class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemRendererUtil.renderAsOther(
            ItemRegistry.BUG_NET, BugMod.asId("bug_net_flat"),
            ItemRendererUtil.ItemDisplay.GUI,
            ItemRendererUtil.ItemDisplay.FIXED,
            ItemRendererUtil.ItemDisplay.ON_SHELF,
            ItemRendererUtil.ItemDisplay.ON_GROUND
        );
    }
}
