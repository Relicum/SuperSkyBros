package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.types.SkyApi;
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
        plugin = SkyApi.getSCB();
    }


    @EventHandler
    public void worldLoad(WorldLoadEvent e) {

        SkyApi.getWorldManager().applyWorldDefaultSettings(e.getWorld().getName());
    }

}
