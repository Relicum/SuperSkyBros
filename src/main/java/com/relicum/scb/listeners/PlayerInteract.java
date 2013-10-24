package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.utils.GemShop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * SuperSkyBros First Created 09/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerInteract implements Listener {

    private SCB plugin;


    public PlayerInteract(SCB p) {

        this.plugin = p;

    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void itemDrop(PlayerDropItemEvent e) {
        if (SCB.getInstance().LBS.isInLobby(e.getPlayer())) return;
        e.setCancelled(true);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(PlayerInteractEvent e) {
        if (!(SCB.getInstance().LBS.isInLobby(e.getPlayer())) && (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
            return;
        }


        Player player = e.getPlayer();
        if (player.getItemInHand().getType() == Material.EMERALD) {
            GemShop.openGem(player);
        }


    }


}
