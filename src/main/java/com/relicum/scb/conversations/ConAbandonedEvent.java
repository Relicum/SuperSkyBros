package com.relicum.scb.conversations;

import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationAbandonedListener;
import org.bukkit.conversations.ExactMatchConversationCanceller;
import org.bukkit.conversations.InactivityConversationCanceller;

/**
 * SuperSkyBros First Created 19/11/13 Event Listener for Conversations when they are abandoned
 *
 * @author Relicum
 * @version 0.1
 */
public class ConAbandonedEvent implements ConversationAbandonedListener {

    @Override
    public void conversationAbandoned(ConversationAbandonedEvent event) {

        if (event.gracefulExit()) {
            event.getContext().getForWhom().sendRawMessage(Col.Grey() + "---------------------------");
            event.getContext().getForWhom().sendRawMessage(Col.Green() + "Setup Closed" + Col.Reset());
            event.getContext().getForWhom().sendRawMessage(Col.Grey() + "---------------------------");


        } else {
            if (event.getCanceller() instanceof InactivityConversationCanceller) {
                event.getContext().getForWhom().sendRawMessage(ChatColor.RED + "Conversation Timed Out");
                return;
            }
            if (event.getCanceller() instanceof ExactMatchConversationCanceller) {
                event.getContext().getForWhom().sendRawMessage(ChatColor.BLUE + "You have ended the conversation");
                return;
            }
            event.getContext().getForWhom().sendRawMessage(ChatColor.RED + event.getCanceller().toString());
        }
    }
}
