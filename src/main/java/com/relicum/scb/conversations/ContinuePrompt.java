package com.relicum.scb.conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.Prompt;

import java.util.List;

/**
 * SuperSkyBros First Created 18/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ContinuePrompt extends FixedSetPrompt {

    private Prompt promptReturn;


    public ContinuePrompt(Prompt returnPrompt) {
        super("YES", "NO");
        this.promptReturn = returnPrompt;
    }


    @Override
    public boolean isInputValid(ConversationContext context,
                                String input) {
        if (input.equalsIgnoreCase("NO") || input.equalsIgnoreCase("YES"))
            return true;
        else
            return false;

    }


    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, String s) {

        if (s.equalsIgnoreCase("NO")) {
            return Prompt.END_OF_CONVERSATION;
        }
        context.setSessionData("continue", s.toLowerCase());

        return this.promptReturn;
    }


    @Override
    public String getPromptText(ConversationContext context) {
        return ChatColor.AQUA + "Would you like to continue? " + formatFixedSet();
    }


    @Override
    public String formatFixedSet() {
        List<String> theset = this.fixedSet;
        String f = ChatColor.GOLD + "[ | ";
        for ( String s : theset ) {
            f += ChatColor.RED + s + ChatColor.GOLD + " | ";
        }
        f += ChatColor.GOLD + "]";
        return f;
    }
}
