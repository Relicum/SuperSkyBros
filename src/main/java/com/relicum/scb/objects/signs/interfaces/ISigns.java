package com.relicum.scb.objects.signs.interfaces;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

import java.util.ArrayList;
import java.util.UUID;

/**
 * SuperSkyBros BaseSign Class Interface First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public interface ISigns {

    /**
     * Gets the signs ID
     *
     * @return UUID of the sign
     */
    UUID getID();

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
     * Function to create destroy sign
     *
     * @param BlockBreakEvent the event
     */
    @EventHandler
    void destroySign(BlockBreakEvent e);

}
