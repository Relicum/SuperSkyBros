package com.relicum.scb.objects.signs.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;

/**
 * SuperSkyBros First Created 23/09/13 Collection of abstract methods to manage text and formatting of a signs line
 *
 * @author Relicum
 * @version 0.1
 */
public class linesUtil {

    /**
     * Strip Color Characters from a single line
     *
     * @param String line of text to have color stripped
     * @return String the line
     */
    public static String stripLineColor(String line) {

        return ChatColor.stripColor(line);

    }


    /**
     * Strip Color Characters from all lines of a sign
     *
     * @param ArrayList<String> lines of text to have color stripped
     * @return ArrayList<String> the stripped lines
     */
    public static ArrayList<String> stripAllColor(ArrayList<String> lines) {

        ArrayList<String> stripped = new ArrayList<>(4);

        for (String line : lines) {
            stripped.add(ChatColor.stripColor(line));
        }

        return stripped;
    }
}
