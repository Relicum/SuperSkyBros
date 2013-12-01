package com.relicum.scb.arena;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public enum ArenaStatus {
    DISABLED,
    WAITING,
    STARTING,
    INGAME,
    RESTARTING,
    ERROR;

    public static ArenaStatus find(String abbr) {
        for (ArenaStatus v : values()) {
            if (v.equals(abbr)) {
                return v;
            }
        }
        return null;
    }


}
