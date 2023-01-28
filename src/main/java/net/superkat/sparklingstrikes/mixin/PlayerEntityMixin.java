package net.superkat.sparklingstrikes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.superkat.sparklingstrikes.SparklingConfig;
import net.superkat.sparklingstrikes.SparklingMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.superkat.sparklingstrikes.SparklingMain.LOGGER;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {
//	public World world;


	public PlayerEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I"))
	private void hitEventNoCrit(Entity target, CallbackInfo ci) {
		if (!SparklingConfig.spawnOnlyOnCrit) {
			this.spawnParticles(target, ci);
		}
		if (!SparklingConfig.spawnSecondaryOnlyOnCrit) {
			this.spawnSecondaryParticles(target, ci);
		}
	}

	@Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V"))
	private void hitEventCrit(Entity target, CallbackInfo ci) {
		if (SparklingConfig.spawnOnlyOnCrit) {
			this.spawnParticles(target, ci);
		}
		if (SparklingConfig.spawnSecondaryOnlyOnCrit) {
			this.spawnSecondaryParticles(target, ci);
		}
	}

	public void spawnParticles(Entity target, CallbackInfo ci) {
		if (SparklingConfig.modEnabled) {
			if (SparklingConfig.spamLog) {
				LOGGER.info("spawnParticles has been called! (Total spawned: " + SparklingConfig.particleAmount + ")");
				LOGGER.info("Mob hit = " + String.valueOf(target));
			}
			switch (SparklingConfig.particleOption) {
				case SPARKLE ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.SPARKLE, target.getX(), target.getBodyY(0.5), target.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
				case STAR ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.STAR, target.getX(), target.getBodyY(0.5), target.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
				case HEART ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.HEART, target.getX(), target.getBodyY(0.5), target.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
				case FLOWER ->
						((ServerWorld) this.world).spawnParticles(SparklingMain.FLOWER, target.getX(), target.getBodyY(0.5), target.getZ(), SparklingConfig.particleAmount, 0.0, 0.0, 0.0, 0.07);
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
				}
			}
		}
	}

}