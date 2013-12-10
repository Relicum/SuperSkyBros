package com.relicum.scb.events;

import com.relicum.scb.Game;
import com.relicum.scb.arena.Arena;
import com.relicum.scb.utils.PlayerStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * SuperSkyBros First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaStatusChangeEvents extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Arena arena;

    private PlayerStatus pre;

    private PlayerStatus status;

    private Player player;

    private boolean withGame = true;

    private Game game;


    /**
     * The default constructor is defined for cleaner code. This constructor assumes the event is synchronous.
     */
    ArenaStatusChangeEvents(Player player, PlayerStatus previous, Arena ar, PlayerStatus st) {
        this.player = player;
        this.arena = ar;
        this.status = st;
        this.pre = previous;
        this.withGame = false;

    }


    /**
     * Alternative Constructor which also passes the Game Instance to
     */
    public ArenaStatusChangeEvents(Player player, PlayerStatus previous, Game game, PlayerStatus st) {

        this.player = player;
        this.game = game;
        this.status = st;
        this.pre = previous;
    }


    /**
     * Alternative Constructor which also passes the Game Instance to
     */
    public ArenaStatusChangeEvents(Player player, PlayerStatus previous, PlayerStatus st) {
        this.player = player;
        this.pre = previous;
        this.status = st;
    }


    /**
     * Get the previous ArenaStatus before the event
     *
     * @return ArenaStatus the Arena Status
     */
    public PlayerStatus getPrevious() {
        return this.pre;
    }


    /**
     * Get the current ArenaStatus that it has changed to.
     *
     * @return ArenaStatus the current Status
     */
    public PlayerStatus getStatus() {
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


    public Player getPlayer() {
        return player;
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
