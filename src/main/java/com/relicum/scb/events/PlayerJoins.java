package com.relicum.scb.events;

import com.relicum.scb.SCB;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Fired when ever a player joins the lobby
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerJoins implements Listener {

	private SCB plugin;


	public PlayerJoins(SCB plugin) {
		this.plugin = plugin;

	}

	@EventHandler
	public void playJoin(PlayerJoinEvent e) {

		if (plugin.getConfig().getBoolean("autoJoinLobby")) {
			if (e.getPlayer().hasPermission("scb.player.join")) {
				e.getPlayer().performCommand("scb join");
			}

		}
	}


}
