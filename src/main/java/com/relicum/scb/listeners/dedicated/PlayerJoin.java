package com.relicum.scb.listeners.dedicated;

import com.relicum.scb.PlayerLoginManager;
import com.relicum.scb.PlayerSettings;
import com.relicum.scb.classes.PlayerType;
import com.relicum.scb.configs.PlayerConfig;
import com.relicum.scb.configs.ServerStatus;
import com.relicum.scb.types.SkyApi;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Player Join Listener when server is in dedicated mode
 * First Created 06/01/14
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerJoin implements Listener {

    public PlayerSettings relicum;
    public PlayerConfig relfile;
    public PlayerSettings playerSettings;
    public ServerStatus status;

    public PlayerJoin(ServerStatus s) {
        this.status = s;
    }


    public void playerJoin(PlayerJoinEvent e) {


        Player p = e.getPlayer();


        if (!p.isOp() && (!status.name().equalsIgnoreCase("READY"))) {

            p.kickPlayer("You have been kicked as the server is not ready to be used");
        } else {
            p.sendMessage("The server status is currently set to " + status.name() + " meaning you need to " + status.name());
        }

        if (!PlayerLoginManager.hasProfile(p.getName())) {

            relfile = new PlayerConfig(PlayerLoginManager.profilePath(p.getName()));
            relfile.saveConfig();

            SkyApi.getCMsg().INFO("File not found creating one");

            playerSettings = new PlayerSettings();
            playerSettings.setPlayerName(p.getName());
            playerSettings.setPlayerType(PlayerType.PLAYER);
            playerSettings.setInLobby(true);


            relfile.getConfig().set(p.getName(), playerSettings);
            relfile.saveConfig();
            relfile.reloadConfig();

            SkyApi.getCMsg().INFO("New Player Settings Stored");
        } else if (PlayerLoginManager.hasProfile(p.getName())) {
            SkyApi.getCMsg().INFO("Settings found for " + p.getName());
            relfile = new PlayerConfig(PlayerLoginManager.profilePath(p.getName()));
            relfile.saveConfig();
            PlayerSettings settings = (PlayerSettings) relfile.getConfig().get(p.getName());
            p.sendMessage("Play type is " + settings.getPlayerType().name());
            p.sendMessage("Lobby status is " + settings.isInLobby());
        }

        e.setJoinMessage("");


        String[] strings = new String[2];
        strings[0] = "    " + "\u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<";
        strings[1] = ChatColor.GOLD + p.getName() + " welcome to the server";

        p.sendMessage(strings);


    }
}
