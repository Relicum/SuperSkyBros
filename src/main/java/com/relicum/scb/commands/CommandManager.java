package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.StringUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Custom Command Handler This deals with all events connect to Commands, including command help
 *
 * @version 0.1
 */
public class CommandManager implements TabExecutor {

    private final List<String> PLAYER = new ArrayList<>();

    private final List<String> ADMIN = new ArrayList<>();

    private final List<String> WADMIN = new ArrayList<>();

    private List<String> WSET = new ArrayList<>(8);

    private List<String> WSETTING = null;


    /**
     * Stores an instance of the main plugin class
     */
    public SCB plugin;

    /**
     * Stores a HashMap of commands
     */
    public Map<String, SubBase> clist = new HashMap<String, SubBase>();

    public boolean addWorld(String w) {
        SkyApi.getCMsg().INFO("We got here with world " + w);
        if (Bukkit.getWorld(w) != null) {
            WSET.add(w);
            return true;
        }
        SkyApi.getCMsg().WARNING("The world could not be added to Command Manager as it is not a valid Bukkit world");
        return false;
    }

    public void addWorld(List<String> w) {

        for (String wo : w) {
            if (Bukkit.getWorld(wo) != null) {
                SkyApi.getCMsg().WARNING("The world " + wo + " could not be added to Command Manager as it is not a valid Bukkit world");
            } else if (WSET.contains(wo)) {
                SkyApi.getCMsg().WARNING("The world " + wo + " could not be added as it is already in the list");
            } else if (!WSET.contains(wo)) {
                WSET.add(wo);
                SkyApi.getCMsg().INFO("The world " + wo + " has successfully been added to the list");
            }
        }
    }

    public void resetWhiteList() {
        WSET.clear();

        try {
            WSET.addAll(plugin.getConfig().getStringList("dedicatedSSBWorlds"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static MessageManager mm = SCB.getMessageManager();


    /**
     * Class that handles all the clist and redirects Them to the correct class
     *
     * @param p SCB
     */
    public CommandManager() {

        this.plugin = SkyApi.getSCB();


        //Only Load world management commands if enabled

        if (SkyApi.getSm().isUseWorldManagement()) {
            resetWhiteList();

            clist.put("modifyworld", new modifyworld());
            clist.put("autosetup", new autosetup());
            clist.put("saveworld", new saveworld());
            clist.put("createworld", new createworld());
            clist.put("set", new set());

            WSETTING = new ArrayList<>();
            WSETTING.add("spawn");
            WSETTING.add("time");
        }

        loadCommands();

        for (Map.Entry<String, SubBase> entry : clist.entrySet()) {

            registerCommand(entry.getKey(), entry.getValue());
            String[] sp = entry.getValue().getCmd().split(" ");
            if (sp[0].equalsIgnoreCase("ssba")) {
                ADMIN.add(sp[1]);
            } else if (sp[0].equalsIgnoreCase("ssbw")) {
                WADMIN.add(sp[1]);
            } else if (sp[0].equalsIgnoreCase("ssb")) {
                PLAYER.add(sp[1]);
            }
        }

    }


    /**
     * This adds all of the clist into a hash map
     */
    private void loadCommands() {


        clist.put("createarena", new createarena());
        clist.put("setspawn", new setspawn());
        clist.put("join", new join());
        clist.put("modify", new modify());
        clist.put("setdedicated", new setdedicated());
        clist.put("setlobby", new setlobby());
        clist.put("setlobbyprotection", new setlobbyprotection());
        clist.put("listarenas", new listarenas());
        clist.put("arenatp", new arenatp());
        clist.put("leave", new leave());
        clist.put("enable", new enable());
        clist.put("disable", new disable());
        clist.put("blacklist", new blacklist());
        clist.put("player", new player());
        clist.put("lobbytp", new lobbytp());
        clist.put("setarenalobby", new setarenalobby());
        clist.put("worldtp", new worldtp());
        clist.put("blacklisted", new blacklisted());
        clist.put("adminmode", new adminmode());

    }


    /**
     * Custom Command Executor
     *
     * @param cs      CommandSender
     * @param cmnd    Command
     * @param string  String
     * @param strings String[]
     * @return boolean
     */
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {

        // Current clist can only be run as a player
        if (!(cs instanceof Player)) {
            plugin.getLogger().severe(mm.noConsole());
            return true;
        }


        Player player = (Player) cs;


        if (plugin.getConfig().contains(player.getWorld().getName())) {
            player.sendMessage(mm.getErrorMessage("command.message.worldOnBlackList"));
            plugin.getLogger().info("You can not run commands in world " + player.getWorld().getName() + " as the world is on the world blacklist remove it from config.yml");
            return true;
        }

        if (strings == null || strings.length < 1L) {

            player.sendMessage(ChatColor.DARK_RED + "No arguments passed");
            return false;
        }

        String sub = strings[0];
        ArrayList<String> t = new ArrayList<String>();
        t.addAll(Arrays.asList(strings));
        t.remove(0);
        strings = t.toArray(new String[t.size()]);

        // Check if the sub command is valid
        if (!clist.containsKey(sub)) {
            cs.sendMessage(mm.getErrorMessage("system.subNotFound"));
            return true;
        }

        // Pre Execute command checks
        SubBase subCom = clist.get(sub);
        // Has the user the permission
        if ((!player.isOp()) && (!VaultManager.perms.has(player, subCom.getPerm()))) {
            player.sendMessage(mm.getNoPerm());
            return true;
        }

        // Check correct args are passed if not send usage
        if ((!subCom.mNode.equalsIgnoreCase("set")) && strings.length != subCom.getNumArgs()) {

            send(player, ChatColor.DARK_RED + "Usage: " + subCom.getUseage());
            return true;
        }

        try {


            clist.get(sub).onCommand(player, strings);


        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(mm.getErrorMessage("system.internal.defaultError"));
            player.sendMessage(ChatColor.AQUA + "./ " + sub + "" + Arrays.toString(strings));
        }
        return true;

    }


    /**
     * Send Message to player Have still to add in my own custom messaging system
     *
     * @param p Player
     * @param s String
     */
    private void send(Player p, String s) {

        p.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.DARK_PURPLE + "SSB" + ChatColor.DARK_GREEN + "]" + ChatColor.DARK_PURPLE + s);
    }


    /**
     * This function will register all the clist With Bukkit as well as setting the Description, Useage Permission and
     * label of the command
     *
     * @param name String
     * @param sb   SubBase
     */
    public boolean registerCommand(String name, SubBase sb) {

        String[] ps = sb.getPerm().split("\\.");
        String ubPerm = ps[0] + "." + ps[1];


        String des = mm.getStringConfig(sb.getDescription());
        des = ChatColor.translateAlternateColorCodes('&', des);

        Permission per = new Permission(ubPerm + "." + name);

        per.setDefault(PermissionDefault.OP);
        per.addParent(ubPerm, true);
        per.setDescription(des);
        plugin.getServer().getPluginManager().addPermission(per);

        CommandMap cmp = getCommandMap();
        PluginCommand cd = getCommand(name.toLowerCase());

        cd.setDescription(des);
        cd.setUsage(sb.getUseage());
        cd.setExecutor(this);
        cd.setPermission(sb.getPerm());


        if (cmp.register(sb.getLabel(), "mc", (Command) cd)) {
            plugin.getLogger().info("Command: /" + sb.getLabel() + " has successfully been registered");


            return true;
        }

        plugin.getLogger().severe("Unknown error command: /" + sb.getLabel() + " did not register at all. Investigation needed");

        return false;


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
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
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
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return commandMap;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] strings) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (strings.length == 1) {
                if (s.equalsIgnoreCase("ssb")) {

                    return StringUtil.copyPartialMatches(strings[0], PLAYER, new ArrayList<String>(PLAYER.size()));
                } else if (s.equalsIgnoreCase("ssba")) {

                    return StringUtil.copyPartialMatches(strings[0], ADMIN, new ArrayList<String>(ADMIN.size()));
                } else if (s.equalsIgnoreCase("ssbw")) {
                    return StringUtil.copyPartialMatches(strings[0], WADMIN, new ArrayList<String>(WADMIN.size()));
                }
            }
            if (strings.length == 2 && s.equalsIgnoreCase("ssba")) {

                if (strings[0].equalsIgnoreCase("adminmode")) {
                    return Arrays.asList("On", "Off");
                }

                if (strings[0].equalsIgnoreCase("setdedicated")) {
                    return Arrays.asList("true", "false");
                }
            }
            if (strings.length == 2 && s.equalsIgnoreCase("ssbw")) {

                if (strings[0].equalsIgnoreCase("set")) {
                    return StringUtil.copyPartialMatches(strings[1], WSET, new ArrayList<String>(WSET.size()));
                }
            }
            if (strings.length == 3 && s.equalsIgnoreCase("ssbw")) {

                if (strings[0].equalsIgnoreCase("set")) {
                    return StringUtil.copyPartialMatches(strings[2], WSETTING, new ArrayList<String>(WSETTING.size()));
                }
            }

        }

        return Arrays.asList("");

    }

}