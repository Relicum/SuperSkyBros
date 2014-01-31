package com.relicum.scb.commands;

import java.io.IOException;
import com.relicum.scb.BukkitInterface;
import com.relicum.scb.types.SkyApi;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * SuperSkyBros First Created 08/10/13
 * 
 * @author Relicum
 * @version 0.1
 */
public class blacklist extends SubBase {

    /**
     * @param player Player
     * @param args String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {

        if ((!args[0].equalsIgnoreCase("add")) && (!args[0].equalsIgnoreCase("remove"))) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.blacklistInvalidArg"));
            return true;
        }

        if (args[0].equalsIgnoreCase("add")) {

            if (BukkitInterface.getWorld(args[1]) != null) {
                if (SkyApi.getSm().addWorldToBlackList(args[1])) {
                    SkyApi.getSCB().saveConfig();
                    SkyApi.getSCB().reloadConfig();
                    player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.blacklistSuccess").replace("%WORLD%", args[1]).replace("%ARG%", "ADDED"));
                    return true;
                }
            } else {
                player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.blacklistInvalidWorld"));
            }
        }
        if (args[0].equalsIgnoreCase("remove")) {
            if (SkyApi.getSm().addWorldToWhiteList(args[1])) {
                SkyApi.getSCB().saveConfig();
                SkyApi.getSCB().reloadConfig();
                player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.blacklistSuccess").replace("%WORLD%", args[1]).replace("%ARG%", "REMOVED"));
                return true;
            } else {
                player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.blacklistNotFound").replace("%WORLD%", args[1]));
            }
        }

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
        mNode = "blacklist";
    }

    /**
     * Simply set this to return the the number of arguments The command should
     * receive
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

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
