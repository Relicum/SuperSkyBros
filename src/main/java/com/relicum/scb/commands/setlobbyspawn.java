package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.objects.LobbySpawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.2
 */
public class setlobbyspawn extends SubBase {

    /**
     * The Hash list of spawn protection.
     */
    private List<String> hashList = new ArrayList<>();


    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) {

        Location loc = player.getLocation();
        LobbySpawn LBS = new LobbySpawn(loc);
        LBS.setPerm("ssb.player.join");
        LBS.setName("LOBBYSPAWN");


        if (LBS.save()) {
            player.sendMessage(SCB.MM.getAdminMessage("command.message.setLobbySucceeded"));
        } else {

            player.sendMessage(SCB.MM.getErrorMessage("command.message.failedToSetLobby"));
        }

        return true;
    }


    /**
     * Simplify set this function to set the field descNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = "setlobbyspawn";
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
        return "ssba.admin.setlobbyspawn";
    }


    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba setlobbyspawn";
    }


    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba setlobbyspawn";
    }


    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba setlobbyspawn";
    }


    @Override
    public Plugin getPlugin() {
        return SCB.getInstance();
    }
}
