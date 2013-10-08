package com.relicum.scb.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

/**
 * SuperSkyBros First Created 03/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class InventoryInteract implements Listener {


    public void onInteract(InventoryInteractEvent e) {

        if (e.getWhoClicked() instanceof Player) {
            e.getWhoClicked().getLocation().getWorld().getName();
        }

    }


}
