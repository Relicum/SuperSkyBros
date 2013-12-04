package com.relicum.scb.conversations.menu;

import org.bukkit.conversations.*;

/**
 * Conversation Abandon Event Listener
 * Deals with the end of conversations cause my various conditions
 *
 * @author Relicum
 * @version 0.1
 */
public class AbandonListener implements ConversationAbandonedListener {
    @Override
    public void conversationAbandoned(ConversationAbandonedEvent event) {
        Conversable c = event.getContext().getForWhom();

        if (event.gracefulExit()) {
            c.sendRawMessage(Menu.TEXT_COLOR + "Exiting SSB Menu");
            Menu.sendLine(c);
            return;
        }

        if (event.getCanceller() instanceof InactivityConversationCanceller) {
            c.sendRawMessage(Menu.ERROR_COLOR + "Conversation Timed Out");
            Menu.sendLine(c);
            return;
        }
        if (event.getCanceller() instanceof ExactMatchConversationCanceller) {
            c.sendRawMessage(Menu.HIGHLIGHT_COLOR + "You have ended the conversation");
            Menu.sendLine(c);
            return;
        }

        c.sendRawMessage(Menu.ERROR_COLOR + event.getCanceller().toString());
        Menu.sendLine(c);

    }
}
