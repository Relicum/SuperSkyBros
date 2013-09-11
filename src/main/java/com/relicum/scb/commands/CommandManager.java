package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.SettingsManager;
import com.relicum.scb.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom Command Handler This deals with all events connect to Commands, including command help
 *
 * @version 0.1
 */
public class CommandManager implements CommandExecutor {


	/**
	 * Stores an instance of the main plugin class
	 */
	public Plugin plugin;
	/**
	 * Stores a HashMap of commands
	 */
	public Map<String, SubBase> clist = new HashMap<String, SubBase>();

	public static MessageManager mm = SCB.getMessageManager();

	/**
	 * Class that handles all the clist and redirects Them to the correct class
	 *
	 * @param p SCB
	 */
	public CommandManager(Plugin p) {

		plugin = p;
		loadCommands();

		for (Map.Entry<String, SubBase> entry : clist.entrySet()) {

			registerCommand(entry.getKey(), entry.getValue());

		}

	}


	/**
	 * This adds all of the clist into a hash map
	 */
	private void loadCommands() {

		//clist.put("setlobbyspawn", new setlobbyspawn());
		clist.put("createarena", new createarena());
		clist.put("setspawn", new setspawn());
		clist.put("join", new join());
		clist.put("modify", new modify());
		clist.put("setautojoin", new setautojoin());
		clist.put("setlobby", new setlobby());
		clist.put("setlobbyprotection", new setlobbyprotection());
		clist.put("listarenas", new listarenas());
		clist.put("arenatp", new arenatp());
		clist.put("leave", new leave());
        clist.put("enable", new enable());
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

		if (SettingsManager.getInstance().isWorldBlackListed(player.getWorld().toString())) {
			player.sendMessage(mm.getAdminMessage("command.message.worldOnBlackList"));
			return true;
		}

		if (strings == null || strings.length < 1) {

			player.sendMessage(ChatColor.DARK_RED + "No arguments passed need to handle help display");
			return true;
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
		if (!(player.isOp()) || !(player.hasPermission(subCom.getPerm()))) {
			player.sendMessage(mm.getNoPerm());
			return true;
		}

		// Check correct args are passed if not send usage
		if (strings.length != subCom.getNumArgs()) {

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

		CommandMap cmp = getCommandMap();
		PluginCommand cd = getCommand(name.toLowerCase());

		String des = mm.getStringConfig(sb.getDescription());
		des = ChatColor.translateAlternateColorCodes('&', des);
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
	 * Returns an instance of CommandMap which Can then be used to correctly register the command and details with Bukkit
	 *
	 * @return CommandMap
	 */
	public CommandMap getCommandMap() {
		CommandMap commandMap = null;

		try {
			if ((SimplePluginManager) plugin.getServer().getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");
				f.setAccessible(true);

				commandMap = (CommandMap) f.get(plugin.getServer().getPluginManager());
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return commandMap;
	}

}