package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.events.WorldInventoryClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * SuperSkyBros First Created 04/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldInventoryClick extends Event implements Listener {

    SCB plugin;


    public WorldInventoryClick(SCB p) {
        this.plugin = p;

    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void worldInventory(WorldInventoryClickEvent e) {
        if (e.isCancelled()) {
            System.out.println("Fuck already canceled");
        }
        System.out.println(e.getWorld().getName() + " Chat from " + e.getWhoClicked().getName());
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void inClick(InventoryClickEvent e) {
        WorldInventoryClickEvent event = new WorldInventoryClickEvent(e.getView(), e.getSlotType(), e.getSlot(), e.getClick(), e.getAction());
        Bukkit.getServer().getPluginManager().callEvent(event);
        System.out.println("First Event Fired");
    }


    @Override
    public HandlerList getHandlers() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
