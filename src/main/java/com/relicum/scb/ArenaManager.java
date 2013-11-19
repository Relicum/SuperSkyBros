package com.relicum.scb;

import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaIO;
import com.relicum.scb.types.SkyBrosApi;
import lombok.Data;

import java.util.*;

/**
 * The type ArenaManager.
 */
public
@Data
class ArenaManager {

    /**
     * The Plugin.
     */
    public SCB plugin;

    /**
     * The Total.
     */
    public Integer total;

    /**
     * The Thearenas.
     */
    private Map<Integer, Arena> thearenas;

    /**
     * The Name to id.
     */
    private Map<String, Integer> nameToId = new HashMap<>();

    /**
     * The Arena ids.
     */
    private Set<String> arenaIds;


    /**
     * The Current.
     */
    private Integer current;


    /**
     * Instantiates a new Arena manager.
     *
     * @param p the p
     */
    public ArenaManager() {

        this.plugin = SkyBrosApi.getSCB();
        setup();

    }


    /**
     * Sets .
     */
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


    /**
     * Remove arena.
     *
     * @param i the i
     * @param n the n
     * @return the boolean
     */
    public boolean removeArena(Integer i, String n) {

        this.thearenas.remove(i);
        this.nameToId.remove(n);
        return true;
    }


    /**
     * Gets arena by id.
     *
     * @param i the i
     * @return the arena by id
     */
    public Arena getArenaById(Integer i) {

        return this.thearenas.get(i);
    }


    /**
     * Gets arena by name.
     *
     * @param s the s
     * @return the arena by name
     */
    public Arena getArenaByName(String s) {

        return thearenas.get(nameToId.get(s));
    }


    /**
     * Gets list messages.
     *
     * @return the list messages
     */
    public ArrayList<String> getListMessages() {
        ArrayList<String> mess = new ArrayList<String>();


        for ( String k : arenaIds ) {
            Arena ad = this.getArenaById(Integer.parseInt(k));
            mess.add(ad.getArenaListMessage());
        }
        return mess;
    }


    /**
     * Gets current.
     *
     * @return the current
     */
    public Integer getCurrent() {
        return current;
    }


    /**
     * Sets current.
     *
     * @param current the current
     */
    public void setCurrent(Integer current) {
        this.current = current;
    }


    /**
     * Check block location.
     *
     * @param v the v
     * @return the boolean
     */
    public boolean checkBlockLocation(org.bukkit.util.Vector v) {

        return this.getArenaById(this.getCurrent()).getAreg().isAABB(v);
    }


    /**
     * Add new arena.
     *
     * @param ar the ar
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
     * Gets arena ids.
     *
     * @return the arena ids
     */
    public Set<String> getArenaIds() {
        return this.arenaIds;
    }


    /**
     * Save arena by id.
     *
     * @param i the i
     */
    public void saveArenaById(Integer i) {

        Arena arena = getArenaById(i);
        ArenaIO AI = new ArenaIO();
        AI.saveArena(arena);
    }


    /**
     * Gets all arenas.
     *
     * @return the all arenas
     */
    public Map<Integer, Arena> getAllArenas() {

        return thearenas;

    }


}

