package com.relicum.scb;

import com.relicum.scb.configs.Lobby2Config;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.SerializedLocation;
import org.bukkit.Location;
import org.bukkit.event.Listener;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class LobbyController implements Listener {

    private Lobby2Config config;

    public LobbyController() {

        this.config = SkyApi.getSm().getLobby2Config();
    }

    public Location getLobbySpawn() {
        return ((SerializedLocation) SkyApi.getSm().getLobbyConfig().getConfig().get("lobby.box.spawn")).getLocation();
    }

}
