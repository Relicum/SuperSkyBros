package com.relicum.scb.conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationPrefix;

/**
 * SuperSkyBros First Created 18/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class CustomPrefix implements ConversationPrefix {

    protected String prefix;


    public CustomPrefix() {

    }


    @Override
    public String getPrefix(ConversationContext context) {

        if (context.getSessionData("pre") == null || context.getSessionData("pre") == 1) {
            this.prefix = ChatColor.translateAlternateColorCodes('&', "&5Prompt&3>");
        }
        if (context.getSessionData("pre") == 2) {
            this.prefix = "";
        }

        return this.prefix;
    }
}
