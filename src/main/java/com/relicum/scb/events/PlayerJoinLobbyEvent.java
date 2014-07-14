package com.relicum.scb.events;

import org.bukkit.entity.Player;
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

    private Player player;

    private String from;

    private boolean isdedicated;

    public PlayerJoinLobbyEvent(Player p, String fr, boolean type) {

        this.player = p;
        this.from = fr;
        this.isdedicated = type;

    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String joinFrom() {
        return this.from;
    }

    public boolean isDedicated() {
        return isdedicated;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
