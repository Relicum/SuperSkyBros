package com.relicum.scb.objects.spawns;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public interface ISpawnable {

	/**
	 * Despawn
	 */
	public void despawn();

	/**
	 * Spawn
	 *
	 * @return int
	 */
	public int spawn();
}
