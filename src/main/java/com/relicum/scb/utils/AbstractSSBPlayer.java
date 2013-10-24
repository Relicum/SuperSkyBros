package com.relicum.scb.utils;

import com.relicum.scb.classes.PlayerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * SuperSkyBros First Created 20/10/13 SSB Player Abstract class
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class AbstractSSBPlayer {

    /**
     * The Old id.  in UUID format
     */
    public UUID ArmourId = null;

    protected PlayerType playerType;


    protected AbstractSSBPlayer(String name) {


        this.lookupPlayer(Bukkit.getPlayerExact(name));

        if (this.ArmourId == null) {
            this.ArmourId = UUID.randomUUID();
        }
    }


    /**
     * Gets armour unique iD in UUID format
     *
     * @return the armour unique iD
     */
    public UUID getArmourUniqueID() {

        return this.ArmourId;
    }


    /**
     * Lookup player.  To validate what type of player they are
     *
     * @param player the player
     * @return the player type from of the types in PlayerType
     */
    public abstract void lookupPlayer(Player player);


    /**
     * Manage the result of the player look up
     *
     * @param obj Object
     */
    protected abstract void managePlayerLookupResult(Object obj);
}
