package com.relicum.scb.configs;


import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaData {


    /**
     * HashMap is to be serialised
     */
    private Map<String, Object> are = new HashMap<String, Object>();


    private String min;

    private String max;

    private String top;

    private String world;

    private boolean enable;

    private String map;

    private String Perm;

    private Integer arenaId;

    private String arenaPath;


    private Vector vMin;

    private Vector vMax;


    private Vector vTop;


    public ArenaData(Map<String, Object> dt) {

    }


    public ArenaData(Vector min, Vector max, Vector top, String wo, String mp, Integer aid, String perm) {

        setvMin(min);
        setvMax(max);
        setvTop(top);
        setWorld(wo);
        setMap(mp);
        setEnable(false);
        setArenaId(aid);
        setPerm(perm);


    }


    public Map<String, Object> getArenaHash() {

        Map<String, Object> arena = new HashMap<String, Object>();
        arena.put("min", getvMin());
        arena.put("max", getvMax());
        arena.put("top", getvTop());
        arena.put("world", getWorld());
        arena.put("enabled", isEnable());
        arena.put("map", getMap());
        arena.put("permission", getPerm());


        return arena;
    }


    /**
     * Get a String representing Vector Containing min
     *
     * @return String
     * @throws IllegalArgumentException if the new value is not acceptable.
     */
    public String getMin() {
        return min;
    }


    /**
     * Get a Vector Containing max
     *
     * @return String
     * @throws IllegalArgumentException if the new value is not acceptable.
     */
    public String getMax() {
        return max;
    }


    /**
     * Set Vector for min
     *
     * @param pos1 String
     * @throws IllegalArgumentException if the new value is not acceptable.
     */
    public void setMin(String pos1) {
        this.min = pos1;
    }


    /**
     * Set Vector for max
     *
     * @param pos2 String
     * @throws IllegalArgumentException
     */
    public void setMax(String pos2) {
        this.max = pos2;
    }


    /**
     * Get the world name as a string
     *
     * @return String
     */
    public String getWorld() {
        return world;
    }


    /**
     * Set the world name as a string
     *
     * @param world String
     */
    public void setWorld(String world) {
        this.world = world;
    }


    /**
     * Returns if the arena is enabled
     *
     * @return boolean
     */
    public boolean isEnable() {
        return enable;
    }


    /**
     * If to set this arena to enable or not
     *
     * @param enable boolean
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    /**
     * Returns the map name of the arena
     *
     * @return String
     */
    public String getMap() {
        return map;
    }


    /**
     * Sets the map name of the arena
     *
     * @param map String
     */
    public void setMap(String map) {
        this.map = map;
    }


    /**
     * Get the permission of the arena
     *
     * @return String
     */
    public String getPerm() {
        return Perm;
    }


    /**
     * Set the permission of the arena
     *
     * @param perm String
     */
    public void setPerm(String perm) {
        Perm = perm;
    }


    /**
     * Get the arena id
     *
     * @return Integer
     */
    public Integer getArenaId() {
        return arenaId;
    }


    /**
     * Set the arena id
     *
     * @param arenaId Integer
     */
    public void setArenaId(Integer arenaId) {
        this.arenaId = arenaId;
        this.setArenaPath();
    }


    /**
     * Set the arena path for storage
     */
    public void setArenaPath() {

        this.arenaPath = ("arena.arenas." + getArenaId());
    }


    /**
     * Get the arena path for to use for looking up from storage
     *
     * @return String
     */
    public String getArenaPath() {
        return arenaPath;
    }


    /**
     * Returns Max Points as a Vector
     *
     * @return Vector
     */
    public Vector getvMax() {
        return vMax;
    }


    /**
     * Set Vector for the max points
     *
     * @param vMax Vector
     */
    public void setvMax(Vector vMax) {
        this.vMax = vMax;
    }


    /**
     * Returns Min Points as a Vector
     *
     * @return Vector
     */
    public Vector getvMin() {
        return vMin;
    }


    /**
     * Set Vector for the min points
     *
     * @param vMin Vector
     */
    public void setvMin(Vector vMin) {
        this.vMin = vMin;
    }


    /**
     * AdminSpawn Vector
     *
     * @return Vector
     */
    public Vector getvTop() {
        return vTop;
    }


    /**
     * Set Vector for admin spawn
     *
     * @param vTop Vector
     */
    public void setvTop(Vector vTop) {
        this.vTop = vTop;
    }


    /**
     * Set Admin Spawn as string
     *
     * @param st String
     */
    public void setTop(String st) {

        this.top = st;
    }


    /**
     * Get Admin Spawn as a String representing a Vector
     *
     * @return String
     */
    public String getTop() {

        return this.top;
    }
}
