package com.relicum.scb.objects.spawns;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.io.Serializable;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class SpawnInst implements ISpawnable, ISpawnableInstance, Serializable {

    static int classCount = 0;

    final Integer spawnId = classCount++;

    protected transient Vector vec;

    protected String world;

    private int x;

    private int y;

    private int z;


    protected SpawnInst() {
    }


    /**
     * Construct is passed a Location
     *
     * @param location
     */
    public SpawnInst(Vector v) {
        this.vec = v;
        this.setX(v.getBlockX());
        this.setY(v.getBlockY());
        this.setZ(v.getBlockZ());
    }


    /**
     * Get the World the location is in
     *
     * @return World
     */
    public World getWorld() {
        return Bukkit.getServer().getWorld(this.getWorldString());
    }

    /**
     * Get world string.
     *
     * @return String
     */
    public String getWorldString() {
        return this.world;
    }

    /**
     * Returns the Location Object
     *
     * @return Location
     */
    public Vector getVector() {
        return this.vec;
    }

    /**
     * Set vector.
     *
     * @param Vector
     */
    public void setVector(Vector v) {
        this.vec = v;
    }

    /**
     * Gets x.
     *
     * @return int
     */
    public int getX() {
        return x;
    }


    /**
     * Sets x.
     *
     * @param int
     */
    public void setX(int x) {
        this.x = x;
    }


    /**
     * Gets z.
     *
     * @return int
     */
    public int getZ() {
        return z;
    }


    /**
     * Sets z.
     *
     * @param int
     */
    public void setZ(int z) {
        this.z = z;
    }


    /**
     * Gets y.
     *
     * @return int
     */
    public int getY() {
        return y;
    }


    /**
     * Sets y.
     *
     * @param int
     */
    public void setY(int y) {
        this.y = y;
    }
}
