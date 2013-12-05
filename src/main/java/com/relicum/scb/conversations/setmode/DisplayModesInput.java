package com.relicum.scb.conversations.setmode;

import com.relicum.scb.conversations.AbstractCustomFixedInput;
import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class DisplayModesInput extends AbstractCustomFixedInput {

    public DisplayModesInput(String... fixedSet) {
        super(fixedSet);
    }


    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, String s) {

        if (s.equalsIgnoreCase("Yes")) {
            context.setSessionData("pre", 2);
            return new DisplayModes();
        }
        if (s.equalsIgnoreCase("No")) {
            context.setSessionData("pre", 2);
            return new SetDisplayMode("Mixed", "Dedicated", "Quit");
        }
        return Prompt.END_OF_CONVERSATION;

    }


    @Override
    public String getPromptText(ConversationContext context) {

        context.getForWhom().sendRawMessage("");
        context.getForWhom().sendRawMessage(Col.Grey() + "---------------------------");
        return ChatColor.GREEN + "Would you like to see a description of each mode? " + Col.Reset() + "\n" +
                formatFixedSet();
    }


    /**
     * Sets formatFixedSet
     */
    @Override
    public void setFormattedText() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
