package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class FirstStart implements CommandExecutor {

    private SCB plugin;

    private final String cHeader = "    \u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
                                           "[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
                                           "*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<\n";

    public ConversationFactory factory;


    public FirstStart(SCB plugin, ConversationFactory factory) {
        this.plugin = plugin;
        this.factory = factory;
        String message;
        message = "&b This command will help you setup the server mode for SuperSkyBros";
        String des = ChatColor.translateAlternateColorCodes('&', message);
        registerCommand("setmodes", des, "/ssbs setmodes", "ssbs setmodes");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender.hasPermission("ssba.admin.setmodes")) {
            if (sender instanceof Conversable) {
                String[] strings1 = new String[19];
                for ( int i = 0; i < 19; i++ ) {
                    strings1[i] = "";
                }
                sender.sendMessage(strings1);
                sender.sendMessage(this.cHeader);
                factory.buildConversation((Conversable) sender).begin();
                return true;
            } else
                return false;
        }
        return false;
    }


    private boolean registerCommand(String name, String description, String usage, String label) {

        CommandMap cmp = getCommandMap();
        PluginCommand cd = getCommand(name.toLowerCase());

        cd.setDescription(description);
        cd.setUsage(usage);
        cd.setExecutor(this);
        cd.setPermission("ssba.admin.setmodes");
        cd.setPermissionMessage(ChatColor.DARK_RED + "You do not have permission to run this command OP only");

        if (cmp.register(label, "mc", (Command) cd)) {
            plugin.getLogger().info("Command: /" + label + " has successfully been registered");
            return true;
        }

        plugin.getLogger().severe("Unknown error command: /" + label + " did not register at all. Investigation " +
                                          "needed");

        return true;
    }


    /**
     * Returns an instance of Command object setup For the command name you give it.
     *
     * @param name String
     * @return PluginCommand
     */
    public PluginCommand getCommand(String name) {


        PluginCommand command = null;
        try {
            Constructor c = PluginCommand.class.getDeclaredConstructor(new Class[]{String.class, Plugin.class});
            c.setAccessible(true);

            command = (PluginCommand) c.newInstance(new Object[]{name, plugin});
        }
        catch ( SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException |
                        InvocationTargetException | NoSuchMethodException e ) {
            e.printStackTrace();
        }

        return command;
    }


    /**
     * Returns an instance of CommandMap which Can then be used to correctly register the command and details with
     * Bukkit
     *
     * @return CommandMap
     */
    public CommandMap getCommandMap() {
        CommandMap commandMap = null;
        PluginManager pm = Bukkit.getServer().getPluginManager();
        try {
            if (pm instanceof PluginManager) {
                Field f = pm.getClass().getDeclaredField("commandMap");
                f.setAccessible(true);

                commandMap = (CommandMap) f.get(pm);
            }
        }
        catch ( NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e ) {
            e.printStackTrace();
        }

        return commandMap;
    }
}
