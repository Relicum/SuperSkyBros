package com.relicum.scb.conversations.setmode;

import com.relicum.scb.objects.signs.utils.Col;
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

        String mess;
        mess = "\n";
        mess += Col.Gold() + "Dedicated Mode: " + Col.Reset() + "\n";
        mess += Col.Green() + "Set to dedicated mode if the server will only be used for running SuperSkyBros" + Col.Reset() + "\n";
        mess += "\n";
        mess += Col.Gold() + "Mixed Mode: " + Col.Reset() + "\n";
        mess += Col.Green() + "Set to mixed mode if the server runs other plugins like factions or has a survival " +
                "world" + Col.Reset() + "\n";
        mess += "\n";
        mess += Col.Green() + "If unsure set to mixed mode you can always change it later" + Col.Reset() + "\n";

        return mess;
    }
}
