package com.relicum.scb.commands;

import com.relicum.scb.types.SkyApi;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

/**
 * SuperSkyBros First Created 24/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class lobbytp extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {

        if (!SkyApi.getSm().getLobbyConfig().getConfig().getBoolean("LOBBYSET")) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.lobbytpFail"));
            return true;
        }

        Location loc = SkyApi.getLobbyManager().getLobbyRg().getLobbySpawn();

        SkyApi.getLobbyManager().teleportToLobby(player, loc);

        player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.lobbytpSuccess"));

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
        mNode = "lobbytp";
    }

    /**
     * Simply set this to return the the number of arguments The command should
     * receive
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
        return "ssba.admin.lobbytp";
    }

    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba lobbytp";
    }

    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba lobbytp";
    }

    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba lobbytp";
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
