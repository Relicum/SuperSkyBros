package com.relicum.scb.mini.scoreboards;

import lombok.Data;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The type GameScoreBoard.
 */

public
@Data
class GameScoreBoard implements IScoreboardMaker {

    /**
     * The scoreboard type.
     */
    private ScoreboardType scoreboardType;

    /**
     * The Max players.
     */

    private int maxPlayers;

    /**
     * The Min players.
     */
    private int minPlayers;

    /**
     * The Max lives.
     */
    private int maxLives;

    /**
     * The Score board display name.
     */
    private String scoreBoardDisplayName;

    /**
     * The Max view.
     */
    private int maxView;

    /**
     * The Game length.
     */
    private long gameLength;

    /**
     * The Update schedule.
     */
    private long updateSchedule;

    /**
     * The Scoreboard.
     */
    public Scoreboard scoreboard;

    private Map<String, Objective> objectiveMap = new HashMap<>();


    public GameScoreBoard(Scoreboard sb) {
        this.setScoreboard(sb);


    }


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

        return null;

    }


    /**
     * Gets main objective and is registered with the scoreboard at the same time Gets stored in HashMap
     *
     * @param name     the name
     * @param criteria the criteria
     * @return the objective has already been registered to the scoreboard
     */
    public Objective setObjective(String name, String criteria) {

        Objective o = this.getScoreboard().registerNewObjective(name, criteria);
        this.objectiveMap.put(name, o);
        return o;
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


    /**
     * Gets objective.
     *
     * @return the objective
     */
    @Override
    public Objective getObjective() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
