package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.conversations.DefaultConversationFactory;
import com.relicum.scb.conversations.setmode.SetModeStart;
import com.relicum.scb.objects.signs.utils.Col;
import com.relicum.scb.types.SkyApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * SuperSkyBros First Created 28/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class CommandManagerFirstJoin implements CommandExecutor {

    public static ConversationFactory factory;
    private final String cHeader = "    \u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC"
            + "[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" + "*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<\n";
    private SCB plugin;
    private boolean saveCommands;

    public CommandManagerFirstJoin(SCB pl) {

        this.plugin = pl;
        String message;
        saveCommands = plugin.getConfig().getBoolean("storeCmds");
        message = "&b This command will help you setup the server mode for SuperSkyBros";
        String des = ChatColor.translateAlternateColorCodes('&', message);
        registerCommand("setmode", des, "/ssba setmode", "ssba setmode", "ssba.admin.setmode");

        factory = DefaultConversationFactory.getDefaultConversation();
        SkyApi.getCMsg().INFO("The " + this.getClass().getSimpleName() + " loader has run");
    }

    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
        if (cs instanceof Player) {
            if (!cs.hasPermission("ssba.admin.setmode")) {
                cs.sendMessage(Col.Dark_Red() + "You do not have permission to run setmode");
                return true;
            }
        }
        if (cs instanceof Conversable) {
            String[] strings1 = new String[19];
            for (int i = 0; i < 19; i++) {
                strings1[i] = "";
            }
            cs.sendMessage(strings1);
            Map<Object, Object> data = new HashMap<>();
            data.put("pre", 2);
            factory.withInitialSessionData(data);
            factory.withFirstPrompt(new SetModeStart());
            factory.buildConversation((Conversable) cs).begin();

            return true;
        } else
            return false;

    }

    private boolean registerCommand(String name, String description, String usage, String label, String perm) {

        String[] ps = perm.split("\\.");
        String ubPerm = ps[0] + "." + ps[1];

        String des = description;

        Permission per = new Permission(ubPerm + "." + name);

        per.setDefault(PermissionDefault.OP);
        per.addParent(ubPerm, true);
        per.setDescription(des);
        plugin.getServer().getPluginManager().addPermission(per);

        CommandMap cmp = getCommandMap();
        PluginCommand cd = getCommand(name.toLowerCase());

        cd.setDescription(des);
        cd.setUsage(usage);
        cd.setExecutor(this);
        cd.setPermission(perm);

        if (cmp.register(label, "mc", (Command) cd)) {
            plugin.getLogger().info("Command: /" + label + " has successfully been registered");
            if (saveCommands) {
                plugin.getSaver().addToStore(cd, per);
            }
            return true;
        }

        plugin.getLogger().severe("Unknown error command: /" + label + " did not register at all. Investigation needed");

        return false;

    }

    /**
     * Returns an instance of Command object setup For the command name you give
     * it.
     *
     * @param name String
     * @return PluginCommand
     */
    public PluginCommand getCommand(String name) {

        PluginCommand command = null;
        try {
            Constructor c = PluginCommand.class.getDeclaredConstructor(new Class[]{String.class, Plugin.class});
            c.setAccessible(true);

            command = (PluginCommand) c.newInstance(name, plugin);
            //command = (PluginCommand) c.newInstance(new Object[]{name , plugin});
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return command;
    }

    /**
     * Returns an instance of CommandMap which Can then be used to correctly
     * register the command and details with Bukkit
     *
     * @return CommandMap
     */
    public CommandMap getCommandMap() {

        CommandMap commandMap = null;

        try {

            Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);

            commandMap = (CommandMap) f.get(Bukkit.getServer());

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return commandMap;
    }
}
