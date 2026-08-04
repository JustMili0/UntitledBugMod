package net.justmili.bugs.datagen.providers;

import net.justmili.bugs.registries.EntityTypeTagRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public class BugsEntityTypeTagProvider extends IntrinsicHolderTagsProvider<EntityType<?>> {
    public BugsEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, Registries.ENTITY_TYPE, future, entityType -> BuiltInRegistries.ENTITY_TYPE.getResourceKey(entityType).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        //this.tag(EntityTypeTagRegistry.BUGS).add();
        this.tag(EntityTypeTagRegistry.INSECTS)
            .add(EntityType.BEE, EntityType.SILVERFISH, EntityType.ENDERMITE, EntityType.SPIDER, EntityType.CAVE_SPIDER);
        this.tag(EntityTypeTagRegistry.HOSTILE_INSECTS)
            .add(EntityType.SILVERFISH, EntityType.ENDERMITE, EntityType.SPIDER, EntityType.CAVE_SPIDER);
        //this.tag(EntityTypeTagRegistry.BEETLES).add();
        //this.tag(EntityTypeTagRegistry.MOLLUSKS).add();

    }
}
