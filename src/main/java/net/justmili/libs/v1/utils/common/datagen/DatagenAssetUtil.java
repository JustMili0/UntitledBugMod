package net.justmili.libs.v1.utils.common.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;

public class DatagenAssetUtil {
    private BlockModelGenerators blockGen;
    private ItemModelGenerators itemGen;
    private final String modId;

    public DatagenAssetUtil(String modId, BlockModelGenerators blockGen) {
        this.modId = modId;
        this.blockGen = blockGen;
    }
    public DatagenAssetUtil(String modId, ItemModelGenerators itemGen) {
        this.modId = modId;
        this.itemGen = itemGen;
    }

    private void delegateItemModel(ItemLike item, Identifier model) {
        blockGen.itemModelOutput.accept(item.asItem(), ItemModelUtils.plainModel(model));
    }
    private MultiVariant plainVariant(Identifier block) {
        return BlockModelGenerators.plainVariant(block);
    }

    public void createWoodFamily(Block planks, Block stairs, Block slab, Block fence, Block fenceGate, Block door, Block trapdoor) {
        TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
        TextureMapping mapping = texturedModel.getMapping();

        // Planks
        Identifier fullBlock = texturedModel.create(planks, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(planks, plainVariant(fullBlock)));

        // Stairs
        Identifier stairsInner = ModelTemplates.STAIRS_INNER.create(stairs, mapping, blockGen.modelOutput);
        Identifier stairsStraight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, blockGen.modelOutput);
        Identifier stairsOuter = ModelTemplates.STAIRS_OUTER.create(stairs, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, plainVariant(stairsInner), plainVariant(stairsStraight), plainVariant(stairsOuter)));
        delegateItemModel(stairs, stairsStraight);

        // Slab
        Identifier slabBottom = ModelTemplates.SLAB_BOTTOM.create(slab, mapping, blockGen.modelOutput);
        Identifier slabTop = ModelTemplates.SLAB_TOP.create(slab, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, plainVariant(slabBottom), plainVariant(slabTop), plainVariant(fullBlock)));
        delegateItemModel(slab, slabBottom);

        // Fence
        Identifier fencePost = ModelTemplates.FENCE_POST.create(fence, mapping, blockGen.modelOutput);
        Identifier fenceSide = ModelTemplates.FENCE_SIDE.create(fence, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createFence(fence, plainVariant(fencePost), plainVariant(fenceSide)));
        delegateItemModel(fence, ModelTemplates.FENCE_INVENTORY.create(fence, mapping, blockGen.modelOutput));

        // Fence gate
        Identifier fgOpen = ModelTemplates.FENCE_GATE_OPEN.create(fenceGate, mapping, blockGen.modelOutput);
        Identifier fgClosed = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGate, mapping, blockGen.modelOutput);
        Identifier fgWallOpen = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGate, mapping, blockGen.modelOutput);
        Identifier fgWallClosed = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGate, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createFenceGate(fenceGate, plainVariant(fgOpen), plainVariant(fgClosed),
            plainVariant(fgWallOpen), plainVariant(fgWallClosed), true));

        // Door & Trapdoor
        blockGen.createDoor(door);
        blockGen.createOrientableTrapdoor(trapdoor);
    }

    public void createStoneFamily(Block stoneType, Block stairs, Block slab) {
        TexturedModel texturedModel = TexturedModel.CUBE.get(stoneType);
        TextureMapping mapping = texturedModel.getMapping();

        // Stone block
        Identifier fullBlock = texturedModel.create(stoneType, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(stoneType, plainVariant(fullBlock)));

        //Stairs
        Identifier stairsInner = ModelTemplates.STAIRS_INNER.create(stairs, mapping, blockGen.modelOutput);
        Identifier stairsStraight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, blockGen.modelOutput);
        Identifier stairsOuter = ModelTemplates.STAIRS_OUTER.create(stairs, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, plainVariant(stairsInner), plainVariant(stairsStraight), plainVariant(stairsOuter)));
        delegateItemModel(stairs, stairsStraight);

        // Slab
        Identifier slabBottom = ModelTemplates.SLAB_BOTTOM.create(slab, mapping, blockGen.modelOutput);
        Identifier slabTop = ModelTemplates.SLAB_TOP.create(slab, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, plainVariant(slabBottom), plainVariant(slabTop), plainVariant(fullBlock)));
        delegateItemModel(slab, slabBottom);
    }

    public void createStoneFamily(Block block, Block stairs, Block slab, Block wall) {
        // Call no-wall createStoneFamily
        createStoneFamily(block, stairs, slab);

        // Wall
        TextureMapping mapping = TexturedModel.CUBE.get(block).getMapping();
        Identifier wallPost = ModelTemplates.WALL_POST.create(wall, mapping, blockGen.modelOutput);
        Identifier wallLow = ModelTemplates.WALL_LOW_SIDE.create(wall, mapping, blockGen.modelOutput);
        Identifier wallTall = ModelTemplates.WALL_TALL_SIDE.create(wall, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createWall(wall, plainVariant(wallPost), plainVariant(wallLow), plainVariant(wallTall)));
        delegateItemModel(wall, ModelTemplates.WALL_INVENTORY.create(wall, mapping, blockGen.modelOutput));
    }

    public void createRedstoneFamily(Block block, Block pressurePlate, Block button) {
        TextureMapping mapping = TexturedModel.CUBE.get(block).getMapping();

        if (pressurePlate != null) {
            Identifier ppUp = ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlate, mapping, blockGen.modelOutput);
            Identifier ppDown = ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlate, mapping, blockGen.modelOutput);
            blockGen.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pressurePlate, plainVariant(ppUp), plainVariant(ppDown)));
        }

        if (button != null) {
            Identifier btn = ModelTemplates.BUTTON.create(button, mapping, blockGen.modelOutput);
            Identifier btnPressed = ModelTemplates.BUTTON_PRESSED.create(button, mapping, blockGen.modelOutput);
            blockGen.blockStateOutput.accept(BlockModelGenerators.createButton(button, plainVariant(btn), plainVariant(btnPressed)));
            delegateItemModel(button, ModelTemplates.BUTTON_INVENTORY.create(button, mapping, blockGen.modelOutput));
        }
    }

    public void createGlassFamily(Block glass, Block pane) {
        blockGen.createGlassBlocks(glass, pane);
    }

    /**
     * Individual
     * Cubes
     */
    public void createCube(Block block, RotationType rotationType) {
        createBlock(block, rotationType, ModelTemplates.CUBE_ALL, TextureMapping.cube(block));
    }

    public void createCarpet(Block carpetBlock, Block fullBlock) {
        Identifier model = ModelTemplates.CARPET.create(carpetBlock, TextureMapping.wool(fullBlock), blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(carpetBlock, plainVariant(model)));
    }
    public void createCubeAndCarpet(Block fullBlock, Block carpetBlock, RotationType rotationType) {
        createCube(fullBlock, rotationType);
        createCarpet(carpetBlock, fullBlock);
    }

    public void createBlock(Block block, RotationType rotationType,
                            ModelTemplate template, TextureMapping textureMapping) {
        Identifier model = template.create(block, textureMapping, blockGen.modelOutput);
        switch (rotationType) {
            case NONE -> blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, plainVariant(model)));
            case HORIZONTAL_Y -> blockGen.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, plainVariant(model))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
            case ALL_DIRECTIONS -> blockGen.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, plainVariant(model))
                .with(BlockModelGenerators.ROTATION_FACING));
            case LOG_XYZ ->
                blockGen.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(block, plainVariant(model)));
        }
    }

    public final void createNonTemplateModelBlock(Block block) {
        createNonTemplateModelBlock(block, block);
    }

    public final void createNonTemplateModelBlock(Block block, Block donor) {
        blockGen.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, plainVariant(ModelLocationUtils.getModelLocation(donor))));
    }

    public enum RotationType {
        NONE,
        HORIZONTAL_Y, // S/W/N/E y-axis
        ALL_DIRECTIONS, // D/U/N/S/W/E
        LOG_XYZ // Log XYZ axis
    }

    /**
     * Functional block templates
     */
    public void createTripwire(Block block) {
        var R90 = BlockModelGenerators.Y_ROT_90;
        var R180 = BlockModelGenerators.Y_ROT_180;
        var R270 = BlockModelGenerators.Y_ROT_270;

        blockGen.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(BlockStateProperties.ATTACHED, BlockStateProperties.EAST, BlockStateProperties.NORTH, BlockStateProperties.SOUTH, BlockStateProperties.WEST)
                .select(false, false, false, false, false, getPlainVariantModelLoc(block, "_ns"))
                .select(false, true, false, false, false, getPlainVariantModelLoc(block, "_n").with(R90))
                .select(false, false, true, false, false, getPlainVariantModelLoc(block, "_n"))
                .select(false, false, false, true, false, getPlainVariantModelLoc(block, "_n").with(R180))
                .select(false, false, false, false, true, getPlainVariantModelLoc(block, "_n").with(R270))
                .select(false, true, true, false, false, getPlainVariantModelLoc(block, "_ne"))
                .select(false, true, false, true, false, getPlainVariantModelLoc(block, "_ne").with(R90))
                .select(false, false, false, true, true, getPlainVariantModelLoc(block, "_ne").with(R180))
                .select(false, false, true, false, true, getPlainVariantModelLoc(block, "_ne").with(R270))
                .select(false, false, true, true, false, getPlainVariantModelLoc(block, "_ns"))
                .select(false, true, false, false, true, getPlainVariantModelLoc(block, "_ns").with(R90))
                .select(false, true, true, true, false, getPlainVariantModelLoc(block, "_nse"))
                .select(false, true, false, true, true, getPlainVariantModelLoc(block, "_nse").with(R90))
                .select(false, false, true, true, true, getPlainVariantModelLoc(block, "_nse").with(R180))
                .select(false, true, true, false, true, getPlainVariantModelLoc(block, "_nse").with(R270))
                .select(false, true, true, true, true, getPlainVariantModelLoc(block, "_nsew"))
                .select(true, false, false, false, false, getPlainVariantModelLoc(block, "_attached_ns"))
                .select(true, false, true, false, false, getPlainVariantModelLoc(block, "_attached_n"))
                .select(true, false, false, true, false, getPlainVariantModelLoc(block, "_attached_n").with(R180))
                .select(true, true, false, false, false, getPlainVariantModelLoc(block, "_attached_n").with(R90))
                .select(true, false, false, false, true, getPlainVariantModelLoc(block, "_attached_n").with(R270))
                .select(true, true, true, false, false, getPlainVariantModelLoc(block, "_attached_ne"))
                .select(true, true, false, true, false, getPlainVariantModelLoc(block, "_attached_ne").with(R90))
                .select(true, false, false, true, true, getPlainVariantModelLoc(block, "_attached_ne").with(R180))
                .select(true, false, true, false, true, getPlainVariantModelLoc(block, "_attached_ne").with(R270))
                .select(true, false, true, true, false, getPlainVariantModelLoc(block, "_attached_ns"))
                .select(true, true, false, false, true, getPlainVariantModelLoc(block, "_attached_ns").with(R90))
                .select(true, true, true, true, false, getPlainVariantModelLoc(block, "_attached_nse"))
                .select(true, true, false, true, true, getPlainVariantModelLoc(block, "_attached_nse").with(R90))
                .select(true, false, true, true, true, getPlainVariantModelLoc(block, "_attached_nse").with(R180))
                .select(true, true, true, false, true, getPlainVariantModelLoc(block, "_attached_nse").with(R270))
                .select(true, true, true, true, true, getPlainVariantModelLoc(block, "_attached_nsew"))
            ));
    }
    private MultiVariant getPlainVariantModelLoc(Block block, String suffix) {
        return plainVariant(ModelLocationUtils.getModelLocation(block, suffix));
    }

    public void createPlant(Block block, BlockModelGenerators.PlantType plantType) {
        blockGen.createCrossBlockWithDefaultItem(block, plantType);
    }

    public void createTallPlant(Block block, BlockModelGenerators.PlantType plantType) {
        blockGen.createDoublePlant(block, plantType);
    }

    public void createCrop(Block block,
                           Property<Integer> ageProperty, int... ageToVisualStageMapping) {
        blockGen.createCropBlock(block, ageProperty, ageToVisualStageMapping);
    }

    public void createFlatItem(Item item) {
        itemGen.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

    public void createFlatBlockItem(Block block) {
        itemGen.generateFlatItem(Item.BY_BLOCK.get(block), ModelTemplates.FLAT_ITEM);
    }
}
