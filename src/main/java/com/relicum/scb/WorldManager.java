package com.relicum.scb;

import com.relicum.scb.configs.WorldConfig;
import com.relicum.scb.types.SkyApi;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.metadata.MetadataStoreBase;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.Random;

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


        SkyApi.getSCB().getLogger().info("WorldGenerator Successfully Loaded");


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
        worldCreator.generator("CleanroomGenerator:.");
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

        try {
            world = Bukkit.getServer().getWorld(name);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        world.loadChunk(0, 0, true);
        world.setSpawnLocation(0, 32, 0);
        world.setKeepSpawnInMemory(true);

        world.getSpawnLocation().getWorld().getChunkAt(world.getSpawnLocation()).load();
        Block block = world.getBlockAt(0, 31, 0);
        block.getState().setType(Material.GOLD_BLOCK);
        block.getState().update(true);
        world.setAutoSave(true);
        world.setDifficulty(Difficulty.HARD);
        world.setStorm(false);
        world.setThundering(false);
        world.setWeatherDuration(9999999);
        world.setTime(6000);
        SkyApi.getSCB().getServer().setDefaultGameMode(GameMode.ADVENTURE);
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doFireTick", "false");
        world.setGameRuleValue("mobGriefing", "false");
        world.setGameRuleValue("mobSpawning", "false");

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
    private long randomSeed() {

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
