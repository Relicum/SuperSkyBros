package com.relicum.scb;

import com.relicum.scb.configs.LobbyConfig;
import com.relicum.scb.mini.SerializedLocation;
import com.relicum.scb.types.SkyApi;
import org.bukkit.Location;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class LobbyController {


    private LobbyConfig config;


    public LobbyController() {

        this.config = SkyApi.getSm().getLobbyConfig();
    }


    public Location getLobbySpawn() {
        return ((SerializedLocation) SkyApi.getSm().getLobbyConfig().getConfig().get("lobby.box.spawn")).getLocation();
    }


}
