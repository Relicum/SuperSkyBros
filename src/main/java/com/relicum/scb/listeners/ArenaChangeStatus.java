package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaStatus;
import com.relicum.scb.events.ArenaStatusChangeEvent;
import com.relicum.scb.types.SkyApi;
import org.bukkit.event.Listener;

/**
 * Deals with the ArenaChangeStatusEvent
 * First Created 27/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaChangeStatus implements Listener {

    private SCB plugin = SkyApi.getSCB();
    private String newStatus;
    private String previousStatus;
    private Arena arena;
    public NullPointerException isnull;

    public void statusChange(ArenaStatusChangeEvent e) {
        if (isValid(e.getArenaStatus())) {
            newStatus = e.getArenaStatus();
        } else {
            plugin.getLogger().severe(isnull.getMessage());
        }
        previousStatus = e.getPreviousStatus();
        arena = e.getArena();

        selected(ArenaStatus.valueOf(newStatus));
    }


    public void selected(ArenaStatus status) {

        switch (status) {
            case DISABLED:
                //Check if the Arena was in game
                //Run end game functions
                //Update Signs to Disable
                //Mark Arena as disabled and no longer selectable
                break;
            case WAITING:
                //Check user has permission if so teleport to arena lobby
                //The arena should be checked it is ready
                //Display initial data on Join signs Map,Players,Status
                //Change PlayerList to reflect rank
                //Update Gamer to show players in a arena lobby and their exact location
                //Handle players returning to lobby using return signs
                break;
            case STARTING:
                //Check the criteria is met to start a game
                //Check they have a class if not give a default one
                //apply correct game settings to players
                //Start countdown and display via scoreboard to players
                //Update sign
                //Assign Spawn points to players including future spawns
                //Teleport To Arena nd switch to game scoreboard
                //Start game timer
                //Game starts
                break;
            case INGAME:
                break;
            case RESTARTING:
                break;
            default:
                break;
        }
    }

    /**
     * Validate the status to check it is an ArenaStatus
     *
     * @param String the status to validate
     * @return true if the new status is valid return false if not
     */
    private boolean isValid(String status) {

        try {
            ArenaStatus.find(status);

        } catch (NullPointerException e) {
            isnull = e;
            return false;
        }

        return true;
    }

}
