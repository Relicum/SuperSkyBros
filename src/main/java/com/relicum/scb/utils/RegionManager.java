package com.relicum.scb.utils;

import com.relicum.scb.types.SkyApi;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Used to Manage WorldEdit Regions
 *
 * @author Relicum
 * @version 0.1
 */
public class RegionManager {

    private static MessageManager mm = SkyApi.getMessageManager();
    private Region r;
    private Map<String, Region> RS = new HashMap<String, Region>();
    private World lw;

    private Location lo;

    private String errorMessage = "";

    @SuppressWarnings("MethodWithMultipleReturnPoints")
    public String RegionManager(Region rg, Location lo, String nm) {
        this.r = rg;
        this.lo = lo;

        if (rg == null) {
            return mm.getErrorMessage("command.message.notARegion");
        }

        SetRegion(rg);
        getLocalWorldOfRegion(rg);
        return null;
    }

    private void SetRegion(Region reg) {
        r = reg;
    }

    public World getLocalWorldOfRegion(Region reg) {

        lw = r.getWorld();
        return lw;
    }

    public String getStringNameOfLocalWorld() {

        return lw.getName();
    }

    public String createArenaSpawn() {

        if (!worldCheck()) {
            return errorMessage;
        }

        Vector max = r.getMaximumPoint();
        Vector min = r.getMinimumPoint();
        Location lmax = vectorToLocation(max);
        Location lmin = vectorToLocation(min);

        return null;
    }

    /**
     * Converts a WorldEdit Vector into a Bukkit Location
     *
     * @param ve Vector
     * @return Location
     */
    public Location vectorToLocation(Vector ve) {

        ve.ceil();
        Location l = new Location(lo.getWorld(), ve.getX(), ve.getY(), ve.getZ());
        return l;

    }

    /**
     * Checks to see if the region and the players are in the same world returns
     * true if there not returns false if they aren't and sets the errorMessage
     * string
     *
     * @return boolean
     */
    private boolean worldCheck() {

        String pWorld = lo.getWorld().toString();
        String rWorld = getStringNameOfLocalWorld();

        if (!pWorld.equalsIgnoreCase(rWorld)) {

            this.errorMessage = mm.getErrorMessage("command.message.worldsMisMatch");
            return false;
        } else {
            return true;
        }
    }

}
