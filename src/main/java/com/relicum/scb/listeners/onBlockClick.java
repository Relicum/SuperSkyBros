package com.relicum.scb.listeners;

import java.util.List;
import com.relicum.scb.events.PlayerJoinLobbyEvent;
import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.objects.inventory.ClearInventory;
import com.relicum.scb.types.SkyApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * SuperSkyBros First Created 09/10/13
 * 
 * @author Relicum
 * @version 0.1
 */
public class onBlockClick implements Listener {

    private List<String> blacklist;

    public onBlockClick() {
        this.blacklist = SkyApi.getSm().blackListed();
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onClick(PlayerInteractEvent e) {

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK)

            return;
        Block clicked = e.getClickedBlock();
        if (!clicked.getType().toString().contains("SIGN"))
            return;

        org.bukkit.block.Sign sign = (org.bukkit.block.Sign) clicked.getState();

        String[] lines = sign.getLines();

        if (this.blacklist.contains(e.getPlayer().getWorld().getName()) && (!ChatColor.stripColor(lines[0]).equalsIgnoreCase("[JOIN LOBBY]")))
            return;

        if ((VaultManager.perms.has(e.getPlayer(), "ssb.player.uselobbysign") || e.getPlayer().isOp()) && (ChatColor.stripColor(lines[0]).equalsIgnoreCase("[JOIN LOBBY]"))) {

            if (SkyApi.getLobbyManager().isInLobby(e.getPlayer())) {
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getErrorMessage("listeners.playerJoin.alreadyInLobby"));
                return;
            }

            PlayerJoinLobbyEvent event = new PlayerJoinLobbyEvent(e.getPlayer(), "SIGN", SkyApi.getSCB().getConfig().getBoolean("dedicatedSSB"));
            Bukkit.getServer().getPluginManager().callEvent(event);

            return;
        }

        if ((VaultManager.perms.has(e.getPlayer(), "ssb.player.uselobbyleave") || e.getPlayer().isOp()) && (ChatColor.stripColor(lines[0]).equalsIgnoreCase("[LEAVE LOBBY]"))) {

            e.getPlayer().performCommand("ssb leave");

        }

        if ((VaultManager.perms.has(e.getPlayer(), "ssb.player.usearenareturn") || e.getPlayer().isOp()) && (ChatColor.stripColor(lines[0]).equalsIgnoreCase("[RETURN]"))) {
            ClearInventory.applyLobbyInv(e.getPlayer());
            e.getPlayer().sendMessage(SkyApi.getMessageManager().getMessage("listeners.onblockclick.returnToLobby"));
            SkyApi.getLobbyManager().teleportToLobby(e.getPlayer(), SkyApi.getLobbyManager().getLobbyRg().getWorld().getSpawnLocation());

        }

        if ((VaultManager.perms.has(e.getPlayer(), "ssb.player.usearenajoin") || e.getPlayer().isOp()) && (ChatColor.stripColor(lines[0]).startsWith("[Arena"))) {
            if (ChatColor.stripColor(lines[3]).equalsIgnoreCase("waiting")) {
                e.getPlayer().sendMessage("Sign Starts with [Arena and status is waiting");
                return;
            }
            e.getPlayer().sendMessage("Sorry sign status is not correct");
            return;

        }

        return;
    }

}
