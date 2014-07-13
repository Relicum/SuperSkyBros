package com.relicum.scb.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * SuperSkyBros
 *
 * @author Relicum
 * @version 0.1
 */
public class LobbyRg {

    private Vector lobbySpawn = null;

    private Vector max = null;

    private Vector min = null;

    private String world;

    private String perm;

    private float yaw;

    public LobbyRg(Vector min, Vector max, Vector lobbySpawn, String wo, String p, float y) {
        this.world = wo;
        this.lobbySpawn = lobbySpawn;
        this.max = max;
        this.min = min;
        this.perm = p;
        setYaw(y);
    }

    /**
     * Set the Lobby Spawn Location
     *
     * @return Location
     */
    public Location getLobbySpawn() {

        return getLobbySpawnVector().toLocation(getWorld(), getYaw(), 0);
    }

    /**
     * set The Max Location
     *
     * @return Location
     */
    public Location getMaxLocation() {

        return max.toLocation(getWorld());
    }

    /**
     * Set the min Location
     *
     * @return Location
     */
    public Location setMinLocation() {

        return min.toLocation(getWorld());
    }

    /**
     * Returns the Max vector
     *
     * @return Vector
     */
    public Vector getMaxVector() {
        return this.max;
    }

    /**
     * Returns the Min Vector
     *
     * @return Vector
     */
    public Vector getMinVector() {
        return this.min;
    }

    /**
     * Returns the AdminSpawn as Vector
     *
     * @return Vector
     */
    public Vector getLobbySpawnVector() {
        return this.lobbySpawn;
    }

    /**
     * Returns bukkit world from string
     *
     * @return World
     */
    public World getWorld() {

        return Bukkit.getServer().getWorld(this.world);
    }

    /**
     * Checks if a location is in a Arena Region
     *
     * @param Vector
     * @return boolean
     */
    public boolean isAABB(Vector Vec) {

        return Vec.isInAABB(this.getMinVector(), this.getMaxVector());
    }

    /**
     * Gets perm.
     *
     * @return String
     */
    public String getPerm() {
        return perm;
    }

    /**
     * Sets perm.
     *
     * @param String the perm
     */
    public void setPerm(String perm) {
        this.perm = perm;
    }

    /**
     * Gets yaw for the spawn location
     *
     * @return float the yaw
     */
    public float getYaw() {

        return this.yaw;

    }

    /**
     * Sets yaw for spawn location
     *
     * @param float the yaw
     */
    public void setYaw(float yaw) {
        this.yaw = getDirection(yaw);
    }

    /**
     * Returns the direction you are looking
     *
     * @param yaw float
     * @return float
     */
    public float getDirection(Float yaw) {
        yaw = yaw / 90;
        yaw = (float) Math.round(yaw);

        if (yaw == -4 || yaw == 0 || yaw == 4) {
            return (0.00F);
        }
        if (yaw == -1 || yaw == 3) {
            return -90.00F;
        }
        if (yaw == -2 || yaw == 2) {
            return -179.00F;
        }
        if (yaw == -3 || yaw == 1) {
            return 90.00F;
        }
        return 5.00F;
    }

}
