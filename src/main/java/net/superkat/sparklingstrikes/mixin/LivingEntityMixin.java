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
public abstract class LivingEntityMixin extends Entity {


	@Shadow @Final private static Logger LOGGER;

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "damage", at = @At(value = "HEAD"))
	void hitEvent(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		LOGGER.info("entity has been hit!");
//		((ServerWorld) this.world).spawnParticles(SparklingMain.SPARKLE, this.getX(), this.getBodyY(0.5), this.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
        spawnParticles();


//		this.spawnParticles();
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
				LOGGER.info("spawnParticles has been called! (Config Amount: " + SparklingConfig.particleAmount + ")");
			}
			for(int spawnAmount = SparklingConfig.particleAmount / 3; spawnAmount >= 0; spawnAmount--) {
				//Determines which direction the particle should go in
				boolean xPosOrNegBoolean = this.random.nextBoolean();
				boolean zPosOrNegBoolean = this.random.nextBoolean();
				int xPosOrNeg = 1;
				int zPosOrNeg = 1;
				if (!xPosOrNegBoolean) {
					xPosOrNeg = -1;
				} if (!zPosOrNegBoolean) {
					zPosOrNeg = -1;
				}
				//Spawns the particle(s)
				switch (SparklingConfig.particleOption) {
					case SPARKLE ->
							this.world.addParticle(SparklingMain.SPARKLE, true, this.getX(), this.getY() + 0.5, this.getZ(), 0.07 * xPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20), 0.05 + this.random.nextFloat() / this.random.nextBetween(8, 20), 0.07 * zPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20));
					case STAR ->
							this.world.addParticle(SparklingMain.STAR, true, this.getX(), this.getY() + 0.5, this.getZ(), 0.07 * xPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20), 0.05 + this.random.nextFloat() / this.random.nextBetween(8, 20), 0.07 * zPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20));
					case HEART ->
							this.world.addParticle(SparklingMain.HEART, true, this.getX(), this.getY() + 0.5, this.getZ(), 0.07 * xPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20), 0.05 + this.random.nextFloat() / this.random.nextBetween(8, 20), 0.07 * zPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20));
					case FLOWER ->
							this.world.addParticle(SparklingMain.FLOWER, true, this.getX(), this.getY() + 0.5, this.getZ(), 0.07 * xPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20), 0.05 + this.random.nextFloat() / this.random.nextBetween(8, 20), 0.07 * zPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20));
					case FAIRYLIGHT ->
							this.world.addParticle(SparklingMain.FAIRYLIGHT, true, this.getX(), this.getY() + 0.5, this.getZ(), 0.07 * xPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20), 0.05 + this.random.nextFloat() / this.random.nextBetween(8, 20), 0.07 * zPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20));
				}
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