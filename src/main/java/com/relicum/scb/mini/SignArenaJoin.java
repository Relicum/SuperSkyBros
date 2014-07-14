package com.relicum.scb.mini;

import com.relicum.scb.utils.SerializedLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * SuperSkyBros First Created 13/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SignArenaJoin {


    private List<SerializedLocation> arenaJoinSigns = new ArrayList<>();


    private SerializedLocation arenaLobby;


    public SignArenaJoin() {

    }

    public void addArenaJoinSignLocation(SerializedLocation location) {
        this.arenaJoinSigns.add(location);
    }

    public SerializedLocation getArenaLobby() {
        return arenaLobby;
    }

    public void setArenaLobby(SerializedLocation arenaLobby) {
        this.arenaLobby = arenaLobby;
    }

    public List<SerializedLocation> getArenaJoinSigns() {
        return arenaJoinSigns;
    }
}
