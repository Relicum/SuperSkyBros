package com.relicum.scb;

import com.relicum.scb.configs.WorldConfig;
import com.relicum.scb.types.SkyApi;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.metadata.MetadataStoreBase;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * The type World manager.
 */
public class WorldManager {

    private final WorldType defaultWorldType;
    private final Environment defaultEnvironment;
    private final boolean generateStructures;
    private final String worldGenerator;
    public WorldConfig config;
    private WorldCreator template;
    private World defaultWorld;
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


    /**
     * Instantiates a new World manager.
     */
    public WorldManager() {
        this.config = SkyApi.getSm().getWorldConfig();
        defaultEnvironment = (Environment.valueOf(this.getConfig().getString("default.environment")));
        defaultWorldType = (WorldType.valueOf(this.getConfig().getString("default.type")));
        generateStructures = this.getConfig().getBoolean("default.structures");
        worldGenerator = this.getConfig().getString("default.generator");


        SkyApi.getCMsg().INFO("WorldGenerator Successfully Loaded");


    }

    public ConfigurationSection getDefaultWorldSettings() {
        return this.config.getConfig().getConfigurationSection("defaultWorldSettings");

    }

    public void loadEnabledWorlds() {

        if (config.getConfig().contains("worlds")) {

            Set<String> st = config.getConfig().getConfigurationSection("worlds").getKeys(false);
            for (String e : st) {
                if (config.getConfig().getBoolean("worlds." + e + ".enable")) {
                    try {
                        SkyApi.getSCB().getWorldCreator(e).createWorld();
                        SkyApi.getCMsg().INFO("World " + e + " was been loaded");
                    } catch (Exception e1) {
                        SkyApi.getCMsg().SERVE("World " + e + " could not be loaded see stack trace");
                        e1.printStackTrace();
                    }

                }
            }

            return;
        }

        SkyApi.getCMsg().INFO("No worlds found");
    }


    /**
     * Gets default world handle.
     *
     * @param name the name
     */
    public void getDefaultWorldHandle(String name) {
        WorldCreator worldCreator = new WorldCreator(name);
        try {
            this.defaultWorld = worldCreator.createWorld();
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.broadcast(ChatColor.RED + "Error loading the main load check logs", "bukkit.broadcast.admin");
        }
        this.defaultWorld = this.applyWorldDefaultSettings(name);

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
        worldCreator.generator("SuperSkyBros");
        worldCreator.seed(this.randomSeed());

        return worldCreator;
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    public FileConfiguration getConfig() {
        return config.getConfig();
    }

    /**
     * Sets config.
     *
     * @param config the config
     */
    public void setConfig(WorldConfig config) {
        this.config = config;
    }

    /**
     * Gets configs.
     *
     * @return the configs
     */
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
            SkyApi.getCMsg().INFO("Attempting to create template World");
            World world = this.template.createWorld();
            MetadataStoreBase meta = new MetadataStoreBase() {

                @Override
                protected String disambiguate(Object o, String s) {
                    return null;
                }
            };

            List<MetadataValue> wm = world.getMetadata("456");
            System.out.println(wm.toString());


            SkyApi.getCMsg().INFO("Finished creating template World");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Is generate default world.
     *
     * @return the boolean
     */
    public boolean isGenerateDefaultWorld() {
        return SkyApi.getSm().isGenerateDefaultWorld();
    }

    /**
     * Set generate default world.
     *
     * @param b the b
     */
    public void setGenerateDefaultWorld(boolean b) {
        SkyApi.getSm().setUseWorldManagement(b);
    }


    public World createNewWorld(String name) {

        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(getDefaultEnvironment())
                .seed(randomSeed())
                .generateStructures(isGenerateStructures())
                .generator(worldGenerator)
                .type(defaultWorldType);

        World world;
        try {
            world = worldCreator.createWorld();
            SkyApi.getSm().addWorldToWhiteList(name);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return world;
    }

    /**
     * Apply world default settings.
     *
     * @param name the name
     * @return the world
     */
    public World applyWorldDefaultSettings(String name) {

        World world = null;
        ConfigurationSection wd = this.config.getConfig().getConfigurationSection("defaultWorldSettings");

        try {
            world = Bukkit.getServer().getWorld(name);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //ConfigurationSection spawn = wd.getConfigurationSection("spawnLocation");
        ConfigurationSection blockSpawn = wd.getConfigurationSection("spawnBlockLocation");
        ConfigurationSection gameRule = wd.getConfigurationSection("gameRules");

        world.loadChunk(0, 0, true);
        world.setSpawnLocation(wd.getInt("spawnLocation.x"), wd.getInt("spawnLocation.y"), wd.getInt("spawnLocation.z"));
        world.setKeepSpawnInMemory(wd.getBoolean("keepSpawnInMemory"));

        world.getSpawnLocation().getWorld().getChunkAt(world.getSpawnLocation()).load();
        Block block = world.getBlockAt(blockSpawn.getInt("x"), blockSpawn.getInt("y"), blockSpawn.getInt("z"));
        block.getState().setType(Material.valueOf(wd.getString("spawnBlockMaterial")));
        block.getState().update(true);
        world.setAutoSave(wd.getBoolean("autoSave"));
        world.setDifficulty(Difficulty.valueOf(wd.getString("difficulty")));
        world.setStorm(wd.getBoolean("setStorm"));
        world.setThundering(wd.getBoolean("setThundering"));
        world.setWeatherDuration(9999999);
        world.setTime(wd.getLong("setTime"));

        world.setGameRuleValue("doDaylightCycle", gameRule.getString("doDaylightCycle"));
        world.setGameRuleValue("doFireTick", gameRule.getString("doFireTick"));
        world.setGameRuleValue("mobGriefing", gameRule.getString("mobGriefing"));
        world.setGameRuleValue("mobSpawning", gameRule.getString("doMobSpawning"));

        world.save();

        SkyApi.getCMsg().INFO("Settings applied for  " + name + " has been successful");
        return world;
    }

    private Location convertToLocation(String l) {

        return null;
    }


    /**
     * Fet a new Random seed for the world
     *
     * @return Randon Long as a world seed
     */
    public long randomSeed() {

        Random random = new Random();
        return random.nextLong();
    }

    /**
     * Gets default world type.
     *
     * @return the default world type
     */
    public WorldType getDefaultWorldType() {
        return defaultWorldType;
    }

    /**
     * Gets default environment.
     *
     * @return the default environment
     */
    public Environment getDefaultEnvironment() {
        return defaultEnvironment;
    }

    /**
     * Is generate structures.
     *
     * @return the boolean
     */
    public boolean isGenerateStructures() {
        return generateStructures;
    }

    /**
     * Gets world generator.
     *
     * @return the world generator
     */
    public String getWorldGenerator() {
        return worldGenerator;
    }

}
