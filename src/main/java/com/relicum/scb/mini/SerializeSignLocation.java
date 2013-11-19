package com.relicum.scb.mini;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * The type SerializeSignLocation.
 */
@SerializableAs("SerializeSignLocation")
public class SerializeSignLocation implements ConfigurationSerializable {

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
     */
    public SerializeSignLocation(String world, int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }


    /**
     * Instantiates a new Serialized location.
     *
     * @param location the location
     */
    public SerializeSignLocation(Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.world = location.getWorld().getName();
    }


    /**
     * Deserialize serialized location.
     *
     * @param map the map
     * @return the serialized location
     */
    public static SerializeSignLocation deserialize(Map<String, Object> map) {
        Object xObject = map.get("xpos"),
                yObject = map.get("ypos"),
                zObject = map.get("zpos"),
                worldObject = map.get("world");
        if (xObject == null || yObject == null || zObject == null || worldObject == null
                    || !(xObject instanceof Integer)
                    || !(yObject instanceof Integer)
                    || !(zObject instanceof Integer)) {
            return null;
        }
        Integer x = (Integer) xObject, y = (Integer) yObject, z = (Integer) zObject;

        String worldString = worldObject.toString();

        return new SerializeSignLocation(worldString, x, y, z);

    }


    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return new Location(Bukkit.getServer().getWorld(world), x + 0.5, y + 0.5, z + 0.5);
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
     * String representation of SerializedLocation
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "SerializedLocation:world=" + getWorld() + ":x=" + getX() + ":y=" + getY() + ":z=" + getZ();

    }
}
