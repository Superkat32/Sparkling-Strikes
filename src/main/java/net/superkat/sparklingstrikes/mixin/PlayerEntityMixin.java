package net.superkat.sparklingstrikes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.superkat.sparklingstrikes.SparklingConfig;
import net.superkat.sparklingstrikes.SparklingMain;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PlayerEntityMixin extends Entity {


	@Shadow @Final private static Logger LOGGER;

	@Shadow protected abstract void takeShieldHit(LivingEntity attacker);

	public PlayerEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "damage", at = @At(value = "HEAD"))
	private void hitEvent(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		LOGGER.info("entity has been hit!");
		this.spawnParticles();
	}
//
//	@Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I"))
//	private void hitEventNoCrit(Entity target, CallbackInfo ci) {
//		if (SparklingConfig.spamLog) {
//			LOGGER.info("Sparkles to be summoned!");
//		}
//		if (!SparklingConfig.spawnOnlyOnCrit) {
//			this.spawnParticles(target, ci);
//		}
//		if (!SparklingConfig.spawnSecondaryOnlyOnCrit) {
//			this.spawnSecondaryParticles(target, ci);
//		}
//	}
//
//	@Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V"))
//	private void hitEventCrit(Entity target, CallbackInfo ci) {
//		if (SparklingConfig.spawnOnlyOnCrit) {
//			if (SparklingConfig.spamLog) {
//				LOGGER.info("Crit sparkles to be summoned!");
//			}
//			this.spawnParticles(target, ci);
//		}
//		if (SparklingConfig.spawnSecondaryOnlyOnCrit) {
//			this.spawnSecondaryParticles(target, ci);
//		}
//	}

	public void spawnParticles() {
		if (SparklingConfig.modEnabled) {
			if (SparklingConfig.spamLog) {
				LOGGER.info("spawnParticles has been called! (Total spawned: " + SparklingConfig.particleAmount + ")");
//				LOGGER.info("Mob hit = " + String.valueOf(this.));
			}
			switch (SparklingConfig.particleOption) {
				case SPARKLE ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.SPARKLE, this.getX(), this.getBodyY(0.5), this.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
				case STAR ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.STAR, this.getX(), this.getBodyY(0.5), this.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
				case HEART ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.HEART, this.getX(), this.getBodyY(0.5), this.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
				case FLOWER ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.FLOWER, this.getX(), this.getBodyY(0.5), this.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
				case FAIRYLIGHT ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.FAIRYLIGHT, this.getX(), this.getBodyY(0.5), this.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
			}
		} else {
			if (SparklingConfig.spamLog) {
				LOGGER.info("No particle(s) shown; mod enabled/disabled status: " + SparklingConfig.modEnabled);
			}
		}
	}

	public void spawnSecondaryParticles(Entity target, CallbackInfo ci) {
		if (SparklingConfig.modEnabled) {
			if (SparklingConfig.spawnSecondaryParticle) {
				if (SparklingConfig.spamLog) {
					LOGGER.info("spawnSecondaryParticles has been called! (Total spawned: " + SparklingConfig.secondaryParticleAmount + ")");
				}
				switch (SparklingConfig.secondaryParticleOption) {
					case SPARKLE ->
							((ServerWorld) this.world).spawnParticles(SparklingMain.SPARKLE, target.getX(), target.getBodyY(0.5), target.getZ(), SparklingConfig.secondaryParticleAmount, 0.0, 0.0, 0.0, 0.07);
					case STAR ->
							((ServerWorld) this.world).spawnParticles(SparklingMain.STAR, target.getX(), target.getBodyY(0.5), target.getZ(), SparklingConfig.secondaryParticleAmount, 0.0, 0.0, 0.0, 0.07);
					case HEART ->
							((ServerWorld) this.world).spawnParticles(SparklingMain.HEART, target.getX(), target.getBodyY(0.5), target.getZ(), SparklingConfig.secondaryParticleAmount, 0.0, 0.0, 0.0, 0.07);
					case FLOWER ->
							((ServerWorld) this.world).spawnParticles(SparklingMain.FLOWER, target.getX(), target.getBodyY(0.5), target.getZ(), SparklingConfig.secondaryParticleAmount, 0.0, 0.0, 0.0, 0.07);
					case FAIRYLIGHT ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.FAIRYLIGHT, target.getX(), target.getBodyY(0.5), target.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
				}
			}
		}
	}

}