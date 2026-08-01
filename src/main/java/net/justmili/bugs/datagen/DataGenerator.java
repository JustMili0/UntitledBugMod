package net.justmili.bugs.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.justmili.bugs.datagen.providers.*;

public class DataGenerator  implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGen) {
        var pack = dataGen.createPack();

        pack.addProvider(BugsModelProvider::new);
        pack.addProvider(BugsBlockLootProvider::new);
        pack.addProvider(BugsRecipeProvider::new);
        pack.addProvider(BugsBlockTagProvider::new);
        pack.addProvider(BugsItemTagProvider::new);
    }
}
