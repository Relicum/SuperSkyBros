package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * SuperSkyBros First Created 28/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class CommandManagerFirstJoin implements CommandExecutor {

    private SCB plugin;


    public CommandManagerFirstJoin(SCB pl) {

        this.plugin = pl;
        String message;
        message = "&bYou need to run this command the to set the SSB server mode you wish to run. If server will only run SSB run &6/ssba setmode dedicated&r";
        message = message + " Or if the server will have other game types like faction etc running as well as SSB the run &6/ssba setmode mixed&r";
        message = message + "&4 Until this has been set you will not be able to use SuperSkyBros";
        String des = ChatColor.translateAlternateColorCodes('&', message);
        registerCommand("setmode", des, "/ssba setmode", "ssba setmode");
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


        if (!(cs instanceof Player)) {
            plugin.getLogger().severe("Error all SSB commands must be run in game not from console");
            return false;
        }


        if (args == null || args.length < 2L) {
            return false;
        }
        Player player = (Player) cs;

        if (!SCB.perms.has(player, "ssba.admins.setmode")) {
            System.out.println("Failed perms check");
            return true;
        }

        setmode(player, args[1]);

        return true;
    }


    private boolean setmode(Player player, String mode) {


        if (!mode.equalsIgnoreCase("mixed") && !mode.equalsIgnoreCase("dedicated")) {
            player.sendMessage(ChatColor.RED + "Error: the choice is " + ChatColor.GOLD + "mixed " + ChatColor.RED + "OR " + ChatColor.GOLD + "dedicated");
            player.sendMessage(ChatColor.RED + "Please run again with a valid option");
            return true;
        }

        boolean themode = false;
        if (mode.equalsIgnoreCase("dedicated"))
            themode = true;
        plugin.getConfig().set("dedicatedSSB", themode);
        // plugin.getConfig().set("firstRun", false);
        // plugin.getConfig().set("firstRunDone",true);
        plugin.saveConfig();
        plugin.reloadConfig();

        player.sendMessage(ChatColor.GREEN + "You have successfully set the server mode to " + mode);
        player.sendMessage(ChatColor.AQUA + "Now stop the server and restart it to continue");

        plugin.saveOnDisable = true;
        return true;
    }


    private boolean registerCommand(String name, String description, String usage, String label) {

        CommandMap cmp = getCommandMap();
        PluginCommand cd = getCommand(name.toLowerCase());

        cd.setDescription(description);
        cd.setUsage(usage);
        cd.setExecutor(this);
        cd.setPermission("ssba.admins.setmode");
        cd.setPermissionMessage(ChatColor.DARK_RED + "You do not have permission to run this command OP only");

        if (cmp.register(label, "mc", (Command) cd)) {
            plugin.getLogger().info("Command: /" + label + " has successfully been registered");
            return true;
        }

        plugin.getLogger().severe("Unknown error command: /" + label + " did not register at all. Investigation needed");

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
        catch ( SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e ) {
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
