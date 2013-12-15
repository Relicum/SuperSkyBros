package com.relicum.scb;


import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.MessageManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Settings Class that gives all other class access To any settings that they need
 *
 * @author Relicum
 * @version 0.1
 */
@SuppressWarnings({"UnusedDeclaration", "WeakerAccess"})
public class SettingsManager {

    /**
     * Hold the static p of the Settings Manager
     */
    private static SettingsManager instance = new SettingsManager();

    private static SCB p;

    private MessageManager mess;

    //private LobbyManager lobby;
    private ArenaManager arena;

    private File f;

    private FileConfiguration fc;

    //private Messages messCon;
    private List<String> bWorlds = new ArrayList<>();


    /**
     * Default Constructor This should never be called Use getInstance method to create p
     */
    SettingsManager() {

    }


    /**
     * Used to get an p of the Settings Manager IIt uses the Singleton Pattern so only a single p of this will ever be
     * in memory and can be shared with any class without having to create a new p every time
     *
     * @return SettingsManager
     */
    public static SettingsManager getInstance() {

        return instance;
    }


    /**
     * Just at the start to setup all the configs And settings
     *
     * @param p SCB
     */
    public void setup(SCB p) {


        SettingsManager.p = p;
        //setupMessageManager(p);


        //Load Config from config Yml
        //p.getConfig().options().copyDefaults(true);
        //p.saveDefaultConfig();


   /*     fileExists("arena.yml");
        arenaCon = new Arena(p, "arena.yml");
        arenaCon.getConfig().options().copyDefaults(true);
        arenaCon.saveDefaultConfig();
        p.getLogger().info("Arena Config Successfully Loaded");*/

/*        fileExists("spawns.yml");
        spawnCon = new Spawns(p, "spawns.yml");
        spawnCon.getConfig().options().copyDefaults(true);
        spawnCon.saveDefaultConfig();
        p.getLogger().info("Spawns Config Successfully Loaded");*/

/*		fileExists("messages.yml");
        messCon = new Messages(p, "messages.yml");
		messCon.getConfig().options().copyDefaults(true);
		messCon.saveDefaultConfig();
		p.getLogger().info("Messages Config Successfully Loaded");*/

        bWorlds = p.getConfig().getStringList(SCB.IGNORE_WORLDS);
        //setupLobbyManager(p);
        //setupArenaManager(p);


    }


    private void setupArenaManager(SCB p) {

        arena = new ArenaManager();
    }


    private void setupMessageManager(SCB p) {

        mess = new MessageManager();

    }

/*	public MessageManager getMessageManager2() {

		return mess;
	}*/

/*    private void setupLobbyManager(SCB p) {

        lobby = new LobbyManager(p);
    }*/

/*    public LobbyManager getLobbyManager() {

        return lobby;
    }*/

/*	public ArenaManager getArenaManager() {

		return arena;
	}

	*//**
     * Chain this with getInstance to access all Arena settings
     *
     * @return Arena
     *//*
    public Arena getArena() {

		return arenaCon;
	}*/

    /**
     Chain this with getInstance to access Message Settings

     @return Messages
     */
/*	public Messages getMessages() {

		return messCon;
	}*/

    /**
     * Chain this with getInstance to access Spawns Settings
     *
     * @return Spawns
     */
/*    public Spawns getSpawns() {

        return spawnCon;
    }*/


    /**
     * Chain this with getInstance to access default configs
     *
     * @return FileConfiguration
     */
    public FileConfiguration getConfig() {

        return p.getConfig();
    }


    /**
     * Checks To see if a file exists If not it creates it
     *
     * @param fi String
     */

    void fileExists(String fi) {

        File file = new File(p.getDataFolder(), fi);
        FileConfiguration fCon;
        try {
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
                fCon = YamlConfiguration.loadConfiguration(p.getResource(fi));
                fCon.save(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Returns an array list of worlds specified not to be have SCB running in
     *
     * @return ArrayList<String>
     */
    public List<String> notWorlds() {

        return bWorlds;
    }


    /**
     * Check if a world is on the black list of Worlds Not to run SCB commands on. True if the world is in the list
     * false if not.
     *
     * @param w String
     * @return boolean
     */
    public boolean isWorldBlackListed(String w) {

        return notWorlds().contains(w);
    }


    public Integer setTotalArenas() {

        Integer to = SkyApi.getSm().getArenaConfig().getConfig().getInt("arena.total");

        return to;

    }

}
