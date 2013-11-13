package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.List;

/**
 * SuperSkyBros First Created 08/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class blacklisted extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {

        List<String> blacklist = SCB.getInstance().getBlackList();
        for ( String s : blacklist ) {
            player.sendMessage(SCB.getMessageManager().getAdminMessage("command.message.blacklistedWorlds").replace("%WORLD%", s));
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
        return 0;
    }


    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssba.admin.blacklisted";
    }


    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba blacklisted";
    }


    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba blacklisted";
    }


    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba blacklisted";
    }


    @Override
    public Plugin getPlugin() {
        return SCB.getInstance();
    }
}
