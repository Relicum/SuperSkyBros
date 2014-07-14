package com.relicum.scb.mini;

import com.relicum.scb.SCB;
import com.relicum.scb.configs.SignConfig;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.SerializedLocation;

/**
 * SuperSkyBros First Created 13/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SignLocationStore {

    private SCB plugin;

    private SignConfig storage;

    private SerializedLocation lobby;


    public SignLocationStore(SCB plugin) {
        this.plugin = plugin;
        this.storage = SkyApi.getSm().getSignConfig();

    }


    private void load() {
        if (!storage.getConfig().contains("lobby")) {
            storage.getConfig().set("lobby", new SerializedLocation("world", 0, 65, 0, 2.0f, 76.9f));
            storage.saveConfig();
            storage.reloadConfig();
            this.lobby = (SerializedLocation) storage.getConfig().get("lobby");
        }
        this.lobby = (SerializedLocation) storage.getConfig().get("lobby");
        storage.getConfig().set(
                "worldSpawn", new SerializedLocation(
                        plugin.getServer().getWorld("world").getSpawnLocation()));
        storage.saveConfig();
        storage.reloadConfig();
        System.out.println(storage.getConfig().get("worldSpawn").toString());
    }


}
