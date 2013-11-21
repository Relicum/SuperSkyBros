package com.relicum.scb.objects.signs.interfaces;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

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
     * Function to create destroy sign
     *
     * @param BlockBreakEvent the event
     */
    @EventHandler
    void destroySign(BlockBreakEvent e);

}
