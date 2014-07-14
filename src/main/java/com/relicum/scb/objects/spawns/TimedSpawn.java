package com.relicum.scb.objects.spawns;

import com.relicum.scb.utils.SerializerUtil;

public class TimedSpawn {

    static int count = 0;
    final int id = count++;
    SpawnInstance sg;
    Long firstSpawnTime, respawnInterval, timeToDespawn;


    public TimedSpawn(long firstSpawnTime, long respawnTime, long timeToDespawn, SpawnInstance sg) {
        this.firstSpawnTime = firstSpawnTime;
        this.sg = sg;
        this.respawnInterval = respawnTime;
        this.timeToDespawn = timeToDespawn;

    }


    public Long getRespawnTime() {
        return respawnInterval;
    }


    public void setRespawnTime(Long timeToNext) {
        this.respawnInterval = timeToNext;
    }


    public Long getFirstSpawnTime() {
        return firstSpawnTime;
    }


    public void setFirstSpawnTime(Long timeToStart) {
        this.firstSpawnTime = timeToStart;
    }


    public void despawn() {
        sg.despawn();
    }


    public int spawn() {
        return sg.spawn();
    }


    public int getId() {
        return id;
    }


    public SpawnInstance getSpawn() {
        return sg;
    }


    public Long getTimeToDespawn() {
        return timeToDespawn;
    }


    @Override
    public String toString() {
        return "[TimedSpawn " + id + " loc=" + SerializerUtil.getLocString(sg.getLocation()) + " sg=" + sg + "]";
    }
}
