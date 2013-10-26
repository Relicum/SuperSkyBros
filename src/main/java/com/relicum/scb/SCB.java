package com.relicum.scb;

import com.relicum.scb.commands.CommandManager;
import com.relicum.scb.commands.CommandManagerFirstJoin;
import com.relicum.scb.commands.DebugManager;
import com.relicum.scb.configs.*;
import com.relicum.scb.listeners.*;
import com.relicum.scb.objects.inventory.InventoryManager;
import com.relicum.scb.utils.GemShop;
import com.relicum.scb.utils.Helper;
import com.relicum.scb.utils.MessageManager;
import com.relicum.scb.we.WorldEditPlugin;
import lombok.Data;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.player.*;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Main SSB Class
 *
 * @author Relicum
 * @version 0.9
 */
public class SCB extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");


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

    private List<String> bWorlds = new ArrayList<>();

    public boolean saveOnDisable = true;

    public InventoryManager INV;

    protected ArrayList<Permission> plist = new ArrayList<>();

    protected PluginManager pm = Bukkit.getServer().getPluginManager();

    public ScheduledManager poolManager;


    public List<String> getBlackList() {
        return bWorlds;
    }


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


    public static Economy econ = null;

    public static Permission perms = null;

    public static Chat chat = null;

    public List<Class<?>> exemptEvents = Arrays.asList(new Class<?>[]{
            AsyncPlayerPreLoginEvent.class, PlayerJoinEvent.class,
            PlayerKickEvent.class, PlayerLoginEvent.class,
            AsyncPlayerPreLoginEvent.class, PlayerQuitEvent.class});

    private boolean isExemptEnabled = true;

    public String blockedMessage = "&c[Error]%player% command cannot be performed in %world% by %plugin%.";

    public boolean isUpdatesEnabled = true;


    public void $(String s) {
        System.out.println("[PerWorldPlugins] " + s);
    }


    public boolean isExemptEnabled() {
        return this.isExemptEnabled;
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
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.reloadConfig();


        /**
         * This function worldEventRestrict was written and designed by MylesC and is part of the PerWorldsPlugin
         * Which can be found at http://dev.bukkit.org/bukkit-mods/perworldplugins/
         * Full Credits belong to him. He has granted usage of this code provided it is made
         * aware he is the author as per https://github.com/MylesIsCool/PerWorldPlugins
         * @author MylesC
         */
        if (getConfig().getBoolean("ppw"))
            worldEventRestrict();

        BukkitInterface.setServer(this.getServer());


        setupPermissions();
        setupChat();
        setupEconomy();


        if (SCB.getInstance().getConfig().getBoolean("firstRun")) {
            this.saveOnDisable = false;
            CommandExecutor cm = new CommandManagerFirstJoin(p);

            p.getCommand("ssba").setExecutor(cm);
            p.getCommand("ssba").setPermissionMessage("You do not have permission to run this command");
            p.pm.registerEvents(new FirstRun(this), this);
            boolean f = new File(getDataFolder() + "/players").exists();
            if (!f) {
                boolean fi = new File(getDataFolder() + "/players").mkdirs();

                if (fi)
                    System.out.println("New Directory created at " + getDataFolder() + "/players");
                else
                    System.out.println("Error: Failed to create players directory at " + getDataFolder() + "/players");
            }
        } else {

            MM = new MessageManager(p);
            CommandExecutor cm = new CommandManager(p);
            p.getCommand("ssb").setExecutor(cm);
            p.getCommand("ssba").setExecutor(cm);
            p.getCommand("ssb").setPermissionMessage(MM.getNoPerm());
            p.getCommand("ssba").setPermissionMessage(MM.getNoPerm());
            //Debug Commands
            if (p.getConfig().getBoolean("debugCommands")) {

                p.getCommand("vList").setExecutor(new DebugManager(p));
                p.getCommand("vList").setPermissionMessage("Only runs from console");


                System.out.println("Debug Commands installed");
            }
            poolManager = new ScheduledManager(2);
            getServer().getScheduler().scheduleSyncDelayedTask(SCB.getInstance(), new Startup(), 15L);


        }


    }


    /**
     * On disable.
     */
    @Override
    public void onDisable() throws NullPointerException {

        if (((this.getConfig().getBoolean("firstRun")) && (!this.getConfig().getBoolean("firstRunDone")))) {
            if (this.saveOnDisable) {
                this.getConfig().set("firstRun", false);
                this.getConfig().set("firstRunDone", true);
            }
            this.saveConfig();

        } else {


            try {
                LBC.saveConfig();
                ARC.saveConfig();
                SPC.saveConfig();
                SNC.saveConfig();
                SFM.saveConfig();
                this.saveConfig();
                ScheduledManager.getScheduler().shutdown();
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }

        }


    }


    public void loadLobbyEvents() {

        if (!this.getConfig().getBoolean("dedicatedSSB")) {
            System.out.println("Loading Lobby Events in SSB");
            p.pm.registerEvents(new LobbyBlockPlace(p), p);
            p.pm.registerEvents(new LobbyBlockBreak(p), p);

            // p.pm.registerEvents(new PlayerBlockDamage(), p);

        }

    }


    public void unloadLobbyEvents() {
        if (!this.getConfig().getBoolean("dedicatedSSB")) {
            LobbyBlockBreak bl = new LobbyBlockBreak(this);
            LobbyBlockPlace bp = new LobbyBlockPlace(this);

            System.out.println("UnLoading Lobby Events in SSB");
        }
    }


    protected class Startup implements Runnable {

        SCB p = SCB.getInstance();


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


            p.bWorlds = p.getConfig().getStringList("ignoreWorlds");
            for ( String w : p.bWorlds ) {

                System.out.println("World " + w + " is in the blacklist");
            }
            p.INV = new InventoryManager();

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
            //MM = new MessageManager(p);
            if (!p.getConfig().getBoolean("enable")) {
                getLogger().info("SCB is being disabled due to it enable being false in config.yml");
                p.pm.disablePlugin(p);
                return;

            }


            p.LBS = new LobbyManager();
            if (p.LBC.getConfig().getBoolean("LOBBYSET")) {
                if (p.getConfig().getBoolean("dedicatedSSB")) {
                    p.pm.registerEvents(new DBlockBreakPlace(p), p);
                } else {

                    p.loadLobbyEvents();
                }
            }

            p.pm.registerEvents(new onBlockClick(p), p);
            p.pm.registerEvents(new PlayerJoin(p), p);
            p.pm.registerEvents(new PlayerQuit(p), p);
            p.pm.registerEvents(new PlayerLoginNoPerm(p), p);
            p.pm.registerEvents(new BlockDamage(p), p);
            p.pm.registerEvents(new PlayerJoinLobby(), p);
            p.pm.registerEvents(new WorldLoad(p), p);
            p.pm.registerEvents(new SignChange(p), p);
            p.pm.registerEvents(new PlayerInteract(p), p);

            //p.pm.registerEvents(new ArenaChangeStatus(p), p);
            // List<String> wol = new ArrayList<>();
            //wol.add("world_the_end");
            //p.pm.registerEvents(new PlayerToggleFly(p,wol),p);


            BroadcastManager.setup();
            GemShop gemShop = new GemShop(p);
            p.SNM = new SignManager();


            //TODO Must refactor out this Helper Class
            Helper.getInstance().setup(p);

            registerNewPerm("ssba.admin.createsign", "Allows  user to create SSB signs", "ssba.admin");
            registerNewPerm("ssb.player.uselobbyjoin", "Allows user to use a lobby join sign", "ssb.player");


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


        private void registerNewPerm(String name, String des, String parent) {
            org.bukkit.permissions.Permission per = new org.bukkit.permissions.Permission(name);
            per.setDescription(des);
            per.addParent(parent, true);
            per.setDefault(PermissionDefault.TRUE);

            p.pm.addPermission(per);


        }
    }


    private void setupEconomy() {

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);

        if (rsp != null) {
            econ = rsp.getProvider();
            log.info("Successfully Hooked into Economy Plugin");
        } else {
            log.warning("Vault could not hook into Economy Plugin");
        }

    }


    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
            chat = rsp.getProvider();
            log.info("Successfully Hooked into Chat Plugin");
        } else {
            log.warning("Vault could not hook into Chat Plugin");
        }


    }


    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);


        if (rsp != null) {
            perms = rsp.getProvider();
            log.info("Successfully Hooked into Permissions Plugin");
        } else {
            log.warning("Vault could not hook into Permissions Plugin");
        }

    }


    @SuppressWarnings("all")
    public void worldEventRestrict() {


        boolean isInjected = false;
        $("Enabled, Attempting to Inject PluginManager");
        if (Bukkit.getPluginManager().getClass().getPackage().getName()
                .contains("Myles")) {
            Bukkit.getServer()
                    .getLogger()
                    .log(Level.SEVERE,
                            "Looks like the FakePluginManager has already been injected, If this is a reload please ignore.");
            isInjected = true;
        }
        try {
            Field f = Bukkit.getServer().getClass()
                    .getDeclaredField("pluginManager");
            f.setAccessible(true);
            PluginManager oldManager = (PluginManager) f
                    .get(Bukkit.getServer());
            if (isInjected) {
                f.set(Bukkit.getServer(),
                        new SSBPluginManager((PluginManager) oldManager
                                .getClass().getDeclaredField("oldManager")
                                .get(oldManager)));
            } else {
                f.set(Bukkit.getServer(), new SSBPluginManager(oldManager));
            }

        }
        catch ( NoSuchFieldException | SecurityException e ) {
            System.out
                    .println("[Error] Failed to inject, please notify the author on bukkitdev. (Type: FieldNotFound, PluginManager)");
        }
        catch ( IllegalArgumentException e ) {
            System.out
                    .println("[Error] Failed to inject, please notify the author on bukkitdev. (Type: IllegalArgument, PluginManager)");
        }
        catch ( IllegalAccessException e ) {
            System.out
                    .println("[Error] Failed to inject, please notify the author on bukkitdev. (Type: AccessError, PluginManager)");
        }
        $("Enabled, Attempting to Inject CommandHandler");
        try {
            Field f = Bukkit.getServer().getClass()
                    .getDeclaredField("commandMap");
            if (f.getType().getClass().getPackage().getName().contains("Myles")) {
                Bukkit.getServer()
                        .getLogger()
                        .log(Level.SEVERE,
                                "Looks like the FakeSimpleCommandMap has already been injected, If this is a reload please ignore.");
                return;
            }
            if (!isInjected) {
                f.setAccessible(true);
                SimpleCommandMap oldCommandMap = (SimpleCommandMap) f
                        .get(Bukkit.getServer());
                f.set(Bukkit.getServer(), new FakeSimpleCommandMap(
                        oldCommandMap));
            }
        }
        catch ( NoSuchFieldException | SecurityException e ) {
            System.out
                    .println("[Error] Failed to inject, please notify the author on bukkitdev. (Type: FieldNotFound, SimpleCommandMap)");
        }
        catch ( IllegalArgumentException e ) {
            System.out
                    .println("[Error] Failed to inject, please notify the author on bukkitdev. (Type: IllegalArgument, SimpleCommandMap)");
        }
        catch ( IllegalAccessException e ) {
            System.out
                    .println("[Error] Failed to inject, please notify the author on bukkitdev. (Type: AccessError, SimpleCommandMap)");
        }

    }


    public boolean checkWorld(org.bukkit.plugin.Plugin plugin, World w) {

        List<String> worlds = bWorlds;

        if (worlds.contains(w.getName())) {
            if (plugin instanceof SCB)
                return false;
            else
                return true;

        } else {
            if (!(plugin instanceof SCB))
                return false;
            else
                return true;
        }
    }

}
