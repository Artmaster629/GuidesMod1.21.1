package com.artmaster.guidesmod;

import com.artmaster.guidesmod.items.EnergeticItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.Optional;

public class ModItems {


    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, ModMain.MODID);


    public static final DeferredHolder<Item, Item> ENERGETIC_ITEM_EMPTY =
            ITEMS.register("energetic_item_empty", () -> new Item(
                            new Item.Properties()
                                    .stacksTo(64)
                    )
            );



    public static final DeferredHolder<Item, EnergeticItem> ENERGETIC_ITEM =
            ITEMS.register("energetic_item", () -> new EnergeticItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .food(new FoodProperties(
                                    6,
                                    6F,
                                    true,
                                    3,
                                    Optional.of(new ItemStack(ENERGETIC_ITEM_EMPTY)),
                                    Collections.emptyList()
                            ))
                    )
            );



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
