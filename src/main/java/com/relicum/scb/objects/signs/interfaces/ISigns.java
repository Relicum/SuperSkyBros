package com.relicum.scb.objects.signs.interfaces;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

import java.util.ArrayList;

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
     * @return Integer
     */
    Integer getID();

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
     * @param SignChangeEvent the event
     */
    @EventHandler
    void createSign(SignChangeEvent e);

    /**
     * Function to create destroy sign
     *
     * @param BlockBreakEvent the event
     */
    @EventHandler
    void destroySign(BlockBreakEvent e);

}
