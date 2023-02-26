package net.superkat.sparklingstrikes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;
import net.superkat.sparklingstrikes.SparklingConfig;
import net.superkat.sparklingstrikes.SparklingMain;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow @Final private static Logger LOGGER;
	@Shadow public abstract boolean tryAttack(Entity target);
	@Shadow public abstract float getHealth();
    @Shadow protected float lastDamageTaken;

    public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "damage", at = @At(value = "HEAD"))
	void hitEvent(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		Entity entity = source.getSource();
		if (!SparklingConfig.onlyIfPlayer) {
			if(SparklingConfig.spamLog) {
				LOGGER.info("Entity has been hit!");
				LOGGER.info("Only If Player is disabled!");
			}
			callParticles();
		} if (!SparklingConfig.secondaryOnlyIfPlayer) {
			if(SparklingConfig.spamLog) {
				LOGGER.info("Entity has been hit!");
				LOGGER.info("Secondary Only If Player is disabled!");
			}
			callSecondaryParticles();
		} if(SparklingConfig.onlyIfPlayer || SparklingConfig.secondaryOnlyIfPlayer) {
				if(entity instanceof PlayerEntity) {
					if(SparklingConfig.spamLog) {
						LOGGER.info("Entity has been hit!");
					}
					if(SparklingConfig.onlyIfPlayer) {
						callParticles();
					} if (SparklingConfig.secondaryOnlyIfPlayer) {
						callSecondaryParticles();
					}
				}
		}
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

	public void spawnParticles(boolean primary, int amount, ParticleEffect particleType) {
		for(int totalAmount = amount; totalAmount >= 1; totalAmount--) {
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
			this.world.addParticle(particleType, true, this.getX(), this.getY() + 0.5, this.getZ(), 0.07 * xPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20), 0.05 + this.random.nextFloat() / this.random.nextBetween(8, 20), 0.07 * zPosOrNeg + this.random.nextFloat() / this.random.nextBetween(7, 20));
		}
	}

	public void determineParticleAmount(boolean primary, ParticleEffect particleType) {
		int spawnAmount = 0;
//		LOGGER.info(String.valueOf(spawnAmount));
		if(primary) {
			spawnAmount = SparklingConfig.particleAmount;
//			LOGGER.info(String.valueOf(spawnAmount));
			spawnParticles(true, spawnAmount, particleType);
		} else {
			spawnAmount = SparklingConfig.secondaryParticleAmount;
//			LOGGER.info(String.valueOf(spawnAmount));
			spawnParticles(false, spawnAmount, particleType);
		}
	}

	public void callParticles() {
		if (SparklingConfig.modEnabled) {
			if (SparklingConfig.spamLog) {
				LOGGER.info("spawnParticles has been called! (Config Amount: " + SparklingConfig.particleAmount + ")");
			}
			ParticleEffect particle = null;
			switch (SparklingConfig.particleOption) {
				case SPARKLE ->
						particle = SparklingMain.SPARKLE;
				case STAR ->
						particle = SparklingMain.STAR;
				case HEART ->
						particle = SparklingMain.HEART;
				case FLOWER ->
						particle = SparklingMain.FLOWER;
				case FAIRYLIGHT ->
						particle = SparklingMain.FAIRYLIGHT;
			}
			determineParticleAmount(true, particle);
		} else {
			if (SparklingConfig.spamLog) {
				LOGGER.info("No particle(s) shown; mod enabled/disabled status: " + SparklingConfig.modEnabled);
			}
		}
	}

	public void callSecondaryParticles() {
		if (SparklingConfig.modEnabled) {
			if (SparklingConfig.spawnSecondaryParticle) {
				if (SparklingConfig.spamLog) {
					LOGGER.info("spawnSecondaryParticles has been called! (Total spawned: " + SparklingConfig.secondaryParticleAmount + ")");
				}
				ParticleEffect particle = null;
				switch (SparklingConfig.secondaryParticleOption) {
					case SPARKLE ->
							particle = SparklingMain.SPARKLE;
					case STAR ->
							particle = SparklingMain.STAR;
					case HEART ->
							particle = SparklingMain.HEART;
					case FLOWER ->
							particle = SparklingMain.FLOWER;
					case FAIRYLIGHT ->
							particle = SparklingMain.FAIRYLIGHT;
				}
				determineParticleAmount(false, particle);
			}
		}
	}

}