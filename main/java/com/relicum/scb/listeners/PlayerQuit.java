package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

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

		Player p = e.getPlayer();
		System.out.println("Player quit event happened for " + e.getPlayer().getDisplayName());
		plugin.LBS.removePlayer(p.getName());
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);


	}
}
