package net.justmili.libs.v1.utils.common;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

public class ResourceUtil {
    public static Identifier parse(String modId, String path) {
        return Identifier.fromNamespaceAndPath(modId, path);
    }
    public static Identifier asPath(String path) {
        return Identifier.parse(path);
    }
    public static Identifier asMinecraft(String path) {
        return parse("minecraft", path);
    }
    public static Identifier asFabric(String path) {
        return parse("fabric", path);
    }
    public static Identifier asQuilt(String path) {
        return parse("quilt", path);
    }
    public static Identifier asForge(String path) {
        return parse("forge", path);
    }
    public static Identifier asNeoForge(String path) {
        return parse("neoforge", path);
    }

    public static Identifier asBlockPath(String path) {
        return asPath("minecraft:block/"+path);
    }
    public static Identifier asItemPath(String path) {
        return asPath("minecraft:item/"+path);
    }

    // Generic
    public static Material mapTextureTop(Block block) {
        return TextureMapping.getBlockTexture(block, "_top");
    }
    public static Material mapTextureBottom(Block block) {
        return TextureMapping.getBlockTexture(block, "_bottom");
    }
    public static Material mapTextureFront(Block block) {
        return TextureMapping.getBlockTexture(block, "_front");
    }
    public static Material mapTextureLeft(Block block) {
        return TextureMapping.getBlockTexture(block, "_left");
    }
    public static Material mapTextureRight(Block block) {
        return TextureMapping.getBlockTexture(block, "_right");
    }
    public static Material mapTextureSide(Block block) {
        return TextureMapping.getBlockTexture(block, "_side");
    }
    // Chest texture suffixes
    public static Material mapTextureFrontRight(Block block) {
        return TextureMapping.getBlockTexture(block, "_front_right");
    }
    public static Material mapTextureFrontLeft(Block block) {
        return TextureMapping.getBlockTexture(block, "_front_left");
    }
    public static Material mapTextureBackRight(Block block) {
        return TextureMapping.getBlockTexture(block, "_back_right");
    }
    public static Material mapTextureBackLeft(Block block) {
        return TextureMapping.getBlockTexture(block, "_back_left");
    }

    public static Material mapTextureFrontOn(Block block) {
        return TextureMapping.getBlockTexture(block, "_front_on");
    }
    public static Material mapTextureOn(Block block) {
        return TextureMapping.getBlockTexture(block, "_on");
    }
    public static Material mapTextureMoist(Block block) {
        return TextureMapping.getBlockTexture(block, "_moist");
    }
}
