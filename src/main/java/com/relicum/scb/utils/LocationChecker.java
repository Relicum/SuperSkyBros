package com.relicum.scb.utils;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

/**
 * SuperSkyBros
 * First Created 10/12/13
 *
 * @author Relicum
 * @version 0.1
 */
@SerializableAs("LocationChecker")
public class LocationChecker implements ConfigurationSerializable {

    /**
     * The Max Vector
     */
    private final Vector max;

    /**
     * The Min Vector
     */
    private final Vector min;

    /**
     * The Object Type
     */
    private final String type;

    /**
     * The Object Id
     */
    private final Integer id;

    /**
     * Instantiates a new Location checker.
     *
     * @param max  the max vector
     * @param min  the min Vector
     * @param type the type of Object
     * @param id   the id Id of object
     */
    public LocationChecker(Vector max, Vector min, String type, Integer id) {
        this.max = max;
        this.min = min;
        this.type = type;
        this.id = id;
    }

    /**
     * Instantiates a new Location checker.
     *
     * @param locationChecker the location checker
     */
    public LocationChecker(LocationChecker locationChecker) {
        this.max = locationChecker.max;
        this.min = locationChecker.min;
        this.type = locationChecker.type;
        this.id = locationChecker.id;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("max", max);
        map.put("min", min);
        map.put("type", type);
        map.put("id", id);

        return map;
    }

    /**
     * Deserialize location checker.
     *
     * @param map the map
     * @return the location checker
     */
    public static LocationChecker deserialize(Map<String, Object> map) {
        Object maxObject = map.get("max"),
                minObject = map.get("min"),
                typeObject = map.get("type"),
                idObject = map.get("id");
        if (maxObject == null || minObject == null || typeObject == null || idObject == null || !(maxObject instanceof Vector)
                || !(minObject instanceof Vector)) {
            return null;
        }

        Integer oid = (Integer) idObject;

        return new LocationChecker((Vector) maxObject, (Vector) minObject, typeObject.toString(), oid);

    }


    /**
     * Get location checker.
     *
     * @return the location checker
     */
    public LocationChecker getChecker() {
        return new LocationChecker(min, max, type, id);
    }


    /**
     * Is Checks to see if the give vector is in
     * The bounded Box
     *
     * @param vector the vector
     * @return the boolean true if the vector is in the box
     */
    public boolean isAABB(Vector vector) {
        return vector.isInAABB(min, max);
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
     * Gets min.
     *
     * @return the min
     */
    public Vector getMin() {
        return min;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }
}
