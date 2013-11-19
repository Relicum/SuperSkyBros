package com.relicum.scb;

import com.relicum.scb.conversations.DefaultConversationFactory;
import com.relicum.scb.conversations.setmode.DisplayModesInput;
import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;


/**
 * Manages setting of the mode that SSB will run as. This will be the only command that will load until the mode is set
 * This command uses the conversation API to guide the user through the process
 *
 * @author Relicum
 * @version 1.0
 */
public class FirstTimeLoader implements CommandExecutor {

    public SCB plugin;

    private final String cHeader = "    \u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
                                           "[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
                                           "*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<\n";

    // public ConversationFactory factory;
    public static ConversationFactory factory;


    public FirstTimeLoader(JavaPlugin javaPlugin) {
        this.plugin = (SCB) javaPlugin;
        plugin.getCommand("relicum").setExecutor(this);
        factory = DefaultConversationFactory.getDefaultConversation();
        SCB.log.info("The " + this.getClass().getSimpleName() + " loader has run");

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Conversable) {
            String[] strings1 = new String[19];
            for ( int i = 0; i < 19; i++ ) {
                strings1[i] = "";
            }
            sender.sendMessage(strings1);
            sender.sendMessage(this.cHeader);
            sender.sendMessage(Col.Green() + "To begin setup you need to decided the server mode");
            sender.sendMessage(Col.Green() + "There are 2 modes " + Col.Gold() + "MIXED " + Col.Green() + "or " + Col
                                                                                                                          .Gold() + "DEDICATED");
            sender.sendMessage("");
            Map<Object, Object> data = new HashMap<>();
            data.put("pre", 1);
            factory.withInitialSessionData(data);
            factory.withFirstPrompt(new DisplayModesInput("Yes", "No"));
            factory.buildConversation((Conversable) sender).begin();

            return true;
        } else
            return false;

    }
}
