package com.relicum.scb;

import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaStatus;
import com.relicum.scb.utils.MessageManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SuperSkyBros First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class GameManager {

    public static GameManager gm;

    private SCB p;

    private ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, String> players = new ConcurrentHashMap<>();


    public GameManager(SCB pl) {

        p = pl;
        GameManager.gm = this;

    }


    private void setup() {
        Map<Integer, Arena> are = getArenaManager().getAllArenas();

        for ( Integer i : are.keySet() ) {
            Arena a = are.get(i);

        }

    }


    /**
     * Returns a reference to the ArenaManager
     *
     * @return ArenaManager
     */
    public ArenaManager getArenaManager() {

        return this.p.ARM;
    }


    /**
     * Returns a reference to the LobbyManager
     *
     * @return LobbyManager
     */
    public LobbyManager getLobbyManager() {

        return this.p.LBS;
    }


    /**
     * Returns a reference to the MessageManager
     *
     * @return MessageManager
     */
    public MessageManager getMessageManager() {

        return SCB.getMessageManager();
    }


    public void addPlayer(SmashPlayer sp) {

        this.players.put(sp.getPlayer().getUniqueId().toString(), sp.getStatus().toString());
    }


    public ArenaStatus getArenaStatus(Integer i) {

        return getArenaManager().getArenaById(i).getArenaStatus();
    }
}
