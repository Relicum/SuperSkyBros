package com.relicum.scb.events;

import com.relicum.scb.Game;
import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaStatus;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * SuperSkyBros First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaStatusChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Arena arena;

    private ArenaStatus pre;

    private ArenaStatus status;

    private boolean withGame = true;

    private Game game;


    /**
     * The default constructor is defined for cleaner code. This constructor assumes the event is synchronous.
     */
    public ArenaStatusChangeEvent(ArenaStatus previous, Arena ar) {
        this.arena = ar;
        this.status = ar.getArenaStatus();
        this.pre = previous;
        this.withGame = false;
    }


    /**
     * Alternative Constructor which also passes the Game Instance to
     */
    public ArenaStatusChangeEvent(ArenaStatus previous, Game game) {
        this.game = game;
        this.status = game.getArena().getArenaStatus();
        this.pre = previous;
    }


    /**
     * Get the previous ArenaStatus before the event
     *
     * @return ArenaStatus the Arena Status
     */
    public ArenaStatus getPrevious() {
        return this.pre;
    }


    /**
     * Get the current ArenaStatus that it has changed to.
     *
     * @return ArenaStatus the current Status
     */
    public ArenaStatus getStatus() {
        return this.status;
    }


    /**
     * Get the Arena the Change Happened in.
     *
     * @return Arena the arena
     */
    public Arena getArena() {

        if (withGame) return this.game.getArena();

        return this.arena;
    }


    /**
     * Does the event come from a game
     *
     * @return boolean
     */
    public boolean isWithGame() {
        return this.withGame;
    }


    /**
     * Get the instance of the Game the Event Happened in
     *
     * @return
     */
    public Game getGame() {

        return game;


    }


    public String GetName() {
        return "";
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
