package com.relicum.scb.listeners;

import com.relicum.scb.PlayerLoginManager;
import com.relicum.scb.PlayerSettings;
import com.relicum.scb.SCB;
import com.relicum.scb.configs.PlayerConfig;
import com.relicum.scb.objects.PlayerLocation;
import com.relicum.scb.objects.inventory.RestoreInventory;
import com.relicum.scb.types.SkyApi;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerQuit implements Listener {

    private SCB plugin;

    public PlayerQuit(SCB pl) {

        plugin = pl;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playQuit(PlayerQuitEvent e) {

        if (Result.KICK_OTHER.equals(Result.KICK_OTHER)) {
            e.setQuitMessage("");
        }
        if (SkyApi.getSm().getAdminMode().contains(e.getPlayer().getName())) {
            SkyApi.getSm().getAdminMode().remove(e.getPlayer().getName());
        }
        if (SkyApi.getLobbyManager().isInLobby(e.getPlayer())) {
            RestoreInventory.restore(e.getPlayer());
            e.getPlayer().teleport(SkyApi.getLobbyManager().getLobbyRg().getWorld().getSpawnLocation().add(0.5, 0.5, 0.5));
            e.setQuitMessage("");
            SkyApi.getInventoryManager().removePlayerFromStore(e.getPlayer().getName());
            SkyApi.getLobbyManager().removePlayer(e.getPlayer());
            if (PlayerLoginManager.hasProfile(e.getPlayer().getName())) {
                PlayerConfig relfile = new PlayerConfig(PlayerLoginManager.profilePath(e.getPlayer().getName()));

                PlayerSettings playerTest = (PlayerSettings) relfile.getConfig().get("player." + e.getPlayer().getName());
                playerTest.setPlayerLocation(PlayerLocation.LOGOUT);

                // playerTest.save();
            }

        }

    }
}
