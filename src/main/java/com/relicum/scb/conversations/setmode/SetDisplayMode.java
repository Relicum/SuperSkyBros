package com.relicum.scb.conversations.setmode;

import com.relicum.scb.conversations.AbstractCustomFixedInput;
import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SetDisplayMode extends AbstractCustomFixedInput {

    public SetDisplayMode(String... fixedSet) {
        super(fixedSet);
    }


    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, String s) {
        if (s.equalsIgnoreCase("Mixed")) {
            context.setSessionData("mode", s);
            return new SetModeResult();
        }
        if (s.equalsIgnoreCase("Dedicated")) {
            context.setSessionData("mode", s);
            return new SetModeResult();
        }
        return Prompt.END_OF_CONVERSATION;

    }


    @Override
    public String getPromptText(ConversationContext context) {

        context.getForWhom().sendRawMessage(Col.Grey() + "---------------------------");
        return Col.Green() + "Please select mode: " + Col.Reset() + formatFixedSet();


    }


    /**
     * Sets formatFixedSet
     */
    @Override
    public void setFormattedText() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
