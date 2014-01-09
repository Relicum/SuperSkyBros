package com.relicum.scb.objects.location;

/**
 * The enum Lobby status.
 */
public enum LobbyStatus {

    /**
     * The DISABLED.
     */
    DISABLED("Disabled"),

    /**
     * The LOADING.
     */
    LOADING("Loading"),

    /**
     * The ONLINE.
     */
    ONLINE("Online"),

    /**
     * The WAITING.
     */
    WAITING("Waiting"),

    /**
     * The START_CHECKS.
     */
    START_CHECKS("Start-Checks"),

    /**
     * The INGAME.
     */
    INGAME("InGame"),

    /**
     * The END_CHECKS.
     */
    END_CHECKS("End-Checks"),

    /**
     * The RESTARTING.
     */
    RESTARTING("Restarting"),

    /**
     * The OFFLINE.
     */
    OFFLINE("Offline");

    /**
     * The Name.
     */
    private final String name;

    /**
     * Instantiates a new Lobby status From Configuration
     *
     * @param node the node
     */
    private LobbyStatus(String node) {
        this.name = node;
    }

    /**
     * Gets node. For Lobby Status
     *
     * @return the node
     */
    public String getNode() {
        return "LobbyStatus." + name;
    }
}
