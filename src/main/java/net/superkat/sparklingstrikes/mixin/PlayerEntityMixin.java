package net.superkat.sparklingstrikes.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.superkat.sparklingstrikes.SparklingMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "damage", at = @At(value = "HEAD"))
    void hitEvent(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        SparklingMain.LOGGER.info("PlayerEntity damage has been called!");
    }

}
