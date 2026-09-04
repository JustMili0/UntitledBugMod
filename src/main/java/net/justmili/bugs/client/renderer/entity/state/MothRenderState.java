package net.justmili.bugs.client.renderer.entity.state;

import net.justmili.bugs.BugMod;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.AnimationState;

public class MothRenderState extends LivingEntityRenderState {
    private static final Identifier DEFAULT_TEXTURE = BugMod.asId("textures/entity/moth/silk.png");

    public enum Sitting {
        FLOOR, WALL, NONE
    }
    public Sitting sitType = Sitting.NONE;
    public final AnimationState flyAnimationState = new AnimationState();
    public final AnimationState floorSitAnimationState = new AnimationState();
    public final AnimationState wallSitAnimationState = new AnimationState();
    public Identifier texture = DEFAULT_TEXTURE;
}
