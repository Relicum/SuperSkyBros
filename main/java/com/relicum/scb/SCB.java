package com.relicum.scb;

import com.relicum.scb.classes.Creeper;
import com.relicum.scb.commands.CommandManager;
import com.relicum.scb.configs.ArenaConfig;
import com.relicum.scb.configs.LobbyConfig;
import com.relicum.scb.configs.SpawnConfig;
import com.relicum.scb.listeners.*;
import com.relicum.scb.utils.Helper;
import com.relicum.scb.utils.MessageManager;
import com.relicum.scb.we.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Main SSB Class
 *
 * @author Relicum
 * @version 0.9
 */
public class SCB extends JavaPlugin {

	/**
	 * The constant MM.
	 */
	public static MessageManager MM;
	/**
	 * Holds a static p of itself as a JavaPlugin object
	 */
	@SuppressWarnings("StaticVariableOfConcreteClass")
	public static SCB p;
	/**
	 * The Group spawn file.
	 */
	public File groupSpawnFile = null;
	/**
	 * The Group spawn.
	 */
	public FileConfiguration groupSpawn = null;
	/**
	 * The LBS.
	 */
	public LobbyManager LBS;
	/**
	 * Lobby Config Object
	 */
	public LobbyConfig LBC;
	/**
	 * Arena Config Object
	 */
	public ArenaConfig ARC;

	public ArenaManager ARM;
	public SpawnConfig SPC;

	/**
	 * The Creeper.
	 */
	public Creeper co;
	protected ArrayList<Permission> plist = new ArrayList<>();
	protected PluginManager pm = Bukkit.getServer().getPluginManager();

	/**
	 * Gets p.
	 *
	 * @return the p
	 */
	public static SCB getInstance() {
		return p;
	}

	/**
	 * Get Instance of MessageManager
	 *
	 * @return MessageManager message manager
	 */
	public static MessageManager getMessageManager() {

		return MM;
	}

	public static WorldEditPlugin getWorldEdit() {

		Plugin WE = BukkitInterface.getServer().getPluginManager().getPlugin("WorldEdit");

		if ((WE instanceof WorldEditPlugin)) {
			return (WorldEditPlugin) WE;

		}

		return null;

	}

	/**
	 * On load. Registers any ConfigurationSerializable files at onLoad Before other things have started to load
	 */
	public void onLoad() {


	}

	/**
	 * On enable.
	 */
	@Override
	public void onEnable() {


		try {
			@SuppressWarnings("LocalVariableOfConcreteClass")
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {

			System.out.println(e.getStackTrace().toString());
		}


		p = this;
		BukkitInterface.setServer(this.getServer());
		getServer().getScheduler().scheduleSyncDelayedTask(p, new Startup(), 15);

		this.getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();


	/*	if (!getConfig().getBoolean("firstRun")) {
			//this.groupSpawn = new YamlConfiguration();

		}



		this.LBC = new LobbyConfig("lobby.yml");
		this.LBC.getConfig().options().copyDefaults(true);
		this.LBC.saveConfig();


		this.SPC = new SpawnConfig("spawns.yml");
		this.SPC.getConfig().options().copyDefaults(true);
		this.SPC.saveConfig();

		this.ARC = new ArenaConfig("arena.yml");
		this.ARC.getConfig().options().copyDefaults(true);
		this.ARC.saveConfig();
		this.ARM = new ArenaManager(this);


		SettingsManager.getInstance().setup(p);
		MM = new MessageManager(this);
		if (!this.getConfig().getBoolean("enable")) {
			getLogger().info("SCB is being disabled due to it enable being false in config.yml");
			this.pm.disablePlugin(this);
			return;

		}

		if (!this.LBC.getConfig().getBoolean("LOBBYSET") && this.getConfig().getBoolean("autoJoinLobby")) {
			getLogger().severe("SSB is been disabled as you have not set the Lobby Spawn and have autojoin set to true please set autojoin to false and set the Lobby Spawn first");
			this.pm.disablePlugin(this);
			return;

		}
		this.LBS = new LobbyManager();
		this.pm.registerEvents(new PlayerJoin(this), this);
		this.pm.registerEvents(new PlayerQuit(this), this);
		this.pm.registerEvents(new PlayerLoginNoPerm(this), this);
		this.pm.registerEvents(new PlayerTP(), this);
		this.loadLobbyEvents();


		//noinspection LocalVariableOfConcreteClass
		CommandExecutor cm = new CommandManager(p);
		getCommand("ssb").setExecutor(cm);
		getCommand("ssba").setExecutor(cm);
		getCommand("ssb").setPermissionMessage(MM.getNoPerm());
		getCommand("ssba").setPermissionMessage(MM.getNoPerm());


		//WorldEditManager.getInstance().setup(p);
		Helper.getInstance().setup(p);
		//ArenaIO ia = new ArenaIO();
		//ia.loadArenas();*/
	}

	/**
	 * On disable.
	 */
	@Override
	public void onDisable() {

		if (SCB.getInstance().getConfig().getBoolean("firstRun")) {

			SCB.getInstance().getConfig().set("firstRun", false);
			this.saveConfig();
		}

		LBS.removeAllPlayers();
		LBC.saveConfig();
		ARC.saveConfig();
		SPC.saveConfig();
		this.saveConfig();


	}

	public void addPerm(String pe) {

		this.plist.add(new Permission(pe));
	}

	public void loadLobbyEvents() {

		if (this.getConfig().getBoolean("enableLobbyProtection")) {
			System.out.println("Loading Lobby Events in SSB");
			this.pm.registerEvents(new LobbyBlockBreak(this), this);
			this.pm.registerEvents(new LobbyBlockPlace(this), this);

		}

	}

	public void unloadLobbyEvents() {

		LobbyBlockBreak bl = new LobbyBlockBreak(this);
		LobbyBlockPlace bp = new LobbyBlockPlace(this);

		BlockBreakEvent.getHandlerList().unregister(bl);
		BlockPlaceEvent.getHandlerList().unregister(bp);

		System.out.println("UnLoading Lobby Events in SSB");
	}

	class Startup extends SCB implements Runnable {


		/**
		 * When an object implementing interface <code>Runnable</code> is used to create a thread, starting the thread causes
		 * the object's <code>run</code> method to be called in that separately executing thread.
		 * <p/>
		 * The general contract of the method <code>run</code> is that it may take any action whatsoever.
		 *
		 * @see Thread#run()
		 */
		@Override
		public void run() {

			PluginManager pm = Bukkit.getServer().getPluginManager();


			if (!p.getConfig().getBoolean("firstRun")) {
				//p.groupSpawn = new YamlConfiguration();

			}


			p.LBC = new LobbyConfig("lobby.yml");
			p.LBC.getConfig().options().copyDefaults(true);
			p.LBC.saveConfig();


			p.SPC = new SpawnConfig("spawns.yml");
			p.SPC.getConfig().options().copyDefaults(true);
			p.SPC.saveConfig();

			p.ARC = new ArenaConfig("arena.yml");
			p.ARC.getConfig().options().copyDefaults(true);
			p.ARC.saveConfig();
			p.ARM = new ArenaManager(p);


			SettingsManager.getInstance().setup(p);
			MM = new MessageManager(p);
			if (!p.getConfig().getBoolean("enable")) {
				getLogger().info("SCB is being disabled due to it enable being false in config.yml");
				p.pm.disablePlugin(p);
				return;

			}

			if (!p.LBC.getConfig().getBoolean("LOBBYSET") && p.getConfig().getBoolean("autoJoinLobby")) {
				getLogger().severe("SSB is been disabled as you have not set the Lobby Spawn and have autojoin set to true please set autojoin to false and set the Lobby Spawn first");
				p.pm.disablePlugin(p);
				return;

			}
			p.LBS = new LobbyManager();
			p.pm.registerEvents(new PlayerJoin(p), p);
			p.pm.registerEvents(new PlayerQuit(p), p);
			p.pm.registerEvents(new PlayerLoginNoPerm(p), p);
			p.pm.registerEvents(new PlayerTP(), p);
			p.loadLobbyEvents();


			//noinspection LocalVariableOfConcreteClass
			CommandExecutor cm = new CommandManager(p);
			p.getCommand("ssb").setExecutor(cm);
			p.getCommand("ssba").setExecutor(cm);
			p.getCommand("ssb").setPermissionMessage(MM.getNoPerm());
			p.getCommand("ssba").setPermissionMessage(MM.getNoPerm());


			//WorldEditManager.getInstance().setup(p);
			Helper.getInstance().setup(p);
			//ArenaIO ia = new ArenaIO();
			//ia.loadArenas();
		}
	}

	private void fileExists(String fi) {

		File file = new File(getDataFolder(), fi);
		FileConfiguration fCon;


		try {
			if (!file.exists()) {
				file.createNewFile();
				fCon = YamlConfiguration.loadConfiguration(SCB.getInstance().getResource(fi));
				fCon.save(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}