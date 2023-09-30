package com.starfish_studios.naturalist.client.model;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.common.entity.LizardTail;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

@Environment(EnvType.CLIENT)
public class LizardTailModel extends GeoModel<LizardTail> {
    public static final ResourceLocation[] TEXTURE_LOCATIONS = new ResourceLocation[]{
            new ResourceLocation(Naturalist.MOD_ID, "textures/entity/lizard/green_tail.png"),
            new ResourceLocation(Naturalist.MOD_ID, "textures/entity/lizard/brown_tail.png"),
            new ResourceLocation(Naturalist.MOD_ID, "textures/entity/lizard/pink_tail.png")
    };

    @Override
    public ResourceLocation getModelResource(LizardTail lizard) {
        return new ResourceLocation(Naturalist.MOD_ID, "geo/lizard_tail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LizardTail lizard) {
        return TEXTURE_LOCATIONS[lizard.getVariant()];
    }

    @Override
    public ResourceLocation getAnimationResource(LizardTail lizard) {
        return new ResourceLocation(Naturalist.MOD_ID, "animations/lizard_tail.animation.json");
    }
}
