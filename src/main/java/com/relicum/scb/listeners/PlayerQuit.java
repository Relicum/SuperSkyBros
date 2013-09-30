package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.SettingsManager;
import com.relicum.scb.SmashPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
@SuppressWarnings("deprecation")
public class PlayerQuit implements Listener {

    private SCB plugin;


    public PlayerQuit(SCB pl) {

        plugin = pl;
    }


    @EventHandler
    public void playQuit(PlayerQuitEvent e) {

        if (Result.KICK_OTHER.equals(Result.KICK_OTHER)) {
            e.setQuitMessage("");
        }
        if (plugin.LBS.isInLobby(e.getPlayer().getName())) {

            System.out.println("Player quit event happened for " + e.getPlayer().getName());
            SmashPlayer sm = SmashPlayer.warp(e.getPlayer().getName());
            sm.removeArmour().clearInventory(sm);
            sm.removeFire().resetHealth(20.00F);
            sm.closeInventory().updateInventory();

            //plugin.LBS.removePlayer(smashPlayer);
        }

    }
}
