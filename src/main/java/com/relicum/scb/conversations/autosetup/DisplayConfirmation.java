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
public class DisplayConfirmation extends AbstractCustomFixedInput {
    public DisplayConfirmation(String... fixedSet) {
        super(fixedSet);
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, String s) {
        if (s.equalsIgnoreCase("Yes")) {
            context.setSessionData("pre", 1);
            return new FinalConfirmation("Yes", "No");
        }
        if (s.equalsIgnoreCase("No")) {
            context.setSessionData("pre", 1);
            return Prompt.END_OF_CONVERSATION;
        }
        return Prompt.END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        Conversable c = context.getForWhom();
        c.sendRawMessage(ChatColor.GRAY + "---------------------------");
        c.sendRawMessage("");
        c.sendRawMessage(ChatColor.GREEN + "If you proceed the server will shut down");
        c.sendRawMessage(ChatColor.DARK_GREEN + "You will then need to restart the server 3 times");
        c.sendRawMessage(ChatColor.GREEN + "The console will tell you when to login");
        c.sendRawMessage(ChatColor.DARK_GREEN + "Simple as that, I advise you to proceed");
        c.sendRawMessage(ChatColor.GRAY + "---------------------------");
        return ChatColor.BLUE + "Would you like to proceed ? " + Col.Reset() + "\n" +
                formatFixedSet();

    }

    /**
     * Sets formatFixedSet
     */
    @Override
    public void setFormattedText() {

    }
}
