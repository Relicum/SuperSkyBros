package com.relicum.scb.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * SuperSkyBros First Created 13/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerLogin implements Listener {


    public void preLogin(AsyncPlayerPreLoginEvent event) {


        if (Bukkit.getPlayerExact(event.getName()) == null) {
            System.out.println(event.getName() + " has not played before");
        } else {

            System.out.println("Player found");
        }

    }

}
