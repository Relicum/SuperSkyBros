package com.relicum.scb.events;


import com.relicum.scb.SCB;
import com.relicum.scb.utils.doublejump.setJumpStatus;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;


/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerStartsJumpEvent extends Event implements Listener {

    SCB p;

    private static final HandlerList handlers = new HandlerList();

    public setJumpStatus jumpStatus;


    public PlayerStartsJumpEvent() {

        p = SCB.getInstance();
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }


    public boolean getSmashPlayer() {
        return true;
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


}