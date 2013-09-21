package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.arena.ArenaIO;
import com.relicum.scb.arena.ArenaRegion;
import com.relicum.scb.configs.ArenaConfig;
import com.relicum.scb.we.WEManager;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
     * @param args   String[]
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
        }
        catch ( IncompleteRegionException e ) {
            player.sendMessage(SCB.MM.getErrorMessage("command.message.noSelection"));
            SCB.getInstance().getLogger().severe("Error setting Arena Region, WorldEdit selection not set correctly");
            e.printStackTrace();
            return true;
        }


        ArenaConfig ac = SCB.getInstance().ARC;

        Integer last = ac.getConfig().getInt("arena.lastId");
        last += 1;

        org.bukkit.util.Vector rmax = new org.bukkit.util.Vector(S.getMaximumPoint().getBlockX(), S.getMaximumPoint().getBlockY(), S.getMaximumPoint().getBlockZ());

        org.bukkit.util.Vector rmin = new org.bukkit.util.Vector(S.getMinimumPoint().getBlockX(), S.getMinimumPoint().getBlockY(), S.getMinimumPoint().getBlockZ());
        org.bukkit.util.Vector radmin = null;

        radmin = new org.bukkit.util.Vector(cen.getBlockX(), S.getMaximumPoint().getBlockY() + 2, cen.getBlockZ());

        List<org.bukkit.util.Vector> chunks = new ArrayList<>();
        chunks.add(S.getMaximumPoint().toVector());
        chunks.get(0).setY(radmin.getY());

        AER = new ArenaRegion(rmin, rmax, radmin, last, player.getWorld().getName(), aname);

        ArenaIO AIO = new ArenaIO();
        if (AIO.newCreate(AER, chunks)) {
            System.out.println("Region has been created");
            //player.sendMessage("A new arena has been created and stored");
        } else {
            SCB.getInstance().getLogger().severe("Error creating or saving new Arena");
            player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.failedToCreateArena"));
            return true;
        }


        String cm = SCB.getMessageManager().getAdminMessage("command.message.createArenaSucceeded");
        String ct;
        ct = cm.replace("%nn%", last.toString());
        player.sendMessage(ct);
        return true;


    }


    /**
     * Simplify set this function to set the field descNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = "createarena";
    }


    /**
     * Simply set this to return the the number of arguments The command should receive
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


}
