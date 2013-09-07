package com.relicum.scb.listeners;

import com.relicum.scb.events.NoFallTPDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * SuperSkyBros
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerTP implements Listener, Cancellable {

	private boolean cancel = false;

	public PlayerTP() {
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void plTP(NoFallTPDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			setCancelled(true);
			e.setDamage(0.00D);
			Player pl = (Player) e.getEntity();
			pl.setHealth(20.00D);
			pl.setFoodLevel(20);

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
