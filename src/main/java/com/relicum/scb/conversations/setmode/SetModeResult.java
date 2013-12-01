package com.relicum.scb.conversations.setmode;

import com.relicum.scb.SCB;
import com.relicum.scb.objects.signs.utils.Col;
import com.relicum.scb.utils.DelayedShutDown;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SetModeResult extends MessagePrompt {


    public SetModeResult() {

    }


    @Override
    protected Prompt getNextPrompt(ConversationContext context) {
        return Prompt.END_OF_CONVERSATION;
    }


    @Override
    public String getPromptText(ConversationContext context) {
        if (context.getSessionData("mode").toString().equalsIgnoreCase("mixed")) {
            if (!setupMode(false)) {
                return Col.Dark_Red() + "Error occurred while setting mode to mixed please check logs";
            }
            DelayedShutDown.shutDown();
            return Col.Gold() + "Mode successfully set to MIXED, the server requires a reboot. The server will shutdown now";
        }
        if (context.getSessionData("mode").toString().equalsIgnoreCase("dedicated")) {
            if (!setupMode(true)) {
                return Col.Dark_Red() + "Error occurred while setting mode to DEDICATED please check logs";
            }

            DelayedShutDown.shutDown();
            return Col.Gold() + "Mode successfully set to DEDICATED,  the server requires a reboot. The server will shutdown now";
        }


        return Col.Green() + "You quit the setup";
    }


    private boolean setupMode(boolean isDedicated) {

        SCB plugin = SCB.getInstance();

        try {
            plugin.getConfig().set(SCB.DEDICATED_SSB, isDedicated);
            plugin.getConfig().set("modeSet", true);
            plugin.saveOnDisable = true;
            plugin.saveConfig();
            plugin.reloadConfig();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;


    }
}
