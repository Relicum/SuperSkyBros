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
 * @version 0.1
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
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("createWorldNameError").replace("%NAME%", name));
            return true;
        }

        if (Bukkit.getWorld(name) != null) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("createWorldAlreadyExists").replace("%NAME%", name));
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
        player.sendMessage(ChatColor.GREEN + "World Created Successfully");

        player.sendMessage(SkyApi.getMessageManager().getAdminMessage("createWorldSuccess"));
        player.sendMessage(SkyApi.getMessageManager().getMessage("createWorldStartSettingss"));
        final World world1 = Bukkit.getWorld(name);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SkyApi.getSCB(), new Runnable() {
            @Override
            public void run() {

                //ConfigurationSection spawn = ws.getConfigurationSection("spawnLocation");
                ConfigurationSection blockSpawn = ws.getConfigurationSection("spawnBlockLocation");
                ConfigurationSection gameRule = ws.getConfigurationSection("gameRules");

                world1.loadChunk(0, 0, true);

                BlockState block = world1.getBlockAt(ws.getInt("spawnBlockLocation.x"), ws.getInt("spawnBlockLocation.y"), ws.getInt("spawnBlockLocation.z")).getState();
                SkyApi.getCMsg().INFO("The block is set to " + block.getType().toString());
                block.setType(Material.valueOf(ws.getString("spawnBlockMaterial")));
                block.update(true);
                SkyApi.getCMsg().INFO("The block is now set to " + block.getType().toString());


                world1.setAutoSave(ws.getBoolean("autoSave"));
                world1.setDifficulty(Difficulty.valueOf(ws.getString("difficulty")));
                world1.setStorm(ws.getBoolean("setStorm"));
                world1.setThundering(ws.getBoolean("setThundering"));
                world1.setWeatherDuration(9999999);
                world1.setTime(ws.getLong("setTime"));

                world1.setGameRuleValue("doDaylightCycle", gameRule.getString("doDaylightCycle"));
                world1.setGameRuleValue("doFireTick", gameRule.getString("doFireTick"));
                world1.setGameRuleValue("mobGriefing", gameRule.getString("mobGriefing"));
                world1.setGameRuleValue("mobSpawning", gameRule.getString("doMobSpawning"));

            }
        }, 20l);

        SkyApi.getCMsg().INFO("Settings applied for  " + name + " has been successful");

        Location location = world.getSpawnLocation();
        location.setPitch(0.0f);
        location.setYaw(90.0f);
        SerializedLocation sl = new SerializedLocation(location);

        SkyApi.getWorldManager().getConfig().set("worlds." + name + ".spawnLocation", sl);

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
