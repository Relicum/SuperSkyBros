package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;


/**
 * SuperSkyBros First Created 09/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class DBlockBreakPlace implements Listener {

    private SCB plugin;

    private final String breakMessage;

    private final String placeMessage;


    public DBlockBreakPlace(SCB p) {

        this.plugin = p;
        this.breakMessage = SCB.getMessageManager().getErrorMessage("listeners.blockbreak.lobbyBreak");
        this.placeMessage = SCB.getMessageManager().getErrorMessage("listeners.blockplace.lobbyPlace");
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent e) {

        if (!SCB.perms.has(e.getPlayer(), "ssba.admin.breakbypass") && !e.getPlayer().isOp()) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(this.breakMessage);
        }
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent e) {

        if (!SCB.perms.has(e.getPlayer(), "ssba.admin.placebypass") && !e.getPlayer().isOp()) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(this.placeMessage);
        }

    }

}
