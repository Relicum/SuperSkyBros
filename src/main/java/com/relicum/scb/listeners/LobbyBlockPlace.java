package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class LobbyBlockPlace implements Listener, Cancellable {


    /**
     * The Plugin.
     */
    public SCB plugin;

    public Vector min;

    public Vector max;

    public String world;

    private boolean cancel = false;


    /**
     * Instantiates a new Lobby block break.
     *
     * @param pl the pl
     */
    public LobbyBlockPlace(JavaPlugin pl) {
        this.plugin = (SCB) pl;
        this.min = plugin.LBS.getLobbyRegion().getMinVector();
        this.max = plugin.LBS.getLobbyRegion().getMaxVector();
        this.world = plugin.LBS.getLobbyRegion().getWorld().getName();

    }


    /**
     * Lobby break.
     *
     * @param BlockBreakEvent the event
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void lobbyBreak(BlockPlaceEvent e) {


        Player player = e.getPlayer();
        String wo = player.getWorld().getName();

        if (!SCB.perms.has(player, "ssba.admins.placeblocks") && !player.isOp() && this.world.equals(wo)) {
            if (SCB.getInstance().LBS.getLobbyRegion().isAABB(e.getBlock().getLocation().toVector())) {
                e.setCancelled(true);
                player.sendMessage(SCB.MM.getErrorMessage("listeners.place.lobbyBreak"));
            }
        }

    }


    /**
     * Is cancelled.
     *
     * @return the boolean
     */
    @Override
    public boolean isCancelled() {
        return cancel;
    }


    /**
     * Sets cancelled.
     *
     * @param b the b
     */
    @Override
    public void setCancelled(boolean b) {
        cancel = b;
    }
}
