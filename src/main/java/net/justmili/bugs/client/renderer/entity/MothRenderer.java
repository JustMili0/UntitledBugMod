package net.justmili.bugs.client.renderer.entity;

import net.justmili.bugs.client.model.animal.moth.MothModel;
import net.justmili.bugs.client.renderer.entity.state.MothRenderState;
import net.justmili.bugs.content.entity.animal.moth.Moth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MothRenderer extends MobRenderer<Moth, MothRenderState, MothModel> {
    public MothRenderer(EntityRendererProvider.Context context, MothModel model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public Identifier getTextureLocation(MothRenderState state) {
        return null;
    }

    @Override
    public MothRenderState createRenderState() {
        return null;
    }
}
