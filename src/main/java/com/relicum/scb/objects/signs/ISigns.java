package com.relicum.scb.objects.signs;

import org.bukkit.event.block.SignChangeEvent;

import java.util.ArrayList;

/**
 * SuperSkyBros Sign Class Interface First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public interface ISigns {

    /**
     * Gets the signs ID
     *
     * @return Integer
     */
    Integer getID();

    /**
     * Returns the Type of Sign
     *
     * @return ESign
     */
    ESign getType();

    /**
     * @return ArrayList<String>
     */
    ArrayList<String> getLines();


    /**
     * Return Create Permission
     *
     * @return String
     */
    String getCreatePermission();

    /**
     * Return Create Permission Message
     *
     * @return String
     */
    String getCreatePermissionMessage();

    /**
     * Function to create the sign
     *
     * @param SignChangeEvent
     * @return boolean
     */
    boolean createSign(SignChangeEvent e);

}
