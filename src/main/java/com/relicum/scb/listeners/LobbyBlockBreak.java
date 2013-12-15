package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.types.SkyApi;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * The type LobbyBlockBreak.
 *
 * @author Relicum
 * @version 0.2
 */
public class LobbyBlockBreak implements Listener, Cancellable {

    /**
     * The Plugin.
     */
    public SCB plugin;
    public Vector min;
    public Vector max;
    public String world;
    public List<String> blacklist;
    private boolean protectionSet;
    private boolean cancel = false;


    /**
     * Instantiates a new Lobby block break.
     *
     * @param pl the pl
     */
    public LobbyBlockBreak(JavaPlugin pl) {
        this.plugin = (SCB) pl;
        this.protectionSet = this.plugin.getConfig().getBoolean("enableLobbyProtection");
        this.blacklist = SkyApi.getSm().blackListed();
        this.min = SkyApi.getLobbyManager().getLobbyRg().getMinVector();
        this.max = SkyApi.getLobbyManager().getLobbyRg().getMaxVector();
        this.world = SkyApi.getLobbyManager().getLobbyRg().getWorld().getName();

    }

    /**
     * Lobby break.
     *
     * @param BlockBreakEvent the event
     */
    @EventHandler(priority = EventPriority.LOW)
    public void lobbyBreak(BlockBreakEvent e) {
        if (this.blacklist.contains(e.getPlayer().getWorld().getName())) return;
        if (!this.protectionSet) return;

        Player player = e.getPlayer();
        String wo = player.getWorld().getName();

        if (!VaultManager.perms.has(player, "ssba.admin.breakblocks")) {
            if (SkyApi.getLobbyManager().getLobbyRg().isAABB(e.getBlock().getLocation().toVector())) {
                e.setCancelled(true);
                player.sendMessage(SCB.MM.getErrorMessage("listeners.blockbreak.lobbyBreak"));
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
