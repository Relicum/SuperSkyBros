package com.relicum.scb.classes.utils;

import java.util.UUID;

/**
 * The interface IPlayer.
 */
public interface IPlayer {

    public UUID playerId = null;

    public String playerName = null;

    /**
     * Gets player's unique iD in UUID format
     *
     * @return the armour unique iD
     */
    public UUID getArmourUniqueID();


    /**
     * Sets player name.
     *
     * @param name the name
     */
    public void setPlayerName(String name);

}
