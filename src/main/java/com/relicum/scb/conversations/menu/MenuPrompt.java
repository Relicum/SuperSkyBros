package com.relicum.scb.conversations.menu;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

/**
 * SuperSkyBros
 * First Created 04/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class MenuPrompt extends StringPrompt {

    @Override
    public final Prompt acceptInput(final ConversationContext context, String input) {
        if (input.startsWith("/")) {
            return this;
        } else if (input.equals("<")) {
            cleanup(context);


        }
        return null;
    }

    public abstract Prompt accept(ConversationContext context, String input);

    public abstract Prompt getPreviousPrompt(ConversationContext context);

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return null;
    }

    public String getHelp(ConversationContext context) {
        return null;
    }

    public void cleanup(ConversationContext context) {
    }
}
