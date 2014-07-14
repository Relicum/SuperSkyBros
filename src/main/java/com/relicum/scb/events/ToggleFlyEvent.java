package com.relicum.scb.events;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerToggleFlightEvent;


/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class ToggleFlyEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public PlayerToggleFlightEvent event;


    public ToggleFlyEvent(PlayerToggleFlightEvent e) {
        this.event = e;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
