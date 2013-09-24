package com.relicum.scb.objects.signs.interfaces;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * SuperSkyBros First Created 23/09/13 Location of the sign
 *
 * @author Relicum
 * @version 0.1
 */
public interface LSigns {

    /**
     * Gets world the sign is in
     *
     * @return String the sign world
     */
    String getSignWorld();

    /**
     * Gets sign as vector.
     *
     * @return Vector the sign as a vector
     */
    Vector getSignAsVector();

    /**
     * Gets sign X Co Ordinate.
     *
     * @return int the signs X
     */
    int getSignX();

    /**
     * Gets sign Y Co Ordinate.
     *
     * @return int the signs Y
     */
    int getSignY();

    /**
     * Gets sign Z Co Ordinate.
     *
     * @return int the signs Z
     */
    int getSignZ();


    /**
     * Gets Yaw of the sign
     *
     * @return float the sign Yaw
     */
    float getYaw();

    /**
     * Gets Pitch of the sign
     *
     * @return float the pitch of the sign
     */
    float getPitch();

    /**
     * Gets Location of the sign
     *
     * @return Location the sign as location
     */
    Location getSignAsLocation();


}
