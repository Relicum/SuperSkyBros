package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.List;

/**
 * SuperSkyBros First Created 08/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SignChange implements Listener {

    private SCB plugin;

    public List<String> blacklist;

    public boolean isDedicated;


    public SignChange(SCB p) {
        this.plugin = p;
        blacklist = plugin.getBlackList();
        this.isDedicated = plugin.getConfig().getBoolean("dedicatedSSB");

    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void signChange(SignChangeEvent e) throws InterruptedException {
        String[] lines = e.getLines();
        if ((!lines[0].equalsIgnoreCase("[JOIN LOBBY]")) && (!lines[1].equalsIgnoreCase("join"))) {
            if (this.blacklist.contains(e.getPlayer().getWorld().getName()))
                return;
        }

        if (lines[0].equalsIgnoreCase("[JOIN LOBBY]") && lines[1].equalsIgnoreCase("join")) {

            if (SCB.perms.has(e.getPlayer(), "ssba.admin.createsign")) {
                e.setLine(0, Col.Dark_Red() + "[JOIN LOBBY]");
                e.setLine(1, "RIGHT CLICK");
                e.setLine(2, "TO JOIN LOBBY");
                e.setLine(3, Col.Dark_Blue() + "SUPERSKYBROS");
                e.getPlayer().sendMessage(SCB.getMessageManager().getAdminMessage("listeners.signchange.joinSuccess"));
                System.out.println("A Join sign has been place by " + e.getPlayer().getName());
            } else {
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                e.getPlayer().sendMessage(SCB.getMessageManager().getErrorMessage("listeners.signchange.noPerms"));
                System.out.println("A Join sign was canceled due to no perms by " + e.getPlayer().getName());
            }


        }

        if (lines[0].equalsIgnoreCase("[LEAVE]") && lines[1].equalsIgnoreCase("leave")) {

            if (SCB.perms.has(e.getPlayer(), "ssba.admin.createsign")) {
                e.setLine(0, Col.Dark_Red() + "[LEAVE]");
                e.setLine(1, "CLICK TO");
                e.setLine(2, "LEAVE LOBBY");
                e.setLine(3, Col.Dark_Blue() + "SUPERSKYBROS");
                e.getPlayer().sendMessage(SCB.getMessageManager().getAdminMessage("listeners.signchange.leaveSuccess"));
                System.out.println("A Leave sign has been place by " + e.getPlayer().getName());
            } else {
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                e.getPlayer().sendMessage(SCB.getMessageManager().getErrorMessage("listeners.signchange.noPerms"));
                System.out.println("A leave sign was canceled due to no perms by " + e.getPlayer().getName());
            }


        }

        if (lines[0].equalsIgnoreCase("[RETURN]") && lines[1].equalsIgnoreCase("lobby")) {

            if (SCB.perms.has(e.getPlayer(), "ssba.admin.createsign")) {
                e.setLine(0, Col.Dark_Red() + "[RETURN]");
                e.setLine(1, "CLICK TO RETURN");
                e.setLine(2, "TO LOBBY");
                e.setLine(3, Col.Dark_Blue() + "SUPERSKYBROS");
                e.getPlayer().sendMessage(SCB.getMessageManager().getAdminMessage("listeners.signchange.leaveGame"));
                System.out.println("A Leave Game has been place by " + e.getPlayer().getName());
            } else {
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                e.getPlayer().sendMessage(SCB.getMessageManager().getErrorMessage("listeners.signchange.noPerms"));
                System.out.println("A leave sign was canceled due to no perms by " + e.getPlayer().getName());
            }


        }

        if (lines[0].equalsIgnoreCase("[ARENA]") && lines[1].equalsIgnoreCase("join") && Integer.parseInt(lines[2]) > 0) {

            if (SCB.perms.has(e.getPlayer(), "ssba.admin.createsign")) {

                Integer arenaID = Integer.parseInt(lines[2]);
                if (!SCB.getInstance().ARC.getConfig().contains("arena.arenas." + arenaID.toString())) {
                    e.setCancelled(true);
                    e.getBlock().breakNaturally();
                    e.getPlayer().sendMessage(SCB.getMessageManager().getErrorMessage("listeners.signchange.arenaIdNotFound").replace("%ID%", arenaID.toString()));
                    return;
                }
                ConfigurationSection config = SCB.getInstance().ARC.getConfig().getConfigurationSection("arena.arenas." + arenaID.toString());

                if (!config.getBoolean("enabled")) {
                    e.setCancelled(true);
                    e.getBlock().breakNaturally();
                    e.getPlayer().sendMessage(SCB.getMessageManager().getErrorMessage("listeners.signchange.arenasNotEnabled").replace("%ID%", arenaID.toString()));
                    return;
                }

                e.setLine(0, Col.Dark_Red() + Col.Bold() + "[Arena-" + arenaID.toString().trim() + "]");
                e.setLine(3, Col.Green() + Col.Bold() + config.getString("status").toUpperCase());
                e.setLine(2, Col.Dark_Blue() + "0/" + config.getString("settings.maxPlayers") + Col.Reset());
                e.setLine(1, config.getString("map").toUpperCase());
                e.getPlayer().sendMessage(SCB.getMessageManager().getAdminMessage("listeners.signchange.arenaSignSuccess").replace("%MAP%", config.getString("map")));
                SCB.getInstance().getLogger().info("An Arena Join Sign has been place by " + e.getPlayer().getName());
                return;
            } else {
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                e.getPlayer().sendMessage(SCB.getMessageManager().getErrorMessage("listeners.signchange.noPerms"));
                SCB.getInstance().getLogger().info("A Arena Join sign was canceled due to no perms by " + e.getPlayer().getName());
            }


        }


    }

}
