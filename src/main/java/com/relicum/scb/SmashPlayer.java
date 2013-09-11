package com.relicum.scb;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Smash Player
 *
 * @author Relicum
 * @version 0.1
 */
public class SmashPlayer {


    private String p;


    public SmashPlayer(Player player) {
        this.p = player.getName();
    }

    public Player getPlayer() {
        return Bukkit.getPlayerExact(p);
    }

    public static SmashPlayer wrap(Player player) {
        return new SmashPlayer(player);
    }
}
