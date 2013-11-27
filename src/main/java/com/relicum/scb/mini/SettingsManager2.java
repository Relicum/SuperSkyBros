package com.relicum.scb.mini;

import com.relicum.scb.configs.*;
import com.relicum.scb.objects.world.BukkitConfig;
import com.relicum.scb.types.SkyBrosApi;
import org.bukkit.Location;

/**
 * The type Settings manager 2.
 */
public class SettingsManager2 {

    private static SettingsManager2 instance;

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


    /**
     * The Worlds Config.
     */
    private WorldConfig worldConfig;

    /**
     * ChtManager
     */
    private WorldConfig chat;

    /**
     *
     */
    private WorldConfig perms;

    /**
     *
     */
    private WorldConfig economy;

    private BukkitConfig bukkitConfig;


    private boolean useWorldManagement;

    /**
     * Instantiates a new Settings manager 2.
     */
    public SettingsManager2() {
        setup();
    }


    /**
     * Sets .
     */
    public void setup() {

        setUseWorldManagement(SkyBrosApi.getSCB().getConfig().getBoolean("useWorldManager"));

        if (isUseWorldManagement()) {
            worldConfig = new WorldConfig("worlds.yml");
            worldConfig.getConfig().options().copyDefaults(true);
            worldConfig.saveConfig();
        }
        lobbyConfig = new LobbyConfig("lobby.yml");
        lobbyConfig.getConfig().options().copyDefaults(true);
        lobbyConfig.saveConfig();

        signConfig = new SignConfig("signs.yml");
        signConfig.getConfig().options().copyDefaults(true);
        signConfig.reloadConfig();

/*        signFormatConfig = new SignFormat("signsText.yml");
        signFormatConfig.getConfig().options().copyDefaults(true);
        signFormatConfig.reloadConfig();

        spawnConfig = new SpawnConfig("spawns.yml");
        spawnConfig.getConfig().options().copyDefaults(true);
        spawnConfig.reloadConfig();

        arenaConfig = new ArenaConfig("arena.yml");
        arenaConfig.getConfig().options().copyDefaults(true);
        arenaConfig.reloadConfig();*/

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
}
