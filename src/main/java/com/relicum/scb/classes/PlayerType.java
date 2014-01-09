package com.relicum.scb.classes;

import org.bukkit.ChatColor;

/**
 * SuperSkyBros First Created 20/10/13 Enum of all player types
 *
 * @author Relicum
 * @version 0.1
 */
public enum PlayerType {

    PLAYER(ChatColor.GRAY + "<%player%>"),
    MOD(ChatColor.BLUE + "<MOD> %player%"),
    ADMIN(ChatColor.RED + "<ADMIN> %player%"),
    VIP(ChatColor.GREEN + "<VIP> %player%"),
    MVP(ChatColor.AQUA + "<MVP> %player%"),
    YOUTUBER(ChatColor.GOLD + "<TUBER> %player%"),
    OWNER(ChatColor.DARK_RED + "<" + ChatColor.RED + "OWNER" + ChatColor.DARK_RED + ">" + ChatColor.RED + "%player%");

    /**
     * The Name.
     */
    private final String name;

    /**
     * Instantiates a new Lobby status From Configuration
     *
     * @param node the node
     */
    private PlayerType(String node) {
        this.name = node;
    }

    /**
     * Gets node. For Lobby Status
     *
     * @return the node
     */
    public String getNode() {
        return "PlayerType" + name;
    }

}
