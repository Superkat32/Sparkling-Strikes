package net.superkat.sparklingstrikes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.superkat.sparklingstrikes.SparklingMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {
//	public World world;

	int particleAmount = 1;

	public PlayerEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I"))
	private void spawnHitParticle(Entity target, CallbackInfo ci) {
		SparklingMain.LOGGER.info("spawnHitParticle has been called!");
//		this.world.addParticle(SparkyStrikes.SPARK, target.getX(), target.getY(), target.getZ(), 0.5, 0.5, 0.5);
		((ServerWorld)this.world).spawnParticles(SparklingMain.SPARKLE, target.getX(), target.getBodyY(0.5), target.getZ(), particleAmount, 0.0, 0.0, 0.0, 0.07);
//		world.addParticle(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getY(), target.getZ(), 0.5, 0.5, 0.5);
	}
}