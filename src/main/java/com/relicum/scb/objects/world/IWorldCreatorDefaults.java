package com.relicum.scb.objects.world;

/**
 * SuperSkyBros
 * First Created 20/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public interface IWorldCreatorDefaults {


    String getName();

    String getWorldType();

    String getEnvironment();

    boolean isStructures();

    String getGenerator();

    long getSeed();

    boolean getAllowNether();

}
