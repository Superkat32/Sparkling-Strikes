package net.superkat.sparklingstrikes;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sparklingstrikes");

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");
	}
}