package com.relicum.scb.objects.location;

import org.bukkit.Location;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public interface ILocation {

    /**
     * Set Location
     */
    public void setLocation(Location l);


    /**
     * Set LocationType
     */
    public void setLocationType();


    /**
     * Get the World the Location is in
     *
     * @return String
     */
    public String getWorld();

    /**
     * Returns Stored Location
     *
     * @return Location
     */
    public Location getLocation();

}
