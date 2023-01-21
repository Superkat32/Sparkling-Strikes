package net.superkat.sparklingstrikes;

import eu.midnightdust.lib.config.MidnightConfig;

public class SparklingConfig extends MidnightConfig {

    @Entry(name = "Particle Amount",isSlider = true,min=1,max=100) public static int particleAmount = 15;

}
