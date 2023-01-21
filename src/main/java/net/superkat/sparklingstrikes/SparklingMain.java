package net.superkat.sparklingstrikes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparklingMain implements ModInitializer {
	public static final String MOD_ID = "sparklingstrikes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final DefaultParticleType SPARKLE = FabricParticleTypes.simple();

	//TODO - Change fabric.mod.json source stuff
	@Override
	public void onInitialize() {
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "sparkle"), SPARKLE);
		LOGGER.info("Hello Fabric world!");
	}
}