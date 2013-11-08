package com.relicum.scb.mini.scoreboards;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.UUID;

/**
 * The interface IScoreboardMaker.
 */
public interface IScoreboardMaker {

    /**
     * Gets id.
     *
     * @return the id
     */
    UUID getId();

    /**
     * Gets scoreboard type.
     *
     * @return the scoreboard type
     */
    ScoreboardType getScoreboardType();

    /**
     * Gets new scoreboard.
     *
     * @return the new scoreboard
     */
    Scoreboard getNewScoreboard();

    /**
     * Gets objective.
     *
     * @return the objective
     */
    Objective getObjective();

    /**
     * Gets criteria.
     *
     * @return the criteria
     */
    Criterias getCriteria();

    /**
     * Gets display slot.
     *
     * @return the display slot
     */
    DisplaySlot getDisplaySlot();

    /**
     * Gets team.
     *
     * @return the team
     */
    Team getTeam();

    /**
     * Gets score.
     *
     * @return the score
     */
    Score getScore();

    /**
     * Gets offline player.
     *
     * @param player the player
     * @return the offline player
     */
    OfflinePlayer getOfflinePlayer(String player);

    /**
     * Update scoreboard.
     *
     * @param uuid the uuid
     * @return the boolean
     */
    boolean updateScoreboard(UUID uuid);

    /**
     * Update all scoreboards.
     *
     * @return the boolean
     */
    boolean updateAllScoreboards();


}
