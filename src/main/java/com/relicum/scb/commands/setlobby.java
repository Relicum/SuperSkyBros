package com.relicum.scb.commands;

import com.relicum.scb.configs.Lobby2Config;
import com.relicum.scb.configs.LobbyConfig;
import com.relicum.scb.configs.ServerStatus;
import com.relicum.scb.objects.LobbyRg;
import com.relicum.scb.objects.LocationType;
import com.relicum.scb.objects.location.LobbyStatus;
import com.relicum.scb.objects.world.ApplyWorldSettings;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.LocationChecker;
import com.relicum.scb.utils.SerializedLocation;
import com.relicum.scb.utils.StringUtils;
import com.relicum.scb.we.WEManager;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

/**
 * Bukkit-SCB
 * 
 * @author Relicum
 * @version 0.1
 */
public class setlobby extends SubBase {

    /**
     * @param player Player
     * @param args String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) {
        String perm = "ssb.player.join";
        WorldEditPlugin wm = new WEManager().getWEP();
        Selection cr = wm.getSelection(player);
        Vector rmin;
        try {
            rmin = new Vector(cr.getMinimumPoint().getBlockX(), cr.getMinimumPoint().getBlockY(), cr.getMinimumPoint().getBlockZ());

        } catch (Exception e) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.setlobbyNoSel"));
            SkyApi.getCMsg().SERVE("Error setting lobby WorldEdit selection not set correctly");
            e.printStackTrace();
            return true;
        }

        Vector rmax;

        rmax = new Vector(cr.getMaximumPoint().getBlockX(), cr.getMaximumPoint().getBlockY(), cr.getMaximumPoint().getBlockZ());

        Vector lobbySpawn = new Vector(player.getLocation().getBlockX() + 0.5, player.getLocation().getBlockY() + 0.5, player.getLocation().getBlockZ() + 0.5);
        Float dir = SkyApi.getLobbyManager().getDirection(player.getLocation().getYaw());

        LobbyRg region = new LobbyRg(rmin, rmax, lobbySpawn, player.getWorld().getName(), perm, player.getLocation().getYaw());

        LobbyConfig LC = SkyApi.getLobbyManager().getLobbySaveObject();

        Location location = player.getLocation();

        location.setPitch((float) Math.round(player.getLocation().getPitch()));
        location.setYaw(StringUtils.getDirection(player.getLocation().getYaw()));
        SerializedLocation minls = new SerializedLocation(cr.getMinimumPoint());
        SerializedLocation maxls = new SerializedLocation(cr.getMaximumPoint());
        SerializedLocation sspawn = new SerializedLocation(location);
        LocationChecker lc = new LocationChecker(cr.getMaximumPoint().toVector(), cr.getMinimumPoint().toVector(), "LOBBY", 1);

        // TEMP TESTING ON NEW NODES
        Lobby2Config lobby2Config = SkyApi.getSm().getLobby2Config();
        lobby2Config.getConfig().set("LOBBYSET", true);
        ConfigurationSection l2 = lobby2Config.getConfig().createSection("main-lobby");
        l2.set("region", lc);
        l2.set("spawn", sspawn);
        l2.set("permission", "ssb.player.join");
        l2.set("type", LocationType.LOBBYSPAWN.toString());
        l2.set("status", LobbyStatus.ONLINE.toString());
        l2.set("settings.can_fly", false);
        l2.set("settings.can_build", false);
        l2.set("settings.can_jump", true);
        l2.set("settings.god_mode", true);
        l2.set("settings.give_book", true);
        l2.set("settings.give_gem", true);
        l2.set("settings.scoreboard", false);
        l2.set("settings.boss_bar", false);
        l2.set("settings.dedicated_leave", false);
        l2.set("settings.random_join", false);
        l2.set("settings.join_full", false);
        l2.set("settings.colored_names", false);
        l2.set("settings.hide_players", false);
        l2.set("settings.mute_players", false);
        l2.set("settings.join_message", true);

        lobby2Config.saveConfig();
        lobby2Config.reloadConfig();
        // END TEMP TESTING

        try {
            if (!LC.getConfig().contains("LOBBY")) {
                LC.getConfig().createSection("LOBBY");
            }
            LC.getConfig().set("LOBBY.REGION.MIN", region.getMinVector());
            LC.getConfig().set("LOBBY.REGION.MAX", region.getMaxVector());
            LC.getConfig().set("LOBBY.REGION.SPAWN", region.getLobbySpawnVector());
            LC.getConfig().set("LOBBY.REGION.YAW", region.getYaw());
            LC.getConfig().set("LOBBY.REGION.WORLD", region.getWorld().getName());
            LC.getConfig().set("LOBBY.REGION.PERM", "ssb.player.join");

            if (!LC.getConfig().contains("lobby.box")) {
                LC.getConfig().createSection("lobby.box");
            }

            ConfigurationSection box = LC.getConfig().getConfigurationSection("lobby.box");
            box.set("min", minls);
            box.set("max", maxls);
            box.set("spawn", sspawn);
            box.set("world", player.getWorld().getName());
            box.set("perm", "ssb.player.join");
            box.set("checker", lc);

            if (!LC.getConfig().contains("LOBBYSET")) {
                LC.getConfig().createSection("LOBBYSET");
            }
            LC.getConfig().set("LOBBYSET", true);
            LC.saveConfig();
            LC.reloadConfig();
        } catch (Exception e) {

            SkyApi.getCMsg().SERVE("Error: saving Lobby Region");
            e.printStackTrace();
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.setlobbyFail"));
            return true;
        }
        ApplyWorldSettings.apply(player.getWorld());
        SkyApi.getLobbyManager().setLobbyRg(region);

        SkyApi.getSCB().getConfig().set("enableLobbyProtection", true);
        if (SkyApi.getSCB().getConfig().getString("serverStatus").equalsIgnoreCase(ServerStatus.SETLOBBY.name())) {
            SkyApi.getSCB().getConfig().set("serverStatus", ServerStatus.SETARENA.name());
        }

        wm.getWorldEdit().clearSessions();
        wm = null;
        SkyApi.getSCB().saveConfig();
        SkyApi.getSCB().reloadConfig();
        SkyApi.getSCB().loadLobbyEvents();

        player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.setlobbySuccess"));
        SkyApi.getCMsg().INFO("Lobby Region and Spawn Point have Been Successfully Set");
        player.sendMessage("");
        player.sendMessage(SkyApi.getMessageManager().getMessage("setup.arena"));

        return true;
    }

    /**
     * Simplify set this function to set the field mNode with the commands
     * description will come from in the messages.yml file You do not need to
     * enter the full node as it will be prefixed for you. Eg is the full node
     * is command.description.createarena you only need to set this to
     * createarena
     */
    @Override
    public void setmDescription() {
        mNode = "setlobby";
    }

    /**
     * Simply set this to return the the number of arguments The command should
     * receive
     * 
     * @return Integer
     */
    @Override
    public Integer setNumArgs() {
        return 0;
    }

    /**
     * Simply set this to return the clist permission
     * 
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssba.admin.setlobby";
    }

    /**
     * Simply set this to return the clist Usage
     * 
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba setlobby";
    }

    /**
     * Set this to the label of the command
     * 
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba setlobby";
    }

    /**
     * Set com
     * 
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba setlobby";
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
