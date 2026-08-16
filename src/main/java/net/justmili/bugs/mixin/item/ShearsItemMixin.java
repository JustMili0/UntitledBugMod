package net.justmili.bugs.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.bugs.registries.tags.BlockTagRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Tool.Rule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;

@Mixin(ShearsItem.class)
public abstract class ShearsItemMixin {

    @ModifyReturnValue(method = "createToolProperties", at = @At("RETURN"))
    private static Tool addSilkBlocks(Tool original) {
        var registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        var rules = new ArrayList<>(original.rules());

        rules.add(Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTagRegistry.SILK_BLOCKS), 10.0F));
        rules.add(Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTagRegistry.SILK_CARPETS), 10.0F));

        return new Tool(rules, original.defaultMiningSpeed(), original.damagePerBlock(), original.canDestroyBlocksInCreative());
    }
}