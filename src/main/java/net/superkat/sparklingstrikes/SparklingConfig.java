package net.superkat.sparklingstrikes;

import eu.midnightdust.lib.config.MidnightConfig;

public class SparklingConfig extends MidnightConfig {

    @Comment(centered = true, category = "primary") public static Comment primaryParticleOptions;
    @Entry(name = "Rough Particle Amount",isSlider = true,min=1,max=100, category = "primary") public static int particleAmount = 15;
    @Entry(category = "primary") public static ParticleOption particleOption = ParticleOption.SPARKLE;
    public enum ParticleOption {
        SPARKLE, STAR, HEART, FLOWER, FAIRYLIGHT
    }

    @Comment(centered = true, category = "secondary") public static Comment secondaryParticleOptions;
    @Entry(category = "secondary") public static boolean spawnSecondaryParticle = false;
    @Entry(name = "Rough Secondary Particle Amount",isSlider=true,min=1,max=100, category = "secondary") public static int secondaryParticleAmount = 7;
    @Entry(category = "secondary") public static SecondaryParticleOption secondaryParticleOption = SecondaryParticleOption.HEART;
    public enum SecondaryParticleOption {
        SPARKLE, STAR, HEART, FLOWER, FAIRYLIGHT
    }

    @Comment(centered = true, category = "other") public static Comment otherOptions;
    @Entry(category = "other") public static boolean modEnabled = true;
    @Entry(category = "other") public static boolean spamLog = false;

}
