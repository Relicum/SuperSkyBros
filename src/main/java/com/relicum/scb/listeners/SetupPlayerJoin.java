package com.relicum.scb.listeners;

import java.util.Arrays;
import java.util.List;
import com.relicum.scb.configs.ServerStatus;
import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.types.SkyApi;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * PlayerJoin Listener that is only enabled at the started to force them to do a
 * basic setup This is so they know rough how to then setup arenas. During this
 * time normal players won't be able to login
 * <p/>
 * First Created 08/01/14
 * 
 * @author Relicum
 * @version 0.1
 */
public class SetupPlayerJoin implements Listener {

    private ServerStatus status;

    public SetupPlayerJoin(ServerStatus st) {
        this.status = st;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onSetUpJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        /*
         * CraftPlayer p = (CraftPlayer) e.getPlayer(); EntityPlayer player =
         * p.getHandle(); NBTTagCompound nbtTagCompound = new NBTTagCompound();
         * NBTTagCompound nbtTagCompound2 = new NBTTagCompound(); //
         * playerAbilities.a(nbtTagCompound);
         * player.abilities.canInstantlyBuild=false;
         * player.abilities.isInvulnerable=true; player.abilities.mayBuild =
         * true; player.abilities.canFly = true;
         * player.abilities.canInstantlyBuild = false; player.updateAbilities();
         * p =(CraftPlayer) player.getBukkitEntity();
         * p.setPlayerListName("TheKing");
         * 
         * System.out.println("Inv is set to" + player.isInvulnerable() +
         * " instbuild is " + player.abilities.canInstantlyBuild); player=null;
         */
        // player.b(nbtTagCompound2);
        if (p.isOp() || VaultManager.perms.has(p, "ssba.admin")) {

            e.setJoinMessage("");
            List<String> st = Arrays.asList("\u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC[\u00A7b\u00A7lSuper-Sky-Bros-Setup\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<",
                    ChatColor.GOLD + e.getPlayer().getName() + " welcome to the server");
            p.sendMessage((String[]) st.toArray());
            ChatColor b = ChatColor.BOLD;
            String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
            p.sendMessage(pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros " + "Beta " + SkyApi.getSCB().getDescription().getVersion() + " this "
                    + "should not be run on a live server be warned");

            p.sendMessage("");
            int sts = status.ordinal();

            if (SkyApi.getSm().isDedicated() && sts == 1 && !SkyApi.getSCB().getConfig().getBoolean("autosetupRun")) {
                p.sendMessage(ChatColor.GREEN + "As the sever is set to dedicated mode you can use the auto setup feature which speeds things up");
                p.sendMessage(ChatColor.GREEN + "To find out more run " + ChatColor.GOLD + "/ssbw autosetup " + ChatColor.GREEN + " for more details");
                p.sendMessage("");
            }

            switch (sts) {
                case 1:
                    p.sendMessage(SkyApi.getMessageManager().getAdminMessage("setup.lobby"));
                    break;
                case 2:
                    p.sendMessage(SkyApi.getMessageManager().getAdminMessage("setup.arena"));
                    break;
                case 3:
                    p.sendMessage(SkyApi.getMessageManager().getAdminMessage("setup.setspawns"));
                    break;
                case 4:
                    p.sendMessage(SkyApi.getMessageManager().getAdminMessage("setup.arenalobby"));
                    break;
                case 5:
                    p.sendMessage(SkyApi.getMessageManager().getAdminMessage("setup.enable"));
                    break;
                case 6:
                    p.sendMessage(SkyApi.getMessageManager().getAdminMessage("setup.arenasign"));
                    break;
                default:
                    p.sendMessage(ChatColor.DARK_RED + "Error has occurred run out of status's");
                    SkyApi.getCMsg().SERVE("Error has occurred with setup in class SetupPlayerJoin please report this to developers");
                    break;

            }

        }

    }

    // Kicks all players without admin or op until at least a single arena is
    // set up
    @EventHandler(priority = EventPriority.LOWEST)
    public void playerJoin(PlayerLoginEvent event) {
        if (!event.getPlayer().isOp() || !VaultManager.perms.has(event.getPlayer(), "ssba.admin")) {
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, SkyApi.getMessageManager().getMessage("system.kickJoinNoPerm"));

        }
    }
}
