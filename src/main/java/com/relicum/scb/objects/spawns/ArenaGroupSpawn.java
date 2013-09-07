package com.relicum.scb.objects.spawns;

import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SuperSkyBros
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaGroupSpawn implements ISpawnable {


	/**
	 * The Chunks used to load spawn point locations
	 */
	private List<Vector> chunks = new ArrayList<>();
	/**
	 * The Arena id.
	 */
	private int arenaId;
	/**
	 * The Spawns.
	 */
	private List<ArenaSpawn> spawns = null;

	/**
	 * Instantiates a new Arena group spawn.
	 *
	 * @param int the id
	 */
	public ArenaGroupSpawn(int id) {
		super();
		this.setArenaId(id);
		this.spawns = new ArrayList<>();

	}

	/**
	 * Instantiates a new Arena group spawn.
	 *
	 * @param List<ArenaSpawn> the sp
	 */
	public ArenaGroupSpawn(ArenaSpawn sp) {
		super();
		this.spawns = new ArrayList<>();
		this.getSpawns().add(this.addGroupIdIfNeeded(sp));
		if ((Integer) arenaId == null) {
			setArenaId(sp.getArenaid());
		}
		Vector v = new Vector();
		v.setX(sp.vec.toLocation(sp.getWorld()).getChunk().getX());
		v.setZ(sp.vec.toLocation(sp.getWorld()).getChunk().getZ());
		this.addChunk(v);
	}

	/**
	 * Add spawn to group. Returns False if arenaids do not match
	 *
	 * @param ArenaSpawn
	 * @return boolean
	 */
	public boolean addSpawn(ArenaSpawn as) {

		if (as.getArenaid() != this.getArenaId()) {

			return false;
		}
		this.getSpawns().add(this.addGroupIdIfNeeded(as));
		Vector v = new Vector();
		v.setX(as.vec.toLocation(as.getWorld()).getChunk().getX());
		v.setZ(as.vec.toLocation(as.getWorld()).getChunk().getZ());
		this.addChunk(v);

		return true;
	}

	private ArenaSpawn addGroupIdIfNeeded(ArenaSpawn as) {

		if ((Integer) as.getGroupId() == null) {
			as.setGroupId(this.getTotal());
		}

		return as;
	}

	/**
	 * Add a group of spawns. Returns false if any of the arena id do not match
	 *
	 * @param List<ArenaSpawn>
	 * @return the boolean
	 */
	public boolean addGroupSpawns(List<ArenaSpawn> gs) {
		for (ArenaSpawn g : gs) {
			if (g.getArenaid() != this.getArenaId())
				return false;
			else {

				this.getSpawns().add(this.addGroupIdIfNeeded(g));
				Vector v = new Vector();
				v.setX(g.vec.toLocation(g.getWorld()).getChunk().getX());
				v.setZ(g.vec.toLocation(g.getWorld()).getChunk().getZ());
				this.addChunk(v);
			}
		}
		return true;
	}


	/**
	 * Get total Spawns in Group
	 *
	 * @return int
	 */
	public int getTotal() {
		return this.getSpawns().size();
	}


	/**
	 * Despawn
	 */
	@Override
	public void despawn() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Spawn Players
	 *
	 * @return int
	 */
	@Override
	public int spawn() {


		return 1;
	}

	/**
	 * Gets arena id.
	 *
	 * @return int
	 */
	public int getArenaId() {
		return arenaId;
	}


	/**
	 * Sets arena id.
	 *
	 * @param int
	 */
	public void setArenaId(int arenaId) {
		this.arenaId = arenaId;
	}

	/**
	 * Gets spawns.
	 *
	 * @return List<ArenaSpawn>
	 */
	public List<ArenaSpawn> getSpawns() {
		return spawns;
	}

	/**
	 * Sets spawns.
	 *
	 * @param List<ArenaSpawn> the spawns
	 */
	public void setSpawns(List<ArenaSpawn> spawns) {
		this.spawns = spawns;
	}

	/**
	 * Gets chunks.
	 *
	 * @return List<Vector> the chunks
	 */
	public List<Vector> getChunks() {
		return this.chunks;
	}

	/**
	 * Sets chunks.
	 *
	 * @param List<Vector> the chunks
	 */
	public void setChunks(List<Vector> chunk) {

		for (Vector v : chunk) {
			this.chunks.add(v);
		}
	}

	/**
	 * Add single chunk.
	 *
	 * @param Vector
	 */
	public void addChunk(Vector cv) {

		if (!this.chunks.contains(cv)) {
			this.chunks.add(cv);
		}
	}


	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>();

		map.put("groupspawn", this.getArenaId());
		map.put("chunks", this.getChunks());
		map.put("spawns", this.spawns);


		return map;
	}
}
