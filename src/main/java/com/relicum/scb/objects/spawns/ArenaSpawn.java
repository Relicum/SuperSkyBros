package com.relicum.scb.objects.spawns;

import com.relicum.scb.utils.SerializedLocation;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Used to Hold Arena Spawn Points Can to Group together
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaSpawn extends SpawnInst {

    private int despawnId;

    private int arenaId = 0;

    private Player pname;

    private int groupId;

    private SerializedLocation loc;

    public ArenaSpawn() {
    }


    /**
     * Constructor
     *
     * @param Vector
     * @param Integer
     * @param String
     * @param SerializedLocation
     */
    public ArenaSpawn(Vector vec, Integer aId, String world, SerializedLocation location) {
        super(vec);
        this.arenaId = aId;
        this.setWorld(world);
        this.loc = location;
    }

    public SerializedLocation getLoc() {
        return loc;
    }

    public void setLoc(SerializedLocation loc) {
        this.loc = loc;
    }

    /**
     * Set world.
     *
     * @param String
     */
    public void setWorld(String s) {
        this.world = s;
    }


    /**
     * Get the Arena ID
     *
     * @return int
     */
    public int getArenaid() {
        return arenaId;
    }


    /**
     * Sets player.
     *
     * @param Player
     */
    public void setPlayer(Player s) {

        this.pname = s;
    }


    /**
     * Gets player.
     *
     * @return Player
     */
    public Player getPlayer() {
        return this.pname;
    }


    /**
     * Despawn
     */
    @Override
    public void despawn() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Spawn player and store the Entity ID to despawn later
     *
     * @return int
     */
    @Override
    public int spawn() {

        Location loc = this.getVector().toLocation(this.getWorld());
        this.pname.teleport(loc);
        return this.pname.getEntityId();

    }


    /**
     * Gets group id. The spawns unique Id in the spawn group
     *
     * @return int the group id
     */
    public int getGroupId() {
        return groupId;
    }


    /**
     * Sets group id.
     *
     * @param int groupId the group id
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }


    public Map<String, Object> getHashStore() {
        Map<String, Object> map = new HashMap<>();
        map.put("groupid", this.getGroupId());
        map.put("world", this.getWorldString());
        map.put("arenaid", this.getArenaid());
        map.put("vector", this.getVector());
        map.put("location", this.getLoc());
        return map;
    }
}
