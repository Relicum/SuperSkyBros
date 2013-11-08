package com.relicum.scb;

import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaRegion;
import com.relicum.scb.arena.ArenaStatus;
import com.relicum.scb.objects.spawns.ArenaGroupSpawn;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;

/**
 * The type Game.
 */
public class Game {

    /**
     * The Arena.
     */
    private Arena arena;

    /**
     * The GUID.
     */
    private String GUID;

    /**
     * The Min players.
     */
    private Integer minPlayers;

    /**
     * The Max players.
     */
    private Integer maxPlayers;

    /**
     * The Max game length.
     */
    private Long maxGameLength;

    /**
     * The Map.
     */
    private String map;

    /**
     * The Umap.
     */
    private String umap;

    /**
     * The Permission.
     */
    private String permission;

    /**
     * The Status.
     */
    private ArenaStatus status;

    /**
     * The Enabled.
     */
    private boolean enabled;

    /**
     * The Arena region.
     */
    private ArenaRegion arenaRegion;

    /**
     * The World.
     */
    private String world;

    /**
     * The Spawn group.
     */
    private ArenaGroupSpawn spawnGroup;

    /**
     * The Players.
     */
    private ArrayList<String> players = new ArrayList<>();


    /**
     * The Scoreboard.
     */
    private Scoreboard scoreboard;


    /**
     * Instantiates a new Game.
     *
     * @param arena the aren
     */
    public Game(Arena aren) {
        this.arena = aren;
        this.setup(arena);
    }


    /**
     * Sets .
     *
     * @param a the a
     */
    private void setup(Arena a) {

        this.GUID = a.getArenaId().toString();
        this.minPlayers = a.getMinPlayers();
        this.maxPlayers = a.getMaxPlayers();
        this.maxGameLength = a.getMaxGameTime();
        this.map = a.getArenaName();
        this.umap = a.getUniqueMap();
        this.permission = a.getPermmission();
        this.status = a.getArenaStatus();
        this.enabled = a.isEnabled();
        this.arenaRegion = a.getAreg();
        this.world = a.getArenaWorld().toString();
        this.spawnGroup = a.getSpawnGroup();

    }


    /**
     * Is waiting.
     *
     * @return the boolean
     */
    public boolean isWaiting() {
        return this.status == ArenaStatus.WAITING;
    }


    /**
     * Is enabled.
     *
     * @return the boolean
     */
    public boolean isEnabled() {
        return this.enabled;
    }


    /**
     * Gets arena.
     *
     * @return the arena
     */
    public Arena getArena() {
        return arena;
    }


    /**
     * Gets min players.
     *
     * @return the min players
     */
    public Integer getMinPlayers() {
        return minPlayers;
    }


    /**
     * Gets max players.
     *
     * @return the max players
     */
    public Integer getMaxPlayers() {
        return maxPlayers;
    }


    /**
     * Gets max game length.
     *
     * @return the max game length
     */
    public Long getMaxGameLength() {
        return maxGameLength;
    }


    /**
     * Gets map.
     *
     * @return the map
     */
    public String getMap() {
        return map;
    }


    /**
     * Gets umap.
     *
     * @return the umap
     */
    public String getUmap() {
        return umap;
    }


    /**
     * Gets permission.
     *
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }


    /**
     * Gets scoreboard.
     *
     * @return the scoreboard
     */
    public Scoreboard getScoreboard() {
        return scoreboard;
    }


    /**
     * Sets scoreboard.
     *
     * @param scoreboard the scoreboard
     */
    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }
}
