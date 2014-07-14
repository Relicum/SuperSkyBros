package com.relicum.scb.utils;

import net.minecraft.util.org.apache.commons.lang3.text.StrBuilder;
import org.bukkit.ChatColor;

/**
 * Collection of static methods to create fancy looking message header and titles and footers.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class StrStyles {

    public static String multiColoredHeader(final ChatColor color, final ChatColor color2, final ChatColor style, char character) {

        StrBuilder sb = new StrBuilder();
        boolean t = true;
        for (int i = 0; i < 53; i++) {
            if (t) {
                sb.append(style).append(color).append(character);
                t = false;
            } else {
                sb.append(style).append(color2).append(character);
                t = true;
            }
        }

        return sb.toString();
    }

    public static String centeredHeading(ChatColor color, ChatColor style, String heading) {

        StrBuilder sb = new StrBuilder(58);
        sb.append(" ").appendPadding(20, ' ').append(style).append("").append(color).append(heading).appendPadding(8, ' ');
        return sb.toString();
    }
}
