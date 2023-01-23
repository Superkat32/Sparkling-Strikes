package net.superkat.sparklingstrikes.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class SparkleParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    SparkleParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
//        this.velocityMultiplier = 0.6F;
        this.spriteProvider = spriteProvider;
        this.maxAge = 40;
        this.scale = 0.1F + this.random.nextFloat() / 2;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.x = x + this.random.nextFloat();
        this.y = y + this.random.nextFloat();
        this.z = z + this.random.nextFloat();
        this.angle = 1F;
//        this.setBoundingBoxSpacing(0.02F, 0.02F);
//        this.velocityX = this.random.nextFloat() + 0.07;
//        this.velocityY = 0;
//        this.velocityZ = this.random.nextFloat() + 0.07;
//        this.startX = x;
//        this.startY = y;
//        this.startZ = z;
        this.collidesWithWorld = false;
        this.setSpriteForAge(spriteProvider);
        int color = this.random.nextBetween(1, 4);
        switch(color) {
            case 1 -> {
                this.setColor(0.9F + this.random.nextFloat() * 0.2F, 0.4F + this.random.nextFloat() * 0.3F, 0.8F + this.random.nextFloat() * 0.2F);
            } case 2 -> {
                this.setColor(0.9F + this.random.nextFloat() * 0.2F, 0.2F + this.random.nextFloat() * 0.3F, 0.6F + this.random.nextFloat() * 0.2F);
            } case 3 -> {
                this.setColor(0.9F + this.random.nextFloat() * 0.2F, 0.8F + this.random.nextFloat() * 0.3F, 0.3F + this.random.nextFloat() * 0.2F);
            } case 4 -> {
                this.setColor(0.6F + this.random.nextFloat() * 0.2F, 0.9F + this.random.nextFloat() * 0.3F, 0.1F + this.random.nextFloat() * 0.2F);
            }
        }
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge || this.scale <= 0) {
            this.markDead();
        } else {
            int extraTime = this.random.nextBetween(1, 5);
            if (this.age <= 4) {
                this.scale += 0.05;
            } else if (this.age - extraTime > 7) {
                this.angle -= 0.06;
//                if (this.angle > 0) {
//                } else if (this.angle < 0) {
//                    SparkyStrikes.LOGGER.info("angle set to 0!");
//                    this.angle = 0;
//                }
                this.scale -= 0.25;
            }
            this.setSpriteForAge(this.spriteProvider);
            this.move(this.velocityX, this.velocityY, this.velocityZ);
        }
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new SparkleParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
