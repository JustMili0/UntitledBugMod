package net.justmili.bugs.mixin;

import net.justmili.bugs.registries.BlockRegistry;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonStructureResolver.class)
public class PistonStructureResolverMixin {

    @Inject(method = "isSticky", at = @At("HEAD"), cancellable = true)
    private static void makeSnailSlimeSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(BlockRegistry.SNAIL_SLIME_BLOCK)) cir.setReturnValue(true);
    }
}
