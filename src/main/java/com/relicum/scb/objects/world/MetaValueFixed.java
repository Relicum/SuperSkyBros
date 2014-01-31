package com.relicum.scb.objects.world;

import com.relicum.scb.types.SkyApi;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

/**
 * SuperSkyBros First Created 30/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class MetaValueFixed {


    private String setSpawnLocation;

    private int setAnimalSpawnLimit;

    private int setMonsterSpawnLimit;

    private String setDifficulty;

    private boolean setKeepSpawnInMemory;

    private boolean setAutoSave;

    private int setAmbientSpawnLimit;

    private boolean setPVP;

    private String setDefaultGameMode;

    private Long getSeed;

    private boolean allowMonsters;

    private boolean allowAnimals;

    private boolean setStorm;

    private int setThunderDuration;

    private boolean setThundering;

    private String setInitialSpawn;

    private String setSpawnBlock;

    private int setWeatherDuration;

    private String generator;

    private String worldName;


    public MetaValueFixed(Plugin owningPlugin, Object value) {
        FixedMetadataValue fv = new FixedMetadataValue(SkyApi.getSCB(), SkyApi.getSCB().getServer().getWorld("Template"));

        WorldMeta wm = new WorldMeta();
        wm.getMetadata("ssbworldV1");
        wm.setMetadata("ssbworldV1-spawn", (MetadataValue) fv.value());


    }


}
