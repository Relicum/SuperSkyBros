package com.relicum.scb;

import com.relicum.scb.mini.scoreboards.ScoreboardType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * SuperSkyBros First Created 05/10/13
 * 
 * @author Relicum
 * @version 0.1
 */
public class ShopManager implements Listener {

    private String econ;

    public Scoreboard sb;

    public Objective ob;

    public Objective ob2;

    public Score score;

    public Score score2;

    public int task;

    SCB plugin;

    public ShopManager(SCB pl) {
        this.plugin = pl;
        // this.setBoard();

    }

    public int blink = 30;

    /**
     * Gets scoreboard type.
     * 
     * @return the scoreboard type
     */
    public ScoreboardType getScoreboardType() {
        return null; // To change body of implemented methods use File |
                     // Settings | File Templates.
    }

    public void setBoard() {

        this.sb = plugin.getServer().getScoreboardManager().getNewScoreboard();
        this.ob = this.sb.registerNewObjective("Point", "dummy");
        this.ob.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.ob.setDisplayName(ChatColor.ITALIC + "" + ChatColor.GOLD + "SuperSkyBros");

        this.score.setScore(1);
        // Score score = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN +
        // "Start"));
        // score.setScore(1);
        // this.ob2 = this.sb.registerNewObjective("PlayerHealth", "health");
        // this.ob2.setDisplaySlot(DisplaySlot.SIDEBAR);
        // this.ob2.setDisplayName(ChatColor.RED + "Player Health");

    }

    public Objective objSelf(Scoreboard board) {
        this.sb = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        this.ob = this.sb.registerNewObjective("Points", "dummy");
        this.ob.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.ob.setDisplayName(ChatColor.GOLD + "SuperSkyBros");
        return this.ob;

    }

    public void temp(Objective obj) {
        Objective objective = objSelf(this.sb);
        Score score = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Time 11:35"));
        score.setScore(14);
        Score sc = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Stopped"));
        sc.setScore(27);

    }

    public void pj(PlayerJoinEvent event) {
        Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("Points", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.GOLD + "SuperSkyBros");
        Score sc = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Coins"));
        sc.setScore(23);

        Player player = event.getPlayer();
        player.setScoreboard(scoreboard);

    }

    public void pq(PlayerQuitEvent event) {

        event.getPlayer().setScoreboard(SCB.getInstance().getServer().getScoreboardManager().getMainScoreboard());
    }

}
