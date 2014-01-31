package com.relicum.scb.listeners;

import java.util.Arrays;
import java.util.List;
import com.relicum.scb.types.SkyApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * SuperSkyBros First Created 28/09/13
 * 
 * @author Relicum
 * @version 0.1
 */
public class FirstRun implements Listener {

    private boolean modeset;

    public FirstRun() {

        SkyApi.getSCB().getConfig().getBoolean("modeSet");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void preLogin(PlayerLoginEvent e) {

        if (!modeset && !e.getPlayer().isOp()) {
            e.setKickMessage(ChatColor.stripColor(SkyApi.getMessageManager().getErrorMessage("system.kickJoinNoPerm")));
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, e.getKickMessage());
            e.setResult(PlayerLoginEvent.Result.KICK_OTHER);

        } else
            e.allow();

    }

    @EventHandler
    public void preLogin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {

            Location location = player.getWorld().getSpawnLocation();
            location.add(0.5, 0.5, 0.5);
            final Location loc = location;
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SkyApi.getSCB(), new Runnable() {

                @Override
                public void run() {
                    System.out.println("Teleporting new player to X " + loc.getX() + " Y " + loc.getY() + " Z " + loc.getZ());
                    player.teleport(loc);
                    player.sendMessage(ChatColor.GREEN + "You have been spawned here as you are a first time player");
                }
            }, 1l);

        }

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void firstJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().isOp()) {
            e.getPlayer().kickPlayer(ChatColor.GREEN + "Sorry currently not allowed in as server is being setup for SuperSkyBros");
        } else {
            e.setJoinMessage(null);
            List<String> st = Arrays.asList("    "
                    + "\u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<",
                    ChatColor.GOLD + e.getPlayer().getName() + " welcome to the server");
            e.getPlayer().sendMessage((String[]) st.toArray());
            ChatColor b = ChatColor.BOLD;
            String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
            e.getPlayer().sendMessage(pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros " + "Beta " + SkyApi.getSCB().getDescription().getVersion()
                    + " this " + "should not be run on a live server be warned");

            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.DARK_RED + "IMPORTANT RUN " + ChatColor.GOLD + "/ssba setmode " + ChatColor.DARK_RED + "TO START SUPERSKYBROS SETUP");
            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage("");
        }
    }
}
