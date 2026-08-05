package net.justmili.bugs;

import net.fabricmc.api.ModInitializer;
import net.justmili.bugs.registries.BlockRegistry;
import net.justmili.bugs.registries.DataComponentRegistry;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.bugs.registries.TabRegistry;
import net.justmili.libs.v1.utils.common.ResourceUtil;
import net.minecraft.resources.Identifier;

public class BugMod implements ModInitializer {
    public static final String MODID = "untitledbugmod";
    public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("UntitledBugMod");

    @Override
    public void onInitialize() {
        DataComponentRegistry.init();
        BlockRegistry.init();
        ItemRegistry.init();
        TabRegistry.init();
    }

    public static Identifier asId(String path) {
        return ResourceUtil.parse(MODID, path);
    }
}
