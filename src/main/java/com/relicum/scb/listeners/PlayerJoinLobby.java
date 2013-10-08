package com.relicum.scb.listeners;

import com.relicum.scb.BroadcastManager;
import com.relicum.scb.SCB;
import com.relicum.scb.events.PlayerJoinLobbyEvent;
import com.relicum.scb.objects.inventory.ClearInventory;
import com.relicum.scb.utils.playerStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;


/**
 * SuperSkyBros First Created 04/10/13
 * <p/>
 * Handles Players Join the lobby from Login,Signs and Commands
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerJoinLobby implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoin(PlayerJoinLobbyEvent e) {
        if (e.joinFrom().equalsIgnoreCase("JOINEDSERVER") || e.joinFrom().equalsIgnoreCase("sign") || e.joinFrom().equalsIgnoreCase("command")) {
            System.out.println("PlayerJoinLobbyEvent  Started");
            e.getPlayer().setPermissionAttachment(e.getPlayer().getPlayer().addAttachment(SCB.getInstance(), "bukkit.broadcast.ssblobby", true));

            //pa.setPermission(BroadcastManager.getLobby(),true);
            SCB.getInstance().INV.storeOldInventory(e.getPlayer().getPlayer());
            ClearInventory.applyLobbyInv(e.getPlayer().getPlayer());
            SCB.getInstance().LBS.addPlayer(e.getPlayer());
            e.getPlayer().pStatus = playerStatus.LOBBY;
            if (!e.isDedicated()) {
                e.getPlayer().sendMessage(SCB.getMessageManager().getAdminMessage("command.message.teleportToLobby"));
            }
            SCB.getInstance().LBS.teleportToLobby(e.getPlayer().getPlayer(), SCB.getInstance().LBS.getLobbyRegion().getLobbySpawn());
            System.out.println(e.getPlayer().getName() + " Has been transported to the lobby");
            e.getPlayer().getPlayer().playSound(e.getPlayer().getLocation(), Sound.PIG_DEATH, 1.0f, 1.0f);
            Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', SCB.MM.getRawMessage("system.lobbyJoinWelcome").replace("%name%", e.getPlayer().getName())), "bukkit.broadcast.ssblobby");
        } else {

            SCB.getInstance().getLogger().warning("Error " + e.getPlayer().getName() + " was not able to join the lobby");
            e.getPlayer().sendMessage(SCB.MM.getErrorMessage("command.message.teleportFail"));
        }

        System.out.println("PlayerJoinLobbyEvent  Ended");
    }

}
