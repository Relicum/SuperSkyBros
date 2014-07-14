package com.relicum.scb.objects;

import com.relicum.scb.objects.location.ILocationImpl;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class ISpawn extends ILocationImpl {

    public String Perm;
    protected String ty;


    /**
     * Constructor that takes a Location Returns an instance of itself for chaining
     *
     * @param Loc Location
     * @throws NullPointerException
     */
    protected ISpawn(Location Loc) {
        super(Loc);
    }


    @Override
    public float getDirection(Float yaw) {
        return super.getDirection(yaw);
    }

    /**
     * Get the type of spawn
     *
     * @return String
     */
    public String getType() {

        return ty.toString();

    }

    /**
     * Set The type of spawn
     *
     * @param lt String
     */
    public void setType(String lt) {

        ty = LocationType.valueOf(lt).name();
    }

    /**
     * Get the permission to use the spawn.
     *
     * @return boolean
     */
    public abstract String getPerm();

    /**
     * Set the permission to go to  spawn
     *
     * @param per String
     */
    public abstract void setPerm(String per);

    /**
     * Teleports the player to the given location
     *
     * @param p Player
     * @param l Location
     * @return boolean
     */
    public abstract boolean teleportToLobby(Player p, Location l);

    /**
     * Method used to save the data to storage
     *
     * @return boolean
     */
    public abstract boolean save();

    /**
     * Method used to load the data from storage
     *
     * @return boolean
     */
    public abstract boolean load();


    /**
     * Gets the X co-ord
     *
     * @return double
     */
    public double getX() {
        DecimalFormat f = new DecimalFormat("0.00");
        String fd = f.format(Loc.getX());
        try {
            return (double) f.parse(fd);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Loc.getX();
    }


    /**
     * Gets the Y co-ord
     *
     * @return double
     */
    public double getY() {

        return Loc.getY();
    }


    /**
     * Gets the Z co-ord
     *
     * @return double
     */
    public double getZ() {
        DecimalFormat f = new DecimalFormat("0.00");
        String fd = f.format(Loc.getZ());
        try {
            return (double) f.parse(fd);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Loc.getZ();
    }


    public float getYaw() {

        return getDirection(Loc.getYaw());
    }


    public float getPitch() {

        return Loc.getPitch();
    }
}
