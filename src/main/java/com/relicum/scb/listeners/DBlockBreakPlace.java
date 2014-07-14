package com.relicum.scb.listeners;

import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.types.SkyApi;
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

    private final String breakMessage;

    private final String placeMessage;

    public DBlockBreakPlace() {

        this.breakMessage = SkyApi.getMessageManager().getErrorMessage("listeners.blockbreak.lobbyBreak");
        this.placeMessage = SkyApi.getMessageManager().getErrorMessage("listeners.blockplace.lobbyPlace");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent e) {

        if (!VaultManager.getInstance().perms.has(e.getPlayer(), "ssba.admin.breakbypass") && !e.getPlayer().isOp()) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(this.breakMessage);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent e) {

        if (!VaultManager.getInstance().perms.has(e.getPlayer(), "ssba.admin.placebypass") && !e.getPlayer().isOp()) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(this.placeMessage);
        }

    }

}
