package com.starfish_studios.naturalist.registry;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.platform.CommonPlatformHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class NaturalistSoundEvents {
    public static final Supplier<SoundEvent> SNAKE_HISS = CommonPlatformHelper.registerSoundEvent("snake_hiss", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.snake.hiss")));
    public static final Supplier<SoundEvent> SNAKE_HURT = CommonPlatformHelper.registerSoundEvent("snake_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.snake.hurt")));
    public static final Supplier<SoundEvent> SNAKE_RATTLE = CommonPlatformHelper.registerSoundEvent("snake_rattle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.snake.rattle")));
    public static final Supplier<SoundEvent> SNAIL_CRUSH = CommonPlatformHelper.registerSoundEvent("snail_crush", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.snail.crush")));
    public static final Supplier<SoundEvent> SNAIL_FORWARD = CommonPlatformHelper.registerSoundEvent("snail_forward", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.snail.forward")));
    public static final Supplier<SoundEvent> SNAIL_BACK = CommonPlatformHelper.registerSoundEvent("snail_back", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.snail.back")));
    public static final Supplier<SoundEvent> BUCKET_FILL_SNAIL = CommonPlatformHelper.registerSoundEvent("bucket_fill_snail", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "item.bucket.fill_snail")));
    public static final Supplier<SoundEvent> BUCKET_EMPTY_SNAIL = CommonPlatformHelper.registerSoundEvent("bucket_empty_snail", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "item.bucket.empty_snail")));
    public static final Supplier<SoundEvent> BEAR_HURT = CommonPlatformHelper.registerSoundEvent("bear_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bear.hurt")));
    public static final Supplier<SoundEvent> BEAR_DEATH = CommonPlatformHelper.registerSoundEvent("bear_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bear.death")));
    public static final Supplier<SoundEvent> BEAR_AMBIENT = CommonPlatformHelper.registerSoundEvent("bear_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bear.ambient")));
    public static final Supplier<SoundEvent> BEAR_AMBIENT_BABY = CommonPlatformHelper.registerSoundEvent("bear_ambient_baby", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bear.ambient_baby")));
    public static final Supplier<SoundEvent> BEAR_HURT_BABY = CommonPlatformHelper.registerSoundEvent("bear_hurt_baby", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bear.hurt_baby")));
    public static final Supplier<SoundEvent> BEAR_SLEEP = CommonPlatformHelper.registerSoundEvent("bear_sleep", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bear.sleep")));
    public static final Supplier<SoundEvent> BEAR_SNIFF = CommonPlatformHelper.registerSoundEvent("bear_sniff", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bear.sniff")));
    public static final Supplier<SoundEvent> BEAR_SPIT = CommonPlatformHelper.registerSoundEvent("bear_spit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bear.spit")));
    public static final Supplier<SoundEvent> BEAR_EAT = CommonPlatformHelper.registerSoundEvent("bear_eat", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bear.eat")));
 public static final Supplier<SoundEvent> ELEPHANT_HURT = CommonPlatformHelper.registerSoundEvent("elephant_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.elephant.hurt")));
 public static final Supplier<SoundEvent> ELEPHANT_AMBIENT = CommonPlatformHelper.registerSoundEvent("elephant_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.elephant.ambient")));
 public static final Supplier<SoundEvent> DEER_AMBIENT = CommonPlatformHelper.registerSoundEvent("deer_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.deer.ambient")));
 public static final Supplier<SoundEvent> DEER_HURT = CommonPlatformHelper.registerSoundEvent("deer_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.deer.hurt")));
 public static final Supplier<SoundEvent> DEER_AMBIENT_BABY = CommonPlatformHelper.registerSoundEvent("deer_ambient_baby", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.deer.ambient_baby")));
 public static final Supplier<SoundEvent> DEER_HURT_BABY = CommonPlatformHelper.registerSoundEvent("deer_hurt_baby", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.deer.hurt_baby")));
    public static final Supplier<SoundEvent> BIRD_HURT = CommonPlatformHelper.registerSoundEvent("bird_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bird.hurt")));
    public static final Supplier<SoundEvent> BIRD_DEATH = CommonPlatformHelper.registerSoundEvent("bird_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bird.death")));
    public static final Supplier<SoundEvent> BIRD_EAT = CommonPlatformHelper.registerSoundEvent("bird_eat", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bird.eat")));
    public static final Supplier<SoundEvent> BIRD_FLY = CommonPlatformHelper.registerSoundEvent("bird_fly", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bird.fly")));
    public static final Supplier<SoundEvent> BIRD_AMBIENT_BLUEJAY = CommonPlatformHelper.registerSoundEvent("bird_ambient_bluejay", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bird.ambient_bluejay")));
    public static final Supplier<SoundEvent> BIRD_AMBIENT_CANARY = CommonPlatformHelper.registerSoundEvent("bird_ambient_canary", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bird.ambient_canary")));
    public static final Supplier<SoundEvent> BIRD_AMBIENT_ROBIN = CommonPlatformHelper.registerSoundEvent("bird_ambient_robin", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bird.ambient_robin")));
    public static final Supplier<SoundEvent> BIRD_AMBIENT_CARDINAL = CommonPlatformHelper.registerSoundEvent("bird_ambient_cardinal", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.bird.ambient_cardinal")));
    public static final Supplier<SoundEvent> FIREFLY_HURT = CommonPlatformHelper.registerSoundEvent("firefly_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.firefly.hurt")));
    public static final Supplier<SoundEvent> FIREFLY_DEATH = CommonPlatformHelper.registerSoundEvent("firefly_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.firefly.death")));
    public static final Supplier<SoundEvent> FIREFLY_HIDE = CommonPlatformHelper.registerSoundEvent("firefly_hide", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.firefly.hide")));
    public static final Supplier<SoundEvent> RHINO_SCRAPE = CommonPlatformHelper.registerSoundEvent("rhino_scrape", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.rhino.scrape")));
    public static final Supplier<SoundEvent> RHINO_AMBIENT = CommonPlatformHelper.registerSoundEvent("rhino_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.rhino.ambient")));
    public static final Supplier<SoundEvent> RHINO_AMBIENT_BABY = CommonPlatformHelper.registerSoundEvent("rhino_ambient_baby", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.rhino.ambient_baby")));
    public static final Supplier<SoundEvent> LION_HURT = CommonPlatformHelper.registerSoundEvent("lion_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.lion.hurt")));
    public static final Supplier<SoundEvent> LION_AMBIENT = CommonPlatformHelper.registerSoundEvent("lion_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.lion.ambient")));
    public static final Supplier<SoundEvent> LION_ROAR = CommonPlatformHelper.registerSoundEvent("lion_roar", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.lion.roar")));
    public static final Supplier<SoundEvent> ZEBRA_AMBIENT = CommonPlatformHelper.registerSoundEvent("zebra_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.zebra.ambient")));
    public static final Supplier<SoundEvent> ZEBRA_HURT = CommonPlatformHelper.registerSoundEvent("zebra_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.zebra.hurt")));
    public static final Supplier<SoundEvent> ZEBRA_DEATH = CommonPlatformHelper.registerSoundEvent("zebra_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.zebra.death")));
    public static final Supplier<SoundEvent> ZEBRA_EAT = CommonPlatformHelper.registerSoundEvent("zebra_eat", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.zebra.eat")));
    public static final Supplier<SoundEvent> ZEBRA_BREATHE = CommonPlatformHelper.registerSoundEvent("zebra_breathe", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.zebra.breathe")));
    public static final Supplier<SoundEvent> ZEBRA_ANGRY = CommonPlatformHelper.registerSoundEvent("zebra_angry", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.zebra.angry")));
    public static final Supplier<SoundEvent> ZEBRA_JUMP = CommonPlatformHelper.registerSoundEvent("zebra_jump", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.zebra.jump")));
    public static final Supplier<SoundEvent> VULTURE_AMBIENT = CommonPlatformHelper.registerSoundEvent("vulture_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.vulture.ambient")));
    public static final Supplier<SoundEvent> VULTURE_HURT = CommonPlatformHelper.registerSoundEvent("vulture_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.vulture.hurt")));
    public static final Supplier<SoundEvent> VULTURE_DEATH = CommonPlatformHelper.registerSoundEvent("vulture_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.vulture.death")));
    public static final Supplier<SoundEvent> GIRAFFE_AMBIENT = CommonPlatformHelper.registerSoundEvent("giraffe_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.giraffe.ambient")));
    public static final Supplier<SoundEvent> HIPPO_AMBIENT = CommonPlatformHelper.registerSoundEvent("hippo_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.hippo.ambient")));
    public static final Supplier<SoundEvent> HIPPO_HURT = CommonPlatformHelper.registerSoundEvent("hippo_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Naturalist.MOD_ID, "entity.hippo.hurt")));

    public static void init() {}
}
