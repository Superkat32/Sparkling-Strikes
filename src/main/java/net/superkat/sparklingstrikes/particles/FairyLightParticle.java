package net.superkat.sparklingstrikes.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class FairyLightParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    float red = 1.0F;
    float green = 0.1F;
    float blue = 0.1F;
    float changeRed;
    float changeGreen;
    float changeBlue;
    int colorTransitionTick = 0;
    int colorTransitionDelay = 18;

    FairyLightParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
//        this.velocityMultiplier = 0.6F;
        this.spriteProvider = spriteProvider;
        this.maxAge = 40;
        this.scale = 0.05F + this.random.nextFloat() / 6;
        this.velocityX = velocityX * 1.03 + this.random.nextFloat() / 100;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ * 1.03 + this.random.nextFloat() / 100;
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
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge || this.scale <= 0) {
            this.markDead();
        } else {
            this.colorTransitionTick += 1;
            if (colorTransitionTick <= colorTransitionDelay) {
                this.transitionColor("GREEN");
            } else if (colorTransitionTick <= colorTransitionDelay * 2) {
                this.transitionColor("BLUE");
            } else if (colorTransitionTick <= colorTransitionDelay * 3) {
                this.transitionColor("RED");
            } else if (colorTransitionTick == colorTransitionDelay * 4) {
                this.colorTransitionTick = 1;
            }
            this.changeColor();
//            this.setColor(red, green, blue);
            int extraTime = this.random.nextBetween(1, 7);
            if (this.age <= 7) {
                this.scale += 0.03;
            } else if (this.age - extraTime > 20) {
                if (this.scale <= 0) {
                    this.markDead();
                } else {
                    this.scale -= 0.05;
                    this.velocityX *= 1.05;
                    this.velocityY *= 1.02;
                    this.velocityZ *= 1.05;
                }
            }
//            this.setSpriteForAge(this.spriteProvider);
            this.move(this.velocityX, this.velocityY, this.velocityZ);
        }
    }

    public void transitionColor(String transitionToColor) {
        switch(transitionToColor) {
            case "RED" -> {
                this.changeRed = 0.05F;
//                this.changeGreen = -0.1F;
                this.changeBlue = -0.05F;
            }
            case "GREEN" -> {
                this.changeRed = -0.05F;
                this.changeGreen = 0.05F;
//                this.changeBlue = -0.1F;
            }
            case "BLUE" -> {
//                this.changeRed = -0.1F;
                this.changeGreen = -0.05F;
                this.changeBlue = 0.05F;
            }
        }
    }

    public void changeColor() {
        this.red += this.changeRed;
        this.green += this.changeGreen;
        this.blue += this.changeBlue;
        this.setColor(this.red, this.green, this.blue);
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
            return new FairyLightParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
