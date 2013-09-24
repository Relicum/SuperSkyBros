package com.relicum.scb;

import com.relicum.scb.classes.Creeper;
import com.relicum.scb.commands.CommandManager;
import com.relicum.scb.commands.DebugManager;
import com.relicum.scb.configs.*;
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

    /**
     * Spawn Config Object
     */
    public SpawnConfig SPC;

    /**
     * BaseSign Config Manager
     */
    public SignConfig SNC;

    /**
     * The SignManager
     */
    public SignManager SNM;

    /**
     * The BaseSign Formatter Config.
     */
    public SignFormat SFM;

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

        p = this;

		/*try {
            @SuppressWarnings("LocalVariableOfConcreteClass")
            MetricsLite metrics = new MetricsLite(p);
			metrics.start();
		} catch (IOException e) {

			System.out.println(e.getStackTrace().toString());
		}*/


        BukkitInterface.setServer(this.getServer());


        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        if (!p.getConfig().isBoolean("dedicatedSSB")) {

            getLogger().severe("****************************************************");
            getLogger().severe("You MUST set in config.yml if this Minecraft Server");
            getLogger().severe("is dedicated to SuperSkyBros or it has other games");
            getLogger().severe("types running along side it.");
            getLogger().severe("Please open the config.yml for SSB,");
            getLogger().severe("If this Minecraft server is dedicated to SSB ");
            getLogger().severe("set 'dedicatedSSB:' true in config.yml or ");
            getLogger().severe("If not set 'dedicatedSSB:' false in config.yml");
            getLogger().severe("The plugin will not run until you have done this");
            getLogger().severe("*****************************************************");
            getLogger().severe("SSB has been disabled due to 'dedicatedSSB' not being set in the config");
            p.pm.disablePlugin(p);
        } else
            getServer().getScheduler().scheduleSyncDelayedTask(p, new Startup(), 15);
    }


    /**
     * On disable.
     */
    @Override
    public void onDisable() {

        if (!p.getConfig().isBoolean("dedicatedSSB")) {
            this.saveConfig();
            return;
        }

        if (SCB.getInstance().getConfig().getBoolean("firstRun")) {

            SCB.getInstance().getConfig().set("firstRun", false);
            this.saveConfig();
        }

        LBS.removeAllPlayers();
        try {
            LBC.saveConfig();
            ARC.saveConfig();
            SPC.saveConfig();
            SNC.saveConfig();
            SFM.saveConfig();
            this.saveConfig();
        }
        catch ( Exception e ) {
            return;
        }


    }


    public void addPerm(String pe) {

        this.plist.add(new Permission(pe));
    }


    public void loadLobbyEvents() {

        if (this.getConfig().getBoolean("enableLobbyProtection")) {
            System.out.println("Loading Lobby Events in SSB");
            p.pm.registerEvents(new LobbyBlockBreak(p), p);
            p.pm.registerEvents(new LobbyBlockPlace(p), p);

        }

    }


    public void unloadLobbyEvents() {

        LobbyBlockBreak bl = new LobbyBlockBreak(this);
        LobbyBlockPlace bp = new LobbyBlockPlace(this);

        BlockBreakEvent.getHandlerList().unregister(bl);
        BlockPlaceEvent.getHandlerList().unregister(bp);

        System.out.println("UnLoading Lobby Events in SSB");
    }


    protected class Startup implements Runnable {

        SCB p;


        public Startup() {
            this.p = SCB.getInstance();
        }


        /**
         * When an object implementing interface <code>Runnable</code> is used to create a thread, starting the thread
         * causes the object's <code>run</code> method to be called in that separately executing thread.
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

            p.SNC = new SignConfig("signs.yml");
            p.SNC.getConfig().options().copyDefaults(true);
            p.SNC.saveConfig();

            p.ARC = new ArenaConfig("arena.yml");
            p.ARC.getConfig().options().copyDefaults(true);
            p.ARC.saveConfig();
            p.ARM = new ArenaManager(p);

            p.SFM = new SignFormat("signsText.yml");
            p.SFM.getConfig().options().copyDefaults(true);
            p.SFM.saveDefaultConfig();

            SettingsManager.getInstance().setup(p);
            MM = new MessageManager(p);
            if (!p.getConfig().getBoolean("enable")) {
                getLogger().info("SCB is being disabled due to it enable being false in config.yml");
                p.pm.disablePlugin(p);
                return;

            }


            p.LBS = new LobbyManager();
            p.pm.registerEvents(new PlayerJoin(p), p);
            p.pm.registerEvents(new PlayerQuit(p), p);
            p.pm.registerEvents(new PlayerLoginNoPerm(p), p);

            //p.pm.registerEvents(new ArenaChangeStatus(p), p);

            p.loadLobbyEvents();


            p.SNM = new SignManager();

            //noinspection LocalVariableOfConcreteClass
            CommandExecutor cm = new CommandManager(p);
            p.getCommand("ssb").setExecutor(cm);
            p.getCommand("ssba").setExecutor(cm);
            p.getCommand("ssb").setPermissionMessage(MM.getNoPerm());
            p.getCommand("ssba").setPermissionMessage(MM.getNoPerm());


            //TODO Must refactor out this Helper Class
            Helper.getInstance().setup(p);

            //Debug Commands
            if (p.getConfig().getBoolean("debugCommands")) {

                p.getCommand("vList").setExecutor(new DebugManager(p));
                p.getCommand("vList").setPermissionMessage("Only runs from console");

                System.out.println("Debug Commands installed");
            }
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
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }

    }
}
