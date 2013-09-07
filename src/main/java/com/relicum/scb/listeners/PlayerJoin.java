package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
@SuppressWarnings("deprecation")
public class PlayerJoin implements Listener, Cancellable {

	private SCB plugin;
	private boolean cancel = false;

	public PlayerJoin(SCB plugin) {
		this.plugin = plugin;

	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void playJoin(PlayerJoinEvent e) {
		Player pl = e.getPlayer();
		setCancelled(true);
		if (e.getPlayer().hasPermission("ssba.admin")) {
			ChatColor b = ChatColor.BOLD;
			String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
			pl.sendMessage(pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros Alpha " + SCB.getInstance().getDescription().getVersion() + " this should not be run on a live server be warned");

		}

		System.out.println("Player Join Event " + pl.getDisplayName() + " has joined");
		if (plugin.getConfig().getBoolean("autoJoinLobby")) {

			pl.performCommand("ssb join");
		}

	}

	@Override
	public boolean isCancelled() {
		return this.cancel;
	}

	@Override
	public void setCancelled(boolean b) {
		this.cancel = b;
	}
}
