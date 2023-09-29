package com.starfish_studios.naturalist.client.model;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.entity.Butterfly;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

@Environment(EnvType.CLIENT)
public class ButterflyModel extends GeoModel<Butterfly> {
    @Override
    public ResourceLocation getModelResource(Butterfly butterfly) {
        return new ResourceLocation(Naturalist.MOD_ID, "geo/butterfly.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Butterfly butterfly) {
        return new ResourceLocation(Naturalist.MOD_ID, "textures/entity/butterfly.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Butterfly butterfly) {
        return new ResourceLocation(Naturalist.MOD_ID, "animations/butterfly.animation.json");
    }
}
