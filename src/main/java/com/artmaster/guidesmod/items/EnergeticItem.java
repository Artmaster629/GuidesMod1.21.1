package com.artmaster.guidesmod.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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



    public static void changeResult(Player player, ResultContainer resultContainer, CallbackInfo ci) {
        var stack = resultContainer.getItem(0);


        var modifiers = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
        if (modifiers != null) {

            ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

            for (var entry : modifiers.modifiers()) {
                var attribute = entry.attribute();
                var modifier = entry.modifier();
                var slot = entry.slot();

                if (attribute.equals(Attributes.ATTACK_DAMAGE)) {
                    double newDamage = modifier.amount() + 2;

                    builder.add(
                            attribute,
                            new AttributeModifier(modifier.id(), newDamage, modifier.operation()),
                            slot
                    );
                } else {
                    // остальные модификаторы как есть
                    builder.add(attribute, modifier, slot);
                }
            }

            ///CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> tag.putBoolean("smith_skill_1", true));
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build());
        }

    }



}
