package fr.fartunruss.additionalmod.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.fartunruss.additionalmod.HalloweenMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhostRenderer extends MobRenderer<GhostEntity, GhostModel2<GhostEntity>> {
    private static final ResourceLocation GHOST_TEXTURES = new ResourceLocation(HalloweenMod.MODID,"textures/entity/ghost.png");

    private final GhostModel2 ghostModel = new GhostModel2();
    public GhostRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GhostModel2<>(), 0.0F);
    }

    /**
     * Returns the location of an entity's texture.
     */

    public ResourceLocation getEntityTexture(GhostEntity entity) {
        return GHOST_TEXTURES;
    }

    protected void preRenderCallback(GhostEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 1.0F;
        matrixStackIn.scale(f, f, f);
    }
}