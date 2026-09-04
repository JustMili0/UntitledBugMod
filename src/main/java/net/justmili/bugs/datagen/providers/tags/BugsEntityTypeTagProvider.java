package net.justmili.bugs.datagen.providers.tags;

import net.justmili.bugs.registries.TagRegistry;
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

        //this.tag(TagRegistry.BUGS).add();
        this.tag(TagRegistry.INSECTS)
            .add(EntityType.BEE, EntityType.SILVERFISH, EntityType.ENDERMITE, EntityType.SPIDER, EntityType.CAVE_SPIDER);
        this.tag(TagRegistry.HOSTILE_INSECTS)
            .add(EntityType.SILVERFISH, EntityType.ENDERMITE, EntityType.SPIDER, EntityType.CAVE_SPIDER);
        //this.tag(TagRegistry.BEETLES).add();
        //this.tag(TagRegistry.MOLLUSKS).add();

        this.tag(TagRegistry.BUG_NET_CATCHABLE) // TODO: add .addTag(EntityTypeTagRegistry.BUGS) after coding the bug type entities
            .addTag(TagRegistry.INSECTS).addTag(TagRegistry.HOSTILE_INSECTS);
    }
}
