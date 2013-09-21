package com.relicum.scb.objects.spawns;

import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public interface ISpawnableInstance {

    /**
     * Get Spawn Vector
     *
     * @return Vector
     */
    public Vector getVector();

    /**
     * Get world.
     *
     * @return World
     */
    public World getWorld();
}
