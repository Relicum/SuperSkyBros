package com.relicum.scb.events;

import com.relicum.scb.arena.Arena;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * SuperSkyBros
 * First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaDisableEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Arena arena;

    /**
     * The default constructor is defined for cleaner code. This constructor
     * assumes the event is synchronous.
     */
    public ArenaDisableEvent(Arena ar) {
        this.arena = ar;
    }

    public Arena getArena() {
        return this.arena;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
