package net.justmili.bugs.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.bugs.registries.ItemRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({Entity.class, Sheep.class, MushroomCow.class, CopperGolem.class, Bogged.class, LeashFenceKnotEntity.class})
public class ShearableEntitiesMixin {

    @Definition(id = "is", method = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z")
    @Definition(id = "SHEARS", field = "Lnet/minecraft/world/item/Items;SHEARS:Lnet/minecraft/world/item/Item;")
    @Expression("?.is(SHEARS)")
    @WrapOperation(method = {"mobInteract", "interact"}, at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean allowCopperShears(ItemStack stack, Object obj, Operation<Boolean> original) {
        return original.call(stack, obj) || stack.is(ItemRegistry.COPPER_SHEARS);
    }
}
