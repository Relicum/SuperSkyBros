package com.relicum.scb.commands;

import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * Base class for Sub clist Extend this class and implement the required abstract methods
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class SubBase implements PluginIdentifiableCommand {

    public String descriptionModePrefix;

    public String mNode;

    public String mdescription;

    public String usageMessage;

    public String perm;

    public Integer numArgs;

    public String label;

    public String cmdd;


    /**
     * Default Constructor
     */
    public SubBase() {

        descriptionModePrefix = "command.description.";
        setmDescription();
        mdescription = descriptionModePrefix + mNode;
        usageMessage = setUsage();
        perm = setPermission();
        numArgs = setNumArgs();
        label = setLabel();
        cmdd = setCmd();

    }


    /**
     * Returns the Command Description
     *
     * @return String
     */

    public final String getDescription() {

        return mdescription;
    }


    /**
     * Returns the number of arguments that should be sent
     *
     * @return Integer
     */
    public Integer getNumArgs() {

        return numArgs;
    }


    /**
     * Returns the clist Permission required
     *
     * @return String
     */
    public String getPerm() {

        return perm;
    }


    /**
     * Returns the command Usage
     *
     * @return String
     */
    public String getUseage() {

        return usageMessage;
    }


    /**
     * Get the command
     *
     * @return String
     */
    public String getCmd() {

        return cmdd;
    }


    /**
     * Returns the label
     *
     * @return String
     */
    public String getLabel() {

        return label;
    }


    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    public abstract boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException;

    /**
     * Simplify set this function to set the field mNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    public abstract void setmDescription();

/*	*//**
     Simply set this to return the clist Description

     @return String
     *//*
    public abstract String setDescription();*/


    /**
     * Simply set this to return the the number of arguments The command should receive
     *
     * @return Integer
     */
    public abstract Integer setNumArgs();

    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    public abstract String setPermission();

    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    public abstract String setUsage();

    /**
     * Set this to the label of the command
     *
     * @return String
     */
    public abstract String setLabel();

    /**
     * Set com
     *
     * @return String
     */
    public abstract String setCmd();

    /**
     Get a formatted message to send to the users set 2nd Param to 1 if command succeeded or 2 if it failed

     @param i        Integer
     @param messPath String
     @return String
     */
    /*public String sendMessage(String messPath, Integer i) {
        String path = "command.message.";
		path += messPath;
		if (i == null || i != 1) {
			return MessageManager.getInstance().getErrorMessage(path);
		}
		return MessageManager.getInstance().getAdminMessage(path);

	}*/

}
