package com.relicum.scb.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * The type ArenaLobby.
 */
public class ArenaLobby {

    /**
     * The Max.
     */
    private Vector max;

    /**
     * The Min.
     */
    private Vector min;

    /**
     * The World.
     */
    private String world;

    /**
     * The Spawn.
     */
    private Vector spawn;

    /**
     * The Arena id.
     */
    private Integer arenaId;


    /**
     * Instantiates a new Arena lobby.
     *
     * @param max     the max
     * @param min     the min
     * @param world   the world
     * @param spawn   the spawn
     * @param arenaId the arena id
     */
    public ArenaLobby(Vector max, Vector min, String world, Vector spawn, Integer arenaId) {
        this.max = max;
        this.min = min;
        this.world = world;
        this.spawn = spawn;
        this.arenaId = arenaId;

    }


    /**
     * Gets min.
     *
     * @return the min
     */
    public Vector getMin() {
        return min;
    }


    /**
     * Sets min.
     *
     * @param min the min
     */
    public void setMin(Vector min) {
        this.min = min;
    }


    /**
     * Gets max.
     *
     * @return the max
     */
    public Vector getMax() {
        return max;
    }


    /**
     * Sets max.
     *
     * @param max the max
     */
    public void setMax(Vector max) {
        this.max = max;
    }


    /**
     * Gets world.
     *
     * @return the world
     */
    public World getWorld() {

        return Bukkit.getServer().getWorld(this.world);
    }


    /**
     * Sets world.
     *
     * @param world the world
     */
    public void setWorld(String world) {
        this.world = world;
    }


    /**
     * Gets arena id.
     *
     * @return the arena id
     */
    public Integer getArenaId() {
        return arenaId;
    }


    /**
     * Sets arena id.
     *
     * @param arenaId the arena id
     */
    public void setArenaId(Integer arenaId) {
        this.arenaId = arenaId;
    }


    /**
     * Gets spawn.
     *
     * @return the spawn
     */
    public Location getSpawn() {
        return spawn.toLocation(getWorld());
    }


    /**
     * Sets spawn.
     *
     * @param spawn the spawn
     */
    public void setSpawn(Vector spawn) {
        this.spawn = spawn;
    }


    /**
     * Is aABB.
     *
     * @param Vec the vec
     * @return the boolean
     */
    public boolean isAABB(Vector Vec) {

        return Vec.isInAABB(this.getMin(), this.getMax());
    }
}
