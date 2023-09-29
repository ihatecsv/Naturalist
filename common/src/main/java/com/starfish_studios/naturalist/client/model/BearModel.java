package com.starfish_studios.naturalist.client.model;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.entity.Bear;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Items;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

@Environment(EnvType.CLIENT)
public class BearModel extends GeoModel<Bear> {
    @Override
    public ResourceLocation getModelResource(Bear crocodile) {
        return new ResourceLocation(Naturalist.MOD_ID, "geo/bear.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Bear bear) {
        if (bear.isAngry()) {
            return new ResourceLocation(Naturalist.MOD_ID, "textures/entity/bear/bear_angry.png");
        } else if (bear.isSleeping()) {
            return new ResourceLocation(Naturalist.MOD_ID, "textures/entity/bear/bear_sleep.png");
        } else if (bear.isEating()) {
            if (bear.getMainHandItem().is(Items.SWEET_BERRIES)) {
                return new ResourceLocation(Naturalist.MOD_ID, "textures/entity/bear/bear_berries.png");
            } else if (bear.getMainHandItem().is(Items.HONEYCOMB)) {
                return new ResourceLocation(Naturalist.MOD_ID, "textures/entity/bear/bear_honey.png");
            }
        }
        return new ResourceLocation(Naturalist.MOD_ID, "textures/entity/bear/bear.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Bear bear) {
        return new ResourceLocation(Naturalist.MOD_ID, "animations/bear.animation.json");
    }

    @Override
    public void setCustomAnimations(Bear bear, long instanceId, AnimationState<Bear> animationState) {
        super.setCustomAnimations(bear, instanceId, animationState);

        if (animationState == null) return;

        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        CoreGeoBone head = this.getAnimationProcessor().getBone("head");

        if (bear.isBaby()) {
            head.setScaleX(1.8F);
            head.setScaleY(1.8F);
            head.setScaleZ(1.8F);
        }

        if (!bear.isSleeping() && !bear.isEating() && !bear.isSitting()) {
            head.setRotX(extraDataOfType.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
