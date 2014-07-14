package com.relicum.scb.objects.inventory;

import org.bukkit.entity.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * SuperSkyBros First Created 03/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class InventoryManager {

    private FileInputStream reader;

    private FileOutputStream writer;

    private String path;

    private Map<String, StorePlayerSettings> playerStore = new HashMap<>();

    // private XmlMap xmap;

    // private PersistenceStrategy strategy;

    public InventoryManager() {

        // path = SkyApi.getSCB().getDataFolder() + "/players/players.xml";
        // this.reader = XStreamReader.getFi(path);
        // this.writer = XStreamWriter.getFos(path);

        // XStream xstream = new XStream(new Xpp3Driver());

        // xstream.alias("Storage", StorePlayerSettings.class);

        // this.strategy = new FilePersistenceStrategy(new
        // File(SkyApi.getSCB().getDataFolder() + "/players/"), xstream);
        // this.xmap = new XmlMap(this.strategy);

    }

    public boolean isInStore(String name) {
        return false; // this.xmap.containsKey(name);
    }

    public StorePlayerSettings getPlayersStore(String name) {
        return null;
        // return (StorePlayerSettings) xmap.get(name);

    }

    public void removePlayerFromStore(String name) {
        // this.xmap.remove(name);
    }

    public boolean storeOldInventory(Player player) {

        if (this.playerStore.containsKey(player.getName()))
            System.out.println("Player already stored but we will update it");

        StorePlayerSettings ps = PlayerStore.storePlayer(player);

        // this.xmap.put(player.getName(), ps);

        return true;

    }

}
