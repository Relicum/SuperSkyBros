package com.relicum.scb.conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.Prompt;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class AbstractCustomFixedInput extends FixedSetPrompt {

    protected String formattedSet;


    public AbstractCustomFixedInput(String... fixedSet) {
        super(fixedSet);
        this.setDefaultFormattedSet();
    }


    @Override
    protected abstract Prompt acceptValidatedInput(ConversationContext context, String s);


    @Override
    public abstract String getPromptText(ConversationContext context);


    @Override
    public boolean isInputValid(ConversationContext context, String input) {

        for (String s : this.fixedSet) {
            if (s.equalsIgnoreCase(input)) return true;
        }
        return false;
    }


    /**
     * Sets formatFixedSet
     */
    public abstract void setFormattedText();


    /**
     * Returns the format for the fix set to be displayed
     *
     * @return the string
     */
    @Override
    public String formatFixedSet() {

        return this.formattedSet;
    }


    protected void setDefaultFormattedSet() {
        String f = ChatColor.GOLD + "[ ";
        for (String s : this.fixedSet) {
            f += ChatColor.RED + s + ChatColor.GOLD + " | ";
        }
        f += ChatColor.GOLD + "]";

        this.formattedSet = f;
    }
}
