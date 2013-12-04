package com.relicum.scb.conversations.menu;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationPrefix;

/**
 * SuperSkyBros
 * First Created 04/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class MenuPrefix implements ConversationPrefix {
    public String prefix;


    public String getPrefix(ConversationContext context) {
        if (context.getSessionData("pre") == null || context.getSessionData("pre") == 1) {
            prefix = Menu.OUTER_PREFIX_COLOR + "[" + Menu.INNER_PREFIX_COLOR + "SSBMenu" + Menu.OUTER_PREFIX_COLOR + "] ";
        }
        if (context.getSessionData("pre") == 2) {
            prefix = "";
        }

        return prefix;
    }
}
