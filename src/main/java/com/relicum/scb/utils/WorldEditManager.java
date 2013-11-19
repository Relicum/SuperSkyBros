package com.relicum.scb.utils;

import com.relicum.scb.SCB;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import org.bukkit.entity.Player;

/**
 * Used to Create and Manage World Edit
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldEditManager {

    private static WorldEditManager instance = new WorldEditManager();

    private static SCB p;

    private static Helper hp = Helper.getInstance();

    private Region r;

    private RegionSelector rs;

    private WorldEditPlugin WEP;


    /**
     * Default Constructor DO NOT USE
     */
    public WorldEditManager() {


    }


    /**
     * Gets a instance of WorldEditManager
     *
     * @return WorldEditManager
     */
    public static WorldEditManager getInstance() {

        return instance;

    }


    /**
     * Sets up WorldEditManager Just pass it instance of SCB
     *
     * @param p SCB
     */
    public void setup(SCB p) {

        WorldEditManager.p = p;
        org.bukkit.plugin.Plugin WE = WorldEditManager.p.getServer().getPluginManager().getPlugin("WorldEdit");

        if (WE instanceof WorldEditPlugin) {

            WEP = (WorldEditPlugin) WE;
        }


    }


    /**
     * Get the current Selection as region
     *
     * @param pl Player
     * @return IRegion
     */
    public Region getSelectionAsRegion(Player pl) {

        String pworld = hp.getPlayersCurrentWorld(pl);

        Selection sel = WEP.getSelection(pl);
        RegionSelector rs = sel.getRegionSelector();
        try {

            r = rs.getRegion();

        }
        catch ( IncompleteRegionException e ) {

            e.printStackTrace();

        }
        return r;
    }


}
