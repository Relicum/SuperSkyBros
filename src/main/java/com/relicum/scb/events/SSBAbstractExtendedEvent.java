package com.relicum.scb.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Abstract Class Used to Extend Built in Events
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class SSBAbstractExtendedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Event rootEvent;

    /**
     * Instantiates a new SSB abstract extended event.
     *
     * @param event the event
     */
    public SSBAbstractExtendedEvent(Event event) {
        this.rootEvent = event;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Returns the root event that was called
     *
     * @return the base Bukkit event connected to this event
     */
    public Event getRootEvent() {
        return rootEvent;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
