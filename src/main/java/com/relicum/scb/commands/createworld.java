package com.relicum.scb.commands;

import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.SerializedLocation;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

/**
 * SuperSkyBros
 * First Created 05/12/13
 *
 * @author Relicum
 * @version 0.3
 */
public class createworld extends SubBase {
    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {
        final String name = ChatColor.stripColor(args[0]);
        if (name.length() < 4) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.createWorldNameError").replace("%NAME%", name));
            return true;
        }

        if (Bukkit.getWorld(name) != null) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.createWorldAlreadyExists").replace("%NAME%", name));
            return true;
        }
        SkyApi.getCMsg().INFO("Attempting to create the new world " + name);
        World world;
        WorldCreator worldCreator;
        final ConfigurationSection ws = SkyApi.getWorldManager().getDefaultWorldSettings();
        try {

            worldCreator = SkyApi.getSCB().getWorldCreator(name);
            worldCreator.type(WorldType.valueOf(ws.getString("creator.type")));
            worldCreator.environment(World.Environment.valueOf(ws.getString("creator.environment")));
            worldCreator.generateStructures(ws.getBoolean("creator.structures"));
            worldCreator.generator(ws.getString("creator.generator") + ":.");
            worldCreator.seed(SkyApi.getWorldManager().randomSeed());

            world = worldCreator.createWorld();
            world.setSpawnLocation(ws.getInt("spawnLocation.x"), ws.getInt("spawnLocation.y"), ws.getInt("spawnLocation.z"));
            world.setKeepSpawnInMemory(ws.getBoolean("keepSpawnInMemory"));
            SkyApi.getSm().addWorldToWhiteList(name);


        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("createWorldError"));
            return true;
        }


        player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.createWorldSuccess").replace("%WORLD%", name));
        player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.createWorldStartSettings").replace("%NAME%", name));

        //ConfigurationSection spawn = ws.getConfigurationSection("spawnLocation");
        ConfigurationSection blockSpawn = ws.getConfigurationSection("spawnBlockLocation");
        ConfigurationSection gameRule = ws.getConfigurationSection("gameRules");

        world.loadChunk(0, 0, true);

        BlockState block = world.getBlockAt(ws.getInt("spawnBlockLocation.x"), ws.getInt("spawnBlockLocation.y"), ws.getInt("spawnBlockLocation.z")).getState();

        block.setType(Material.valueOf(ws.getString("spawnBlockMaterial")));
        block.update(true);
        world.setPVP(ws.getBoolean("pvp"));
        world.setAutoSave(true);
        world.setDifficulty(Difficulty.valueOf(ws.getString("difficulty")));
        world.setStorm(ws.getBoolean("setStorm"));
        world.setThundering(ws.getBoolean("setThundering"));
        world.setWeatherDuration(9999999);
        world.setTime(ws.getLong("setTime"));
        world.setGameRuleValue("doDaylightCycle", gameRule.getString("doDaylightCycle"));
        world.setGameRuleValue("doFireTick", gameRule.getString("doFireTick"));
        world.setGameRuleValue("mobGriefing", gameRule.getString("mobGriefing"));
        world.setGameRuleValue("doMobSpawning", gameRule.getString("doMobSpawning"));


        world.save();
        SkyApi.getCMsg().INFO("Settings applied for  " + name + " has been successful");
        ConfigurationSection newWorld = SkyApi.getWorldManager().getConfig().createSection("worlds." + name);
        Location location = world.getSpawnLocation();
        location.setPitch(0.0f);
        location.setYaw(90.0f);
        SerializedLocation sl = new SerializedLocation(location);
        newWorld.set("enable", true);
        newWorld.set("pvp", world.getPVP());
        newWorld.set("doBlockBreak", false);
        newWorld.set("doBedSpawning", false);
        newWorld.set("keepSpawnInMemory", world.getKeepSpawnInMemory());
        newWorld.set("time", world.getTime());
        newWorld.set("difficulty", world.getDifficulty().name());
        newWorld.set("autoSave", world.isAutoSave());
        newWorld.set("spawnLocation", sl);
        newWorld.set("gamerules.doDaylightCycle", world.getGameRuleValue("doDaylightCycle"));
        newWorld.set("gamerules.doFireTick", world.getGameRuleValue("doFireTick"));
        newWorld.set("gamerules.mobGriefing", world.getGameRuleValue("mobGriefing"));
        newWorld.set("gamerules.doMobSpawning", world.getGameRuleValue("doMobSpawning"));
        newWorld.set("weather.setThundering", world.isThundering());
        newWorld.set("weather.setStorm", world.hasStorm());
        newWorld.set("weather.setWeatherDuration", world.getWeatherDuration());
        newWorld.set("properties.WorldType", world.getWorldType().getName());
        newWorld.set("properties.Environment", world.getEnvironment().name());
        newWorld.set("properties.generateStructures", world.canGenerateStructures());
        newWorld.set("properties.generator", ws.getString("creator.generator") + ":.");
        newWorld.set("properties.seed", world.getSeed());
        newWorld.set("spawning.monsters.enable", world.getAllowMonsters());
        newWorld.set("spawning.monsters.limit", world.getMonsterSpawnLimit());
        newWorld.set("spawning.monsters.ticksPer", world.getTicksPerMonsterSpawns());
        newWorld.set("spawning.animals.enable", world.getAllowAnimals());
        newWorld.set("spawning.animals.limit", world.getAnimalSpawnLimit());
        newWorld.set("spawning.animals.ticksPer", world.getTicksPerAnimalSpawns());

        player.sendMessage(SkyApi.getMessageManager().getAdminMessage("world.messages.restartAdvisable"));

        SkyApi.getWorldManager().getConfigs().saveConfig();
        SkyApi.getSCB().saveConfig();

        return true;
    }

    /**
     * Simplify set this function to set the field mNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = this.getClass().getSimpleName();
    }

    /**
     * Simply set this to return the the number of arguments The command should receive
     *
     * @return Integer
     */
    @Override
    public Integer setNumArgs() {
        return 1;
    }

    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssbw.admin.createworld";
    }

    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssbw createworld [name]";
    }

    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssbw createworld";
    }

    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssbw createworld";
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
