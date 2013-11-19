package com.relicum.scb.mini;

import com.relicum.scb.configs.*;
import com.relicum.scb.types.SkyBrosApi;
import org.bukkit.Location;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SettingsManager2 {


    private LobbyConfig lobbyConfig;

    private SignConfig signConfig;

    private SignFormat signFormatConfig;

    private SpawnConfig spawnConfig;

    private ArenaConfig arenaConfig;


    public SettingsManager2() {
        setup();
    }


    public void setup() {

        SkyBrosApi.getSCB().saveDefaultConfig();
        SkyBrosApi.getSCB().getConfig().options().copyDefaults(true);

        lobbyConfig = new LobbyConfig("lobby.yml");
        lobbyConfig.getConfig().options().copyDefaults(true);
        lobbyConfig.reloadConfig();

        signConfig = new SignConfig("signs.yml");
        signConfig.getConfig().options().copyDefaults(true);
        signConfig.reloadConfig();

        signFormatConfig = new SignFormat("signsText.yml");
        signFormatConfig.getConfig().options().copyDefaults(true);
        signFormatConfig.reloadConfig();

        spawnConfig = new SpawnConfig("spawns.yml");
        spawnConfig.getConfig().options().copyDefaults(true);
        spawnConfig.reloadConfig();

        arenaConfig = new ArenaConfig("arena.yml");
        arenaConfig.getConfig().options().copyDefaults(true);
        arenaConfig.reloadConfig();

    }


    public Location getLobbySpawn() {
        return ((SerializedLocation) lobbyConfig.getConfig().get("lobby.box.spawn")).getLocation();
    }
}
