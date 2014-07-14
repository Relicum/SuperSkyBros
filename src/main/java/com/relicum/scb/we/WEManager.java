package com.relicum.scb.we;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class WEManager {

    private WorldEditPlugin WPL = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

    public WEManager WEManager() {

        return this;

    }

    /**
     * @return WorldEditPlugin
     * @throws ClassCastException
     */
    @SuppressWarnings("ConstantConditions")
    public WorldEditPlugin getWEP() {

        return (WorldEditPlugin) WPL;
    }

    /**
     * Gets a new instance of CuboidRegionSelector This can't be static as it is
     * unique to the Arena
     *
     * @return CuboidRegionSelector
     */

    public CuboidSelection getSelector(Player pt) {
        CuboidSelection rs;
        rs = null;
        try {
            rs = (CuboidSelection) getWEP().getSelection(pt);
            rs.getRegionSelector();
        } catch (ClassCastException e) {
            WPL.getLogger().log(Level.SEVERE, e.getStackTrace().toString());
        }
        return rs;
    }

}
