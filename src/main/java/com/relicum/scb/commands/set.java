package com.relicum.scb.commands;

import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.SerializedLocation;
import com.relicum.scb.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

/**
 * SuperSkyBros
 * Command to set settings in a world
 *
 * @author Relicum
 * @version 0.1
 */
public class set extends SubBase {
    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {
        World world;

        if ((world = Bukkit.getWorld(args[0])) == null) {
            world = player.getWorld();
        }

        if (args[1].equalsIgnoreCase("spawn")) {

            world.setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
            world.setKeepSpawnInMemory(true);

            Location location = world.getSpawnLocation();
            location.setPitch((float) Math.round(player.getLocation().getPitch()));
            location.setYaw(StringUtils.getDirection(player.getLocation().getYaw()));
            SerializedLocation sl = new SerializedLocation(location);

            //location = new SerializedLocation(world.getName(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), StringUtils.getDirection(player.getLocation().getYaw()), (float) Math.round(player.getLocation().getPitch()));
            SkyApi.getSm().setSerializedWorldSpawnLocation(sl, world.getName());
            player.sendMessage(SkyApi.getMessageManager().getAdminMessage("world.messages.setSpawnSuccess")
                    .replace("%X%", String.valueOf(player.getLocation().getBlockX()))
                    .replace("%Y%", String.valueOf(player.getLocation().getBlockY()))
                    .replace("%Z%", String.valueOf(player.getLocation().getBlockZ())));
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
        return 3;
    }

    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssbw.admin.set";
    }

    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssbw set {world] [setting] [value]";
    }

    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssbw set";
    }

    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssbw set";
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
