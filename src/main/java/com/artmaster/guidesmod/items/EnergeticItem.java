package com.artmaster.guidesmod.items;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnergeticItem
        extends Item {

    public EnergeticItem(Properties properties) {
        super(properties);
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }








    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {


        if (level.random.nextDouble() < 0.1) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 4800));
        } else {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 4800));
        }

        return super.finishUsingItem(stack, level, livingEntity);

    }



}
