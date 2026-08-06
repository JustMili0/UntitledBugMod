package net.justmili.bugs.content.block;

import net.justmili.bugs.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;

public class SnailSlimeBlock extends HoneyBlock {

    public SnailSlimeBlock(Properties properties) {
        super(properties.friction(0.98f).sound(SoundType.SLIME_BLOCK).mapColor(MapColor.SAND));
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (isSlidingDown(pos, entity)) {
            doSlideMovement(entity);
            maybeDoSlideEffects(level, entity);
        }
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        entity.playSound(SoundEvents.SLIME_BLOCK_FALL, 1.0F, 1.0F);

        if (!level.isClientSide()) showJumpParticlesServerSide(level, entity);
        if (entity.causeFallDamage(fallDistance, 0.2F, level.damageSources().fall()))
            entity.playSound(this.soundType.getFallSound(), this.soundType.getVolume() * 0.5F, this.soundType.getPitch() * 0.75F);
    }

    // broadcastEntityEvent 54 but for Snail Slime Block
    private void showJumpParticlesServerSide(Level level, Entity entity) {
        if (level instanceof ServerLevel serverLevel) {
            var blockState = BlockRegistry.SNAIL_SLIME_BLOCK.defaultBlockState();

            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                entity.getX(), entity.getY(), entity.getZ(), 10, 0.0, 0.0, 0.0, 0.0);
        }
    }

    private static double getOldDeltaY(double deltaY) {
        return deltaY / 0.98 + 0.08;
    }

    private static double getNewDeltaY(double deltaY) {
        return (deltaY - 0.08) * 0.98;
    }

    private boolean isSlidingDown(BlockPos pos, Entity entity) {
        if (entity.onGround()) return false;
        if (entity.getY() > pos.getY() + 0.9375 - 1.0E-7) return false;
        if (getOldDeltaY(entity.getDeltaMovement().y) >= -0.08) return false;

        double dx = Math.abs(pos.getX() + 0.5 - entity.getX());
        double dz = Math.abs(pos.getZ() + 0.5 - entity.getZ());
        double overlapDistance = 0.4375 + (entity.getBbWidth() / 2.0);

        return dx + 1.0E-7 > overlapDistance || dz + 1.0E-7 > overlapDistance;
    }

    private void doSlideMovement(Entity entity) {
        var deltaMovement = entity.getDeltaMovement();

        if (getOldDeltaY(deltaMovement.y) < -0.13) {
            double horizontalReductionFactor = -0.05 / getOldDeltaY(deltaMovement.y);

            entity.setDeltaMovement(new Vec3(deltaMovement.x * horizontalReductionFactor,
                getNewDeltaY(-0.05), deltaMovement.z * horizontalReductionFactor));
        } else {
            entity.setDeltaMovement(new Vec3(deltaMovement.x, getNewDeltaY(-0.05), deltaMovement.z));
        }
        entity.resetFallDistance();
    }

    private void maybeDoSlideEffects(Level level, Entity entity) {
        if (!doesEntitySlideEffects(entity)) return;
        var random = level.getRandom();
        if (random.nextInt(5) == 0) entity.playSound(SoundEvents.SLIME_BLOCK_FALL, 1.0F, 1.0F);
        if (level.isClientSide() && random.nextInt(5) == 0) showSlideParticles(entity);
    }

    private static boolean doesEntitySlideEffects(Entity entity) {
        return entity instanceof LivingEntity || entity instanceof AbstractMinecart
            || entity instanceof PrimedTnt || entity instanceof AbstractBoat;
    }

    public static void showSlideParticles(Entity entity) {
        showParticles(entity, 5);
    }

    public static void showJumpParticles(Entity entity) {
        showParticles(entity, 10);
    }

    private static void showParticles(Entity entity, int count) {
        if (!entity.level().isClientSide()) return;
        var blockState = BlockRegistry.SNAIL_SLIME_BLOCK.defaultBlockState();

        for (int i = 0; i < count; ++i) {
            entity.level().addParticle(
                new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                entity.getX(), entity.getY(), entity.getZ(),
                0.0F, 0.0F, 0.0F
            );
        }
    }
}