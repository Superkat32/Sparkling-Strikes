package net.superkat.sparklingstrikes;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

import static net.superkat.sparklingstrikes.SparklingMain.LOGGER;

@Environment(EnvType.CLIENT)
public class HeartParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;
    int extraTime = this.random.nextBetween(1, 15);

    HeartParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
//        this.velocityMultiplier = 0.6F;
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
//        this.angle = 1F;
//        this.setBoundingBoxSpacing(0.02F, 0.02F);
//        this.velocityX = this.random.nextFloat() + 0.07;
//        this.velocityY = 0;
//        this.velocityZ = this.random.nextFloat() + 0.07;
//        this.startX = x;
//        this.startY = y;
//        this.startZ = z;
        this.collidesWithWorld = true;
        this.setSpriteForAge(spriteProvider);
        int color = this.random.nextBetween(1, 4);
        switch(color) {
            case 1 -> {
                this.setColor(1.0F, 0.05F, 0.2F);
                LOGGER.info("1");
            } case 2 -> {
                this.setColor(0.9F, 0.4F, 0.4F);
                LOGGER.info("2");
            } case 3 -> {
                this.setColor(0.9F, 0.2F, 0.4F);
                LOGGER.info("3");
            } case 4 -> {
                this.setColor(1.0F, 0.2F, 0.3F);
                LOGGER.info("4");
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
//            int extraTime = this.random.nextBetween(1, 5);
            if (this.onGround) {
                this.scale -= 0.05;
            }
//            if (this.age - extraTime > 15) {
//                this.angle -= 0.06;
//                if (this.angle > 0) {
//                } else if (this.angle < 0) {
//                    SparkyStrikes.LOGGER.info("angle set to 0!");
//                    this.angle = 0;
//                }
//            }
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
