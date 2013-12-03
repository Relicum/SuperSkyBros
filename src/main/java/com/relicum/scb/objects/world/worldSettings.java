package com.relicum.scb.objects.world;

import lombok.experimental.Builder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type World settings.
 */
public
@Builder(fluent = true, chain = true)
class worldSettings {

    private Map<String, Object> map = new HashMap<>();

    private String spawn = "0,32,0";

    private String spawnBlock = "GOLD_BLOCK";

    private boolean inMemory = true;

    private String blockLocation = "0,31,0";

    private boolean monsters = false;

    private boolean animals = false;

    private String difficulty = "NORMAL";

    private String levelType = "FLAT";

    private String environment = "NORMAL";

    private Long seed;

    private int maxHeight = 256;

    private boolean pvp = true;

    private int gameMode = 2;

    private int view = 7;

    private boolean structures = false;

    private int protection = 16;

    private String generator = "CleanroomGenerator";

    private boolean hardcore = false;

    private boolean autoSave = true;

    private String name;

    private boolean allowedNether = false;

    private boolean allowedTheEnd;

    private boolean canFly;

    private Integer maxPlayers;

    private long worldTime;

    private boolean whitelist;

    private boolean onlineMode;

    private Integer spawnProtection;

    private String weather;

    private long weatherDuration;

    private List<Entity> worldEntity;

    private List<LivingEntity> livingEntityList;

    private String motd;

    private boolean commandBlock;

    /**
     * Instantiates a new World settings.
     */
    public Map<String, Object> worldSet() {

        map.put("getSpawnLocation", "0,31,0");
        map.put("isSpawnInMemory", true);
        map.put("monsters", true);
        map.put("animals", true);
        map.put("difficulty", "NORMAL");
        map.put("levelType", "FLAT");
        map.put("environment", "NORMAL");
        map.put("seed", seed);
        map.put("worldHeight", maxHeight);
        map.put("pvp", true);
        map.put("gameMode", 2);
        map.put("viewDistance", 7);
        map.put("structures", false);
        map.put("generator", "CleanroomGenerator");
        map.put("hardcore", false);
        map.put("autoSave", true);
        map.put("WorldName", name);
        map.put("allowedNether", false);
        map.put("allowedTheEnd", false);
        map.put("whitelist", false);
        map.put("online-mode", true);
        map.put("server-ip", "127.0.0.1");
        map.put("spawn-protection", 16);
        map.put("motd", "A Super Craft Bros Server");
        map.put("commandBlock", false);

        return map;
    }


    /**
     * Apply settings.
     *
     * @return the boolean
     */
    public boolean applySettings() {

        World world = Bukkit.getWorld(name);


        return true;
    }

    private boolean loadChunk(int x, int z) {
        try {
            Bukkit.getWorld(this.name).loadChunk(x, z);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public String getSpawn() {
        return spawn;
    }
}
