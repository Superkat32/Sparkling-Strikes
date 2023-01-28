package net.superkat.sparklingstrikes;

import eu.midnightdust.lib.config.MidnightConfig;

public class SparklingConfig extends MidnightConfig {

    @Comment(centered = true) public static Comment primaryParticleOptions;
    @Entry public static boolean spawnOnlyOnCrit = false;
    @Entry(name = "Particle Amount",isSlider = true,min=1,max=100) public static int particleAmount = 15;
    @Entry public static ParticleOption particleOption = ParticleOption.SPARKLE;
    public enum ParticleOption {
        SPARKLE, STAR, HEART, FLOWER
    }

    @Comment(centered = true) public static Comment secondaryParticleOptions;
    @Entry public static boolean spawnSecondaryParticle = false;
    @Entry public static boolean spawnSecondaryOnlyOnCrit = false;
    @Entry(name = "Secondary Particle Amount",isSlider=true,min=1,max=100) public static int secondaryParticleAmount = 7;
    @Entry public static SecondaryParticleOption secondaryParticleOption = SecondaryParticleOption.HEART;
    public enum SecondaryParticleOption {
        SPARKLE, STAR, HEART, FLOWER
    }

    @Comment(centered = true) public static Comment otherOptions;
    @Entry public static boolean modEnabled = true;


}
