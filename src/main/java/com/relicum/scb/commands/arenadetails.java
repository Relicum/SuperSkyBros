/**
 * Name: arenadetails.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class arenadetails extends SubBase {
    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {
        player.sendMessage(ChatColor.RED + "Command is still being written");
        return true;
    }

    /**
     * Simplify set this function to set the field mNode with the commands
     * description will come from in the messages.yml file You do not need to
     * enter the full node as it will be prefixed for you. Eg is the full node
     * is command.description.createarena you only need to set this to
     * createarena
     */
    @Override
    public void setmDescription() {
        mNode = "arenadetails";
    }

    /**
     * Simply set this to return the the number of arguments The command should
     * receive
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
        return "ssba.admin.arenadetails";
    }

    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba arenadetails [arenaid]";
    }

    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba arenadetails";
    }

    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba arenadetails";
    }

    @Override
    public Plugin getPlugin() {
        return null;
    }
}
