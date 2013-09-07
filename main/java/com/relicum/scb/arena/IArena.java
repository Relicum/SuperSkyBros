package com.relicum.scb.arena;

import com.relicum.scb.objects.spawns.ArenaGroupSpawn;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * Interface for an arena object. This holds and runs everything to do with the arena Each arena has it's own Arena
 * class
 *
 * @author Relicum
 * @version 0.1
 */
public interface IArena {

	String getArenaName();

	Integer getArenaId();

	ArenaStatus getArenaStatus();

	boolean isEnabled();

	ArenaGroupSpawn getSpawnGroup();

	int getMaxPlayers();

	int getMinPlayers();

	String getPermmission();

	Long getMaxGameTime();

	Vector getAdminSpawn();

	World getArenaWorld();

	void loadArena();

	void saveArena();

}
