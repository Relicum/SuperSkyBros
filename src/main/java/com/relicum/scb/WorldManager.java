package com.relicum.scb;

import com.relicum.scb.configs.WorldConfig;
import com.relicum.scb.objects.world.WorldConfigurator;
import com.relicum.scb.types.SkyBrosApi;
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

    private static WorldConfigurator bukkitConfig;

    private WorldConfig config;

    private SCB plugin;

    private WorldCreator template;

    private Map<String, Object> worldSettings;

    //Set PVP to True
    //Stop pvp in lobbies
    //Difficulty Hard
    //GameMode Adventure
    //Set Default Gamemode
    //Set player time  to midday (if mixed mode)
    //Disable bed spawn
    //Keep time at midday
    //Set weather to clear 9999999
    //Stop any rain or thunder
    //nether to false
    //end to false
    //fly enabled
    //npcs false
    //view distance to 7
    //player timeout to 5 mins
    //firsttime players
    //set world spawns
    //restrict commands Lobby and Game


    public WorldManager() {
        this.plugin = SkyBrosApi.getSCB();
        defaultGenerate = this.plugin.getConfig().getBoolean("generateDefaultWorld");
        this.config = SkyBrosApi.getSettingsManager2().getWorldConfig();


        this.worldSettings = this.config.getConfig().getConfigurationSection("worldSettings").getValues(true);

      /*  try{
            WorldConfigurator.setBukkitConfig("bukkit.yml");
            if(!WorldConfigurator.setBukkit()){
                System.out.println("Error occurred while writing to bukkit.yml");
            }else{
                System.out.println("The bukkit.yml has been modified successfully");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }*/

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
        } catch (Exception e) {
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

        } catch (Exception e) {
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
        } catch (NullPointerException e) {
            e.printStackTrace();
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        world.setSpawnLocation(25, 30, 1);
        world.setKeepSpawnInMemory(true);
        world.getSpawnLocation().getWorld().getChunkAt(world.getSpawnLocation()).load();
        Block block = world.getBlockAt(0, 64, 0);
        block.getState().setType(Material.GOLD_BLOCK);
        block.getState().update(true);
        world.setAutoSave(true);
        world.setDifficulty(Difficulty.HARD);
        world.setStorm(false);
        world.setThundering(false);
        world.setWeatherDuration(9999999);
        world.setTime(6000);
        plugin.getServer().setDefaultGameMode(GameMode.ADVENTURE);
        world.setGameRuleValue("doDaylightCycle", "false");
        world.save();
        System.out.println("Settings applied for  " + name + " has been successful");
    }


    private Location convertToLocation(String l) {

        return null;
    }


    private long randomSeed() {

        Random random = new Random();
        return random.nextLong();
    }

    public void setMainProperties() {
        Map<String, Object> st = config.getConfig().getConfigurationSection("mainWorld").getValues(true);

    }
}
