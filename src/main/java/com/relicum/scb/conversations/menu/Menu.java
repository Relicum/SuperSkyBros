package com.relicum.scb.conversations.menu;

import com.relicum.scb.SCB;
import org.bukkit.ChatColor;
import org.bukkit.conversations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Menue System to configure SSB.
 * It will be broken down into a series of sub menus
 * It will also be permission based.
 * First Created 04/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class Menu {

    //colors
    public static final ChatColor TEXT_COLOR = ChatColor.GOLD;
    public static final ChatColor HIGHLIGHT_COLOR = ChatColor.YELLOW;
    public static final ChatColor KEYWORD_COLOR = ChatColor.DARK_AQUA;
    public static final ChatColor KEYLETTER_COLOR = ChatColor.AQUA;
    public static final ChatColor ERROR_COLOR = ChatColor.RED;
    public static final ChatColor OUTER_PREFIX_COLOR = ChatColor.BLUE;
    public static final ChatColor INNER_PREFIX_COLOR = ChatColor.GREEN;

    //Prompts
    public static final Prompt MESSAGE = new MessagePrompt();
    public static final Prompt MAIN_MENU = new MainMenu();
    public static final ConversationPrefix MENU_PREFIX = new MenuPrefix();

    private static Map<Conversable, Conversation> conversations = new HashMap<Conversable, Conversation>();
    private static ConversationFactory menuFactory;

    private static Map<Object, Object> data = new HashMap<>();


    /**
     * Sets up a new conversation factory.
     *
     * @param plugin the plugin
     * @param modal  the modal True if the conversation is not5 to show any outside chat
     * @param data   the data used to add to session data
     */
    public static void setupFactory(SCB plugin, boolean modal, Map<Object, Object> data) {
        Menu.data = data;

        menuFactory = new ConversationFactory(plugin)
                .withFirstPrompt(Menu.MAIN_MENU)
                .withModality(modal)
                .withEscapeSequence("q")
                .withLocalEcho(false)
                .withTimeout(120)
                .thatExcludesNonPlayersWithMessage(Menu.ERROR_COLOR + "Only Players can use the Menu")
                .withPrefix(MENU_PREFIX)
                .withInitialSessionData(data)
                .addConversationAbandonedListener(new AbandonListener());

    }

    public static void openMenu(Conversable conversable) {
        sendLine(conversable);
        Conversation conversation = menuFactory.buildConversation(conversable);
        conversation.begin();
        conversations.put(conversable, conversation);
    }

    public static void closeAllMenus() {
        for (Conversation conversation : conversations.values()) {
            conversation.abandon();
        }
        conversations.clear();
    }


    public static void sendLine(Conversable c) {
        c.sendRawMessage(TEXT_COLOR + "---------------------------------------------------");
    }
}
