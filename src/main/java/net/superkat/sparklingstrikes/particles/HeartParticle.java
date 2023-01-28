package net.superkat.sparklingstrikes.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class HeartParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;
    int extraTime = this.random.nextBetween(1, 15);

    HeartParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.maxAge = 30 + extraTime;
        this.scale = 0.1F + this.random.nextFloat() / 3;
        this.velocityX = velocityX;
        this.velocityY = velocityY + 0.15F;
        this.velocityZ = velocityZ;
        this.gravityStrength = 0.3F;
        this.x = x + this.random.nextFloat();
        this.y = y + this.random.nextFloat();
        this.z = z + this.random.nextFloat();
        this.alpha = 1F;
        this.collidesWithWorld = true;
        this.setSpriteForAge(spriteProvider);
        int color = this.random.nextBetween(1, 4);
        switch(color) {
            case 1 -> {
                this.setColor(1.0F, 0.05F, 0.2F);
            } case 2 -> {
                this.setColor(0.9F, 0.4F, 0.4F);
            } case 3 -> {
                this.setColor(0.9F, 0.2F, 0.4F);
            } case 4 -> {
                this.setColor(1.0F, 0.2F, 0.3F);
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
            if (this.age > 7) {
                if (this.velocityY != -0.50) {
                    this.velocityY -= 0.05;
                }
            }
            if (this.age > 30 - extraTime) {
                this.alpha -= 0.10F;
            }
            if (this.onGround) {
                this.scale -= 0.05;
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
            return new HeartParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
