package com.relicum.scb.mini.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

/**
 * SuperSkyBros First Created 25/10/13 Handels the creation and updating of the in game scoreboards
 *
 * @author Relicum
 * @version 0.1
 */
public class ScoreBoardManager {

    private Map<String, Object> scoreboards = new HashMap<>();

    private Map<String, Object> liveBoards = new HashMap<>();

    private Map<Integer, String> taskIds = new HashMap<>();


    public ScoreBoardManager() {
    }


    public GameScoreBoard getNewGameScoreBoard() {

        GameScoreBoard GB = new GameScoreBoard(this.getNewScoreBoard());
        GB.setScoreboardType(ScoreboardType.INGAME);
        return GB;

    }


    /**
     * Get new score board.
     *
     * @return the scoreboard
     */
    private Scoreboard getNewScoreBoard() {
        return Bukkit.getServer().getScoreboardManager().getNewScoreboard();
    }


    /**
     * Get main scoreboard.
     *
     * @return the scoreboard
     */
    private Scoreboard getMainScoreboard() {
        return Bukkit.getServer().getScoreboardManager().getMainScoreboard();
    }
}
