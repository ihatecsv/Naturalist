package com.starfish_studios.naturalist.common.entity.core;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

// This is the class that acts as the heart for all of our animals.
// It allows us to reduce the amount of code present in each animal's class for better organization.
/** It is also JAM-PACKED with notes, so I can remember how things work or for other people to learn. */

public class NaturalistAnimal extends net.minecraft.world.entity.animal.Animal implements GeoAnimatable {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(ItemTags.PIGLIN_FOOD);
    /** Food items are defined here. I used "PIGLIN_FOOD" as an example, but any tag or even a list of items can be used. */

    protected NaturalistAnimal(EntityType<? extends net.minecraft.world.entity.animal.Animal> entityType, Level level) {
        super(entityType, level);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 1.0D);
                /* Deals 0.5 hearts of damage by default -- not required by all animals, just a base.
                * Not having it and forgetting it can cause issues, so it's best to just include it.
                 */
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
        /* return NaturalistEntityTypes.BEAR.get().create(level); */
        return null;
    }

    @Override
    public double getMyRidingOffset() {
        return 0.14;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    // ANIMATION
    /** The following code blocks are used to animate the entity.
      * It can be changed per instance if that mob needs different animations,
      * but these are the base animations most of our mobs use.
    **/

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    public double getTick(Object object) {
        return 0; // TODO: ??
    }

    private <E extends GeoAnimatable> PlayState predicate(final AnimationState<E> event) {  // Replace `YourEntityName` with the name of your entity
        if (event.getLimbSwingAmount() > 1.0E-6) { // Replaced `getDeltaMovement().horizontalDistanceSqr()`
            if (this.isSprinting()) {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("run"));
                event.getController().setAnimationSpeed(2.0D);
            } else {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("walk"));
                event.getController().setAnimationSpeed(1.0D);
            }
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
        }
        // event.getController().markNeedsReload();
        return PlayState.STOP;
    }

    // registerControllers adds or "creates" animation controllers
    /** These are used in instances such as the code block above - where it says "PlayState predicate";
     *  That predicate then calls the animations below -
     *  by using multiple predicates, animations can be played on top of one another since they use different controllers.
     *  Using a single controller will cause animations to override one another.
     */
    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        // TODO: used to be 5
        // data.setResetSpeedInTicks(5);
        controllers.add(new AnimationController<>(this, "controller", 5, this::predicate));
        /* controllers.add(new AnimationController<>(this, "attackController", 5, this::predicate)); */
    }

    // Returns the factory created at the top of the class

    // MISC. METHODS
    /** This is where I keep miscellaneous things used elsewhere.
     *  This keeps the things I'm "actively" working on a bit more organized. */

    @Override
    public boolean isFood(@NotNull ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    @Override
    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            this.setSprinting(this.getMoveControl().getSpeedModifier() >= 1.25D);
        } else {
            this.setSprinting(false);
        }
        super.customServerAiStep();
    }
}
