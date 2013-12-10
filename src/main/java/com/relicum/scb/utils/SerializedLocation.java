package com.relicum.scb.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

/**
 * The type SerializedLocation.
 */
@SerializableAs("SerializedLocation")
public class SerializedLocation implements ConfigurationSerializable {

    /**
     * The X.
     */
    private final int x;

    /**
     * The Y.
     */
    private final int y;

    /**
     * The Z.
     */
    private final int z;

    /**
     * The Yaw.
     */
    private final float yaw;

    /**
     * The Pitch.
     */
    private final float pitch;

    /**
     * The World.
     */
    private final String world;


    /**
     * Instantiates a new Serialized location.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     * @param z     the z
     * @param yaw   the yaw
     * @param pitch the pitch
     */
    public SerializedLocation(String world, int x, int y, int z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.yaw = yaw;
        this.pitch = pitch;
    }


    /**
     * Instantiates a new Serialized location.
     *
     * @param location the location
     */
    public SerializedLocation(Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.world = location.getWorld().getName();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }


    /**
     * Deserialize serialized location.
     *
     * @param map the map
     * @return the serialized location
     */
    public static SerializedLocation deserialize(Map<String, Object> map) {
        Object xObject = map.get("xpos"),
                yObject = map.get("ypos"),
                zObject = map.get("zpos"),
                worldObject = map.get("world"),
                yawObject = map.get("yawpos"),
                pitchObject = map.get("pitchpos");
        if (xObject == null || yObject == null || zObject == null || worldObject == null || !(xObject instanceof Integer) || !(yObject instanceof Integer) || !(zObject instanceof Integer)) {
            return null;
        }
        Integer x = (Integer) xObject, y = (Integer) yObject, z = (Integer) zObject;
        Double yaw = (Double) yawObject, pitch = (Double) pitchObject;
        String worldString = worldObject.toString();

        return new SerializedLocation(worldString, x, y, z, yaw.floatValue(), pitch.floatValue());

    }


    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return new Location(Bukkit.getServer().getWorld(world), x + 0.5, y + 0.5, z + 0.5, yaw, pitch);
    }


    /**
     * Get vector.
     *
     * @return the vector
     */
    public Vector getVector() {
        return new Vector(x, y, z);
    }


    /**
     * Serialize map.
     *
     * @return the map ready to be serialised
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("world", world);
        map.put("xpos", x);
        map.put("ypos", y);
        map.put("zpos", z);
        map.put("yawpos", yaw);
        map.put("pitchpos", pitch);
        return map;
    }


    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }


    /**
     * Gets world.
     *
     * @return the world as a string
     */
    public String getWorld() {
        return world;
    }


    /**
     * Gets z.
     *
     * @return the z
     */
    public int getZ() {
        return z;
    }


    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }


    /**
     * Gets yaw.
     *
     * @return the yaw
     */
    public float getYaw() {
        return yaw;
    }


    /**
     * Gets pitch.
     *
     * @return the pitch
     */
    public float getPitch() {
        return pitch;
    }


    /**
     * String representation of SerializedLocation
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "SerializedLocation:world=" + getWorld() + ":x=" + getX() + ":y=" + getY() + ":z=" + getZ() + ":yaw=" + getYaw() + ":pitch=" + getPitch();
    }
}
