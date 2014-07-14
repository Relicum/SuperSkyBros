/**
 * Name: WManager.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb;

import com.relicum.scb.configs.WorldDefaults;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.util.Random;

/**
 * WManager - MiniGames World creator and manager
 *
 * @author Relicum
 * @version 0.2
 */
public class WManager {

    // TODO Create a new world
    // TODO Save the new world settings to its own file
    // TODO Create a register that stores what worlds to load at start up
    // TODO Reapply world settings including the main on server start. These
    // should be as per each world file
    // TODO Allow user to change all these settings in game

    /**
     * Instantiates a new {@link com.relicum.scb.WManager}.
     * <p/>
     * <p/>
     * NOT TO BE CALLED DIRECTLY
     */
    public WManager() {
        new WorldDefaults();
    }

    /**
     * Create a New World
     *
     * @param wname         the name of the world as a {@link java.lang.String}
     * @param worldType     the world type {@link org.bukkit.WorldType}
     * @param environment   the world {@link org.bukkit.World.Environment}
     * @param genStructures {@link java.lang.Boolean} true to generate
     *                      structures
     * @param genSettings   the generator settings as a {@link java.lang.String}
     * @param theSeed       the seed for the world
     * @return the newly created {@link org.bukkit.World}
     */
    public World createNew(String wname, WorldType worldType, World.Environment environment, boolean genStructures, String genSettings, long theSeed) {

        WorldCreator wc = getCreator(wname);
        wc.type(worldType);
        wc.environment(environment);
        wc.generateStructures(genStructures);
        wc.generator(genSettings);
        wc.seed(theSeed);

        return wc.createWorld();
    }

    /**
     * Create New Void World
     *
     * @param wname the name of the world as a {@link java.lang.String}
     * @return the new {@link org.bukkit.World}
     */
    public World createNewVoid(String wname) {
        return createNew(wname, WorldDefaults.worldType, WorldDefaults.environment, WorldDefaults.generate_structures, WorldDefaults.generatorString, randomSeed());
    }

    public World loadWorld(String wname) {
        WorldCreator wc = getCreator(wname);

        return null;
    }

    public void unloadWorld(String wname) {

    }

    public void deleteWorld(String wname) {

    }

    public void saveWorldSettingsToFile(World world) {

    }

    public void saveAllWorldsSettings() {

    }

    public void loadWorldSettingsFromFile(String wname) {

    }

    public void registerWorld(String wname) {

    }

    public void unRegisterWorld(String wname) {

    }

    /**
     * Gets a new instance of {@link org.bukkit.WorldCreator}
     *
     * @param wname the name of the world as a {@link java.lang.String}
     * @return the new {@link org.bukkit.WorldCreator}
     */
    private WorldCreator getCreator(String wname) {
        return new WorldCreator(wname);
    }

    /**
     * Get a new Random seed for the world
     *
     * @return Random Long as a world seed
     */
    public long randomSeed() {

        Random random = new Random(2354623576l);
        return random.nextLong();
    }
}
