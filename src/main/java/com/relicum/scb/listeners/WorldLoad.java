package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.events.ToggleFlyEvent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * SuperSkyBros
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldLoad implements Listener {

    private SCB plugin;


    public WorldLoad(SCB pl) {
        this.plugin = pl;
    }


    @EventHandler
    public void pJump(ToggleFlyEvent e) {

        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.PIG_DEATH, 1.0f, 1.0f);
    }

}
