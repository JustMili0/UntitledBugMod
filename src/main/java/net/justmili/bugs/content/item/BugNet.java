package net.justmili.bugs.content.item;

import net.justmili.bugs.BugMod;
import net.justmili.bugs.registries.EntityTypeTagRegistry;
import net.justmili.bugs.registries.ItemTagRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.function.Consumer;

import static net.justmili.bugs.registries.DataComponentRegistry.BUG_NET_ENTITY_DATA;

public class BugNet extends Item {
    public BugNet(Properties properties) {
        super(properties.durability(96).enchantable(14).repairable(ItemTagRegistry.BUG_NET_REPAIRABLES));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag flag) {
        if (stack.has(BUG_NET_ENTITY_DATA)) {
            var customName = getEntityCustomName(stack);
            if (customName.isPresent()) {
                tooltipLine(builder, "item.untitledbugmod.tooltip.net_full_with_name", customName.get(), getCaughtEntityName(stack));
            } else {
                tooltipLine(builder, "item.untitledbugmod.tooltip.net_full", getCaughtEntityName(stack));
            }
            tooltipLine(builder, "item.untitledbugmod.tooltip.tip_net_release");
        } else {
            tooltipLine(builder, "item.untitledbugmod.tooltip.tip_net_capture");
        }
    }
    private void tooltipLine(Consumer<Component> builder, String key, Object... args) {
        builder.accept(Component.translatable(key, args).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        stack = player.getItemInHand(hand); // Mojang, why
        if (stack.has(BUG_NET_ENTITY_DATA)) return InteractionResult.FAIL;

        if (!target.is(EntityTypeTagRegistry.BUG_NET_CATCHABLE)) return InteractionResult.PASS;
        if (target.is(EntityTypeTagRegistry.HOSTILE_INSECTS) && !target.hasEffect(MobEffects.WEAKNESS)) return InteractionResult.FAIL;

        capture(stack, target, player);
        player.swing(hand);
        return InteractionResult.SUCCESS;
    }
    private void capture(ItemStack stack, LivingEntity entity, Player player) {
        CompoundTag tag;
        try (ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(entity.problemPath(), BugMod.LOGGER)) {
            TagValueOutput output = TagValueOutput.createWithContext(reporter, entity.registryAccess());
            entity.save(output);
            tag = output.buildResult();
        }

        stack.set(BUG_NET_ENTITY_DATA, CustomData.of(tag));

        var targetPos = entity.blockPosition();
        entity.discard();

        player.level().playSound(null, targetPos, SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);

        if (player.isCrouching() && stack.has(BUG_NET_ENTITY_DATA)) {
            release(stack, level, player);
            player.swing(hand);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
    private void release(ItemStack stack, Level level, Player player) {
        var data = stack.get(BUG_NET_ENTITY_DATA);
        if (data == null) return;

        var tag = data.copyTag();
        var registryAccess = level.registryAccess();

        Optional<Entity> spawned;
        try (ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(() -> "bug_net_release", BugMod.LOGGER)) {
            ValueInput input = TagValueInput.create(reporter, registryAccess, tag);
            spawned = EntityType.create(input, level, EntitySpawnReason.SPAWN_ITEM_USE);
        }

        spawned.ifPresent(entity -> {
            var eyePos = player.getEyePosition();
            var maxPos = eyePos.add(player.getLookAngle().scale(2.0));
            var hitResult = level.clip(new ClipContext(eyePos, maxPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

            Vec3 releasePos;
            if (hitResult.getType() != HitResult.Type.BLOCK) {
                releasePos = maxPos;
            } else {
                var blockFace = hitResult.getDirection();
                var faceOffset = new Vec3(blockFace.getUnitVec3i()).scale(entity.getBbWidth() / 2.0 + 0.05);
                releasePos = hitResult.getLocation().add(faceOffset);
            }

            entity.snapTo(releasePos, entity.getYRot(), entity.getXRot());
            level.addFreshEntity(entity);
            entity.level().playSound(null, entity.blockPosition(), SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 0.8f, 0.5f);
        });

        stack.remove(BUG_NET_ENTITY_DATA);
    }

    @Override
    public boolean canDestroyBlock(ItemStack stack, BlockState state, Level level, BlockPos pos, LivingEntity user) {
        if (!(user instanceof Player player) || stack.has(BUG_NET_ENTITY_DATA)) return false;
        return player.gameMode().isSurvival();
    }

    // Some helpers to get names and stuff of the caught entity
    public static Optional<Identifier> getEntityIdentifier(ItemStack stack) {
        var data = stack.get(BUG_NET_ENTITY_DATA);
        if (data == null) return Optional.empty();

        return data.copyTag().getString("id").filter(id -> !id.isEmpty())
            .flatMap(id -> Optional.ofNullable(Identifier.tryParse(id)));
    }
    public static String getEntityIdentifierString(ItemStack stack) {
        return getEntityIdentifier(stack).map(Identifier::toString).orElse("unknown");
    }
    public static Component getCaughtEntityName(ItemStack stack) {
        return getEntityIdentifier(stack).flatMap(BuiltInRegistries.ENTITY_TYPE::getOptional)
            .map(EntityType::getDescription).orElse(Component.literal("Unknown"));
    }
    public static Optional<Component> getEntityCustomName(ItemStack stack) {
        var data = stack.get(BUG_NET_ENTITY_DATA);
        if (data == null) return Optional.empty();

        var tag = data.copyTag();
        if (!tag.contains("CustomName")) return Optional.empty();

        return ComponentSerialization.CODEC.parse(NbtOps.INSTANCE, tag.get("CustomName")).result();
    }
}