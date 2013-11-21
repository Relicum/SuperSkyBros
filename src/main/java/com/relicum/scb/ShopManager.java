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
        //this.setBoard();


    }


    public int blink = 30;


    /**
     * Gets scoreboard type.
     *
     * @return the scoreboard type
     */
    public ScoreboardType getScoreboardType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public void setBoard() {

        this.sb = plugin.getServer().getScoreboardManager().getNewScoreboard();
        this.ob = this.sb.registerNewObjective("Point", "dummy");
        this.ob.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.ob.setDisplayName(ChatColor.ITALIC + "" + ChatColor.GOLD + "SuperSkyBros");

        this.score.setScore(1);
        //  Score score = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Start"));
        //  score.setScore(1);
        // this.ob2 = this.sb.registerNewObjective("PlayerHealth", "health");
        // this.ob2.setDisplaySlot(DisplaySlot.SIDEBAR);
        //this.ob2.setDisplayName(ChatColor.RED + "Player Health");


    }


    public Objective objSelf(Scoreboard board) {
        this.sb = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        this.ob = this.sb.registerNewObjective("Points", "dummy");
        this.ob.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.ob.setDisplayName(ChatColor.ITALIC + "" + ChatColor.GOLD + "SuperSkyBros");
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

        if (plugin.getServer().getOnlinePlayers().length == 1) {
            update();
        }
        this.score = this.ob.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + event.getPlayer().getName()));
        this.score.setScore(1);


        Bukkit.getServer().getScheduler().runTaskLater(
                this.plugin, new Runnable() {

            @Override
            public void run() {
                System.out.println("Next tick show board");
            }
        }, 100L);
        Player player = event.getPlayer();
        player.setScoreboard(this.sb);


    }


    public void pq(PlayerQuitEvent event) {

        if (plugin.getServer().getOnlinePlayers().length == 1) {
            plugin.getServer().getScheduler().cancelTask(this.task);
        }

        this.sb.resetScores(Bukkit.getOfflinePlayer(event.getPlayer().getName()));
        event.getPlayer().setScoreboard(SCB.getInstance().getServer().getScoreboardManager().getMainScoreboard());
    }


    public void update() {

        final Score score1 = this.score;

        this.task = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(
                plugin, new Runnable() {


            int tt = 0;

            int st = 11;


            @Override
            public void run() {

                tt++;
                score1.setScore(tt);
                System.out.println("tt is now " + tt);
            }


        }, 0L, 20L);
        System.out.println("Task id = " + task);
        System.out.println("Task ended");
    }
}
