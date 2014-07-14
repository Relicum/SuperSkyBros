package com.relicum.scb.events;

import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaStatus;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Manages changes to an Arenas Status
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaStatusChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final ArenaStatus arenaStatus;
    private final ArenaStatus previousStatus;
    private Arena arena;


    /**
     * Instantiates a new Arena status change event. This occurs when
     * the arena changes from one status to another
     *
     * @param newStatus the new status the arena has changed to
     * @param oldStatus the previous status the arena was in
     * @param Arena     the arena that status has changed
     */
    public ArenaStatusChangeEvent(ArenaStatus newStatus, ArenaStatus oldStatus, Arena a) {
        this.arenaStatus = newStatus;
        this.previousStatus = oldStatus;
        this.arena = a;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Gets the new Status the Arena has changed to
     *
     * @return the arena status as a String
     */
    public String getArenaStatus() {
        return arenaStatus.toString();
    }

    /**
     * Gets previous status the Arena was in.
     *
     * @return the previous status as a String
     */
    public String getPreviousStatus() {
        return previousStatus.toString();
    }

    /**
     * Gets the arena that status has changed in
     *
     * @return the arena as an Arena Object
     */
    public Arena getArena() {
        return arena;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
