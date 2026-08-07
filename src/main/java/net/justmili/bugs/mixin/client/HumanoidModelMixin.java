package net.justmili.bugs.mixin.client;

import net.justmili.bugs.registries.DataComponentRegistry;
import net.justmili.bugs.registries.ItemRegistry;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends HumanoidRenderState> extends EntityModel<T> implements ArmedModel<T>, HeadedModel {
    @Final @Shadow
    public ModelPart rightArm;
    @Final @Shadow
    public ModelPart leftArm;

    @Unique
    private static final float bugmod$armRot = -75 * Mth.DEG_TO_RAD;

    private HumanoidModelMixin(ModelPart root, Function<Identifier, RenderType> renderType) {
        super(root, renderType);
    }

    @Inject(method = "poseRightArm", at = @At("RETURN"))
    private void bugs$poseRightArm(T state, CallbackInfo ci) {
        if (bugmod$isFullNet(state.rightHandItemStack)) this.rightArm.xRot = bugmod$armRot;
    }

    @Inject(method = "poseLeftArm", at = @At("RETURN"))
    private void bugs$poseLeftArm(T state, CallbackInfo ci) {
        if (bugmod$isFullNet(state.leftHandItemStack)) this.leftArm.xRot = bugmod$armRot;
    }

    @Unique
    private boolean bugmod$isFullNet(ItemStack stack) {
        return stack.is(ItemRegistry.BUG_NET) && stack.has(DataComponentRegistry.BUG_NET_ENTITY_DATA);
    }
}