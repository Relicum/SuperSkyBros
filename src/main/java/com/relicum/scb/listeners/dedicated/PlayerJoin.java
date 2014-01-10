package com.relicum.scb.listeners.dedicated;

import com.relicum.scb.PlayerLoginManager;
import com.relicum.scb.PlayerSettings;
import com.relicum.scb.classes.PlayerType;
import com.relicum.scb.configs.PlayerConfig;
import com.relicum.scb.configs.ServerStatus;
import com.relicum.scb.objects.PlayerLocation;
import com.relicum.scb.objects.inventory.PlayerDefaults;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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

    public PlayerSettings settings;
    public PlayerConfig playerConfig;
    public PlayerSettings playerSettings;
    public ServerStatus status;

    public PlayerJoin(ServerStatus s) {
        this.status = s;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerJoin(PlayerJoinEvent e) {


        Player p = e.getPlayer();


        if (!PlayerLoginManager.hasProfile(p.getName())) {

            playerConfig = new PlayerConfig(PlayerLoginManager.profilePath(p.getName()));
            playerConfig.saveConfig();

            playerSettings = new PlayerSettings();
            playerSettings.setPlayerName(p.getName());
            playerSettings.setPlayerType(PlayerType.PLAYER);
            playerSettings.setInLobby(true);
            playerSettings.setOp(p.isOp());
            playerSettings.setWorld(p.getWorld().getName());
            playerSettings.setPlayerLocation(PlayerLocation.LOBBY);

            playerConfig.getConfig().set(p.getName(), playerSettings);

            playerConfig.saveConfig();
            playerConfig = null;

        } else if (PlayerLoginManager.hasProfile(p.getName())) {

            playerConfig = new PlayerConfig(PlayerLoginManager.profilePath(p.getName()));

            playerConfig.saveConfig();
            playerSettings = (PlayerSettings) playerConfig.getConfig().get(p.getName());
            playerSettings.setPlayerLocation(PlayerLocation.LOBBY);
            playerConfig.saveConfig();
            playerConfig.reloadConfig();
            playerConfig = null;
        }

        e.setJoinMessage("");

        PlayerDefaults.applyDefaultSettings(p);
        PlayerDefaults.applyLobbyInventory(p);

        String[] strings = new String[3];
        strings[0] = "    " + "\u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<";
        strings[1] = ChatColor.GOLD + p.getName() + " welcome to the server";
        strings[2] = ChatColor.AQUA + "Player game name is " + playerSettings.getPlayerName();

        p.sendMessage(strings);


    }
}
