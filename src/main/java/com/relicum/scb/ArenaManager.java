package com.relicum.scb;

import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Manages Arenas
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaManager {

    public SCB plugin;

    public Integer total;

    private Map<Integer, Arena> thearenas;

    private Map<String, Integer> nameToId = new HashMap<>();

    private Set<String> arenaIds;


    private Integer current;


    public ArenaManager(SCB p) {

        this.plugin = p;
        setup();

    }


    public void setup() {


        this.setCurrent(0);
        this.total = SCB.getInstance().ARC.getConfig().getInt("arena.total");
        if (total > 0) {
            ArenaIO Aio = new ArenaIO();
            thearenas = Aio.loadArenas();
            arenaIds = Aio.getArenaKeys();
            for ( String k : arenaIds ) {
                String na = thearenas.get(Integer.parseInt(k)).getUniqueMap();
                nameToId.put(na, Integer.parseInt(k));
            }


        } else {
            plugin.getLogger().info("No Arenas were loaded, most likely none have been created");

        }
    }


    public boolean removeArena(Integer i, String n) {

        this.thearenas.remove(i);
        this.nameToId.remove(n);
        return true;
    }


    /**
     * Get an arena by Id
     *
     * @param i Integer
     * @return Arena
     */
    public Arena getArenaById(Integer i) {

        return this.thearenas.get(i);
    }


    public Arena getArenaByName(String s) {

        return thearenas.get(nameToId.get(s));
    }


    public ArrayList<String> getListMessages() {
        ArrayList<String> mess = new ArrayList<String>();


        for ( String k : arenaIds ) {
            Arena ad = this.getArenaById(Integer.parseInt(k));
            mess.add(ad.getArenaListMessage());
        }
        return mess;
    }


    /**
     * Gets current Arena ID stored by modify command
     *
     * @return Integer
     */
    public Integer getCurrent() {
        return current;
    }


    /**
     * Sets Arena ID that you wish to modify
     *
     * @param current Integer
     */
    public void setCurrent(Integer current) {
        this.current = current;
    }


    public boolean checkBlockLocation(org.bukkit.util.Vector v) {

        return this.getArenaById(this.getCurrent()).getAreg().isAABB(v);
    }


    /**
     * Add new arena on the fly.
     *
     * @param Arena the ar
     * @return the boolean
     */
    public boolean addNewArena(Arena ar) {

        if (this.total < 1) {
            setup();
        } else {

            this.total = this.total + 1;
            this.nameToId.put(ar.getUniqueMap(), ar.getArenaId());
            this.thearenas.put(ar.getArenaId(), ar);
            this.arenaIds.add(ar.getArenaId().toString());
        }
        return true;
    }


    /**
     * Returns Set of Arena Id's
     *
     * @return Set<String>
     */
    public Set<String> getArenaIds() {
        return this.arenaIds;
    }


    /**
     * Save an ArenaById
     *
     * @param Integer
     */
    public void saveArenaById(Integer i) {

        Arena arena = getArenaById(i);
        ArenaIO AI = new ArenaIO();
        AI.saveArena(arena);
    }


    public Map<Integer, Arena> getAllArenas() {

        return thearenas;

    }


}

