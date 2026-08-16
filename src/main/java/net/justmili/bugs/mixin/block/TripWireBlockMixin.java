package net.justmili.bugs.mixin.block;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.bugs.registries.ItemRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.TripWireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TripWireBlock.class)
public abstract class TripWireBlockMixin {

    @Definition(id = "is", method = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z")
    @Definition(id = "SHEARS", field = "Lnet/minecraft/world/item/Items;SHEARS:Lnet/minecraft/world/item/Item;")
    @Expression("?.is(SHEARS)")
    @WrapOperation(method = "playerWillDestroy", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean disarmWithCopperShears(ItemStack stack, Object obj, Operation<Boolean> original) {
        return original.call(stack, obj) || stack.is(ItemRegistry.COPPER_SHEARS);
    }
}