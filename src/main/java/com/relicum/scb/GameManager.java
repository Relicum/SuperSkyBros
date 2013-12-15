package com.relicum.scb;

import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaStatus;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.MessageManager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SuperSkyBros First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class GameManager {

    //public static GameManager gm;

    private SCB p;

    private ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, String> players = new ConcurrentHashMap<>();


    private List<String> notWorlds;


    public GameManager(SCB pl) {

        p = pl;


    }


    private void setup() {
        Map<Integer, Arena> are = SkyApi.getArenaManager().getAllArenas();

        for (Integer i : are.keySet()) {
            Arena a = are.get(i);

        }

    }


    /**
     * Returns a reference to the LobbyManager
     *
     * @return LobbyManager
     */
    public LobbyManager getLobbyManager() {


        return SkyApi.getLobbyManager();
    }


    /**
     * Returns a reference to the MessageManager
     *
     * @return MessageManager
     */
    public MessageManager getMessageManager() {

        return SCB.getMessageManager();
    }


    public void addPlayer(SmashPl sp) {

        this.players.put(sp.getPlayer().getUniqueId().toString(), sp.getStatus().toString());
    }


    public ArenaStatus getArenaStatus(Integer i) {

        return SkyApi.getArenaManager().getArenaById(i).getArenaStatus();
    }


    /**
     * In black listed world.
     *
     * @param world the world
     * @return the boolean false if they are in a blacklisted world
     */
    public boolean inBlackListedWorld(String world) {
        return (notWorlds.isEmpty()) && (notWorlds.contains(world));

    }
}
