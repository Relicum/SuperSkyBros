package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import org.bukkit.entity.Player;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class setlobbyprotection extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) {

        String toSet = args[0].toLowerCase();

        if (!toSet.equalsIgnoreCase("true") && (!toSet.equalsIgnoreCase("false"))) {
            player.sendMessage(SCB.MM.getErrorMessage("command.message.autoProtectInvaildArg"));
            System.out.println("The arg passed was " + args[0]);
            return true;
        }
        boolean current = SCB.getInstance().getConfig().getBoolean("enableLobbyProtection");
        boolean setTo = false;
        if (toSet.equalsIgnoreCase("true")) setTo = true;

        if (setTo == current) {

            String res = SCB.getMessageManager().getAdminMessage("command.message.autoProtectAlreadySet");
            String re = res.replace("%nn%", toSet);
            player.sendMessage(re);

            return true;

        }
        //TODO Still requires a reload when setting protection to off
        SCB.getInstance().getConfig().set("enableLobbyProtection", setTo);
        if (!setTo) {
            SCB.getInstance().unloadLobbyEvents();

        } else {

            SCB.getInstance().loadLobbyEvents();
            System.out.println("New Events Registered");
        }


        SCB.getInstance().saveConfig();
        SCB.getInstance().reloadConfig();


        //SCB.getInstance().LBS.saveSpawnsFile();


        String res = SCB.getMessageManager().getAdminMessage("command.message.autoProtectSuccess");
        String re = res.replace("%nn%", toSet);
        player.sendMessage(re);

        return true;
    }


    /**
     * Simplify set this function to set the field mNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = "setlobbyprotection";
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
        return "ssba.admin.setlobbyprotection";
    }


    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba setlobbyprotection [true:false]";
    }


    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba setlobbyprotection";
    }


    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba setlobbyprotection";
    }
}
