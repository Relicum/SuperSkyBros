package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.SettingsManager;
import com.relicum.scb.SmashPlayer;
import com.relicum.scb.utils.playerStatus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


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


        if (SettingsManager.getInstance().notWorlds().contains(e.getPlayer().getWorld().getName()) && !e.getPlayer().isOp()) {
            return;
        }
        if (SettingsManager.getInstance().notWorlds().contains(e.getPlayer().getWorld().getName()) && plugin.getConfig().getBoolean("dedicatedSSB") && !SCB.perms.has(e.getPlayer(), "ssba.admin")) {
            e.setJoinMessage(null);
            System.out.println("Player Join Event has been thrown");
            e.getPlayer().kickPlayer(SCB.getMessageManager().getErrorMessage("system.kickJoinWorldBlacklisted"));
            System.out.println("Player " + e.getPlayer().getName() + " was kicked trying to the world " + e.getPlayer().getWorld().getName() + " which is on the black list you can change this in the config.yml");
            return;
        }


        if (SCB.perms.has(e.getPlayer(), "ssba.admin") || e.getPlayer().isOp()) {
            e.setJoinMessage(null);
            ChatColor b = ChatColor.BOLD;
            String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
            e.getPlayer().sendMessage(pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros Beta " + SCB.getInstance().getDescription().getVersion() + " this should not be run on a live server be warned");

        }


        if (plugin.getConfig().getBoolean("dedicatedSSB")) {

            SmashPlayer pl = new SmashPlayer(e.getPlayer());

            if (!SCB.getInstance().LBC.getConfig().contains("LOBBY.REGION")) {
                if (pl.isOp()) {
                    e.setJoinMessage(SCB.getMessageManager().getErrorMessage("system.opAutoJoinOverRide"));
                    plugin.getLogger().severe("You need to set a lobby spawn or players can not join");

                    return;

                } else {

                    e.setJoinMessage(null);
                    pl.kickPlayer(SCB.getMessageManager().getErrorMessage("system.noLobby"));
                    plugin.getLogger().severe(e.getPlayer().getName() + " has been kicked as you have not set a Lobby Spawn yet");
                    return;
                }
            }


            if (SCB.perms.has(pl.getPlayer(), "ssb.player.join") || pl.isOp()) {
                pl.setpStatus(playerStatus.JOINEDSERVER);
                pl.setMyLocation("JOINEDSERVER");
                plugin.LBS.addPlayer(pl);
                String tmp;
                e.setJoinMessage(tmp = ChatColor.translateAlternateColorCodes('&', SCB.getMessageManager().getRawMessage("system.autoJoin").replace("%player%", e.getPlayer().getName())));
                System.out.println("Starting inv update in load");
                pl.getInventory().setItem(8, new ItemStack(Material.EMERALD));
                pl.teleportToLobby();
                pl.setpStatus(playerStatus.LOBBY);
                pl.setMyLocation("LOBBY");
                System.out.println("Finished loading inv on load");
            } else {
                e.setJoinMessage("");

                pl.kickPlayer(SCB.getMessageManager().getErrorMessage("system.noJoinPerm"));
            }


        }

    }


    public boolean displayBetaMessage(PlayerJoinEvent e) {


        e.setJoinMessage(null);
        ChatColor b = ChatColor.BOLD;
        String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
        e.getPlayer().sendMessage(pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros Beta " + SCB.getInstance().getDescription().getVersion() + " this should not be run on a live server be warned");


        return true;
    }
}
