package com.relicum.scb.listeners;

import java.util.List;
import com.relicum.scb.SCB;
import com.relicum.scb.configs.ServerStatus;
import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.objects.signs.utils.Col;
import com.relicum.scb.types.SkyApi;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * Manages the creation of Interactive Game Signs
 * 
 * @author Relicum
 * @version 0.1
 */
public class SignChange implements Listener {

    public List<String> blacklist;

    public boolean isDedicated;

    private SCB plugin = SkyApi.getSCB();

    public SignChange() {
        blacklist = SkyApi.getSm().blackListed();
        this.isDedicated = SkyApi.getSCB().getConfig().getBoolean("dedicatedSSB");

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void signChange(SignChangeEvent e) throws InterruptedException {
        String[] lines = e.getLines();
        if (this.blacklist.contains(e.getPlayer().getWorld().getName()) && ((!(lines[1]).equalsIgnoreCase("join")) || (!(lines[0]).equalsIgnoreCase("[SSBLOBBY]"))))
            return;

        // Lobby Join Sign
        if (lines[0].equalsIgnoreCase("[SSBLOBBY]") && lines[1].equalsIgnoreCase("join")) {

            if (VaultManager.perms.has(e.getPlayer(), "ssba.admin.createsign")) {
                e.setLine(0, Col.Dark_Red() + "[JOIN LOBBY]");
                e.setLine(1, "CLICK TO JOIN");
                e.setLine(2, Col.Aqua() + Col.Italic() + "SUPER");
                e.setLine(3, Col.Green() + Col.Italic() + "SKY" + Col.Yellow() + Col.Italic() + "BROS");
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getAdminMessage("listeners.signchange.joinSuccess"));
                plugin.getLogger().info("A Join sign has been place by " + e.getPlayer().getName());

            } else {
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getErrorMessage("listeners.signchange.noPerms"));
                plugin.getLogger().info("A Join sign was canceled due to no perms by " + e.getPlayer().getName());
            }

            return;
        }
        // Lobby Leave Sign
        if (lines[0].equalsIgnoreCase("[SSBLOBBY]") && lines[1].equalsIgnoreCase("leave")) {

            if (VaultManager.perms.has(e.getPlayer(), "ssba.admin.createsign")) {
                e.setLine(0, Col.Dark_Red() + "[LEAVE LOBBY]");
                e.setLine(1, "CLICK TO LEAVE");
                e.setLine(2, Col.Aqua() + Col.Italic() + "SUPER");
                e.setLine(3, Col.Green() + Col.Italic() + "SKY" + Col.Yellow() + Col.Italic() + "BROS");
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getAdminMessage("listeners.signchange.leaveSuccess"));
                plugin.getLogger().info("A Leave sign has been place by " + e.getPlayer().getName());
            } else {
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getErrorMessage("listeners.signchange.noPerms"));
                plugin.getLogger().info("A leave sign was canceled due to no perms by " + e.getPlayer().getName());
            }

            return;
        }
        // Lobby Return Sign used in arena lobbies to take the player back to
        // the main lobby
        if (lines[0].equalsIgnoreCase("[SSBLOBBY]") && lines[1].equalsIgnoreCase("return")) {

            if (VaultManager.perms.has(e.getPlayer(), "ssba.admin.createsign")) {
                e.setLine(0, Col.Dark_Red() + "[RETURN]");
                e.setLine(1, "CLICK TO RETURN");
                e.setLine(2, "TO LOBBY");
                e.setLine(3, Col.Dark_Blue() + "SUPERSKYBROS");
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getAdminMessage("listeners.signchange.leaveGame"));
                plugin.getLogger().info("A Leave Game has been place by " + e.getPlayer().getName());
            } else {
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getErrorMessage("listeners.signchange.noPerms"));
                plugin.getLogger().info("A leave sign was canceled due to no perms by " + e.getPlayer().getName());
            }

            return;
        }
        // Arena Join Sign updates to show game status, number of players etc
        if (lines[0].equalsIgnoreCase("[SSBARENA]") && lines[1].equalsIgnoreCase("join") && Integer.parseInt(lines[2]) > 0) {

            if (VaultManager.perms.has(e.getPlayer(), "ssba.admin.createsign")) {

                Integer arenaID = Integer.parseInt(lines[2]);
                if (!SkyApi.getSm().getArenaConfig().getConfig().contains("arena.arenas." + arenaID.toString())) {
                    e.setCancelled(true);
                    e.getBlock().breakNaturally();
                    e.getPlayer().sendMessage(SkyApi.getMessageManager().getErrorMessage("listeners.signchange" + ".arenaIdNotFound").replace("%ID%", arenaID.toString()));
                    return;
                }
                ConfigurationSection config = SkyApi.getSm().getArenaConfig().getConfig().getConfigurationSection("arena.arenas." + arenaID.toString());

                if (!config.getBoolean("enabled")) {
                    e.setCancelled(true);
                    e.getBlock().breakNaturally();
                    e.getPlayer().sendMessage(SkyApi.getMessageManager().getErrorMessage("listeners.signchange" + ".arenasNotEnabled").replace("%ID%", arenaID.toString()));
                    return;
                }

                e.setLine(0, Col.Dark_Red() + Col.Bold() + "[Arena-" + arenaID.toString().trim() + "]");
                e.setLine(3, Col.Green() + Col.Bold() + config.getString("status").toUpperCase());
                e.setLine(2, Col.Dark_Blue() + "0/" + config.getString("settings.maxPlayers") + Col.Reset());
                e.setLine(1, config.getString("map").toUpperCase());
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getAdminMessage("listeners.signchange" + ".arenaSignSuccess").replace("%MAP%", config.getString("map")));

                SkyApi.getSCB().getLogger().info("An Arena Join Sign has been place by " + e.getPlayer().getName());

                if (SkyApi.getSCB().getConfig().getString("serverStatus").equalsIgnoreCase(ServerStatus.CREATEJOINSIGN.name())) {
                    SkyApi.getSCB().getConfig().set("serverStatus", ServerStatus.READY.name());
                    SkyApi.getSCB().saveConfig();
                    SkyApi.getSCB().reloadConfig();
                    e.getPlayer().sendMessage("");
                    e.getPlayer().sendMessage(SkyApi.getMessageManager().getMessage("setup.ready"));
                }

            } else {
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                e.getPlayer().sendMessage(SkyApi.getMessageManager().getErrorMessage("listeners.signchange.noPerms"));
                plugin.getLogger().info("A Arena Join sign was canceled due to no perms by " + e.getPlayer().getName());
            }

        }

    }

}
