package com.relicum.scb.objects.signs.interfaces;

import com.relicum.scb.SmashPl;
import org.bukkit.block.Sign;

/**
 * SuperSkyBros First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public interface PSign {

    /**
     * Return Use Permission
     *
     * @return String
     */
    String getUsePermission();

    /**
     * Return Use Permission Message
     *
     * @return String
     */
    String getUserPermissionMessage();

    /**
     * Player Interacts with sign this function deals with it.
     *
     * @param Sign
     * @param SmashPlayer
     * @return boolean
     */
    boolean useSign(Sign sign, SmashPl player);

}
