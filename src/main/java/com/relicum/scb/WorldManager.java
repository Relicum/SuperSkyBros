package com.relicum.scb;

import com.relicum.scb.configs.WorldConfig;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.metadata.MetadataStoreBase;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * SuperSkyBros First Created 29/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldManager {

    private boolean defaultGenerate;

    private WorldConfig config;

    private SCB plugin;

    private WorldCreator template;

    private Map<String, Object> worldSettings;


    public WorldManager(SCB p, WorldConfig worldConfig) {
        this.plugin = p;
        defaultGenerate = p.getConfig().getBoolean("generateDefaultWorld");
        this.config = worldConfig;


        this.worldSettings = this.config.getConfig().getConfigurationSection("worldSettings").getValues(true);


        plugin.getLogger().info("WorldGenerator Successfully Loaded");
       /* this.setTemplateDefaults();
        if (this.config.getConfig().getBoolean("firstTime")) {
            this.generateTemplate();
            plugin.getLogger().info("New Template World Successfully Created");
            this.config.getConfig().set("firstTime", false);
            this.config.saveConfig();
            this.config.reloadConfig();
        }*/

        // this.generateTemplate();


    }


    /**
     * Set template defaults for new works.
     */
    private void setTemplateDefaults() {

        this.template = new WorldCreator(this.getConfig().getString("default.name"));
        this.template.type(WorldType.valueOf(this.getConfig().getString("default.type")));
        this.template.environment(Environment.valueOf(this.getConfig().getString("default.environment")));
        this.template.generateStructures(this.getConfig().getBoolean("default.structures"));
        this.template.generator(this.getConfig().getString("default.generator"));

    }


    private WorldCreator applyDefaultSettings(String name) {

        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.type(WorldType.FLAT);
        worldCreator.environment(Environment.NORMAL);
        worldCreator.generateStructures(false);
        worldCreator.generator("CleanroomGenerator:.");
        worldCreator.seed(this.randomSeed());

        return worldCreator;
    }


    /**
     * Gets config as FileConfiguration
     *
     * @return the config as a FileConfiguration object
     */
    public FileConfiguration getConfig() {
        return config.getConfig();
    }


    public void setConfig(WorldConfig config) {
        this.config = config;
    }


    public WorldConfig getConfigs() {
        return this.config;
    }


    /**
     * Generate template world.
     *
     * @return the boolean true if world was created successfully
     */
    private boolean generateTemplate() {

        try {
            this.plugin.getLogger().info("Attempting to create template World");
            World world = this.template.createWorld();
            MetadataStoreBase meta = new MetadataStoreBase() {

                @Override
                protected String disambiguate(Object o, String s) {
                    return null;
                }
            };

            List<MetadataValue> wm = world.getMetadata("456");
            System.out.println(wm.toString());


            this.plugin.getLogger().info("Finished creating template World");
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * If true the default world will be deleted and recreated As a empty void world
     *
     * @return the boolean if true recreate default world
     */
    public boolean isDefaultGenerate() {
        return defaultGenerate;
    }


    private World createNewWorld(String name) {

        WorldCreator worldCreator = this.applyDefaultSettings("Template");
        World world;
        try {
            world = worldCreator.createWorld();

        }
        catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
        System.out.println("New world called " + name + "has been created");
        return world;
    }


    public void applyWorldDefaultSettings(String name) {

        World world = null;

        try {
            world = Bukkit.getServer().getWorld(name);
        }
        catch ( NullPointerException e ) {
            e.printStackTrace();
            return;
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
            return;
        }

        world.setSpawnLocation(0, 64, 0);
        world.setKeepSpawnInMemory(true);
        world.setSpawnFlags(false, false);
        Block block = world.getBlockAt(0, 65, 0);
        block.getState().setType(Material.GOLD_BLOCK);
        block.getState().update(true);
        world.save();
        System.out.println("Settings applied for  " + name + "has been successful");
    }


    private Location convertToLocation(String l) {

        return null;
    }


    private long randomSeed() {

        Random random = new Random();
        return random.nextLong();
    }
}
