package com.relicum.scb.listeners;


import com.relicum.scb.SCB;
import org.bukkit.entity.Player;

import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

/**
 * SuperSkyBros First Created 29/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerBlockDamage implements Listener {

    public String world;


    public PlayerBlockDamage() {
        this.world = SCB.getInstance().LBS.getLobbyRegion().getWorld().getName();
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void blockDamage(BlockDamageEvent e) {

        Player player = e.getPlayer();
        String wo = player.getWorld().getName();

        if (!SCB.perms.has(player, "ssba.admin.breakblocks") && !player.isOp() && this.world.equals(wo)) {
            if (SCB.getInstance().LBS.getLobbyRegion().isAABB(e.getBlock().getLocation().toVector())) {
                Result DENY;
                e.setCancelled(true);
                player.sendMessage(SCB.MM.getErrorMessage("listeners.blockbreak.lobbyBreak"));
            }
        }
    }

}
