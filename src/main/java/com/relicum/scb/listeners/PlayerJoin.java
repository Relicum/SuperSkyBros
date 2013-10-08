package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.SettingsManager;
import com.relicum.scb.SmashPlayer;
import com.relicum.scb.events.PlayerJoinLobbyEvent;
import com.relicum.scb.utils.playerStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;


/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */

public class PlayerJoin implements Listener {

    private SCB plugin;

    private playerStatus status;


    public PlayerJoin(SCB plugin) {
        this.plugin = plugin;

    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void signChange(SignChangeEvent e) throws InterruptedException {

        String[] lines = e.getLines();
        if (lines[0].equalsIgnoreCase("ssb")) {
            e.setLine(0, "§e<---§1SSB--->|");
            e.setLine(1, "JOIN THE LOBBY");
            e.setLine(2, "§aSUPERSKYBROS");
        }

        System.out.println("A sign has been place by " + e.getPlayer().getName());


    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void playJoin(PlayerJoinEvent e) {


        if (SCB.getInstance().getConfig().getBoolean("dedicatedSSB")) {
            SmashPlayer pl = new SmashPlayer(e.getPlayer());

            if (!SCB.getInstance().LBC.getConfig().contains("LOBBY.REGION")) {
                if (pl.isOp()) {
                    e.setJoinMessage(SCB.getMessageManager().getErrorMessage("system.opAutoJoinOverRide"));
                    plugin.getLogger().severe("You need to set a lobby spawn or players can not join");
                    return;

                } else {

                    e.setJoinMessage("");
                    pl.kickPlayer(SCB.getMessageManager().getErrorMessage("system.noLobby"));
                    plugin.getLogger().severe(e.getPlayer().getName() + " has been kicked as you have not set a Lobby Spawn yet");
                    return;
                }

            }

            if (SCB.perms.has(pl.getPlayer(), "ssb.player.join") || pl.isOp()) {
                e.setJoinMessage("");
                if (SCB.perms.has(pl.getPlayer(), "ssba.admin") || pl.getPlayer().isOp()) {
                    ChatColor b = ChatColor.BOLD;
                    String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
                    pl.sendMessage(pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros Beta " + SCB.getInstance().getDescription().getVersion() + " this should not be run on a live server be warned");

                }
                pl.setpStatus(playerStatus.JOINEDSERVER);
                pl.setMyLocation("JOINEDSERVER");
                PlayerJoinLobbyEvent event = new PlayerJoinLobbyEvent(pl, "JOINEDSERVER", SCB.getInstance().getConfig().getBoolean("dedicatedSSB"));
                Bukkit.getServer().getPluginManager().callEvent(event);
                System.out.println("Gets to the dedicatedssb section");
                return;
            } else {
                e.setJoinMessage("");

                pl.kickPlayer(SCB.getMessageManager().getErrorMessage("system.noJoinPerm"));
                return;
            }
        }


        if (SettingsManager.getInstance().notWorlds().contains(e.getPlayer().getWorld().getName()) && !e.getPlayer().isOp()) {
            System.out.println("Player Join Returned right at the top");
            return;
        }
        if (SettingsManager.getInstance().notWorlds().contains(e.getPlayer().getWorld().getName()) && plugin.getConfig().getBoolean("dedicatedSSB") && !SCB.perms.has(e.getPlayer(), "ssba.admin")) {
            e.setJoinMessage("");
            System.out.println("Player Join Event has been thrown");
            e.getPlayer().kickPlayer(SCB.getMessageManager().getErrorMessage("system.kickJoinWorldBlacklisted"));
            System.out.println("Player " + e.getPlayer().getName() + " was kicked trying from the world " + e.getPlayer().getWorld().getName() + " which is on the black list you can change this in the config.yml");
            return;
        }


        if (SCB.perms.has(e.getPlayer(), "ssba.admin") || e.getPlayer().isOp()) {
            e.setJoinMessage("");
            ChatColor b = ChatColor.BOLD;
            String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
            e.getPlayer().sendMessage(pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros Beta " + SCB.getInstance().getDescription().getVersion() + " this should not be run on a live server be warned");


        }

        if (!SCB.getInstance().LBC.getConfig().contains("LOBBY.REGION")) {
            if (e.getPlayer().isOp()) {
                e.setJoinMessage(SCB.getMessageManager().getErrorMessage("system.opAutoJoinOverRide"));
                plugin.getLogger().severe("You need to set a lobby spawn or players can not join");
                return;

            } else {

                e.setJoinMessage("");
                e.getPlayer().kickPlayer(SCB.getMessageManager().getErrorMessage("system.noLobby"));
                plugin.getLogger().severe(e.getPlayer().getName() + " has been kicked as you have not set a Lobby Spawn yet");
                return;
            }

        }


        System.out.println("Player passed right through all Join event conditions ???");
    }


    public boolean displayBetaMessage(PlayerJoinEvent e) {


        e.setJoinMessage(null);
        ChatColor b = ChatColor.BOLD;
        String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
        e.getPlayer().sendMessage(pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros Beta " + SCB.getInstance().getDescription().getVersion() + " this should not be run on a live server be warned");


        return true;
    }
}
