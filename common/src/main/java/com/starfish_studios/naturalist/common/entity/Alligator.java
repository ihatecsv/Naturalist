package com.starfish_studios.naturalist.common.entity;

import com.starfish_studios.naturalist.common.entity.core.NaturalistAnimal;
import com.starfish_studios.naturalist.common.entity.core.EggLayingAnimal;
import com.starfish_studios.naturalist.common.entity.core.ai.goal.*;
import com.starfish_studios.naturalist.core.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Alligator extends NaturalistAnimal implements GeoEntity, EggLayingAnimal {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(NaturalistTags.ItemTags.ALLIGATOR_FOOD_ITEMS);
    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(Alligator.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LAYING_EGG = SynchedEntityData.defineId(Alligator.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> KILL_COOLDOWN = SynchedEntityData.defineId(Alligator.class, EntityDataSerializers.INT);

    int layEggCounter;
    boolean isDigging;

    public Alligator(EntityType<? extends NaturalistAnimal> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f);
        this.setMaxUpStep(1.0F);
    }

    public static boolean checkAlligatorSpawnRules(EntityType<? extends Alligator> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(BlockTags.FROGS_SPAWNABLE_ON) && level.getRawBrightness(pos, 0) > 8;
    }
    
    @javax.annotation.Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return this.isBaby() ? NaturalistSoundEvents.GATOR_AMBIENT_BABY.get() : NaturalistSoundEvents.GATOR_HURT.get();
    }

    @javax.annotation.Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return this.isBaby() ? NaturalistSoundEvents.GATOR_AMBIENT_BABY.get() : NaturalistSoundEvents.GATOR_DEATH.get();
    }

    @Override
    public float getVoicePitch() {
        return this.isBaby() ? super.getVoicePitch() * 0.65F : super.getVoicePitch();
    }

    @javax.annotation.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return this.isBaby() ? NaturalistSoundEvents.GATOR_AMBIENT_BABY.get() : NaturalistSoundEvents.GATOR_AMBIENT.get();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
        return NaturalistEntityTypes.ALLIGATOR.get().create(level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.2).add(Attributes.MAX_HEALTH, 30.0).add(Attributes.ATTACK_DAMAGE, 6.0).add(Attributes.KNOCKBACK_RESISTANCE, 0.60);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new EggLayingBreedGoal<>(this, 1.0));
        this.goalSelector.addGoal(1, new LayEggGoal<>(this, 1.0));
        this.goalSelector.addGoal(2, new CloseMeleeAttackGoal(this, 1.25D, true)
        {
            public boolean canUse() {
                return super.canUse() && !isBaby() && getKillCooldown() == 0;
            }

            public void stop() {
                super.stop();
                setKillCooldown(2400);
            }
        });
        this.goalSelector.addGoal(3, new BabyPanicGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.2D));
        this.goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new BabyHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (entity) -> !this.isBaby() && entity.isInWater()));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, (entity) -> {
            if(entity instanceof Alligator) return false;
            Iterable<BlockPos> list = BlockPos.betweenClosed(entity.blockPosition().offset(-2, -2, -2), entity.blockPosition().offset(2, 2, 2));
            boolean isEntityNearAlligatorEggs = false;
            for (BlockPos pos : list) {
                if (level().getBlockState(pos).is(NaturalistBlocks.ALLIGATOR_EGG.get())) {
                    isEntityNearAlligatorEggs = true;
                    break;
                }
            }
            return !this.isBaby() && isEntityNearAlligatorEggs;
        }));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, (entity) -> !this.isBaby() && entity.getType().is(NaturalistTags.EntityTypes.ALLIGATOR_HOSTILES)));
    }


    @Override
    public boolean isFood(@NotNull ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.98F;
    }

    @Override
    public int getMaxHeadYRot() {
        return 40;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean hasEgg() {
        return this.entityData.get(HAS_EGG);
    }

    @Override
    public void setHasEgg(boolean hasEgg) {
        this.entityData.set(HAS_EGG, hasEgg);
    }

    @Override
    public Block getEggBlock() {
        return NaturalistBlocks.ALLIGATOR_EGG.get();
    }

    @Override
    public TagKey<Block> getEggLayableBlockTag() {
        return NaturalistTags.BlockTags.ALLIGATOR_EGG_LAYABLE_ON;
    }

    @Override
    public boolean isLayingEgg() {
        return this.entityData.get(LAYING_EGG);
    }

    @Override
    public void setLayingEgg(boolean isLayingEgg) {
        this.layEggCounter = isLayingEgg ? 1 : 0;
        this.entityData.set(LAYING_EGG, isLayingEgg);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_EGG, false);
        this.entityData.define(LAYING_EGG, false);
        this.entityData.define(KILL_COOLDOWN, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("HasEgg", this.hasEgg());
        compound.putInt("KillCooldown", this.getKillCooldown());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setHasEgg(compound.getBoolean("HasEgg"));
        this.setKillCooldown(compound.getInt("KillCooldown"));
    }

    public void setKillCooldown(int ticks) {
        this.entityData.set(KILL_COOLDOWN, ticks);
    }

    public int getKillCooldown() {
        return this.entityData.get(KILL_COOLDOWN);
    }

    @Override
    public int getLayEggCounter() {
        return this.layEggCounter;
    }

    @Override
    public void setLayEggCounter(int layEggCounter) {
        this.layEggCounter = layEggCounter;
    }

    @Override
    public boolean canFallInLove() {
        return super.canFallInLove() && !this.hasEgg();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        BlockPos pos = this.blockPosition();
        if (this.isAlive() && this.isLayingEgg() && this.layEggCounter >= 1 && this.layEggCounter % 5 == 0 && this.level().getBlockState(pos.below()).is(this.getEggLayableBlockTag())) {
            this.level().levelEvent(2001, pos, Block.getId(this.level().getBlockState(pos.below())));
        }
        this.setKillCooldown(Math.max(0, this.getKillCooldown() - 1));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    protected <E extends Alligator> PlayState predicate(final AnimationState<E> event) {
        if (this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6) {
            if (this.isInWater()) {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("swim"));
            } else {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("walk"));
                event.getController().setAnimationSpeed(1.5D);
            }
        } else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
        }
        return PlayState.CONTINUE;
    }

    protected <E extends Alligator> PlayState attackPredicate(final AnimationState<E> event) {
        if (this.swinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("bite"));
            // event.getController().markNeedsReload();
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        // data.setResetSpeedInTicks(10);
        controllers.add(new AnimationController<>(this, "controller", 10, this::predicate));
        controllers.add(new AnimationController<>(this, "attackController", 0, this::attackPredicate));
    }


    static class AlligatorBreedGoal extends BreedGoal {

        public AlligatorBreedGoal(NaturalistAnimal naturalistAnimal, double d) {
            super(naturalistAnimal, d);
        }

        @Override
        protected void breed() {

        }
    }
}
