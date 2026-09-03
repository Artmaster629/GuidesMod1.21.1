package com.artmaster.guidesmod;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = ModMain.MODID)
public class ModEvents {


    @SubscribeEvent
    public static void onEvent(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        if (player.getMainHandItem().getItem() == Items.AIR) {
            event.setCanceled(true);
            return;
        }

        if (!player.level().isClientSide()) { //серверная сторона
            ItemStack stack = player.getMainHandItem();

            if (stack.getItem() == Items.NETHERITE_PICKAXE) {
                if (player.getAttributeValue(Attributes.ARMOR) >= 10) {
                    BlockPos pos = event.getPos();

                    BlockState state = event.getState(); //способ 1

                    Level level = player.level();
                    Block lava = Blocks.LAVA;
                    level.setBlock(pos, lava.defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }








    }


}
