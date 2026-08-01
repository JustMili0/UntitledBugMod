package net.justmili.libs.v1.utils.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Nullable;

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
        createCube(block, rotationType, ModelTemplates.CUBE_ALL, TextureMapping.cube(block));
    }

    public void createCube(Block block, RotationType rotationType,
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

    public enum RotationType {
        NONE,
        HORIZONTAL_Y, // S/W/N/E y-axis
        ALL_DIRECTIONS, // D/U/N/S/W/E
        LOG_XYZ // Log XYZ axis
    }

    /**
     * Functional block templates
     */
    public void createCraftingTable(Block table, Block bottomTexture) {
        TextureMapping mapping = new TextureMapping()
            .put(TextureSlot.PARTICLE, ResourceUtil.mapTextureFront(table))
            .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(bottomTexture))
            .put(TextureSlot.UP, ResourceUtil.mapTextureTop(table))
            .put(TextureSlot.NORTH, ResourceUtil.mapTextureFront(table))
            .put(TextureSlot.EAST, ResourceUtil.mapTextureSide(table))
            .put(TextureSlot.SOUTH, ResourceUtil.mapTextureFront(table))
            .put(TextureSlot.WEST, ResourceUtil.mapTextureSide(table));

        Identifier model = ModelTemplates.CUBE.create(table, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(table, plainVariant(model)));
        delegateItemModel(table, model);
    }

    public void createFurnace(Block block) {
        TextureMapping unlitMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFront(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block));
        TextureMapping litMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontOn(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block));

        Identifier litModel = ModelTemplates.CUBE_ORIENTABLE.create(ResourceUtil.mapTextureOn(block).sprite(), litMapping, blockGen.modelOutput);
        Identifier unlitModel = ModelTemplates.CUBE_ORIENTABLE.create(block, unlitMapping, blockGen.modelOutput);

        blockGen.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(block)
                .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, plainVariant(litModel), plainVariant(unlitModel)))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        delegateItemModel(block, unlitModel);
    }

//    public void createChest(Block block) {
//        TextureMapping singleMapping = new TextureMapping()
//            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
//            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block))
//            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFront(block));
//
//        TextureMapping leftMapping = new TextureMapping()
//            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
//            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block))
//            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontRight(block))
//            .put(TextureSlot.SOUTH, ResourceUtil.mapTextureBackRight(block));
//
//        TextureMapping rightMapping = new TextureMapping()
//            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
//            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block))
//            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontLeft(block))
//            .put(TextureSlot.SOUTH, ResourceUtil.mapTextureBackLeft(block));
//
//        ModelTemplate customBackOrientable = new ModelTemplate(Optional.of(ResourceUtil.asBlockPath("orientable")),
//            Optional.empty(), TextureSlot.TOP, TextureSlot.FRONT, TextureSlot.SIDE, TextureSlot.SOUTH);
//
//        Identifier singleModel = ModelTemplates.CUBE_ORIENTABLE.create(block, singleMapping, blockGen.modelOutput);
//        Identifier leftModel = customBackOrientable.create(ResourceUtil.mapTextureLeft(block).sprite(), leftMapping, blockGen.modelOutput);
//        Identifier rightModel = customBackOrientable.create(ResourceUtil.mapTextureRight(block).sprite(), rightMapping, blockGen.modelOutput);
//
//        blockGen.blockStateOutput.accept(
//            MultiVariantGenerator.dispatch(block)
//                .with(PropertyDispatch.property(BlockStateProperties.WATERLOGGED)
//                    .select(false, Variant.variant())
//                    .select(true, Variant.variant())
//                )
//                .with(PropertyDispatch.property(BlockStateProperties.CHEST_TYPE)
//                    .select(ChestType.SINGLE, Variant.variant().with(VariantProperties.MODEL, singleModel))
//                    .select(ChestType.LEFT, Variant.variant().with(VariantProperties.MODEL, leftModel))
//                    .select(ChestType.RIGHT, Variant.variant().with(VariantProperties.MODEL, rightModel))
//                )
//                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
//        );
//
//        delegateItemModel(block, singleModel);
//    }

    /**
     * Individual
     * Magic or whatever
     * Portals
     */
//    public void createNetherPortal(Block block) {
//        blockGen.blockStateOutput.accept(
//            MultiVariantGenerator.multiVariant(block)
//                .with(PropertyDispatch.property(BlockStateProperties.HORIZONTAL_AXIS)
//                    .select(Direction.Axis.X, Variant.variant()
//                        .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_ns")))
//                    .select(Direction.Axis.Z, Variant.variant()
//                        .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_ew")))
//                )
//        );
//    }

    /**
     * Individual
     * Nature
     */
//    public void createFarmland(Block topTexture, Block wrapTexture) {
//        TextureMapping dry = new TextureMapping()
//            .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(wrapTexture))
//            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(topTexture));
//        TextureMapping moist = new TextureMapping()
//            .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(wrapTexture))
//            .put(TextureSlot.TOP,ResourceUtil.mapTextureMoist(topTexture));
//        Identifier dryModel = ModelTemplates.FARMLAND.create(topTexture, dry, blockGen.modelOutput);
//        Identifier moistModel = ModelTemplates.FARMLAND.create(
//            ResourceUtil.mapTextureMoist(topTexture), moist, blockGen.modelOutput);
//        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(topTexture)
//            .with(BlockModelGenerators.createEmptyOrFullDispatch(
//                BlockStateProperties.MOISTURE, 7, moistModel, dryModel)));
//    }

    public void createCactus(Block block) {
        Material sideTexture = ResourceUtil.mapTextureSide(block);
        Material bottomTexture = ResourceUtil.mapTextureBottom(block);
        Material topTexture = ResourceUtil.mapTextureTop(block);

        Identifier modelLocation = ModelLocationUtils.getModelLocation(block);

        blockGen.modelOutput.accept(modelLocation, () -> {
            JsonObject root = new JsonObject();
            root.addProperty("parent", "block/block");

            JsonObject textures = new JsonObject();
            textures.addProperty("particle", sideTexture.toString());
            textures.addProperty("bottom", bottomTexture.toString());
            textures.addProperty("top", topTexture.toString());
            textures.addProperty("side", sideTexture.toString());
            root.add("textures", textures);

            JsonArray elements = new JsonArray();

            // Top and bottom faces
            JsonObject el1 = new JsonObject();
            el1.add("from", toJsonArray(0, 0, 0));
            el1.add("to", toJsonArray(16, 16, 16));
            JsonObject faces1 = new JsonObject();
            faces1.add("down", face("0 0 16 16", "#bottom", "down"));
            faces1.add("up", face("0 0 16 16", "#top", "up"));
            el1.add("faces", faces1);
            elements.add(el1);

            // North/south faces
            JsonObject el2 = new JsonObject();
            el2.add("from", toJsonArray(0, 0, 1));
            el2.add("to", toJsonArray(16, 16, 15));
            JsonObject faces2 = new JsonObject();
            faces2.add("north", face("0 0 16 16", "#side", null));
            faces2.add("south", face("0 0 16 16", "#side", null));
            el2.add("faces", faces2);
            elements.add(el2);

            // West/east faces
            JsonObject el3 = new JsonObject();
            el3.add("from", toJsonArray(1, 0, 0));
            el3.add("to", toJsonArray(15, 16, 16));
            JsonObject faces3 = new JsonObject();
            faces3.add("west", face("0 0 16 16", "#side", null));
            faces3.add("east", face("0 0 16 16", "#side", null));
            el3.add("faces", faces3);
            elements.add(el3);

            root.add("elements", elements);
            return root;
        });

        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, plainVariant(modelLocation)));
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

    /// Generation helpers
    private static JsonArray toJsonArray(int x, int y, int z) {
        JsonArray arr = new JsonArray();
        arr.add(x);
        arr.add(y);
        arr.add(z);
        return arr;
    }

    private JsonObject face(String uv, String texture, @Nullable String cullface) {
        JsonObject face = new JsonObject();
        String[] parts = uv.split(" ");
        JsonArray uvArr = new JsonArray();
        for (String p : parts) uvArr.add(Integer.parseInt(p));
        face.add("uv", uvArr);
        face.addProperty("texture", texture);
        if (cullface != null) face.addProperty("cullface", cullface);
        return face;
    }
}
