package com.relicum.scb.arena;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public interface IASPAWN {

	Integer getSpawnId();

	double getX();

	double getY();

	double getZ();

	float getYAW();

	float getPitch();

	World getWorld();

	Integer getTypeId();

	Location getLocation();

}
