package net.superkat.sparklingstrikes;

import eu.midnightdust.lib.config.MidnightConfig;

public class SparklingConfig extends MidnightConfig {

    @Entry public static boolean modEnabled = true;
    @Entry public static boolean spawnOnlyOnCrit = false;

    @Entry(name = "Particle Amount",isSlider = true,min=1,max=100) public static int particleAmount = 15;

    @Entry public static ParticleOption particleOption = ParticleOption.SPARKLE;
    public enum ParticleOption {
        SPARKLE, STAR, HEART, FLOWER
    }

}
