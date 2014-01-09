package com.relicum.scb;

import com.relicum.scb.classes.PlayerType;
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

    /**
     * Serialize map.
     *
     * @return the map
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("plType", getPlayerType().name());
        this.setLastSeen();
        map.put("seenLast", getLastSeen());
        map.put("seenFirst", getFirstSeen());
        map.put("inLobby", isInLobby());
        map.put("pName", getPlayerName());
        map.put("isSeen", isSeen());
        return map;

    }

    /**
     * Deserialize player test.
     *
     * @param map the map
     * @return the player test
     */
    public static PlayerSettings deserialize(Map<String, Object> map) {

        Object typeObject = map.get("plType"),
                firstObject = map.get("seenFirst"),
                lastObject = map.get("seenLast"),
                inLobbyObject = map.get("inLobby"),
                pNameObject = map.get("pName"),
                pIsSeenObject = map.get("isSeen");
        if (pNameObject == null || lastObject == null || firstObject == null || typeObject == null) {
            return null;
        }

        PlayerType playerType = PlayerType.valueOf((String) typeObject);
        boolean seen = (boolean) pIsSeenObject;
        Long firstSeen = (Long) firstObject;
        Long lastSeen = System.currentTimeMillis();
        boolean isInLobby = (boolean) inLobbyObject;
        String playerName = (String) pNameObject;


        return new PlayerSettings(playerType, firstSeen, lastSeen, isInLobby, playerName, true);


    }

    protected PlayerSettings(PlayerType pt, Long firstS, Long lastS, boolean inLobby, String name, boolean seenBefore) {

        playerType = pt;
        seen = seenBefore;
        lastSeen = lastS;
        firstSeen = firstS;
        isInLobby = inLobby;
        playerName = name;

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
}
