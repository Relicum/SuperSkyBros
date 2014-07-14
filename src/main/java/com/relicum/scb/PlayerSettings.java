package com.relicum.scb;

import com.relicum.scb.classes.PlayerType;
import com.relicum.scb.objects.PlayerLocation;
import com.relicum.scb.types.SkyApi;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Player test.
 */
@SerializableAs("PlayerSettings")
public class PlayerSettings implements ConfigurationSerializable {


    private PlayerType playerType;
    private Long firstSeen = null;
    private Long lastSeen;
    private boolean isInLobby;
    private String playerName;
    private boolean seen;
    private PlayerLocation playerLocation;
    private boolean isOp;
    private String world;
    private int kills = 0;
    private int deaths = 0;
    private double kdr = 0.0d;
    private int wins = 0;
    private int gamesPlayed = 0;


    /**
     * Instantiates a new Player settings.
     *
     * @param pt         the pt
     * @param firstS     the first s
     * @param lastS      the last s
     * @param inLobby    the in lobby
     * @param name       the name
     * @param seenBefore the seen before
     * @param pl         the pl
     * @param world      the world
     * @param isOp       the is op
     * @param k          the k
     * @param d          the d
     * @param kd         the kd
     * @param w          the w
     * @param gp         the gp
     */
    protected PlayerSettings(PlayerType pt, Long firstS, Long lastS, boolean inLobby, String name, boolean seenBefore,
                             PlayerLocation pl, String world, boolean isOp, int k, int d, double kd, int w, int gp) {

        this.playerType = pt;
        this.seen = seenBefore;
        this.lastSeen = lastS;
        this.firstSeen = firstS;
        this.isInLobby = inLobby;
        this.playerName = name;
        this.playerLocation = pl;
        this.world = world;
        this.isOp = isOp;
        this.kills = k;
        this.deaths = d;
        this.kdr = kd;
        this.wins = w;
        this.gamesPlayed = gp;

    }

    /**
     * Player test.
     *
     * @return the player test
     */
    public PlayerSettings() {
        if (firstSeen == null) {
            seen = true;
            setFirstSeen();
        }
        setLastSeen();


    }

    /**
     * Player test.
     *
     * @param name the name
     * @return instance of itself for Chaining
     */
    public PlayerSettings(String name) {
        if (firstSeen == null) {
            seen = true;
            setFirstSeen();
        }
        setLastSeen();


    }

    /**
     * Deserialize player test.
     *
     * @param map the map
     * @return the player test
     */
    public static PlayerSettings deserialize(Map<String, Object> map) {

        Object typeObject = map.get("GamerType"),
                firstObject = map.get("seenFirst"),
                lastObject = map.get("seenLast"),
                inLobbyObject = map.get("inLobby"),
                pNameObject = map.get("Gamer"),
                pIsSeenObject = map.get("isSeen"),
                pLocationObject = map.get("GamerLocation"),
                pIsOpObject = map.get("isOp"),
                pWorldObject = map.get("World"),
                pKillsObject = map.get("kills"),
                pDeathsObject = map.get("deaths"),
                pKdrObject = map.get("kdr"),
                pWinsObject = map.get("wins"),
                pGamesPlayedObject = map.get("gamesPlayed");


        if (pNameObject == null || lastObject == null || firstObject == null || typeObject == null || pLocationObject == null
                || pIsOpObject == null || pWorldObject == null || pKillsObject == null || pDeathsObject == null || pKdrObject == null
                || pWinsObject == null || pGamesPlayedObject == null) {
            return null;
        }

        PlayerType playerType = PlayerType.valueOf((String) typeObject);
        boolean seen = (boolean) pIsSeenObject;
        Long firstSeen = (Long) firstObject;
        Long lastSeen = System.currentTimeMillis();
        boolean isInLobby = (boolean) inLobbyObject;
        String playerName = (String) pNameObject;
        String world = (String) pWorldObject;
        boolean isOp = (boolean) pIsOpObject;
        PlayerLocation pl = PlayerLocation.valueOf((String) pLocationObject);
        int kill = (int) pKillsObject;
        int death = (int) pDeathsObject;
        double kd = (double) pKdrObject;
        int win = (int) pWinsObject;
        int gamePlayed = (int) pGamesPlayedObject;


        return new PlayerSettings(playerType, firstSeen, lastSeen, isInLobby, playerName, true, pl, world, isOp, kill, death, kd, win, gamePlayed);


    }

    /**
     * Serialize map.
     *
     * @return the map
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("GamerType", getPlayerType().name());
        this.setLastSeen();
        map.put("seenLast", getLastSeen());
        map.put("seenFirst", getFirstSeen());
        map.put("inLobby", isInLobby());
        map.put("Gamer", getPlayerName());
        map.put("isSeen", isSeen());
        map.put("GamerLocation", getPlayerLocation().name());
        map.put("World", getWorld());
        map.put("isOp", isOp());
        map.put("kills", getKills());
        map.put("deaths", getDeaths());
        this.setKdr();
        map.put("kdr", getKdr());
        map.put("wins", getWins());
        map.put("gamesPlayed", getGamesPlayed());
        return map;

    }

    public PlayerLocation getPlayerLocation() {
        return playerLocation;
    }

    public PlayerSettings setPlayerLocation(PlayerLocation playerLocation) {
        this.playerLocation = playerLocation;
        return this;
    }

    /**
     * Gets player type.
     *
     * @return the player type
     */
    public PlayerType getPlayerType() {
        return playerType;
    }

    /**
     * Sets player type.
     *
     * @param playerType the player type
     * @return instance of itself for Chaining
     */
    public PlayerSettings setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
        return this;
    }

    /**
     * Gets first seen.
     *
     * @return the first seen
     */
    public long getFirstSeen() {
        return firstSeen;
    }

    /**
     * Sets first seen.
     *
     * @param firstSeen the first seen
     */
    private void setFirstSeen() {
        this.firstSeen = System.currentTimeMillis();
    }

    /**
     * Gets last seen.
     *
     * @return the last seen
     */
    public long getLastSeen() {
        return lastSeen;
    }

    /**
     * Sets last seen.
     *
     * @param lastSeen the last seen
     */
    public void setLastSeen() {
        this.lastSeen = System.currentTimeMillis();
    }

    /**
     * Is in lobby.
     *
     * @return the boolean
     */
    public boolean isInLobby() {
        return isInLobby;
    }

    /**
     * Sets in lobby.
     *
     * @param isInLobby the is in lobby
     * @return instance of itself for Chaining
     */
    public PlayerSettings setInLobby(boolean isInLobby) {
        this.isInLobby = isInLobby;
        return this;
    }

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets player name.
     *
     * @param playerName the player name
     * @return instance of itself for Chaining
     */
    public PlayerSettings setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    /**
     * Is seen.
     *
     * @return the boolean
     */
    public boolean isSeen() {
        return seen;
    }

    public void save(String filePath) {

        SkyApi.getSCB().getConfig().set("player." + getPlayerName(), this);
        SkyApi.getSCB().saveConfig();
    }

    /**
     * Gets world.
     *
     * @return the world
     */
    public String getWorld() {
        return world;
    }

    /**
     * Sets world.
     *
     * @param world the world
     * @return the world
     */
    public PlayerSettings setWorld(String world) {
        this.world = world;
        return this;
    }

    /**
     * Is op.
     *
     * @return the boolean
     */
    public boolean isOp() {
        return isOp;
    }

    /**
     * Sets op.
     *
     * @param isOp the is op
     * @return the op
     */
    public PlayerSettings setOp(boolean isOp) {
        this.isOp = isOp;
        return this;
    }

    /**
     * Gets games played.
     *
     * @return the games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets games played.
     *
     * @param gamesPlayed the games played
     * @return the games played
     */
    public PlayerSettings setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed += gamesPlayed;
        return this;
    }

    /**
     * Gets wins.
     *
     * @return the wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets wins.
     *
     * @param wins the wins
     * @return the wins
     */
    public PlayerSettings setWins(int wins) {
        this.wins += wins;
        return this;
    }

    /**
     * Gets kdr.
     *
     * @return the kdr
     */
    public double getKdr() {
        return kdr;
    }

    /**
     * Sets kdr.
     *
     * @return the kdr
     */
    public PlayerSettings setKdr() {
        if (getDeaths() == 0) {
            this.kdr = getKills();
        } else if (getDeaths() > 0 && getKills() == 0) {
            this.kdr = 0.0;
        } else {
            this.kdr = getKills() / getDeaths();
        }

        return this;
    }

    /**
     * Gets deaths.
     *
     * @return the deaths
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Sets deaths.
     *
     * @param deaths the deaths
     * @return the deaths
     */
    public PlayerSettings setDeaths(int deaths) {
        this.deaths += deaths;
        return this;
    }

    /**
     * Gets kills.
     *
     * @return the kills
     */
    public int getKills() {
        return kills;
    }

    /**
     * Sets kills.
     *
     * @param kills the kills
     * @return the kills
     */
    public PlayerSettings setKills(int kills) {
        this.kills += kills;
        return this;
    }
}
