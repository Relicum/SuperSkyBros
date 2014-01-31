package com.relicum.scb.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.relicum.scb.arena.ArenaIO;
import com.relicum.scb.arena.ArenaRegion;
import com.relicum.scb.configs.ArenaConfig;
import com.relicum.scb.configs.ServerStatus;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.SerializedLocation;
import com.relicum.scb.we.WEManager;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Create a new Arena
 * 
 * @author Relicum
 * @version 0.4
 */
public class createarena extends SubBase {

    /**
     * Stores Hashes of Chunks in Region
     */
    private Map<String, Object> map = new HashMap<>();

    /**
     * @param player Player
     * @param args String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) {

        String aname = args[0];

        ArenaRegion AER;
        WorldEditPlugin wm = new WEManager().getWEP();
        Selection S = wm.getSelection(player);

        Vector cen = null;
        try {
            cen = new Vector(S.getRegionSelector().getRegion().getCenter());
        } catch (IncompleteRegionException e) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.noSelection"));
            SkyApi.getCMsg().SERVE("Error setting Arena Region, WorldEdit selection not set correctly");
            e.printStackTrace();
            return true;
        }

        ArenaConfig ac = SkyApi.getSm().getArenaConfig();

        Integer last = ac.getConfig().getInt("arena.lastId");
        last += 1;

        org.bukkit.util.Vector rmax = new org.bukkit.util.Vector(S.getMaximumPoint().getBlockX(), S.getMaximumPoint().getBlockY(), S.getMaximumPoint().getBlockZ());

        org.bukkit.util.Vector rmin = new org.bukkit.util.Vector(S.getMinimumPoint().getBlockX(), S.getMinimumPoint().getBlockY(), S.getMinimumPoint().getBlockZ());
        org.bukkit.util.Vector radmin = null;

        radmin = new org.bukkit.util.Vector(cen.getBlockX() + 0.5, S.getMaximumPoint().getBlockY() + 2, cen.getBlockZ() + 0.5);

        List<org.bukkit.util.Vector> chunks = new ArrayList<>();
        chunks.add(S.getMaximumPoint().toVector());
        chunks.get(0).setY(radmin.getY());

        AER = new ArenaRegion(rmin, rmax, radmin, last, player.getWorld().getName(), aname, new SerializedLocation(S.getMaximumPoint()),
                              new SerializedLocation(S.getMinimumPoint()), new SerializedLocation(radmin.toLocation(player.getWorld())));

        ArenaIO AIO = new ArenaIO();
        if (AIO.newCreate(AER, chunks)) {
            SkyApi.getCMsg().INFO("Region has been created");
            // player.sendMessage("A new arena has been created and stored");
        } else {
            SkyApi.getCMsg().SERVE("Error creating or saving new Arena");
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.failedToCreateArena"));
            return true;
        }
        if (SkyApi.getSCB().getConfig().getString("serverStatus").equalsIgnoreCase(ServerStatus.SETARENA.name())) {
            SkyApi.getSCB().getConfig().set("serverStatus", ServerStatus.SETAREASPAWNS.name());
            SkyApi.getSCB().saveConfig();
        }
        String cm = SkyApi.getMessageManager().getAdminMessage("command.message.createArenaSucceeded");
        String ct;
        ct = cm.replace("%nn%", last.toString());
        player.sendMessage(ct);
        player.sendMessage("");
        player.sendMessage(SkyApi.getMessageManager().getMessage("setup.setspawns"));
        return true;

    }

    /**
     * Simplify set this function to set the field descNode with the commands
     * description will come from in the messages.yml file You do not need to
     * enter the full node as it will be prefixed for you. Eg is the full node
     * is command.description.createarena you only need to set this to
     * createarena
     */
    @Override
    public void setmDescription() {
        mNode = "createarena";
    }

    /**
     * Simply set this to return the the number of arguments The command should
     * receive
     * 
     * @return Integer
     */
    @Override
    public Integer setNumArgs() {
        return 1;
    }

    /**
     * Simply set this to return the clist permission
     * 
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssba.admin.createarena";
    }

    /**
     * Simply set this to return the clist Usage
     * 
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba createarena [name]";
    }

    /**
     * Set this to the label of the command
     * 
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba createarena";
    }

    /**
     * Set com
     * 
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba createarena";
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
