package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.types.SkyBrosApi;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

/**
 * SuperSkyBros
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldLoad implements Listener {

    private SCB plugin;


    public WorldLoad() {
        plugin = SkyBrosApi.getSCB();
    }


    @EventHandler
    public void worldLoad(WorldLoadEvent e) {

        SkyBrosApi.getWorldManager().applyWorldDefaultSettings(e.getWorld().getName());
    }

}
