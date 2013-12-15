package com.relicum.scb.mini.scoreboards;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameScoreBoard
        implements IScoreboardMaker {
    private ScoreboardType scoreboardType;
    private int maxPlayers;
    private int minPlayers;
    private int maxLives;
    private String scoreBoardDisplayName;
    private int maxView;
    private long gameLength;
    private long updateSchedule;
    public Scoreboard scoreboard;
    private Map<String, Objective> objectiveMap = new HashMap();

    public GameScoreBoard(Scoreboard sb) {
        setScoreboard(sb);
    }

    public UUID getId() {
        return null;
    }

    public ScoreboardType getScoreboardType() {
        return null;
    }

    public Scoreboard getNewScoreboard() {
        return null;
    }

    public Objective setObjective(String name, String criteria) {
        Objective o = getScoreboard().registerNewObjective(name, criteria);
        objectiveMap.put(name, o);
        return o;
    }

    public Criterias getCriteria() {
        return null;
    }

    public DisplaySlot getDisplaySlot() {
        return null;
    }

    public Team getTeam() {
        return null;
    }

    public Score getScore() {
        return null;
    }

    public OfflinePlayer getOfflinePlayer(String player) {
        return null;
    }

    public boolean updateScoreboard(UUID uuid) {
        return false;
    }

    public boolean updateAllScoreboards() {
        return false;
    }

    public Objective getObjective() {
        return null;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxLives() {
        return maxLives;
    }

    public String getScoreBoardDisplayName() {
        return scoreBoardDisplayName;
    }

    public int getMaxView() {
        return maxView;
    }

    public long getGameLength() {
        return gameLength;
    }

    public long getUpdateSchedule() {
        return updateSchedule;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Map<String, Objective> getObjectiveMap() {
        return objectiveMap;
    }


    public void setScoreboardType(ScoreboardType scoreboardType) {
        this.scoreboardType = scoreboardType;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public void setMaxLives(int maxLives) {
        this.maxLives = maxLives;
    }

    public void setScoreBoardDisplayName(String scoreBoardDisplayName) {
        this.scoreBoardDisplayName = scoreBoardDisplayName;
    }

    public void setMaxView(int maxView) {
        this.maxView = maxView;
    }

    public void setGameLength(long gameLength) {
        this.gameLength = gameLength;
    }

    public void setUpdateSchedule(long updateSchedule) {
        this.updateSchedule = updateSchedule;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void setObjectiveMap(Map<String, Objective> objectiveMap) {
        this.objectiveMap = objectiveMap;
    }


    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof GameScoreBoard)) return false;
        GameScoreBoard other = (GameScoreBoard) o;
        if (!other.canEqual(this)) return false;
        Object this$scoreboardType = getScoreboardType();
        Object other$scoreboardType = other.getScoreboardType();
        if (this$scoreboardType == null ? other$scoreboardType != null : !this$scoreboardType.equals(other$scoreboardType))
            return false;
        if (getMaxPlayers() != other.getMaxPlayers()) return false;
        if (getMinPlayers() != other.getMinPlayers()) return false;
        if (getMaxLives() != other.getMaxLives()) return false;
        Object this$scoreBoardDisplayName = getScoreBoardDisplayName();
        Object other$scoreBoardDisplayName = other.getScoreBoardDisplayName();
        if (this$scoreBoardDisplayName == null ? other$scoreBoardDisplayName != null : !this$scoreBoardDisplayName.equals(other$scoreBoardDisplayName))
            return false;
        if (getMaxView() != other.getMaxView()) return false;
        if (getGameLength() != other.getGameLength()) return false;
        if (getUpdateSchedule() != other.getUpdateSchedule()) return false;
        Object this$scoreboard = getScoreboard();
        Object other$scoreboard = other.getScoreboard();
        if (this$scoreboard == null ? other$scoreboard != null : !this$scoreboard.equals(other$scoreboard))
            return false;
        Object this$objectiveMap = getObjectiveMap();
        Object other$objectiveMap = other.getObjectiveMap();
        return this$objectiveMap == null ? other$objectiveMap == null : this$objectiveMap.equals(other$objectiveMap);
    }

    public boolean canEqual(Object other) {
        return other instanceof GameScoreBoard;
    }

    public int hashCode() {
        int PRIME = 31;
        int result = 1;
        Object $scoreboardType = getScoreboardType();
        result = result * 31 + ($scoreboardType == null ? 0 : $scoreboardType.hashCode());
        result = result * 31 + getMaxPlayers();
        result = result * 31 + getMinPlayers();
        result = result * 31 + getMaxLives();
        Object $scoreBoardDisplayName = getScoreBoardDisplayName();
        result = result * 31 + ($scoreBoardDisplayName == null ? 0 : $scoreBoardDisplayName.hashCode());
        result = result * 31 + getMaxView();
        long $gameLength = getGameLength();
        result = result * 31 + (int) ($gameLength >>> 32 ^ $gameLength);
        long $updateSchedule = getUpdateSchedule();
        result = result * 31 + (int) ($updateSchedule >>> 32 ^ $updateSchedule);
        Object $scoreboard = getScoreboard();
        result = result * 31 + ($scoreboard == null ? 0 : $scoreboard.hashCode());
        Object $objectiveMap = getObjectiveMap();
        result = result * 31 + ($objectiveMap == null ? 0 : $objectiveMap.hashCode());
        return result;
    }

    public String toString() {
        return "GameScoreBoard(scoreboardType=" + getScoreboardType() + ", maxPlayers=" + getMaxPlayers() + ", minPlayers=" + getMinPlayers() + ", maxLives=" + getMaxLives() + ", scoreBoardDisplayName=" + getScoreBoardDisplayName() + ", maxView=" + getMaxView() + ", gameLength=" + getGameLength() + ", updateSchedule=" + getUpdateSchedule() + ", scoreboard=" + getScoreboard() + ", objectiveMap=" + getObjectiveMap() + ")";
    }

}