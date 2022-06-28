package com.starfish_studios.naturalist.client.model;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.entity.Rhino;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

@Environment(EnvType.CLIENT)
public class RhinoModel extends AnimatedGeoModel<Rhino> {
    @Override
    public ResourceLocation getModelResource(Rhino rhino) {
        return new ResourceLocation(Naturalist.MOD_ID, "geo/rhino.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Rhino rhino) {
        return new ResourceLocation(Naturalist.MOD_ID, "textures/entity/rhino.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Rhino rhino) {
        return new ResourceLocation(Naturalist.MOD_ID, "animations/rhino.animation.json");
    }

    @Override
    public void setLivingAnimations(Rhino rhino, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(rhino, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");

        if (rhino.isBaby()) {
            head.setScaleX(2.0F);
            head.setScaleY(2.0F);
            head.setScaleZ(2.0F);
        }

        if (!rhino.isSprinting()) {
            head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD);
            head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
        }
    }
}