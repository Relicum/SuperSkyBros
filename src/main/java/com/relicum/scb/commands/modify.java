package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Set which arena to modify
 *
 * @author Relicum
 * @version 0.1
 */
public class modify extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) {

        try {
            SCB.getInstance().ARM.setCurrent(Integer.parseInt(args[0]));
            String s = SCB.getMessageManager().getAdminMessage("command.message.modifySuccess");
            String tmp = s.replace("%ID%", args[0]);
            player.sendMessage(tmp);
        }
        catch ( Exception e ) {
            String s = SCB.getMessageManager().getErrorMessage("command.message.modifyException");
            String tmp = s.replace("%ID%", args[0]);
            player.sendMessage(tmp);
            e.printStackTrace();
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
        mNode = "modify";
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
        return "ssba.admin.modify";
    }


    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba modify [arena#]";
    }


    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba modify";
    }


    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba modify";
    }


    @Override
    public Plugin getPlugin() {
        return SCB.getInstance();
    }
}
