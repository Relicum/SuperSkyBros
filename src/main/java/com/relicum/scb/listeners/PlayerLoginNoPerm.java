package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.types.SkyApi;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * SuperSkyBros
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerLoginNoPerm implements Listener {

    public SCB plugin;

    public PlayerLoginNoPerm(JavaPlugin p) {
        this.plugin = (SCB) p;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void preLogin(PlayerLoginEvent e) {

        if (plugin.getConfig().getBoolean("autoJoinLobby")) {
            if (!e.getPlayer().hasPermission("ssb.player.join")) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, SkyApi.getMessageManager().getRawMessage("system.kickJoinNoPerm"));

            }
        }
    }
}
