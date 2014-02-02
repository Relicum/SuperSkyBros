package com.relicum.scb.listeners;

import com.relicum.scb.events.PlayerJoinLobbyEvent;
import com.relicum.scb.objects.inventory.ClearInventory;
import com.relicum.scb.types.SkyApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * SuperSkyBros First Created 04/10/13
 * <p/>
 * Handles Players Join the lobby from Login,Signs and Commands
 * 
 * @author Relicum
 * @version 0.1
 */
public class PlayerJoinLobby implements Listener {

    public PlayerJoinLobby() {

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoin(PlayerJoinLobbyEvent e) {
        if (e.joinFrom().equalsIgnoreCase("JOINEDSERVER") || e.joinFrom().equalsIgnoreCase("sign") || e.joinFrom().equalsIgnoreCase("command")) {

            e.getPlayer().addAttachment(SkyApi.getSCB(), "bukkit.broadcast.ssblobby", true);

            SkyApi.getInventoryManager().storeOldInventory(e.getPlayer().getPlayer());
            ClearInventory.applyLobbyInv(e.getPlayer().getPlayer());
            SkyApi.getLobbyManager().addPlayer(e.getPlayer());

           if (!e.isDedicated()) {
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.teleportToLobby"));
            }
            this.teleportToLobby(e.getPlayer().getPlayer(), SkyApi.getLobbyManager().getLobbyRg().getLobbySpawn().add(0.5, 0.5, 0.5));
            System.out.println(e.getPlayer().getName() + " Has been transported to the lobby");

            Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&',
                    SkyApi.getMessageManager().getRawMessage("system.lobbyJoinWelcome").replace("%name%", e.getPlayer().getName())),
                    "bukkit.broadcast.ssblobby");

        } else {

            SkyApi.getSCB().getLogger().warning("Error " + e.getPlayer().getName() + " was not able to join the lobby");
            e.getPlayer().sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.teleportFail"));

        }
    }

    /**
     * Teleports the player to the given location
     * 
     * @param p Player
     * @param l Location
     * @return boolean
     * @throws IllegalArgumentException
     */
    public boolean teleportToLobby(final Player p, final Location l) {

        SkyApi.getSCB().getServer().getScheduler().runTaskLater(SkyApi.getSCB(), new Runnable() {

            @Override
            public void run() {
                if (!p.teleport(l)) {
                    System.out.println("Error teleporting player to lobby");
                }
                p.playSound(l, Sound.LEVEL_UP, 1.0f, 1.0f);
            }
        }, 5L);

        return true;
    }

}
