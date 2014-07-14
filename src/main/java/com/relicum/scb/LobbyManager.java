package com.relicum.scb;

import com.relicum.scb.configs.LobbyConfig;
import com.relicum.scb.objects.LobbyRg;
import com.relicum.scb.types.SkyApi;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;

import java.util.*;

/**
 * The type Lobby manager.
 */
public class LobbyManager implements Listener {

    /**
     * The World as string
     */
    private String world;

    /**
     * Stores the configs
     */
    private LobbyConfig config;

    private ArrayList<String> pname = new ArrayList<>();

    /**
     * Stores List of players in the Lobby
     */
    private Map<String, Player> players = new HashMap<>();

    private ArrayList<UUID> players2 = new ArrayList<>();

    private LobbyRg lobbyRg;

    /**
     * Constructor
     */
    public LobbyManager() {

        setup();
    }

    public static Permission getLobbyBroadCastPerm() {
        return BroadcastManager.getLobby();
    }

    /**
     * Initial setup, loads lobbyspawn from file and creates lobbyspawn object
     */
    public void setup() {

        // sp = SkyApi.getSCB().Spawns;
        this.config = SkyApi.getSm().getLobbyConfig();

        loadLobbySpawn();

    }

    /**
     * Loads the lobbyspawn and passes it to lobbyspawn object
     *
     * @return boolean
     */
    public boolean loadLobbySpawn() {

        if (config.getConfig().contains("LOBBY.REGION")) {
            Map<String, Object> lrc = config.getConfig().getConfigurationSection("LOBBY.REGION").getValues(true);
            Double ya = (Double) lrc.get("YAW");

            Double pit = (Double) lrc.get("PITCH");
            this.lobbyRg = new LobbyRg((org.bukkit.util.Vector) lrc.get("MIN"), (org.bukkit.util.Vector) lrc.get("MAX"), (org.bukkit.util.Vector) lrc.get("SPAWN"),
                    (String) lrc.get("WORLD"), (String) lrc.get("PERM"), (float) ya.floatValue());

            SkyApi.getCMsg().INFO("Lobby and Lobby Spawn have successfully been loaded");

            return true;
        }

        SkyApi.getCMsg().WARNING("No Lobby has been created and set");

        return false;
    }

    /**
     * Save spawns file.
     *
     * @return boolean
     */
    public boolean saveLobbyFile() {
        try {

            this.config.saveDefaultConfig();

            setup();

        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
            return false;
        }
        return true;

    }

    /**
     * Adds a player to the lobby
     *
     * @param play String
     */
    public void addPlayer(Player player) {

        if (!isInLobby(player)) {
            System.out.println(player.getName());
            players.put(player.getName(), player);
            pname.add(player.getName());
            players2.add(player.getUniqueId());
            System.out.println("Player " + player.getName() + " added to lobby list from line 124 of player lobby");
        }

    }

    /**
     * Remove player. Using Bukkit Player as input
     *
     * @param player the Player
     */
    public void removePlayer(Player player) {

        if (isInLobby(player.getName())) {
            players.remove(player.getName());
            players2.remove(player.getUniqueId());
            pname.remove(player.getName());
            System.out.println("Player " + player.getName() + " removed from lobby list");
        }
    }

    /**
     * Checks to see if a player is already in the lobby using the players name
     *
     * @param play String
     * @return boolean
     */
    public boolean isInLobby(String play) {
        return pname.contains(play);

    }

    /**
     * Checks to see if a player is already in the lobby using the Players
     * object
     *
     * @param player the Player
     * @return the boolean
     */
    public boolean isInLobby(Player player) {
        return pname.contains(player.getName());
    }

    /**
     * Removes all Players from LobbyManager List
     */
    public void removeAllPlayers() {

        if (!players.isEmpty() || !pname.isEmpty() || !players2.isEmpty()) {
            int psize = players.size();
            int pnsize = pname.size();
            int p2size = players2.size();
            pname.clear();
            players.clear();
            players2.clear();
            System.out.println("The list is empty " + psize + " players removed and " + pnsize + " names removed");
        }
    }

    /**
     * Check to see if the given path exists
     *
     * @param path String
     * @return boolean
     */
    public boolean checkExists(String path) {

        if (this.config.getConfig().contains(path)) {
            return true;
        } else {
            System.out.println("Check exists in Lobby manager can't find path");
        }
        return false;
    }

    /**
     * Takes a String and returns the matching World Object
     *
     * @param wo String
     * @return World
     */
    public World getWorld(String wo) {

        return SkyApi.getSCB().getServer().getWorld(wo);

    }

    /**
     * Get players in lobby.
     *
     * @return the list
     */
    public Collection<Player> getPlayersInLobby() {

        return players.values();
    }

    public List<String> getPlayerNamesInLobby() {

        return pname;
    }

    public List<UUID> getPlayersInUUIDList() {

        return players2;
    }

    /**
     * Returns the direction you are looking
     *
     * @param yaw float
     * @return float
     */
    public float getDirection(Float yaw) {
        yaw = yaw / 90;
        yaw = (float) Math.round(yaw);

        if (yaw == -4 || yaw == 0 || yaw == 4) {
            return (0.00F);
        }
        if (yaw == -1 || yaw == 3) {
            return -90.00F;
        }
        if (yaw == -2 || yaw == 2) {
            return -179.00F;
        }
        if (yaw == -3 || yaw == 1) {
            return 90.00F;
        }
        return 5.00F;
    }

    /**
     * Get lobby save object.
     *
     * @return LobbyConfig
     */
    public LobbyConfig getLobbySaveObject() {

        return this.config;
    }

    /**
     * Store lobby region. and make a List of Chunks Encoded in Base64 to allow
     * for easy testing if a block is in the region
     *
     * @param cu
     * @return boolean
     */
    @Deprecated
    public boolean StoreLobbyRegion(LobbyRg cu) {
        Map<String, Object> reg = new HashMap<>();

        return true;
    }

    /**
     * Gets world as string.
     *
     * @return the world as string
     */
    public String getWorldAsString() {
        return this.world;
    }

    /**
     * Sets world as string.
     *
     * @param wo the wo
     */
    public void setWorldAsString(String wo) {
        this.world = wo;
    }

    /**
     * Gets lobby region.
     *
     * @return LobbyRg lobby region
     */
    public LobbyRg getLobbyRg() {
        return lobbyRg;
    }

    public void setLobbyRg(LobbyRg l) {
        this.lobbyRg = l;
    }

    /**
     * Teleports the player to the given location
     *
     * @param p Player
     * @param l Location
     * @return boolean
     * @throws IllegalArgumentException
     */
    public boolean teleportToLobby(final Player p, final Location l) {

        SkyApi.getSCB().getServer().getScheduler().runTaskLater(SkyApi.getSCB(), new Runnable() {

            @Override
            public void run() {
                if (!p.teleport(l)) {
                    System.out.println("Error teleporting player to lobby");
                }
            }
        }, 5L);

        return true;
    }

    public boolean isSmashPlayer(String name) {
        if (pname != null && pname.size() > 0)
            if (pname.contains(name)) {
                return true;
            }
        return false;
    }
}
