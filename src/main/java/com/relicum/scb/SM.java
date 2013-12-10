package com.relicum.scb;

import com.relicum.scb.configs.*;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.SerializedLocation;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Settings manager 2.
 */
public class SM {

    private static SM instance;

    /**
     * Main Plugin Configs
     */
    private FileConfiguration config;

    /**
     * Default Data Folder
     */
    private File dataFolder;

    /**
     * The Lobby config.
     */
    private LobbyConfig lobbyConfig;

    /**
     * The Sign config.
     */
    private SignConfig signConfig;

    /**
     * The Sign format config.
     */
    private SignFormat signFormatConfig;

    /**
     * The Spawn config.
     */
    private SpawnConfig spawnConfig;

    /**
     * The Arena config.
     */
    private ArenaConfig arenaConfig;


    private boolean dedicated;

    /**
     * Blacklisted Worlds
     */
    private List<String> worldBlackListed = new ArrayList<>();

    /**
     * Worlds that are dedicated to SSB only
     */
    private List<String> ssbWorlds = new ArrayList<>();


    /**
     * The Worlds Config.
     */
    private WorldConfig worldConfig;


    private boolean useWorldManagement;
    private boolean generateDefaultWorld;

    /**
     * Instantiates a new Settings manager 2.
     */
    public SM() {
        setup();
    }


    /**
     * Sets .
     */
    public void setup() {
        config = SkyApi.getSCB().getConfig();
        dataFolder = SkyApi.getSCB().getDataFolder();
        worldBlackListed.addAll(config.getStringList("ignoreWorlds"));
        ssbWorlds.addAll(config.getStringList("dedicatedSSBWorlds"));
        dedicated = config.getBoolean("dedicatedSSB");
        setgenerateDefaultWorld(config.getBoolean("generateDefaultWorld"));
        setUseWorldManagement(config.getBoolean("useWorldManager"));


        if (isUseWorldManagement()) {
            worldConfig = new WorldConfig("worlds.yml");
            worldConfig.getConfig().options().copyDefaults(true);
            worldConfig.saveDefaultConfig();


        }
        lobbyConfig = new LobbyConfig("lobby.yml");
        lobbyConfig.getConfig().options().copyDefaults(true);
        lobbyConfig.saveDefaultConfig();


        signConfig = new SignConfig("signs.yml");
        signConfig.getConfig().options().copyDefaults(true);
        signConfig.saveDefaultConfig();


        signFormatConfig = new SignFormat("signsText.yml");
        signFormatConfig.getConfig().options().copyDefaults(true);
        signFormatConfig.saveDefaultConfig();

/*
        spawnConfig = new SpawnConfig("spawns.yml");
        spawnConfig.getConfig().options().copyDefaults(true);
        spawnConfig.reloadConfig();

        arenaConfig = new ArenaConfig("arena.yml");
        arenaConfig.getConfig().options().copyDefaults(true);
        arenaConfig.reloadConfig();*/

    }


    public void setSerializedWorldSpawnLocation(SerializedLocation location, String name) {

        worldConfig.getConfig().set("worlds." + name + ".spawnLocation", location);
        worldConfig.saveConfig();
    }

    /**
     * Gets lobby spawn.
     *
     * @return the lobby spawn
     */
    public Location getLobbySpawn() {
        return ((SerializedLocation) lobbyConfig.getConfig().get("lobby.box.spawn")).getLocation();
    }


    /**
     * Gets lobby config.
     *
     * @return the lobby config
     */
    public LobbyConfig getLobbyConfig() {
        return lobbyConfig;
    }


    /**
     * Sets lobby config.
     *
     * @param lobbyConfig the lobby config
     */
    public void setLobbyConfig(LobbyConfig lobbyConfig) {
        this.lobbyConfig = lobbyConfig;
    }


    /**
     * Gets arena config.
     *
     * @return the arena config
     */
    public ArenaConfig getArenaConfig() {
        return arenaConfig;
    }

    /**
     * Sets arena config.
     *
     * @param arenaConfig the arena config
     */
    public void setArenaConfig(ArenaConfig arenaConfig) {
        this.arenaConfig = arenaConfig;
    }


    /**
     * Gets spawn config.
     *
     * @return the spawn config
     */
    public SpawnConfig getSpawnConfig() {
        return spawnConfig;
    }


    /**
     * Sets spawn config.
     *
     * @param spawnConfig the spawn config
     */
    public void setSpawnConfig(SpawnConfig spawnConfig) {
        this.spawnConfig = spawnConfig;
    }


    /**
     * Gets sign format config.
     *
     * @return the sign format config
     */
    public SignFormat getSignFormatConfig() {
        return signFormatConfig;
    }


    /**
     * Sets sign format config.
     *
     * @param signFormatConfig the sign format config
     */
    public void setSignFormatConfig(SignFormat signFormatConfig) {
        this.signFormatConfig = signFormatConfig;
    }


    /**
     * Gets sign config.
     *
     * @return the sign config
     */
    public SignConfig getSignConfig() {
        return signConfig;
    }


    /**
     * Sets sign config.
     *
     * @param signConfig the sign config
     */
    public void setSignConfig(SignConfig signConfig) {
        this.signConfig = signConfig;
    }


    /**
     * Gets world config.
     *
     * @return the world config
     */
    public WorldConfig getWorldConfig() {
        return worldConfig;
    }


    /**
     * Sets world config.
     *
     * @param worldConfig the world config
     */
    public void setWorldConfig(WorldConfig worldConfig) {
        this.worldConfig = worldConfig;
    }

    /**
     * Should we use inbuilt world Management
     *
     * @return the boolean
     */
    public boolean isUseWorldManagement() {
        return useWorldManagement;
    }

    /**
     * Sets if we should use inbuilt world management
     *
     * @param useWorldManagement the use world management
     */
    public void setUseWorldManagement(boolean useWorldManagement) {
        this.useWorldManagement = useWorldManagement;
    }

    /**
     * Is generate default world. True or false
     *
     * @return the boolean if true we will be generating the default world
     */
    public boolean isGenerateDefaultWorld() {
        return generateDefaultWorld;
    }

    /**
     * Sets default world to be true or false. Eg True means we will set default world.
     *
     * @param boolean should we generate default world
     */
    public void setgenerateDefaultWorld(boolean generateDefaultWorld) {
        this.generateDefaultWorld = generateDefaultWorld;
    }

    /**
     * Black listed Worlds
     *
     * @return the list of worlds black listed
     */
    public List<String> blackListed() {
        return worldBlackListed;
    }


    /**
     * Gets ssb worlds list
     *
     * @return the ssb worlds list contain worlds dedicated to SSB
     */
    public List<String> getSsbWorlds() {
        return ssbWorlds;
    }

    /**
     * Is server in dedicated mode
     *
     * @return true if the server is in dedicated mode
     */
    public boolean isDedicated() {
        return dedicated;
    }

    /**
     * Sets ssb worlds list
     */
    public boolean addWorldToWhiteList(String w) {

        if (blackListed().contains(w)) {
            blackListed().remove(w);
            config.set("ignoreWorlds", blackListed());
        }
        try {
            if (!ssbWorlds.contains(w)) {
                ssbWorlds.add(w);
                config.set("dedicatedSSBWorlds", ssbWorlds);
                SkyApi.getCommandManager().resetWhiteList();
                SkyApi.getCMsg().INFO("World " + w + " has been successfully added to the white list");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets plugin config.
     *
     * @return the plugin config
     */
    public FileConfiguration getPluginConfig() {
        return config;
    }

    public boolean addWorldToBlackList(String w) {
        if (ssbWorlds.contains(w)) {
            ssbWorlds.remove(w);
            config.set("dedicatedSSBWorlds", ssbWorlds);

        }
        try {
            if (!blackListed().contains(w)) {
                blackListed().add(w);
                config.set("ignoreWorlds", blackListed());
                SkyApi.getCommandManager().resetWhiteList();

                SkyApi.getCMsg().INFO("World " + w + " has been successfully added to the white list");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Remove world form black and white list.
     *
     * @param w the w
     * @return the boolean
     */
    public boolean removeWorldFormBlackAndWhiteList(String w) {


        try {
            if (ssbWorlds.contains(w)) {
                ssbWorlds.remove(w);
            }
            if (blackListed().contains(w)) {
                blackListed().remove(w);
            }

            config.set("dedicatedSSBWorlds", ssbWorlds);
            config.set("ignoreWorlds", blackListed());

            return true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public void loadFile(File file) {

        File t = file;


        SkyApi.getCMsg().INFO("Writing new file: " + t.getAbsolutePath());


        if (!t.exists()) {

            try {
                t.createNewFile();
                FileWriter out = new FileWriter(t);
                System.out.println(file);
                InputStream is = getClass().getResourceAsStream("/" + file);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    out.write(line + "\n");
                    System.out.println(line);
                }
                out.flush();
                is.close();
                isr.close();
                br.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
