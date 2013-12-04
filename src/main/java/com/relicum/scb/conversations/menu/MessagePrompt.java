package com.relicum.scb.conversations.menu;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

/**
 * SuperSkyBros
 * First Created 04/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class MessagePrompt extends MenuPrompt {
    @Override
    public Prompt accept(ConversationContext context, String input) {
        return null;
    }

    @Override
    public Prompt getPreviousPrompt(ConversationContext context) {
        return null;
    }
}
