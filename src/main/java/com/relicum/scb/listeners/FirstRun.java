package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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


    @EventHandler(priority = EventPriority.HIGHEST)
    public void firstJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().isOp()) {
            e.getPlayer().kickPlayer("Sorry currently not allowed in allowed in server is being setup for SuperSkyBros");
        } else {
            e.setJoinMessage(null);
            ChatColor b = ChatColor.BOLD;
            String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
            e.getPlayer().sendMessage(pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros Beta " + SCB.getInstance().getDescription().getVersion() + " this should not be run on a live server be warned");

            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.DARK_RED + "YOU MUST DO THE BELOW NOW OR SSB WILL NOT BE RUNNING");

            e.getPlayer().sendMessage(ChatColor.GREEN + "Run the following command to set the SSB server mode");

            e.getPlayer().sendMessage(ChatColor.AQUA + "If the server will only be running SSB ");
            e.getPlayer().sendMessage(ChatColor.GREEN + "Run " + ChatColor.GOLD + "/ssba setmode dedicated");
            e.getPlayer().sendMessage(ChatColor.AQUA + "Or if the server has other plugins eg factions or other game modes, then");
            e.getPlayer().sendMessage(ChatColor.GREEN + "Run " + ChatColor.GOLD + "/ssba setmode mixed");
            e.getPlayer().sendMessage(ChatColor.AQUA + "After you have done that stop the server and restart it.");
        }
    }

}
