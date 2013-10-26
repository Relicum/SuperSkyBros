package com.relicum.scb.mini;

/**
 * SuperSkyBros First Created 25/10/13 Deals with everything pre and post game. Including background tasks
 *
 * @author Relicum
 * @version 0.1
 */


import com.relicum.scb.listeners.LobbyBlockPlace;
import lombok.Data;


/**
 * The type LobbyManager.
 */
public
@Data
class LobbyManager {

    /**
     * The Name.
     */
    private String name;

    /**
     * The Age.
     */
    private Integer age;

    /**
     * The Plugin.
     */
    private LobbyBlockPlace plugin;


    /**
     * Instantiates a new Lobby manager.
     *
     * @param n the n
     */
    public LobbyManager(String n) {

        this.name = n;

    }

}
