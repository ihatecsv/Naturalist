package com.starfish_studios.naturalist.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.entity.Firefly;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@Environment(EnvType.CLIENT)
public class FireflyGlowLayer extends GeoRenderLayer<Firefly> {
    private static final ResourceLocation TOP_LAYER = new ResourceLocation(Naturalist.MOD_ID, "textures/entity/firefly/firefly_glow_top.png");
    private static final ResourceLocation BACK_LAYER = new ResourceLocation(Naturalist.MOD_ID, "textures/entity/firefly/firefly_glow_back.png");
    private static final ResourceLocation BOTTOM_LAYER = new ResourceLocation(Naturalist.MOD_ID, "textures/entity/firefly/firefly_glow_bottom.png");
    private static final ResourceLocation LEFT_LAYER = new ResourceLocation(Naturalist.MOD_ID, "textures/entity/firefly/firefly_glow_left.png");
    private static final ResourceLocation RIGHT_LAYER = new ResourceLocation(Naturalist.MOD_ID, "textures/entity/firefly/firefly_glow_right.png");
    // private static final ResourceLocation MODEL = new ResourceLocation(Naturalist.MOD_ID, "geo/firefly.geo.json");

    public FireflyGlowLayer(GeoRenderer<Firefly> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Firefly animatable, BakedGeoModel bakedModel, RenderType renderType,
                       MultiBufferSource bufferSource, VertexConsumer buffer, float partialTicks,
                       int packedLightIn, int packedOverlay) {

        if (animatable.isGlowing()) {
            renderLayer(poseStack, animatable, TOP_LAYER, bufferSource, partialTicks, packedLightIn);
            renderLayer(poseStack, animatable, BACK_LAYER, bufferSource, partialTicks, packedLightIn);
            renderLayer(poseStack, animatable, BOTTOM_LAYER, bufferSource, partialTicks, packedLightIn);
            renderLayer(poseStack, animatable, LEFT_LAYER, bufferSource, partialTicks, packedLightIn);
            renderLayer(poseStack, animatable, RIGHT_LAYER, bufferSource, partialTicks, packedLightIn);
        }
    }

    private void renderLayer(PoseStack poseStack, Firefly animatable, ResourceLocation layer, MultiBufferSource bufferSource, float partialTicks, int packedLightIn) {
        RenderType renderLayer = animatable.isGlowing() ? RenderType.eyes(layer) : RenderType.entityCutoutNoCull(layer);
        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, renderLayer, bufferSource.getBuffer(renderLayer), partialTicks, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}