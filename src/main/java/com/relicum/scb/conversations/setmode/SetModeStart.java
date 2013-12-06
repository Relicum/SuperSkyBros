package com.relicum.scb.conversations.setmode;

import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.ChatColor;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

/**
 * SuperSkyBros
 * First Created 04/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SetModeStart extends MessagePrompt {

    private final String cHeader = "    \u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
            "[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
            "*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<\n";

    @Override
    protected Prompt getNextPrompt(ConversationContext context) {
        context.setSessionData("pre", 1);
        return new DisplayModesInput("Yes", "No");
    }

    @Override
    public String getPromptText(ConversationContext context) {
        Conversable c = context.getForWhom();
        c.sendRawMessage(cHeader);
        c.sendRawMessage(ChatColor.GREEN + "To begin you need to decide the server mode");
        c.sendRawMessage(ChatColor.GREEN + "There are 2 modes that you can choose");
        c.sendRawMessage("");
        c.sendRawMessage(ChatColor.GOLD + "MIXED MODE" + ChatColor.GREEN + " or " + ChatColor.GOLD + "DEDICATED MODE");
        context.getForWhom().sendRawMessage(Col.Grey() + "---------------------------");
        return ChatColor.BLUE + "Type /quit at anytime to exit";
    }
}
