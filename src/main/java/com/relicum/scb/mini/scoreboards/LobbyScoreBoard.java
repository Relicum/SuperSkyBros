package com.relicum.scb.mini.scoreboards;

import lombok.Data;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.UUID;

/**
 * SuperSkyBros
 * First Created 27/11/13
 *
 * @author Relicum
 * @version 0.1
 */
@Data
public class LobbyScoreBoard implements IScoreboardMaker {


    /**
     * Gets id.
     *
     * @return the id
     */
    @Override
    public UUID getId() {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets scoreboard type.
     *
     * @return the scoreboard type
     */
    @Override
    public ScoreboardType getScoreboardType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets new scoreboard.
     *
     * @return the new scoreboard
     */
    @Override
    public Scoreboard getNewScoreboard() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets objective.
     *
     * @return the objective
     */
    @Override
    public Objective getObjective() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets criteria.
     *
     * @return the criteria
     */
    @Override
    public Criterias getCriteria() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets display slot.
     *
     * @return the display slot
     */
    @Override
    public DisplaySlot getDisplaySlot() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets team.
     *
     * @return the team
     */
    @Override
    public Team getTeam() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    @Override
    public Score getScore() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets offline player.
     *
     * @param player the player
     * @return the offline player
     */
    @Override
    public OfflinePlayer getOfflinePlayer(String player) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Update scoreboard.
     *
     * @param uuid the uuid
     * @return the boolean
     */
    @Override
    public boolean updateScoreboard(UUID uuid) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Update all scoreboards.
     *
     * @return the boolean
     */
    @Override
    public boolean updateAllScoreboards() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
