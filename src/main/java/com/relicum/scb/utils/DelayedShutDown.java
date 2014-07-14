package com.relicum.scb.utils;

import com.relicum.scb.types.SkyApi;
import org.bukkit.Bukkit;

/**
 * SuperSkyBros
 * Simple and quick method to do a delayed shutdown
 *
 * @author Relicum
 * @version 0.1
 */
public class DelayedShutDown {


    public static void shutDown() {

        Bukkit.getServer().getScheduler().runTaskLater(SkyApi.getSCB(), new Runnable() {
            @Override
            public void run() {

                Bukkit.getServer().shutdown();
            }
        }, 120l);
    }
}
