package com.relicum.scb.commands;

import com.relicum.scb.types.SkyApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
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
        String name = ChatColor.stripColor(args[0]);
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
        try {
            world = SkyApi.getWorldManager().createNewWorld(name);
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("createWorldError"));
            return true;
        }
        player.sendMessage(SkyApi.getMessageManager().getAdminMessage("createWorldSuccess").replace("%NAME%", name));
        player.sendMessage(SkyApi.getMessageManager().getMessage("createWorldStartSettingss").replace("%NAME%", name));

        if (world.loadChunk(0, 0, true)) {
            BlockState blockState = world.getBlockAt(0, 31, 0).getState();
            SkyApi.getCMsg().INFO("The block is currently " + blockState.getType().toString());
            blockState.setType(Material.GOLD_BLOCK);
            blockState.update(true);
            SkyApi.getCMsg().INFO("The block has now been set as a " + blockState.getType().toString());
            world.setSpawnLocation(0, 32, 0);
            world.setKeepSpawnInMemory(true);
            world.setAutoSave(true);
            try {
                Thread.sleep(200l);
            } catch (InterruptedException e) {
                e.printStackTrace();
                player.sendMessage(SkyApi.getMessageManager().getErrorMessage("createWorldError"));
                return true;
            }
            world.setPVP(true);
            world.setTime(6000);
            world.setWeatherDuration(999999);
            world.setThundering(false);
            world.setStorm(false);
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setGameRuleValue("doFireTick", "false");
            world.setGameRuleValue("doMobSpawning", "false");
            world.setGameRuleValue("mobGriefing", "false");
            world.save();
            world.setAutoSave(true);
            SkyApi.getCMsg().INFO("All world settings and spawn point have been ve for world " + name);
            player.sendMessage(SkyApi.getMessageManager().getAdminMessage("applyWorldSettings").replace("%NAME%", name));
        }

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
