package com.relicum.scb.conversations.autosetup;

import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.ChatColor;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

/**
 * SuperSkyBros
 * First Created 10/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ActionList extends StringPrompt {

    @Override
    public String getPromptText(ConversationContext context) {
        Conversable c = context.getForWhom();
        c.sendRawMessage(Col.Grey() + "---------------------------");
        c.sendRawMessage(ChatColor.BLUE + "AutoSetup does the following:");
        c.sendRawMessage(ChatColor.GOLD + "1) " + ChatColor.GREEN + "The main world will be deleted");
        c.sendRawMessage(ChatColor.GOLD + "2) " + ChatColor.GREEN + "The 'end' and 'nether' will be deleted");
        c.sendRawMessage(ChatColor.GOLD + "3) " + ChatColor.GREEN + "Server settings will be auto applied");
        c.sendRawMessage(ChatColor.GOLD + "4) " + ChatColor.GREEN + "A new empty void world is created");
        c.sendRawMessage(ChatColor.GOLD + "5) " + ChatColor.GREEN + "This world is set as the main world");
        c.sendRawMessage(Col.Grey() + "---------------------------");
        return ChatColor.BLUE + "Enter any key to continue";

    }

    @Override
    public Prompt acceptInput(ConversationContext context, String s) {
        if (!s.isEmpty()) {
            context.setSessionData("pre", 1);
            return new DisplayConfirmation("Yes", "No");
        }
        return this;

    }
}
