package com.relicum.scb.mini.scoreboards;

import org.bukkit.scoreboard.Scoreboard;

/**
 * The interface for a ScoreBoard Manager
 */
public interface IScoreBoard {

    /**
     * The enum DISPLAYSLOT.
     */
    public enum DISPLAYSLOT {
        /**
         * The SIDEBAR.
         */SIDEBAR,
        /**
         * The BELOW_NAME.
         */BELOW_NAME,
        /**
         * The PLAYER_LIST.
         */PLAYER_LIST
    }


    /**
     * Gets sB manager.
     *
     * @return the sB manager
     */
    ScoreBoardManager getSBManager();

    /**
     * Gets new score board.
     *
     * @return the new score board
     */
    Scoreboard getNewScoreBoard();

    /**
     * Gets main scoreboard.
     *
     * @return the main scoreboard
     */
    Scoreboard getMainScoreboard();

    /**
     * Gets scoreboard.
     *
     * @param iD the i d
     * @return the scoreboard
     */
    Scoreboard getScoreboard(Integer iD);

    /**
     * Add player to scoreboard.
     *
     * @param player the player
     * @return the boolean
     */
    boolean addPlayerToScoreboard(String player);

    /**
     * Remove player from scoreboard.
     *
     * @param player the player
     * @return the boolean
     */
    boolean removePlayerFromScoreboard(String player);


    /**
     * Sets player scoreboard.
     *
     * @param player the player
     * @return the player scoreboard
     */
    boolean setPlayerScoreboard(String player);

    /**
     * Clear scoreboard.
     *
     * @param scoreboard the scoreboard
     * @return the boolean
     */
    boolean clearScoreboard(Scoreboard scoreboard);

    /**
     * Destroy scoreboard. When no longer needed
     *
     * @param scoreboard the scoreboard
     * @return the boolean
     */
    boolean destroyScoreboard(Scoreboard scoreboard);

    /**
     * Update pre game scoreboards.
     *
     * @return boolean true on success
     */
    boolean updatePreGameScoreboards();

    /**
     * Update specified instance of scoreboard.
     *
     * @return boolean true on success
     */
    boolean updateScoreboard();

    /**
     * Initialise scoreboard, when an a arena is active or has been restarted
     *
     * @return boolean return true on success
     */
    boolean initialiseScoreboard();

    /**
     * Save scoreboard location along with any reference data.
     *
     * @return boolean true on success
     */
    boolean saveScoreboardLocation();

    /**
     * Save all.  As it says save all required data
     *
     * @return boolean true on success
     */
    boolean saveAll();

    /**
     * Load all scoreboards on server start or restart
     *
     * @return boolean true on success
     */
    boolean loadAllScoreboards();

    /**
     * Load a particular instance of a scoreboard.
     *
     * @return boolean true on success
     */
    boolean loadScoreboard();
}
