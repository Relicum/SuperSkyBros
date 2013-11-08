package com.relicum.scb.objects.inventory;

import com.relicum.scb.SCB;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlMap;
import org.bukkit.entity.Player;

import java.io.File;
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

    private XmlMap xmap;

    private PersistenceStrategy strategy;


    public InventoryManager() {

        //path = SCB.getInstance().getDataFolder() + "/players/players.xml";
        // this.reader = XStreamReader.getFi(path);
        //this.writer = XStreamWriter.getFos(path);

        XStream xstream = new XStream(new Xpp3Driver());

        xstream.alias("Storage", StorePlayerSettings.class);


        this.strategy = new FilePersistenceStrategy(new File(SCB.getInstance().getDataFolder() + "/players/"), xstream);
        this.xmap = new XmlMap(this.strategy);

    }


    public boolean isInStore(String name) {
        return this.xmap.containsKey(name);
    }


    public StorePlayerSettings getPlayersStore(String name) {

        return (StorePlayerSettings) xmap.get(name);

    }


    public void removePlayerFromStore(String name) {
        this.xmap.remove(name);
    }


    public boolean storeOldInventory(Player player) {


        if (this.playerStore.containsKey(player.getName()))
            System.out.println("Player already stored but we will update it");

        StorePlayerSettings ps = PlayerStore.storePlayer(player);


        this.xmap.put(player.getName(), ps);

        return true;

    }


}
