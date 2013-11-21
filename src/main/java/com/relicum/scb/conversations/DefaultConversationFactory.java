package com.relicum.scb.conversations;

import com.relicum.scb.SCB;
import org.bukkit.conversations.ConversationFactory;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class DefaultConversationFactory {

    //public static ConversationFactory factory;


    /**
     * Get default conversation. Your need to set the FirstPrompt before calling build
     *
     * @return the conversation factory
     */
    public static ConversationFactory getDefaultConversation() {

        ConversationFactory factory;
        factory = new ConversationFactory(SCB.getInstance()).withModality(true).withPrefix(new CustomPrefix()).withEscapeSequence("/quit").thatExcludesNonPlayersWithMessage(
                "Only players can run this conversation").withLocalEcho(false).addConversationAbandonedListener(new ConAbandonedEvent()).withTimeout(60);

        return factory;
    }

}
