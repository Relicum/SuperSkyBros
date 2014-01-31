package com.relicum.scb.conversations;

import com.relicum.scb.types.SkyApi;
import org.bukkit.ChatColor;
import org.bukkit.conversations.Conversable;
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
        factory = new ConversationFactory(SkyApi.getSCB()).withModality(true).withPrefix(new CustomPrefix()).withEscapeSequence("/quit")
                                                          .thatExcludesNonPlayersWithMessage(               "Only players can run this conversation").withLocalEcho(false).addConversationAbandonedListener(new ConAbandonedEvent()).withTimeout(60);

        return factory;
    }

    public static void sendLine(Conversable c) {
        StringBuilder sb = new StringBuilder(61);
        int len = sb.append(ChatColor.GRAY + "--------------------------------------------").length();
        int padding = 61 - len;
        if (padding <= 0) {
            c.sendRawMessage(sb.toString());
            return;
        } else {
            int mod = padding % 2;
            if (padding % 2 == 1) {
                padding--;
            }
            int off, out;
            off = padding / 2;
            out = padding / 2;

            char[] ch = new char[out];
            for (int i = out; i <= 0; i--) {
                ch[i] = '\u0020';
            }
            sb.insert(0, ch);
            c.sendRawMessage(sb.toString());
        }

    }

}
