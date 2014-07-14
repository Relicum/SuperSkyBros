package com.relicum.scb.commands;

import com.relicum.scb.types.SkyApi;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class listlobbyspawn extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) {
        return false; // To change body of implemented methods use File |
        // Settings | File Templates.
    }

    /**
     * Simplify set this function to set the field descNode with the commands
     * description will come from in the messages.yml file You do not need to
     * enter the full node as it will be prefixed for you. Eg is the full node
     * is command.description.createarena you only need to set this to
     * createarena
     */
    @Override
    public void setmDescription() {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    /**
     * Simply set this to return the the number of arguments The command should
     * receive
     *
     * @return Integer
     */
    @Override
    public Integer setNumArgs() {
        return null; // To change body of implemented methods use File |
        // Settings | File Templates.
    }

    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    @Override
    public String setPermission() {
        return null; // To change body of implemented methods use File |
        // Settings | File Templates.
    }

    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return null; // To change body of implemented methods use File |
        // Settings | File Templates.
    }

    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return null; // To change body of implemented methods use File |
        // Settings | File Templates.
    }

    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return null; // To change body of implemented methods use File |
        // Settings | File Templates.
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
