package net.superkat.sparklingstrikes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class SparklingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(SparklingMain.SPARKLE, SparkleParticle.Factory::new);

    }
}
