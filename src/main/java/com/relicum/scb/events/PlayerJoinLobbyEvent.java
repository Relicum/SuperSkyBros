package com.relicum.scb.events;

import com.relicum.scb.SmashPl;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * SuperSkyBros First Created 03/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerJoinLobbyEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private SmashPl player;

    private String from;

    private boolean isdedicated;


    public PlayerJoinLobbyEvent(SmashPl p, String fr, boolean type) {

        this.player = p;
        this.from = fr;
        this.isdedicated = type;


    }


    public SmashPl getPlayer() {
        return this.player;
    }


    public String joinFrom() {
        return this.from;
    }


    public boolean isDedicated() {
        return isdedicated;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
