package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.GemShop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

/**
 * SuperSkyBros First Created 09/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerInteract implements Listener {

    private SCB plugin;

    private List<String> blacklist;


    public PlayerInteract(SCB p) {

        this.plugin = p;
        this.blacklist = plugin.getBlackList();

    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void itemDrop(PlayerDropItemEvent e) {
        if (this.blacklist.contains(e.getPlayer().getWorld().getName())) return;
        if (SkyApi.getLobbyManager().isInLobby(e.getPlayer())) {
            e.setCancelled(true);
            return;
        }
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(PlayerInteractEvent e) {

        if ((e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR)) return;
        //TODO Deal with mixed mode and if player is in lobby


        Player player = e.getPlayer();
        if (player.getItemInHand().getType() == Material.EMERALD) {
            GemShop.openGem(player);
        }


    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void dragItem(InventoryDragEvent e) {
        if (this.blacklist.contains(e.getWhoClicked().getWorld().getName())) return;
        if (!(SkyApi.getLobbyManager().isInLobby(e.getWhoClicked().getName()))) {
            return;
        } else {
            e.setCancelled(true);
        }
    }


}
