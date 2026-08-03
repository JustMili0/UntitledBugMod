package net.justmili.bugs.content.mechanics.client.logic;

import net.justmili.bugs.BugMod;
import net.justmili.bugs.content.item.BugNet;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.libs.v1.utils.client.ItemRendererUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class UpdateBugNetModels {
    public static void onClientTick(Minecraft client) {
        var player = client.player;
        if (player == null) return;

        var stack = player.getMainHandItem();
        var bugNet = new ItemStack(ItemRegistry.BUG_NET);
        if (!stack.is(bugNet.getItem())) return;

        var components = bugNet.getComponents();
        if (!components.has(BugNet.BUG_NET_ENTITY_DATA)) return;
        var data = components.get(BugNet.BUG_NET_ENTITY_DATA);

        // get what entity and set model accordingly
        ItemRendererUtil.renderAsOther(ItemRegistry.BUG_NET, BugMod.asId("bug_net_flat"), guiLike()); // Defaults
    }

    private static ItemRendererUtil.ItemDisplay[] guiLike() {
        return new ItemRendererUtil.ItemDisplay[] {
            ItemRendererUtil.ItemDisplay.GUI,
            ItemRendererUtil.ItemDisplay.FIXED,
            ItemRendererUtil.ItemDisplay.ON_SHELF,
            ItemRendererUtil.ItemDisplay.ON_GROUND
        };
    }
    private static ItemRendererUtil.ItemDisplay[] handheldLike() {
        return new ItemRendererUtil.ItemDisplay[] {
            ItemRendererUtil.ItemDisplay.FIRST_PERSON,
            ItemRendererUtil.ItemDisplay.THIRD_PERSON,
            ItemRendererUtil.ItemDisplay.HEAD
        };
    }
}
