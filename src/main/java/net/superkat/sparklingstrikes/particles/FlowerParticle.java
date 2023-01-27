package net.superkat.sparklingstrikes.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class FlowerParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    FlowerParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
//        this.velocityMultiplier = 0.6F;
        this.spriteProvider = spriteProvider;
        this.maxAge = 40;
        this.scale = 0.05F + this.random.nextFloat() / 12;
        this.velocityX = velocityX;
        this.velocityY = velocityY - 0.03 - this.random.nextFloat() / 30;
        this.velocityZ = velocityZ;
        this.x = x + this.random.nextFloat();
        this.y = y + this.random.nextFloat();
        this.z = z + this.random.nextFloat();
//        this.angle = 0.30F;
//        this.setBoundingBoxSpacing(0.02F, 0.02F);
//        this.velocityX = this.random.nextFloat() + 0.07;
//        this.velocityY = 0;
//        this.velocityZ = this.random.nextFloat() + 0.07;
//        this.startX = x;
//        this.startY = y;
//        this.startZ = z;
        this.collidesWithWorld = true;
        this.setSpriteForAge(spriteProvider);
        //The fifth number is to make it have a chance to be just white
//        int color = this.random.nextBetween(1, 5);
//        switch(color) {
//            case 1 -> {
//                this.setColor(0.8F + this.random.nextFloat() * 0.2F, 0.4F + this.random.nextFloat() * 0.3F, 0.8F + this.random.nextFloat() * 0.2F);
//            } case 2 -> {
//                this.setColor(0.7F + this.random.nextFloat() * 0.2F, 0.2F + this.random.nextFloat() * 0.3F, 0.6F + this.random.nextFloat() * 0.2F);
//            } case 3 -> {
//                this.setColor(0.8F + this.random.nextFloat() * 0.2F, 0.8F + this.random.nextFloat() * 0.3F, 0.3F + this.random.nextFloat() * 0.2F);
//            } case 4 -> {
//                this.setColor(0.6F + this.random.nextFloat() * 0.2F, 0.9F + this.random.nextFloat() * 0.3F, 0.1F + this.random.nextFloat() * 0.2F);
//            }
//        }
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge || this.scale <= 0) {
            this.markDead();
        } else {
            int extraTime = this.random.nextBetween(1, 5);
            if (this.age <= 7) {
                this.scale += 0.03;
            } else if (this.age - extraTime > 13) {
                if (this.alpha <= 0) {
                    this.markDead();
                } else {
                    this.alpha -= 0.05;
                    this.velocityX *= 1.03;
                    this.velocityY -= 0.01;
                    this.velocityZ *= 1.03;
                }
            }
//            this.setSpriteForAge(this.spriteProvider);
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
            FlowerParticle flowerParticle = new FlowerParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
            flowerParticle.setSprite(this.spriteProvider);
            return flowerParticle;
        }
    }
}
