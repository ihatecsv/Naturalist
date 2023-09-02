package com.starfish_studios.naturalist.client.model;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.common.entity.Elephant;
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
public class ElephantModel extends AnimatedGeoModel<Elephant> {
    @Override
    public ResourceLocation getModelResource(Elephant elephant) {
        return new ResourceLocation(Naturalist.MOD_ID, "geo/elephant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Elephant elephant) {
        return new ResourceLocation(Naturalist.MOD_ID, /*elephant.isDirty() ? "textures/entity/elephant_dirt.png" :*/ "textures/entity/elephant.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Elephant elephant) {
        return new ResourceLocation(Naturalist.MOD_ID, "animations/elephant.animation.json");
    }

    @Override
    public void setLivingAnimations(Elephant elephant, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(elephant, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");
        IBone bigTusks = this.getAnimationProcessor().getBone("tusks");
        IBone smallTusks = this.getAnimationProcessor().getBone("baby_tusks");
        IBone babyTrunk = this.getAnimationProcessor().getBone("trunk4");
        IBone leftEar = this.getAnimationProcessor().getBone("left_ear");
        IBone rightEar = this.getAnimationProcessor().getBone("right_ear");
        IBone chests = this.getAnimationProcessor().getBone("chests");
        IBone saddle = this.getAnimationProcessor().getBone("saddle");

        if (elephant.isBaby()) {
            head.setScaleX(1.3F); head.setScaleY(1.3F); head.setScaleZ(1.3F);
            leftEar.setScaleX(1.2F); leftEar.setScaleY(1.2F); leftEar.setScaleZ(1.2F);
            rightEar.setScaleX(1.2F); rightEar.setScaleY(1.2F); rightEar.setScaleZ(1.2F);
            head.setScaleX(1.5F);
            head.setScaleY(1.5F);
            head.setScaleZ(1.5F);
        } else {
            head.setScaleX(1.0F);
            head.setScaleY(1.0F);
            head.setScaleZ(1.0F);
        }

        chests.setHidden(!elephant.hasChest() || elephant.isBaby());
        saddle.setHidden(!elephant.isSaddled() || elephant.isBaby());

        bigTusks.setHidden(elephant.isBaby());
        smallTusks.setHidden(elephant.isBaby());
        smallTusks.setHidden(!elephant.isBaby());

        babyTrunk.setHidden(elephant.isBaby());

//        head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD);
        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}
