package net.justmili.bugs.datagen.providers;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.BlockRegistry;
import net.justmili.libs.v1.utils.common.DatagenAssetUtil;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class BugsModelProvider extends FabricModelProvider {
    public BugsModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockGen) {
        var gen = new DatagenAssetUtil(BugMod.MODID, blockGen);
        var noRotation = DatagenAssetUtil.RotationType.NONE;

        gen.createCube(BlockRegistry.SILK_TRIPWIRE, DatagenAssetUtil.RotationType.HORIZONTAL_Y);
        gen.createCube(BlockRegistry.WHITE_SILK, noRotation);
        gen.createCube(BlockRegistry.LIGHT_GRAY_SILK, noRotation);
        gen.createCube(BlockRegistry.GRAY_SILK, noRotation);
        gen.createCube(BlockRegistry.BLACK_SILK, noRotation);
        gen.createCube(BlockRegistry.BROWN_SILK, noRotation);
        gen.createCube(BlockRegistry.RED_SILK, noRotation);
        gen.createCube(BlockRegistry.ORANGE_SILK, noRotation);
        gen.createCube(BlockRegistry.YELLOW_SILK, noRotation);
        gen.createCube(BlockRegistry.LIME_SILK, noRotation);
        gen.createCube(BlockRegistry.GREEN_SILK, noRotation);
        gen.createCube(BlockRegistry.CYAN_SILK, noRotation);
        gen.createCube(BlockRegistry.LIGHT_BLUE_SILK, noRotation);
        gen.createCube(BlockRegistry.BLUE_SILK, noRotation);
        gen.createCube(BlockRegistry.PURPLE_SILK, noRotation);
        gen.createCube(BlockRegistry.MAGENTA_SILK, noRotation);
        gen.createCube(BlockRegistry.PINK_SILK, noRotation);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemGen) {
        var gen = new DatagenAssetUtil(BugMod.MODID, itemGen);

        gen.createFlatBlockItem(BlockRegistry.SILK_TRIPWIRE);
    }
}
