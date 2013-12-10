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
public class SetAutoStart extends StringPrompt {

    private final String cHeader = "    \u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
            "[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
            "*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<\n";


    @Override
    public String getPromptText(ConversationContext context) {
        Conversable c = context.getForWhom();
        c.sendRawMessage(cHeader);
        c.sendRawMessage(ChatColor.GREEN + "Autosetup is a very powerful feature.");
        c.sendRawMessage(ChatColor.GREEN + "It is to be used for DEDICATED server only");
        c.sendRawMessage("");
        c.sendRawMessage(ChatColor.BLUE + "Type /quit at anytime to exit");
        context.getForWhom().sendRawMessage(Col.Grey() + "---------------------------");
        return ChatColor.GREEN + "Enter any key to continue";

    }

    @Override
    public Prompt acceptInput(ConversationContext context, String s) {
        if (!s.isEmpty()) {
            context.setSessionData("pre", 2);
            return new ActionList();
        }
        return this;
    }
}
