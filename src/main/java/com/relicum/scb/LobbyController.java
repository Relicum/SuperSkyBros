package com.relicum.scb;

import com.relicum.scb.configs.LobbyConfig;
import com.relicum.scb.mini.SerializedLocation;
import org.bukkit.Location;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class LobbyController {


    private LobbyConfig config;

    private SCB plugin;


    public LobbyController() {


        this.plugin = SCB.getInstance();
        this.config = new LobbyConfig("lobby.yml");
        this.config.getConfig().options().copyDefaults(true);
        this.config.saveConfig();

    }


    public Location getLobbySpawn() {
        return ((SerializedLocation) config.getConfig().get("lobby.box.spawn")).getLocation();
    }


}
