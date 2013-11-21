package com.relicum.scb.objects.spawns;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores a group of spawns what have a common goal For example Spawn Points in an Arena
 *
 * @author Relicum
 * @version 0.2
 */
public class SpawnGroup extends SpawnInstance implements Spawnable {

    /**
     * Stores the name of the SpawnGroup
     */
    private String name;

    /**
     * Stores An ArrayList to hold the instances of SpawnInstance
     */
    private List<SpawnInstance> spawns = new ArrayList<SpawnInstance>();


    /**
     * Constructor with name of Spawn Group
     *
     * @param name String
     */
    public SpawnGroup(String name) {
        super(null);
        this.name = name;
    }


    /**
     * Constructor with a new SpawnInstance to add to the group
     *
     * @param spawn SpawnInstance
     */
    public SpawnGroup(SpawnInstance spawn) {
        super(null);
        spawns.add(spawn);
    }


    /**
     * Add a list of SpawnInstance's
     *
     * @param _spawns List<SpawnInstance>
     */
    public void addSpawns(List<SpawnInstance> _spawns) {
//		System.out.println(" adding all spawns " + _spawns);
        spawns.addAll(_spawns);
    }


    /**
     * Return name of Spawn Group
     *
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * Despawn each spawn and remove entities
     */
    public void despawn() {
        for (Spawnable spawn : spawns) spawn.despawn();
    }


    /**
     * Spawn all the SpawnInstance's in group returning The Spawn Id
     *
     * @return int
     */
    public int spawn() {
        for (Spawnable spawn : spawns) {
            spawn.spawn();
        }
        return spawnId;
    }


    /**
     * Set the Location for all the SpawnInstance's
     *
     * @param l Location
     */
    @Override
    public void setLocation(Location l) {
        this.loc = l;
        for (SpawnInstance spawn : spawns) {
            spawn.setLocation(l);
        }
    }


    /**
     * Custom toString to be used to store the SpawnGroup
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[SpawnGroup " + name + " ");
        boolean first = true;
        for (Spawnable spawn : spawns) {
            if (!first) sb.append(", ");
            else first = false;
            sb.append(spawn.toString());
        }
        sb.append("]");
        return sb.toString();
    }


    /**
     * Used to set the group name
     *
     * @param nm String
     */
    public void setName(String nm) {

        this.name = nm;

    }
}
