package net.justmili.bugs.client.model.animal.moth;

import net.justmili.bugs.client.animation.definitions.MothAnimation;
import net.justmili.bugs.client.renderer.entity.state.MothRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class MothModel extends EntityModel<MothRenderState> {
	private final ModelPart moth;
	private final ModelPart left_wing;
	private final ModelPart right_wing;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart left_antenna;
	private final ModelPart right_antenna;
	private final ModelPart bone;
	private final ModelPart front_legs;
	private final ModelPart middle_legs;
	private final ModelPart back_legs;
	private final KeyframeAnimation flying;
	private final KeyframeAnimation sittingFloor;
	private final KeyframeAnimation sittingWall;

	public MothModel(ModelPart root) {
		super(root, RenderTypes::entityCutoutCull);

		this.moth = root.getChild("moth");
        this.left_wing = this.moth.getChild("left_wing");
		this.right_wing = this.moth.getChild("right_wing");
		this.torso = this.moth.getChild("torso");
		this.head = this.torso.getChild("head");
		this.left_antenna = this.head.getChild("left_antenna");
		this.right_antenna = this.head.getChild("right_antenna");
		this.bone = this.torso.getChild("bone");
		this.front_legs = this.torso.getChild("front_legs");
		this.middle_legs = this.torso.getChild("middle_legs");
		this.back_legs = this.torso.getChild("back_legs");

		this.flying = MothAnimation.flying.bake(root);
		this.sittingFloor = MothAnimation.sit_floor.bake(root);
		this.sittingWall = MothAnimation.sit_wall.bake(root);
	}

	public static LayerDefinition createBodyLayer() {
		var meshDef = new MeshDefinition();
		var partDef = meshDef.getRoot();

		var moth = partDef.addOrReplaceChild("moth", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		var left_wing = moth.addOrReplaceChild("left_wing",
			CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.9239F, 0.3827F, -4.0F, 18.0F, 0.0F, 12.0F,
				new CubeDeformation(0.0F)).mirror(false).texOffs(36, 0).mirror().addBox(-0.9239F, 0.3827F, 8.0F, 7.0F, 0.0F, 12.0F,
				new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, -9.0F, 1.0F));

		var right_wing = moth.addOrReplaceChild("right_wing",
			CubeListBuilder.create().texOffs(0, 0).addBox(-17.0761F, 0.3827F, -4.0F, 18.0F, 0.0F, 12.0F,
				new CubeDeformation(0.0F)).texOffs(36, 0).addBox(-6.0761F, 0.3827F, 8.0F, 7.0F, 0.0F, 12.0F,
				new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -9.0F, 1.0F));

		var torso = moth.addOrReplaceChild("torso",
			CubeListBuilder.create().texOffs(0, 12).addBox(-4.5F, -5.0F, -5.0F, 9.0F, 9.0F, 5.0F,
				new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		var head = torso.addOrReplaceChild("head",
			CubeListBuilder.create().texOffs(26, 26).addBox(-2.5F, -3.0F, -4.0F, 5.0F, 5.0F, 4.0F,
				new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -5.0F));

		var left_antenna = head.addOrReplaceChild("left_antenna",
			CubeListBuilder.create().texOffs(28, 12).addBox(-1.0F, -9.0F, 0.0F, 3.0F, 9.0F, 0.0F,
				new CubeDeformation(0.0F)),
			PartPose.offsetAndRotation(2.0F, -3.0F, -2.0F, 0.3054F, -0.3491F, 0.7418F));

		var right_antenna = head.addOrReplaceChild("right_antenna",
			CubeListBuilder.create().texOffs(28, 12).mirror().addBox(-2.0F, -9.0F, 0.0F, 3.0F, 9.0F, 0.0F,
				new CubeDeformation(0.0F)).mirror(false),
			PartPose.offsetAndRotation(-2.0F, -3.0F, -2.0F, 0.3054F, 0.3491F, -0.7418F));

		var bone = torso.addOrReplaceChild("bone",
			CubeListBuilder.create().texOffs(0, 26).addBox(-3.5F, -2.0F, 0.0F, 7.0F, 7.0F, 6.0F,
				new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		var front_legs = torso.addOrReplaceChild("front_legs",
			CubeListBuilder.create().texOffs(34, 12).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F,
				new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.0F, -2.0F));

		var middle_legs = torso.addOrReplaceChild("middle_legs",
			CubeListBuilder.create().texOffs(28, 21).addBox(-4.0F, 0.0F, 0.0F, 5.0F, 2.0F, 0.0F,
				new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.0F, 0.0F));

		var back_legs = torso.addOrReplaceChild("back_legs",
			CubeListBuilder.create().texOffs(28, 23).addBox(-4.0F, 0.0F, 0.0F, 5.0F, 2.0F, 0.0F,
				new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.0F, 2.0F));

		return LayerDefinition.create(meshDef, 64, 64);
	}

	@Override
	public void setupAnim(final MothRenderState state) {
		super.setupAnim(state);

		switch (state.sitType) {
			case FLOOR -> this.sittingFloor.apply(state.floorSitAnimationState, state.ageInTicks);
			case WALL -> this.sittingWall.apply(state.wallSitAnimationState, state.ageInTicks);
			case NONE -> {}
		}

		this.flying.apply(state.flyAnimationState, state.ageInTicks);
	}
}