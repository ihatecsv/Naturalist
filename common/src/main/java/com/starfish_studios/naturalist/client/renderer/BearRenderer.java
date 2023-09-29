package com.starfish_studios.naturalist.client.renderer;

import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.starfish_studios.naturalist.client.model.BearModel;
import com.starfish_studios.naturalist.client.renderer.layers.BearShearedLayer;
import com.starfish_studios.naturalist.entity.Bear;
import com.starfish_studios.naturalist.platform.ClientPlatformHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.GeoBone;

@Environment(EnvType.CLIENT)
public class BearRenderer extends GeoEntityRenderer<Bear> {

    public BearRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BearModel());
        this.shadowRadius = 0.9F;
        addRenderLayer(new BearShearedLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Bear bear) {
        return ClientPlatformHelper.arch$getTextureLocation(this.model, bear);
    }

    @Override
    public void render(Bear bear, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(bear, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        if (animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }

    @Override
    public void renderRecursively(PoseStack stack, Bear bear, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight,
        int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("snout")) {
            stack.pushPose();
            stack.mulPose(new Quaternionf(-0.7071f, 0.0f, 0.0f, 0.7071f));
            stack.translate(0.0D, 1.3D, 0.8D);

//            stack.scale(1.0F,1.0F,1.0F);
            
            Minecraft.getInstance().getItemRenderer().renderStatic(bear.getItemBySlot(EquipmentSlot.MAINHAND), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, packedLight, packedOverlay, stack, bufferSource, bear.level(), 0);
            
            stack.popPose();
            buffer = bufferSource.getBuffer(RenderType.entityTranslucent(getTextureLocation(bear)));
        }
        super.renderRecursively(stack, bear, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
