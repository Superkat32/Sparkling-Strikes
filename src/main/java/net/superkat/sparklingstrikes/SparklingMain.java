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

	//TODO - Fix multiplayer not working
	//TODO - Add mod's repo to fabric.mod.json

    /*How to add a new particle.
    1. Create new DefaultParticleType
    2. Register the particle here, in SparklingMain
    3. Register the particle in SparklingClient (Don't forget about the factory if you want it!)
    4. Create the particle json
    5. Add the particle texture
    6. Add the particle option in SparklingConfig
    7. Add the particle option in PlayerEntityMixin in the switch case, so that it can actually be used
    8. Add the particle option text in en_us.json
    9. Repeat steps 6-8, but for the secondary particle
    10. Celebrate! You have now added a new particle, future self!
     */

	public static final DefaultParticleType SPARKLE = FabricParticleTypes.simple();
	public static final DefaultParticleType STAR = FabricParticleTypes.simple();
	public static final DefaultParticleType HEART = FabricParticleTypes.simple();
	public static final DefaultParticleType FLOWER = FabricParticleTypes.simple();
	public static final DefaultParticleType FAIRYLIGHT = FabricParticleTypes.simple();

	//TODO - Change fabric.mod.json source stuff
	//TODO - Create icon.png
	@Override
	public void onInitialize() {
		//load config
		MidnightConfig.init(MOD_ID, SparklingConfig.class);

		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "sparkle"), SPARKLE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "star"), STAR);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "heart"), HEART);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "flower"), FLOWER);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "fairylight"), FAIRYLIGHT);
	}
}