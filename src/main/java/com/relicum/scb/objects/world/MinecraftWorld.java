package com.relicum.scb.objects.world;

import lombok.Data;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;

/**
 * SuperSkyBros First Created 29/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public
@Data
class MinecraftWorld {

    private World world;

    private worldSettings ws;


    public MinecraftWorld(worldSettings w) {
        this.ws = w;
    }


    public void setWorldSpawnLocation() {
        String sp[] = ws.getSpawn().split(",");
        this.world.setSpawnLocation(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]), Integer.parseInt(sp[2]));

    }


    public void keepSpawnInMemory() {
        this.world.setKeepSpawnInMemory(this.ws.isInMemory());
    }


    public void setSpawnFlags() {
        this.world.setSpawnFlags(ws.isAnimals(), ws.isMonsters());
    }


    public void setDifficulty() {
        this.world.setDifficulty(Difficulty.valueOf(ws.getDifficulty().toUpperCase()));
    }


    public void setPVP() {
        this.world.setPVP(ws.isPvp());


    }


    private Location locationConvert(String l) {

        String sp[] = l.split(",");
        Integer i1 = Integer.parseInt(sp[0]);
        Integer i2 = Integer.parseInt(sp[1]);
        Integer i3 = Integer.parseInt(sp[2]);

        return new Location(this.world, i1 + 0.5, i2 + 0.5, i3 + 0.5);
    }


}
