package com.relicum.scb.conversations.autosetup;

import com.relicum.scb.conversations.AbstractCustomFixedInput;
import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.ChatColor;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

/**
 * SuperSkyBros
 * First Created 10/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class FinalConfirmation extends AbstractCustomFixedInput {
    public FinalConfirmation(String... fixedSet) {
        super(fixedSet);
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, String s) {
        if (s.equalsIgnoreCase("Yes")) {
            context.setSessionData("pre", 1);
            return new DoAutoSetUp();
        }
        if (s.equalsIgnoreCase("No")) {
            return Prompt.END_OF_CONVERSATION;
        }
        return Prompt.END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        Conversable c = context.getForWhom();
        c.sendRawMessage("");
        c.sendRawMessage(Col.Grey() + "---------------------------");
        return ChatColor.BLUE + "Final Confirmation would you like to proceed ? " + Col.Reset() + "\n" +
                formatFixedSet();
    }

    /**
     * Sets formatFixedSet
     */
    @Override
    public void setFormattedText() {

    }
}
