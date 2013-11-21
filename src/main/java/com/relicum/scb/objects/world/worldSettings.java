package com.relicum.scb.objects.world;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 * SuperSkyBros First Created 29/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public
@Data
class worldSettings {

    private String spawn = "0,65,0";

    private String spawnBlock = "GLASS";

    private boolean inMemory = true;

    private String blockLocation = "0,64,0";

    private boolean monsters = false;

    private boolean animals = false;

    private String difficulty = "NORMAL";

    private String levelType = "FLAT";

    private String environment = "NORMAL";

    private Long seed;

    private int maxHeight = 256;

    private boolean pvp = true;

    private int gameMode = 0;

    private int view = 10;

    private boolean structures = false;

    private int protection = 16;

    private String generator = "CleanroomGenerator";

    private boolean hardcore = false;

    private boolean autoSave = false;

    private String name;


    public worldSettings() {


    }


    public boolean applySettings() {

        World world = Bukkit.getWorld(this.getName());
        this.setSeed(world.getSeed());

        return true;
    }

}
