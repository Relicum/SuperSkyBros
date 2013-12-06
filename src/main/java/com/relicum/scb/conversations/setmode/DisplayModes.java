package com.relicum.scb.conversations.setmode;

import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class DisplayModes extends MessagePrompt {

    public DisplayModes() {
    }


    @Override
    protected Prompt getNextPrompt(ConversationContext context) {
        return new SetDisplayMode("Mixed", "Dedicated", "Quit");
    }


    @Override
    public String getPromptText(ConversationContext context) {
        Conversable c = context.getForWhom();
        String mess;
        c.sendRawMessage("");
        c.sendRawMessage(Col.Grey() + "---------------------------");
        c.sendRawMessage(Col.Gold() + "Dedicated Mode: " + Col.Reset());
        c.sendRawMessage(Col.Green() + "Set to dedicated if will only be used for SuperSkyBros");
        c.sendRawMessage(Col.Grey() + "---------------------------");
        c.sendRawMessage(Col.Gold() + "Mixed Mode: " + Col.Reset());
        c.sendRawMessage(Col.Green() + "Set to mixed mode if the server runs other plugins like");
        c.sendRawMessage(Col.Green() + "factions or has a survival world" + Col.Reset());
        mess = "\n" + Col.Blue() + "If unsure set to mixed mode you can always change it later" + Col.Reset();

        return mess;
    }
}
