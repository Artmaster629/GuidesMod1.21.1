package com.artmaster.guidesmod.mixins;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KineticBlockEntity.class)
public abstract class KineticBlockEntityMixin extends BlockEntity {


    @Shadow public abstract void requestModelDataUpdate();

    @Shadow public boolean networkDirty;

    public KineticBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }


    @Unique
    private Boolean isRainingLast = null;


    @Inject(method = "tick", at = @At(value = "RETURN"))
    private void tick(CallbackInfo ci) {
        if (this.getLevel() != null) {
            boolean isRainingNow = this.getLevel().isRaining();

            if (isRainingLast == null) {
                isRainingLast = isRainingNow;
                return;
            }

            if (isRainingLast == isRainingNow) {
                return;
            }

            isRainingLast = isRainingNow;

            if (this.getLevel().isClientSide) {
                this.requestModelDataUpdate();
            } else {
                this.networkDirty = true;
            }

        }
    }

    @ModifyReturnValue(method = "getTheoreticalSpeed", at = @At(value = "RETURN"))
    private float getTheoreticalSpeed(float original) {
        if (this.getLevel() != null && this.getLevel().isRaining()) {
            return original * 0.5f;
        }
        return original;



    }

}
