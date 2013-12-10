package com.relicum.scb.conversations.autosetup;

import com.relicum.scb.objects.signs.utils.Col;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.DelayedShutDown;
import org.bukkit.ChatColor;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

/**
 * SuperSkyBros
 * First Created 10/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class DoAutoSetUp extends MessagePrompt {
    @Override
    protected Prompt getNextPrompt(ConversationContext context) {
        return Prompt.END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        Conversable c = context.getForWhom();
        SkyApi.getSm().getPluginConfig().set("generateDefaultWorld", true);
        SkyApi.getSm().getPluginConfig().set("worldGenerateStage", 1);
        SkyApi.getSCB().saveConfig();
        c.sendRawMessage("");
        c.sendRawMessage(Col.Grey() + "---------------------------");
        c.sendRawMessage(ChatColor.RED + "AutoSetup will now shutdown the server");
        DelayedShutDown.shutDown();
        return ChatColor.RED + "Please restart the server 3 times";
    }
}
