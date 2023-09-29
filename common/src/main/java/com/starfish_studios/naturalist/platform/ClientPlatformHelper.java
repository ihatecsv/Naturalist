package com.starfish_studios.naturalist.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

import java.util.function.Supplier;

public class ClientPlatformHelper {
    @ExpectPlatform
    public static void setRenderLayer(Supplier<Block> block, RenderType type) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity> void registerEntityRenderers(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends GeoAnimatable> ResourceLocation arch$getTextureLocation(GeoModel<T> model, T entity) {
        throw new AssertionError();
    }
}
