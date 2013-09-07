package com.relicum.scb.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Custom Event That details stops any damage on admin tp events
 *
 * @author Relicum
 * @version 0.1
 */
public class NoFallTPDamageEvent extends EntityDamageEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private Player pl;
	private Entity ent;
	private float dam;
	private EntityDamageEvent.DamageCause cause;

	public NoFallTPDamageEvent(Entity damagee, DamageCause cause, double damage) {
		super(damagee, cause, damage);
		setDamage(5.00D);
	}

	public Entity getEnt() {
		return this.ent;
	}

	public double getDamage() {
		return dam;
	}

	public EntityDamageEvent.DamageCause getDamageCaused() {
		return this.cause;
	}

	public HandlerList getHandlers() {

		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public boolean isIgnoringCancelled() {
		return false;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean b) {
		this.cancelled = b;
	}
}
