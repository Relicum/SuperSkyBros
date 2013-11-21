package com.relicum.scb.objects.spawns;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Tameable;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity Spawning
 *
 * @author alkarinv
 * @version 3.8.8
 */
public class EntitySpawn extends SpawnInstance {

    private EntityType et;

    int number = 1;


    List<LivingEntity> uids = new ArrayList<LivingEntity>();


    /**
     * Constructor
     *
     * @param et EntityType
     */
    public EntitySpawn(EntityType et) {
        super(null);
        this.et = et;
    }


    /**
     * Constructor
     *
     * @param et     EntityType
     * @param number int
     */
    public EntitySpawn(EntityType et, int number) {
        super(null);
        this.et = et;
        this.number = number;
    }


    /**
     * Trys To Spawn an Instance of an Entity by ID If it's still alive, clears the ID if it's not. Or spawns the entity
     * at the Location and returns the UID
     *
     * @return Integer
     */
    public int spawn() {
        for (LivingEntity id : uids) {
            if (!id.isDead()) {
                return spawnId;
            } /// The entities are already spawned
        }
        uids.clear();
        for (int i = 0; i < number; i++) {
            LivingEntity le = (LivingEntity) loc.getWorld().spawnEntity(loc, et);
            uids.add(le);
        }
        return spawnId;
    }


    /**
     * Despawns the Entity and removes it.
     */
    public void despawn() {
        for (LivingEntity id : uids) {
            if (!id.isDead()) {
                id.remove();
            }
        }
        uids.clear();
    }


    /**
     * The entity is tamed if it's possible
     *
     * @param tamer AnimalTamer
     */
    public void setOwner(AnimalTamer tamer) {
        for (LivingEntity id : uids) {
            if (!id.isDead() && id instanceof Tameable) {
                ((Tameable) id).setTamed(true);
                ((Tameable) id).setOwner(tamer);
            }
        }
    }


    /**
     * Returns the entity by String name
     *
     * @return String
     */
    @Deprecated
    public String getEntityString() {
        return et.getName();
    }


    /**
     * Returns the Entity ID
     *
     * @return int
     */
    public int getNumber() {
        return number;
    }


    @Override
    public String toString() {
        return "[EntitySpawn " + et + ":" + number + "]";
    }
}
