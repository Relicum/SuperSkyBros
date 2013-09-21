package com.relicum.scb.objects.spawns;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * SpawnInstance
 *
 * @author alkarinv
 * @version 3.8.8
 */


public abstract class SpawnInstance implements Spawnable, SpawnableInstance {

    static int classCount = 0;

    final Integer spawnId = classCount++;

    Location loc;


    /**
     * Construct is passed a Location
     *
     * @param location
     */
    public SpawnInstance(Location location) {

        this.loc = location;

    }


    /**
     * Get the World the location is in
     *
     * @return World
     */
    public World getWorld() {
        return loc != null ? loc.getWorld() : null;
    }


    /**
     * Set Location
     *
     * @param l Location
     */
    public void setLocation(Location l) {
        this.loc = l;
    }


    /**
     * Get Location
     *
     * @return Location
     */
    public Location getLocation() {
        return loc;
    }
}
