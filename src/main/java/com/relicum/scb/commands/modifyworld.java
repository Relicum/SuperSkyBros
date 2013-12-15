package com.relicum.scb.commands;

import com.relicum.scb.types.SkyApi;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

/**
 * SuperSkyBros
 * First Created 10/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class modifyworld extends SubBase {
    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {
        player.sendMessage(ChatColor.GREEN + "This command is still to be written");
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
        return 2;
    }

    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssbw.admin." + this.getClass().getSimpleName();
    }

    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssbw modifyworld [world] [load|unload|remove|delete]";
    }

    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssbw modifyworld";
    }

    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssbw modifyworld";
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
