package com.relicum.scb.objects;

/**
 * The enum Player location.
 */
public enum PlayerLocation {

    /**
     * The LOBBY.
     */LOBBY("Lobby"),
    /**
     * The INGAME.
     */INGAME("Game"),
    /**
     * The ARENALOBBY.
     */ARENALOBBY("GameLobby"),
    /**
     * The UNKNOWN.
     */UNKNOWN("Unknown"),
    /**
     * The LOGOUT.
     */LOGOUT("LogOut");
    /**
     * The Name.
     */
    private final String name;


    /**
     * Instantiates a new Player location.
     *
     * @param node the node
     */
    private PlayerLocation(String node) {
        this.name = node;
    }


    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return name;
    }
}
