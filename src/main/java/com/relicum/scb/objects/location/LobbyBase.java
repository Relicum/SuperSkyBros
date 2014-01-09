package com.relicum.scb.objects.location;

import com.relicum.scb.objects.LocationType;
import com.relicum.scb.utils.SerializedLocation;
import lombok.ToString;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Lobby base.
 */
@ToString
@SerializableAs("LobbyBase")
public class LobbyBase implements ILobby, ConfigurationSerializable {

    /**
     * The Min.
     */
    public Vector min;
    /**
     * The Max.
     */
    public Vector max;
    /**
     * The Spawn.
     */
    public SerializedLocation spawn;
    /**
     * The Lobby permission.
     */
    public String lobbyPermission;
    /**
     * The Type.
     */
    public LocationType type;
    /**
     * The Status.
     */
    public LobbyStatus status;
    /**
     * The Can fly.
     */
    public boolean canFly;
    /**
     * The Can build.
     */
    public boolean canBuild;
    /**
     * The Players.
     */
    private Map<String, Object> players;

    /**
     * Gets min vector.
     *
     * @return the min vector
     */
    @Override
    public Vector getMinVector() {
        return this.min;
    }

    /**
     * Gets max vector.
     *
     * @return the max vector
     */
    @Override
    public Vector getMaxVector() {
        return this.max;
    }

    /**
     * Gets spawn.
     *
     * @return the spawn
     */
    @Override
    public SerializedLocation getSpawn() {
        return this.spawn;
    }

    /**
     * Gets lobby permission.
     *
     * @return the lobby permission
     */
    @Override
    public String getLobbyPermission() {
        return this.lobbyPermission;
    }

    /**
     * Gets lobby type.
     *
     * @return the lobby type
     */
    @Override
    public LocationType getLobbyType() {
        return this.type;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    @Override
    public LobbyStatus getStatus() {
        return this.status;
    }

    /**
     * Can fly.
     *
     * @return the boolean
     */
    @Override
    public boolean canFly() {
        return this.canFly;
    }

    /**
     * Can build.
     *
     * @return the boolean
     */
    @Override
    public boolean canBuild() {
        return this.canBuild;
    }

    /**
     * Is in lobby.
     *
     * @param name the name
     * @return the boolean
     */
    @Override
    public boolean isInLobby(String name) {
        return this.players.containsKey(name);
    }

    /**
     * Sets min.
     *
     * @param min the min
     */
    public void setMin(Vector min) {
        this.min = min;
    }

    /**
     * Sets max.
     *
     * @param max the max
     */
    public void setMax(Vector max) {
        this.max = max;
    }

    /**
     * Sets spawn.
     *
     * @param spawn the spawn
     */
    public void setSpawn(SerializedLocation spawn) {
        this.spawn = spawn;
    }

    /**
     * Sets lobby permission.
     *
     * @param lobbyPermission the lobby permission
     */
    public void setLobbyPermission(String lobbyPermission) {
        this.lobbyPermission = lobbyPermission;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(LocationType type) {
        this.type = type;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(LobbyStatus status) {
        this.status = status;
    }

    /**
     * Sets can fly.
     *
     * @param canFly the can fly
     */
    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    /**
     * Sets can build.
     *
     * @param canBuild the can build
     */
    public void setCanBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    /**
     * Serialize map.
     *
     * @return the map
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("min", min);
        map.put("max", max);
        map.put("spawn", spawn);
        map.put("perms", lobbyPermission);
        map.put("type", type.name());
        map.put("status", status.name());
        map.put("canFly", canFly);
        map.put("canBuild", canBuild);
        map.put("players", players);

        return map;
    }


    /**
     * Instantiates a new Lobby base.
     *
     * @param filename        the filename
     * @param min             the min
     * @param max             the max
     * @param spawn           the spawn
     * @param lobbyPermission the lobby permission
     * @param type            the type
     * @param status          the status
     * @param canFly          the can fly
     * @param canBuild        the can build
     * @param players         the players
     */
    public LobbyBase(Vector min, Vector max, SerializedLocation spawn, String lobbyPermission, LocationType type, LobbyStatus status, boolean canFly, boolean canBuild, Map<String, Object> players) {
        this.min = min;
        this.max = max;
        this.spawn = spawn;
        this.lobbyPermission = lobbyPermission;
        this.type = type;
        this.status = status;
        this.canFly = canFly;
        this.canBuild = canBuild;
        this.players = players;
    }


    /**
     * Deserialize lobby base.
     *
     * @param map the map
     * @return the lobby base
     */
    public static LobbyBase deserialize(Map<String, Object> map) {
        Map<String, Object> players = new HashMap<>();
        Vector min = (Vector) map.get("min");
        Vector max = (Vector) map.get("max");
        SerializedLocation spawn = (SerializedLocation) map.get("spawn");
        String lobbyPermission = (String) map.get("perms");
        LocationType type = LocationType.valueOf((String) map.get("type"));
        LobbyStatus status = LobbyStatus.valueOf((String) map.get("status"));
        boolean canFly = (Boolean) map.get("canFly");
        boolean canBuild = (Boolean) map.get("canBuild");
        players = (Map<String, Object>) map.get("players");

        return new LobbyBase(min, max, spawn, lobbyPermission, type, status, canFly, canBuild, players);

    }
}
