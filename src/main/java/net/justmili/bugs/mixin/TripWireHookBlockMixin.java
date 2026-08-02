package net.justmili.bugs.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.justmili.bugs.registries.BlockRegistry;
import net.minecraft.world.level.block.TripWireHookBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TripWireHookBlock.class)
public abstract class TripWireHookBlockMixin {

    @Definition(id = "wireState", local = @Local(type = BlockState.class, name = "wireState"))
    @Definition(id = "testPosState", local = @Local(type = BlockState.class, name = "testPosState"))
    @Definition(id = "is", method = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z")
    @Definition(id = "TRIPWIRE", field = "Lnet/minecraft/world/level/block/Blocks;TRIPWIRE:Lnet/minecraft/world/level/block/Block;")
    @Expression({"wireState.is(TRIPWIRE)", "testPosState.is(TRIPWIRE)"})
    @WrapOperation(method = "calculateState", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean checkIsSilkTripwire(BlockState instance, Object obj, Operation<Boolean> original) {
        return original.call(instance, obj) || instance.is(BlockRegistry.SILK_TRIPWIRE);
    }
}