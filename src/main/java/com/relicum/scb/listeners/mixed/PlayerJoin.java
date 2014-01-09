package com.relicum.scb.listeners.mixed;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * SuperSkyBros
 * First Created 06/01/14
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerJoin implements Listener {

    public PlayerJoin() {
    }


    public void playerJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        player.sendMessage(ChatColor.BLUE + "Logged in when server is in mixed mode");

    }
}
