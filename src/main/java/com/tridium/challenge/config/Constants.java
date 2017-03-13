package com.tridium.challenge.config;

/**
 * Application constants.
 */
public final class Constants {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    
    private Constants() {
    }
    
    public enum Version {V_1_1, V_2_0}
}
