package net.superkat.sparklingstrikes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.superkat.sparklingstrikes.particles.FlowerParticle;
import net.superkat.sparklingstrikes.particles.HeartParticle;
import net.superkat.sparklingstrikes.particles.SparkleParticle;
import net.superkat.sparklingstrikes.particles.StarParticle;

public class SparklingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(SparklingMain.SPARKLE, SparkleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(SparklingMain.STAR, StarParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(SparklingMain.HEART, HeartParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(SparklingMain.FLOWER, FlowerParticle.Factory::new);

    }
}
