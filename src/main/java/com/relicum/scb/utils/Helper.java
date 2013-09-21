package com.relicum.scb.utils;

import com.relicum.scb.SCB;
import com.sk89q.worldedit.Vector;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class Helper {

    private static Helper instance = new Helper();

    protected static SCB pl;


    private Helper() {

    }


    public static Helper getInstance() {

        return instance;
    }


    public void setup(SCB p) {
        Helper.pl = p;
    }


    public boolean fileExists(String fi) {

        File file = new File(pl.getDataFolder(), fi);

        try {
            if (!file.exists()) {
                return false;
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * @param fi String
     * @return boolean
     */
    public boolean loadFile(String fi) {

        File file = new File(pl.getDataFolder(), fi);
        try {
            if (file.createNewFile()) {
                FileConfiguration fCon = YamlConfiguration.loadConfiguration(pl.getResource(fi));
                fCon.save(file);
                return true;
            }

        }
        catch ( Exception e ) {
            e.printStackTrace();

        }
        return false;
    }


    /**
     * Returns players current world name as a string
     *
     * @param pl Player
     * @return String
     */
    public String getPlayersCurrentWorld(Player pl) {

        return pl.getWorld().toString();
    }


    /**
     * Returns players current world as a World object
     *
     * @param pl Player
     * @return World
     */
    public World getPlayersWorld(Player pl) {

        return pl.getWorld();
    }


    /**
     * Returns the player current location as a Vector
     *
     * @param pl Player
     * @return Vector
     */
    public Vector currentPlayerLocation(Player pl) {

        Location loc = pl.getLocation();

        Vector v = new Vector(loc.getX(), loc.getY(), loc.getZ());

        return v;
    }


}
