package net.justmili.bugs.mixin;

import net.justmili.bugs.registries.BlockRegistry;
import net.justmili.bugs.registries.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TripWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TripWireBlock.class)
public abstract class TripWireBlockMixin {

    @Inject(method = "playerWillDestroy", at = @At("HEAD"), cancellable = true)
    private void disarmSilkTripwireWithCopperShears(Level level, BlockPos pos, BlockState state, Player player, CallbackInfoReturnable<BlockState> cir) {
        if (state.is(BlockRegistry.SILK_TRIPWIRE)) {
            if (!level.isClientSide() && !player.getMainHandItem().isEmpty() && player.getMainHandItem().is(ItemRegistry.COPPER_SHEARS)) {
                level.setBlock(pos, state.setValue(TripWireBlock.DISARMED, true), 260);
                level.gameEvent(player, GameEvent.SHEAR, pos);
            }
            cir.setReturnValue(state);
        }
    }
}