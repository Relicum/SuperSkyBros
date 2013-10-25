package com.relicum.scb.mini;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * SuperSkyBros First Created 25/10/13 Experimenting with code design feel there are to Many crossed connections between
 * arens and storage
 *
 * @author Relicum
 * @version 0.1
 */
public interface IMiniArena {

    static ArenaStatus STATUS = null;

    int getArenaId();

    String getArenaName();

    ArenaStatus getStatus();

    boolean isEnabled();

    int getMaxPlayers();

    int getMinPlayers();

    String getPermission();

    Long getMaxGameTime();

    Vector getAdminSpawn();

    Vector getArenaLobbySpawn();

    Vector getLeaveArenaSpawn();

    String getArenaWorldAsString();

    List<?> getUpdateableSignLocations();

    SpawnGroup getSpawnGroup();


}
