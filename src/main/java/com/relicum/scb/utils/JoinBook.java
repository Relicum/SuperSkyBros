package com.relicum.scb.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * SuperSkyBros First Created 05/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class JoinBook implements ConfigurationSerializable {

    private boolean enabled;

    private String num;

    private int slot;

    private String displayName;

    private Set<String> lore;

    private String mainTitle;

    private String author;

    private String perm;

    private Map<String, Object> pages = new HashMap<>();


    private JoinBook() {


    }


    private void setConfig() {

        this.enabled = true;
        this.slot = 27;
        this.perm = "ssba.admin.joinbook";
        this.displayName = "&aSuper Sky Bros Guide";
        this.lore.add(ChatColor.translateAlternateColorCodes('&', "&5Get Help on Playing"));
        this.lore.add(ChatColor.translateAlternateColorCodes('&', "&5Donor Ranks"));
        this.mainTitle = ChatColor.translateAlternateColorCodes('&', "&8Super Sky Bros");
        this.author = ChatColor.translateAlternateColorCodes('&', "&aRelicum and Co");


        Map<String, Object> page = new HashMap<>();
        String[] s = new String[4];
        s[0] = ChatColor.translateAlternateColorCodes('&', "&aWelcome to the SSB Guide");
        s[1] = ChatColor.translateAlternateColorCodes('&', "&5Here you will find a basic guide");
        s[2] = ChatColor.translateAlternateColorCodes('&', "&5to get you started.");
        page.put("1", s);
        String[] s2 = new String[4];
        s2[0] = ChatColor.translateAlternateColorCodes('&', "&5This is displaying on page 2");
        s2[1] = ChatColor.translateAlternateColorCodes('&', "&5The good can have 250 pages");
        this.pages.put("2", s2);


    }


    /**
     * Creates a Map representation of this class.
     * <p/>
     * This class must provide a method to restore this class, as defined in the {@link
     * org.bukkit.configuration.serialization.ConfigurationSerializable} interface javadocs.
     *
     * @return Map containing the current state of this class
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        this.enabled = true;
        this.slot = 27;
        this.perm = "ssba.admin.joinbook";
        this.displayName = "&aSuper Sky Bros Guide";
        this.lore.add(ChatColor.translateAlternateColorCodes('&', "&5Get Help on Playing"));
        this.lore.add(ChatColor.translateAlternateColorCodes('&', "&5Donor Ranks"));
        this.mainTitle = ChatColor.translateAlternateColorCodes('&', "&8Super Sky Bros");
        this.author = ChatColor.translateAlternateColorCodes('&', "&aRelicum and Co");

        Map<String, String[]> page = new HashMap<>();
        String[] s = new String[4];
        s[0] = ChatColor.translateAlternateColorCodes('&', "&aWelcome to the SSB Guide");
        s[1] = ChatColor.translateAlternateColorCodes('&', "&5Here you will find a basic guide");
        s[2] = ChatColor.translateAlternateColorCodes('&', "&5to get you started.");
        this.pages.put("1", s);
        String[] s2 = new String[4];
        s[0] = ChatColor.translateAlternateColorCodes('&', "&5This is displaying on page 2");
        s[1] = ChatColor.translateAlternateColorCodes('&', "&5The good can have 250 pages");
        this.pages.put("2", s2);

        return this.pages;
    }
}
