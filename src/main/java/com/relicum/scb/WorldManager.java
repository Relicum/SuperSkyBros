package com.relicum.scb;

import com.relicum.scb.configs.WorldConfig;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.metadata.MetadataStoreBase;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.Map;

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


    public WorldManager(SCB p) {
        this.plugin = p;
        defaultGenerate = p.getConfig().getBoolean("generateDefaultWorld");
        this.config = new WorldConfig("worlds.yml");
        this.config.getConfig().options().copyDefaults(true);
        this.config.saveConfig();


        this.worldSettings = this.config.getConfig().getConfigurationSection("worldSettings").getValues(true);


        plugin.getLogger().info("WorldGenerator Successfully Loaded");
        this.setTemplateDefaults();
        if (this.config.getConfig().getBoolean("firstTime")) {
            this.generateTemplate();
            plugin.getLogger().info("New Template World Successfully Created");
            this.config.getConfig().set("firstTime", false);
            this.config.saveConfig();
            this.config.reloadConfig();
        }

        this.generateTemplate();
        System.out.println("Template world loading");

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


    public World createNewWorld(String name) {

        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.copy(this.template);
        World world;
        try {
            world = worldCreator.createWorld();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }


        world.setSpawnLocation(0, 65, 0);
        world.setKeepSpawnInMemory(true);
        world.setDifficulty(Difficulty.NORMAL);


        return world;
    }


    private Location convertToLocation(String l) {

        return null;
    }
}
