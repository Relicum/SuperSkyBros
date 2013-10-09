package com.relicum.scb.commands;

import com.relicum.scb.BukkitInterface;
import com.relicum.scb.SCB;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * SuperSkyBros First Created 08/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class blacklist extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {

        if ((!args[0].equalsIgnoreCase("add")) && (!args[0].equalsIgnoreCase("delete"))) {
            player.sendMessage("Error: Invalid argument " + ChatColor.DARK_RED + args[0] + ChatColor.RESET + " either 'add' or 'delete' are valid");
            return true;
        }

        if (args[0].equalsIgnoreCase("add")) {

            if (BukkitInterface.getWorld(args[1]) != null) {
                if (SCB.getInstance().getBlackList().contains(args[1])) {
                    player.sendMessage("World is already on the blacklist");
                    return true;
                }
                SCB.getInstance().getBlackList().add(args[1]);
                SCB.getInstance().getConfig().set("ignoreWorlds", SCB.getInstance().getBlackList());
                SCB.getInstance().saveConfig();
                SCB.getInstance().reloadConfig();
                player.sendMessage("Successfully added " + args[1] + " to ignore list");
                return true;
            } else {
                player.sendMessage("World not valid");
            }
        }
        if (args[0].equalsIgnoreCase("delete")) {
            if (SCB.getInstance().getBlackList().contains(args[1])) {
                SCB.getInstance().getBlackList().remove(args[1]);
                SCB.getInstance().getConfig().set("ignoreWorlds", SCB.getInstance().getBlackList());
                SCB.getInstance().saveConfig();
                SCB.getInstance().reloadConfig();
                player.sendMessage("Successfully removed " + args[1] + " from ignore list");
                return true;
            } else {
                player.sendMessage("World " + args[1] + " was not on the ignore list");
            }
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
        mNode = "blacklist";
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

        return "ssba.admin.blacklist";
    }


    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {

        return "/ssba blacklist [add|remove] <worldname>";
    }


    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba blacklist";
    }


    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba blacklist";
    }
}
