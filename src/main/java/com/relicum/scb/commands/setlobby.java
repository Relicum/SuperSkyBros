package com.relicum.scb.commands;

import com.relicum.scb.SCB;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class setlobby extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) {
        String perm = "ssb.player.join";
        WorldEditPlugin wm = new WEManager().getWEP();
        Selection cr = wm.getSelection(player);
        Vector rmin;
        try {
            rmin = new Vector(
                    cr.getMinimumPoint().getBlockX(), cr.getMinimumPoint().getBlockY(), cr.getMinimumPoint().getBlockZ());

        } catch (Exception e) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.setlobbyNoSel"));
            SkyApi.getCMsg().SERVE("Error setting lobby WorldEdit selection not set correctly");
            e.printStackTrace();
            return true;
        }


        Vector rmax;

        rmax = new Vector(
                cr.getMaximumPoint().getBlockX(), cr.getMaximumPoint().getBlockY(), cr.getMaximumPoint().getBlockZ());

        Vector lobbySpawn = new Vector(
                player.getLocation().getBlockX() + 0.5, player.getLocation().getBlockY() + 0.5, player.getLocation().getBlockZ() + 0.5);
        Float dir = SkyApi.getLobbyManager().getDirection(player.getLocation().getYaw());

        LobbyRg region = new LobbyRg(
                rmin, rmax, lobbySpawn, player.getWorld().getName(), perm, player.getLocation().getYaw());

        LobbyConfig LC = SkyApi.getLobbyManager().getLobbySaveObject();

        Location location = player.getLocation();
        location.setPitch((float) Math.round(player.getLocation().getPitch()));
        location.setYaw(StringUtils.getDirection(player.getLocation().getYaw()));
        SerializedLocation minls = new SerializedLocation(cr.getMinimumPoint());
        SerializedLocation maxls = new SerializedLocation(cr.getMaximumPoint());
        SerializedLocation sspawn = new SerializedLocation(location);

        //TEMP TESTING ON NEW NODES
        Lobby2Config lobby2Config = SkyApi.getSm().getLobby2Config();

        ConfigurationSection l2 = lobby2Config.getConfig().createSection("main-lobby");
        Map<String, Object> players = new HashMap<>();
        players.put("Relicum.armour", player.getInventory().getArmorContents());
        players.put("Relicum.contents", player.getInventory().getContents());
        player.closeInventory();
        l2.set("points.min", cr.getMinimumPoint().toVector());
        l2.set("points.max", cr.getMaximumPoint().toVector());
        l2.set("spawn", sspawn);
        l2.set("permission", "ssb.player.join");
        l2.set("type", LocationType.LOBBYSPAWN.toString());
        l2.set("status", LobbyStatus.ONLINE.toString());
        l2.set("checks.canFly", false);
        l2.set("checks.canBuild", true);
        l2.set("inventories", players);

        lobby2Config.saveConfig();
        lobby2Config.reloadConfig();
        //END TEMP TESTING


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

            LocationChecker lc = new LocationChecker(cr.getMaximumPoint().toVector(), cr.getMinimumPoint().toVector(), "LOBBY", 1);
            ConfigurationSection box = LC.getConfig().getConfigurationSection("lobby.box");
            box.set("min", minls);
            box.set("max", maxls);
            box.set("spawn", sspawn);
            box.set("world", player.getWorld().getName());
            box.set("perm", "ssb.player.join");
            box.set("checker", lc);

            if (!LC.getConfig().contains(SCB.LOBBYSET)) {
                LC.getConfig().createSection(SCB.LOBBYSET);
            }
            LC.getConfig().set(SCB.LOBBYSET, true);
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

        SCB.getInstance().getConfig().set("enableLobbyProtection", true);
        if (SkyApi.getSCB().getConfig().getString("serverStatus").equalsIgnoreCase(ServerStatus.SETLOBBY.name())) {
            SkyApi.getSCB().getConfig().set("serverStatus", ServerStatus.SETARENA.name());
        }

        wm.getWorldEdit().clearSessions();

        SkyApi.getSCB().saveConfig();
        SkyApi.getSCB().reloadConfig();
        SkyApi.getSCB().loadLobbyEvents();

        player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.setlobbySuccess"));
        SkyApi.getCMsg().INFO("Lobby Region and Spawn Point have Been Successfully Set");

        return true;
    }


    /**
     * Simplify set this function to set the field mNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = "setlobby";
    }


    /**
     * Simply set this to return the the number of arguments The command should receive
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
        return SCB.getInstance();
    }
}
