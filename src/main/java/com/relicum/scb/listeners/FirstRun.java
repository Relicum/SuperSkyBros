package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;
import java.util.List;

/**
 * SuperSkyBros First Created 28/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class FirstRun implements Listener {

    private SCB plugin;


    public FirstRun(SCB pl) {
        this.plugin = pl;
    }


    @EventHandler
    public void preLogin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            System.out.println("Player NOT played before");
            Location location = player.getWorld().getSpawnLocation();
            location.add(0.5, 0.5, 0.5);
            final Location loc = location;
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(
                    SCB.getInstance(), new Runnable() {

                @Override
                public void run() {
                    System.out.println(
                            "Teleporting new player to X " + loc.getX() + " Y " + loc.getY() + " Z " + loc.getZ());
                    player.teleport(loc);
                    player.sendMessage(ChatColor.GREEN + "You have been spawned here as you are a first time player");
                }
            }, 1l);
            return;

        } else System.out.println("Player has been here before");

    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void firstJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().isOp()) {
            e.getPlayer().kickPlayer(ChatColor.GREEN + "Sorry currently not allowed in as server is being setup for SuperSkyBros");
        } else {
            e.setJoinMessage(null);
            List<String> st = Arrays.asList(
                    "    " + "\u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<",
                    ChatColor.GOLD +
                            e.getPlayer().getName() + " welcome to the server");
            e.getPlayer().sendMessage((String[]) st.toArray());
            ChatColor b = ChatColor.BOLD;
            String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
            e.getPlayer().sendMessage(
                    pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros " +
                            "Beta " + SCB.getInstance().getDescription().getVersion() + " this " +
                            "should not be run on a live server be warned");

            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.DARK_RED + "IMPORTANT RUN " + ChatColor.GOLD + "/ssba setmode " + ChatColor.DARK_RED + "TO START SUPERSKYBROS SETUP");
        }
    }
}
