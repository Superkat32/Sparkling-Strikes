package net.superkat.sparklingstrikes;

import eu.midnightdust.lib.config.MidnightConfig;
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
	public static final DefaultParticleType STAR = FabricParticleTypes.simple();

	//TODO - Change fabric.mod.json source stuff
	//TODO - Create icon.png
	@Override
	public void onInitialize() {
		//load config
		MidnightConfig.init(MOD_ID, SparklingConfig.class);

		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "sparkle"), SPARKLE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "star"), STAR);
//		LOGGER.info("Hello Fabric world!");
	}
}