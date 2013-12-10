package com.relicum.scb.listeners;

import com.relicum.scb.types.SkyApi;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.List;

/**
 * SuperSkyBros
 * First Created 04/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldListeners implements Listener {

    private final List<String> blacklist;

    private final boolean isDedicated;


    /**
     * Instantiates a new World listeners.
     */
    public WorldListeners() {

        this.blacklist = SkyApi.getSm().blackListed();
        this.isDedicated = SkyApi.getSm().isDedicated();
    }

    /**
     * Is dedicated.
     *
     * @return the true if server is
     */
    public boolean isDedicated() {
        return isDedicated;
    }

    /**
     * Is world blacklist
     *
     * @param String world to check
     * @return the boolean true if it's not blacklisted
     */
    public boolean isNotBlacklist(String w) {
        return !blacklist.contains(w);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void leafDecay(LeavesDecayEvent e) {
        if (isDedicated() || isNotBlacklist(e.getBlock().getWorld().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void weatherChange(WeatherChangeEvent e) {
        if (isDedicated() || isNotBlacklist(e.getWorld().getName())) {
            if (e.toWeatherState()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void thunder(ThunderChangeEvent e) {
        if (isDedicated() || isNotBlacklist(e.getWorld().getName())) {
            if (e.toThunderState()) {
                e.setCancelled(true);
            }
        }

    }
}
