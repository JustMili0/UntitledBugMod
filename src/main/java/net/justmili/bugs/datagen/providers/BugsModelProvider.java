package net.justmili.bugs.datagen.providers;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.justmili.bugs.BugMod;
import net.justmili.bugs.content.block.HoneyClumpBlock;
import net.justmili.bugs.registries.BlockRegistry;
import net.justmili.bugs.registries.ItemRegistry;
import net.justmili.libs.v1.utils.common.datagen.DatagenAssetUtil;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.world.level.block.MultifaceBlock;

public class BugsModelProvider extends FabricModelProvider {
    public BugsModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockGen) {
        var gen = new DatagenAssetUtil(BugMod.MODID, blockGen);
        var noRotation = DatagenAssetUtil.RotationType.NONE;

        gen.createTripwire(BlockRegistry.SILK_TRIPWIRE);

        gen.createCubeAndCarpet(BlockRegistry.WHITE_SILK, BlockRegistry.WHITE_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.LIGHT_GRAY_SILK, BlockRegistry.LIGHT_GRAY_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.GRAY_SILK, BlockRegistry.GRAY_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.BLACK_SILK, BlockRegistry.BLACK_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.BROWN_SILK, BlockRegistry.BROWN_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.RED_SILK, BlockRegistry.RED_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.ORANGE_SILK, BlockRegistry.ORANGE_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.YELLOW_SILK, BlockRegistry.YELLOW_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.LIME_SILK, BlockRegistry.LIME_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.GREEN_SILK, BlockRegistry.GREEN_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.CYAN_SILK, BlockRegistry.CYAN_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.LIGHT_BLUE_SILK, BlockRegistry.LIGHT_BLUE_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.BLUE_SILK, BlockRegistry.BLUE_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.PURPLE_SILK, BlockRegistry.PURPLE_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.MAGENTA_SILK, BlockRegistry.MAGENTA_SILK_CARPET, noRotation);
        gen.createCubeAndCarpet(BlockRegistry.PINK_SILK, BlockRegistry.PINK_SILK_CARPET, noRotation);

        createHoneyClumpBlockStates(blockGen);

        gen.createNonTemplateConnectable(BlockRegistry.SNAIL_SLIME_TRAIL);
        gen.createNonTemplateModelBlock(BlockRegistry.SNAIL_SLIME_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemGen) {
        var gen = new DatagenAssetUtil(BugMod.MODID, itemGen);

        gen.createFlatItem(ItemRegistry.COPPER_SHEARS);
        itemGen.declareCustomModelItem(ItemRegistry.BUG_NET);
        gen.createFlatItem(ItemRegistry.SILK);
        gen.createFlatItem(ItemRegistry.HONEY_CLUMP);
        gen.createFlatItem(ItemRegistry.SNAIL_SLIME);
        gen.createFlatItem(ItemRegistry.SNAIL_SLIME_BOTTLE);
    }

    private void createHoneyClumpBlockStates(BlockModelGenerators blockGen) {
        var block = BlockRegistry.HONEY_CLUMP;
        var directionProperties = BlockModelGenerators.selectMultifaceProperties(block.defaultBlockState(), MultifaceBlock::getFaceProperty);
        var condition = BlockModelGenerators.condition();
        var generator = MultiPartGenerator.multiPart(block);
        directionProperties.forEach((property, mutator) -> condition.term(property, false));

        String[] clumpWords = {"one_clump", "two_clumps", "three_clumps"};

        for (int clumps = HoneyClumpBlock.MIN_CLUMPS; clumps <= HoneyClumpBlock.MAX_CLUMPS; clumps++) {
            String suffix = "_" + clumpWords[clumps - 1];
            var model = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, suffix));
            var clumpCondition = BlockModelGenerators.condition().term(HoneyClumpBlock.CLUMPS, clumps);

            directionProperties.forEach((property, mutator) -> {
                generator.with(BlockModelGenerators.and(BlockModelGenerators.condition(property, true), clumpCondition), model.with(mutator));
                generator.with(BlockModelGenerators.and(condition, clumpCondition), model.with(mutator));
            });
        }

        blockGen.blockStateOutput.accept(generator);
    }
}
