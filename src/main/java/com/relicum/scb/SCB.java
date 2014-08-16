package com.relicum.scb;

import com.relicum.scb.commands.CommandManagerFirstJoin;
import com.relicum.scb.commands.DebugManager;
import com.relicum.scb.configs.ServerStatus;
import com.relicum.scb.database.SQLManager;
import com.relicum.scb.listeners.*;
import com.relicum.scb.mini.SignLocationStore;
import com.relicum.scb.objects.inventory.StorePlayerSettings;
import com.relicum.scb.objects.world.WorldGenerator;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.*;
import com.relicum.scb.we.WorldEditPlugin;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Main SSB Class
 *
 * @author Relicum
 * @version 0.9
 */

public class SCB extends JavaPlugin implements Listener {

    public static Locale locale;
    @SuppressWarnings("StaticVariableOfConcreteClass")
    private static SCB p;
    public long primaryThread = Thread.currentThread().getId();
    public boolean saveOnDisable = true;
    public boolean isUpdatesEnabled = true;
    public ExecutorService loginService;
    protected CommandSaver saver = null;
    private WorldManager worldManager;
    private PluginManager pm = Bukkit.getServer().getPluginManager();
    private boolean debug = true;
    /**
     * Gets instance of WorldEdit to use
     *
     * @return the WorldEdit plugin api
     */
    public static WorldEditPlugin getWorldEdit() {

        Plugin WE = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

        if ((WE instanceof WorldEditPlugin)) {
            return (WorldEditPlugin) WE;

        }
        return null;
    }

    @SuppressWarnings("RefusedBequest")
    @Override
    public void onLoad() {

    }

    /**
     * On enable.
     */
    @SuppressWarnings("RefusedBequest")
    @Override
    public void onEnable() {

        p = this;
        ConfigurationSerialization.registerClass(SerializedLocation.class);
        ConfigurationSerialization.registerClass(LocationChecker.class);
        ConfigurationSerialization.registerClass(PlayerSettings.class);
        ConfigurationSerialization.registerClass(StorePlayerSettings.class);

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        String[] l = getConfig().getString("locale", "en_GB").split("_");
        locale = new Locale(l[0], l[1]);

        Locale.setDefault(locale);
        saveResource("MessagesBundle.properties", true);
        saveResource("MessagesBundle_en_GB.properties", true);


        if (getConfig().getBoolean("storeCmds") && getConfig().getBoolean("modeSet")) {
            System.out.println("Command & Permission saver is being activated for full commands mode");
            this.saver = new CommandSaver("fullCommands");
        } else if (getConfig().getBoolean("storeCmds") && !getConfig().getBoolean("modeSet")) {
            System.out.println("Command & Permission saver is being activated for first join commands");
            this.saver = new CommandSaver("firstJoin");
        }

        SkyApi.init(this);
        SkyApi.getCMsg().INFO("Initialising SuperSkyBros Started");
        SkyApi.getCMsg().INFO("Main Thread ID is " + primaryThread);
        SkyApi.getCMsg().INFO("ServerStatus is set to " + getConfig().getString("serverStatus"));

        if (getConfig().getBoolean("mysql")) {
            try {
                SQLManager sqlManager = new SQLManager(this);
                SkyApi.getCMsg().INFO("SQL Has Connected");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        if (getConfig().getBoolean("threads.useLoginService")) {

            try {
                loginService = ScheduledManager.loginService(getConfig().getInt("threads.loginThreads"));
                SkyApi.getCMsg().INFO("New Login ExecutorService created");
            } catch (Exception e) {
                SkyApi.getCMsg().SERVE("Fatal error creating Login ExecutorService");
                e.printStackTrace();
            }
        }
        SkyApi.getSm();
        SkyApi.getCMsg().INFO("Settings manager Initialised");
        // settings.setup();
        saveResource("messages.properties", true);
        // SkyApi.getCMsg().INFO("New Message Properties file saved");


        SkyApi.getVaultManager();


        if (SkyApi.getSm().isUseWorldManagement() && SkyApi.getSm().isGenerateDefaultWorld()) {
            SkyApi.getCMsg().INFO("Starting auto setup for dedicated servers");
            updateBukkitConfigs();
        }

        if (p.getConfig().getBoolean("debugCommands")) {

            p.getCommand(DebugManager.V_LIST).setExecutor(new DebugManager(p));
            p.getCommand(DebugManager.V_LIST).setPermissionMessage("Only runs from console");

            SkyApi.getCMsg().INFO("Debug Commands installed");
        }

        if (getConfig().getString("serverStatus").equalsIgnoreCase("MODEUNSET")) {
            if (getConfig().getBoolean("firstRun") && (!getConfig().getBoolean("modeSet"))) {
                this.saveOnDisable = false;
                CommandExecutor cm = new CommandManagerFirstJoin(p);

                p.getCommand("ssba").setExecutor(cm);

                p.pm.registerEvents(new FirstRun(), this);

                FileUtils.createDirectory(getDataFolder().toString(), "players");
                FileUtils.createDirectory(getDataFolder().toString(), "worlds");

                if (getConfig().getBoolean("storeCmds")) {
                    saver.saveStoreToFile();

                }

            }
        } else {

            CommandExecutor cm = SkyApi.getCommandManager();

            SkyApi.getCommandManager().addWorld(SkyApi.getSm().getSsbWorlds());
            p.getCommand("ssb").setExecutor(cm);
            p.getCommand("ssba").setExecutor(cm);
            p.getCommand("ssbw").setExecutor(cm);
            p.getCommand("ssb").setPermissionMessage(SkyApi.getMessageManager().getNoPerm());
            p.getCommand("ssba").setPermissionMessage(SkyApi.getMessageManager().getNoPerm());
            p.getCommand("ssbw").setPermissionMessage(SkyApi.getMessageManager().getNoPerm());
            // Debug Commands

            ScheduledManager poolManager = new ScheduledManager(getConfig().getInt("threads.timerScheduled"));
            getServer().getScheduler().scheduleSyncDelayedTask(SkyApi.getSCB(), new Startup(), 15L);

        }

    }

    @SuppressWarnings("RefusedBequest")
    @Override
    public void onDisable() {

        if ((this.getConfig().getBoolean("firstRun")) && (!this.getConfig().getBoolean("firstRunDone"))) {
            if (this.saveOnDisable) {
                this.getConfig().set("firstRun", false);
                this.getConfig().set("firstRunDone", true);
            }
            SkyApi.getSm().getLobbyConfig().saveConfig();
            SkyApi.getSm().getWorldConfig().saveConfig();
            SkyApi.getSm().getSignConfig().saveConfig();
            SkyApi.getSm().getSignFormatConfig().saveConfig();
            if (getConfig().getBoolean("threads.useLoginService")) {
                if (ScheduledManager.loginServiceForShutDown()) {
                    SkyApi.getCMsg().INFO("Login Service Shutdown successfully");
                } else {
                    SkyApi.getCMsg().SERVE("Fatal error shutting down Login Service");
                }
            }
            this.saveConfig();

        } else {
            try {
                SkyApi.getSm().getLobbyConfig().saveConfig();
                SkyApi.getSm().getWorldConfig().saveConfig();
                SkyApi.getSm().getSignConfig().saveConfig();
                SkyApi.getSm().getSignFormatConfig().saveConfig();
                SkyApi.getSm().getArenaConfig().saveConfig();
                SkyApi.getSm().getSpawnConfig().saveConfig();
                if (getConfig().getBoolean("threads.useLoginService")) {
                    if (ScheduledManager.loginServiceForShutDown()) {
                        SkyApi.getCMsg().INFO("Login Service Shutdown successfully");
                    } else {
                        SkyApi.getCMsg().SERVE("Fatal error shutting down Login Service");
                    }
                }
                this.saveConfig();
                ScheduledManager.getScheduler().shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadLobbyEvents() {

        if (!this.getConfig().getBoolean("dedicatedSSB")) {
            SkyApi.getCMsg().INFO("Loading Lobby Events in SSB");
            pm.registerEvents(new LobbyBlockPlace(p), p);
            pm.registerEvents(new LobbyBlockBreak(p), p);

        }

    }

    public void unloadLobbyEvents() {
        if (!this.getConfig().getBoolean("dedicatedSSB")) {
            LobbyBlockBreak bl = new LobbyBlockBreak(this);
            LobbyBlockPlace bp = new LobbyBlockPlace(this);

            SkyApi.getCMsg().INFO("UnLoading Lobby Events in SSB");
        }
    }

    private void registerNewPerm(String name, String des, String parent) {
        org.bukkit.permissions.Permission per = new org.bukkit.permissions.Permission(name);

        per.setDescription(des);
        per.addParent(parent, true);
        per.setDefault(PermissionDefault.OP);
        p.pm.addPermission(per);
    }

    /**
     * Update Main World Settings
     */
    public void updateBukkitConfigs() {
        int currentStage = getConfig().getInt("worldGenerateStage");
        if (currentStage == 3) {
            getServer().getScheduler().scheduleSyncDelayedTask(SkyApi.getSCB(), new Runnable() {
                @Override
                public void run() {
                    applyWorldDefaultSettings("world");
                }
            }, 20l);

            getConfig().set("generateDefaultWorld", false);
            getConfig().set("worldGenerateStage", 1);
            saveConfig();
            return;
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("bukkit.yml"));

        config.set("worlds.world.generator", "SuperSkyBros");

        try {
            config.save(new File("bukkit.yml"));
            SkyApi.getCMsg().INFO("SuperSkyBros has been set to world in bukkit.yml");

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.removeDefaultWorld("world");
        config.set("settings.allow-end", false);

        try {
            config.save(new File("bukkit.yml"));
            SkyApi.getCMsg().INFO("Allow the_end has been set to false in bukkit.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertiesManager prop = new PropertiesManager();
        Map<String, Object> st = SkyApi.getSm().getWorldConfig().getConfig().getConfigurationSection("mainWorld").getValues(true);
        for (Map.Entry e : st.entrySet()) {
            prop.setPropertiesConfig((String) e.getKey(), e.getValue());
        }
        try {
            prop.savePropertiesConfig();
            SkyApi.getCMsg().INFO("New settings have been applied to server.properties file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.removeDefaultWorld("world" + "_the_end");
        this.removeDefaultWorld("world" + "_nether");

        SkyApi.getSm().getWorldConfig().getConfig().set("mainWorld.level-name", SkyApi.getSCB().getConfig().getString("world"));
        if (currentStage == 1) {
            getConfig().set("worldGenerateStage", 2);
            saveConfig();
            SkyApi.getCMsg().INFO("The server will shutdown please restart to complete set up");
            DelayedShutDown.shutDown();
        } else if (currentStage == 2) {
            getConfig().set("worldGenerateStage", 3);
            saveConfig();
            SkyApi.getCMsg().INFO("The server will shutdown please restart to complete set up");
            DelayedShutDown.shutDown();
        }
    }

    public boolean removeDefaultWorld(String path) {

        FileUtils.clear(new File(path));
        try {
            FileUtils.clear(new File(path));
        } catch (Exception e) {
            SkyApi.getSCB().getLogger().severe(e.getMessage());
            return false;
        }

        SkyApi.getCMsg().INFO("Successfully deleted " + path + " folder the server will now restart");

        return true;
    }

    public World applyWorldDefaultSettings(final String name) {

        World world;

        try {
            world = Bukkit.getServer().getWorld(name);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        SkyApi.getCMsg().INFO("Attempting to apply world settings to the world " + name);
        world.loadChunk(0, 0, true);
        BlockState block = world.getBlockAt(0, 31, 0).getState();

        block.setType(Material.GOLD_BLOCK);
        block.update(true);

        world.setSpawnLocation(0, 32, 0);
        world.setKeepSpawnInMemory(true);

        world.setAutoSave(true);
        world.setDifficulty(Difficulty.HARD);

        world.setStorm(false);
        world.setThundering(false);
        world.setWeatherDuration(9999999);
        world.setTime(6000);
        getServer().setDefaultGameMode(GameMode.ADVENTURE);
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doFireTick", "false");
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("mobGriefing", "false");
        world.save();
        getConfig().set("autosetupRun", true);
        saveConfig();
        SkyApi.getCMsg().INFO("Settings applied for  " + name + " has been successful");
        SkyApi.getCMsg().INFO("You can now login enjoy !!");
        return world;
    }

    /**
     * Get world creator.
     *
     * @param name the name of the world
     * @return the world creator
     */
    public WorldCreator getWorldCreator(String name) {
        return new WorldCreator(name);
    }

    public CommandSaver getSaver() {
        return saver;
    }

    public void setSaver(CommandSaver saver) {
        this.saver = saver;
    }

    @Override
    public WorldGenerator getDefaultWorldGenerator(String worldName, String id) {

        return new WorldGenerator();
    }

    class Startup implements Runnable {

        SCB p = SkyApi.getSCB();

        @Override
        public void run() {

            SkyApi.getWorldManager().loadEnabledWorlds();
            SkyApi.loadManagers();

            new SignLocationStore(p);

            if (!p.getConfig().getBoolean("enable")) {
                SkyApi.getCMsg().INFO("SCB is being disabled due to it enable being false in config.yml");
                p.pm.disablePlugin(p);
                return;

            }

            // Load PlayerJoin Listener dependant if server is setup thn, if
            // it's dedicated mode or mixed mode
            if (!getConfig().getString("serverStatus").equalsIgnoreCase("READY")) {

                p.pm.registerEvents(new SetupPlayerJoin(ServerStatus.valueOf(getConfig().getString("serverStatus"))), p);
                SkyApi.getCMsg().INFO("Player Join Listener loaded for setup");

            } else if (getConfig().getString("serverStatus").equalsIgnoreCase("READY") && p.getConfig().getBoolean("dedicatedSSB")) {

                p.pm.registerEvents(new com.relicum.scb.listeners.dedicated.PlayerJoin(ServerStatus.valueOf(getConfig().getString("serverStatus"))), p);
                SkyApi.getCMsg().INFO("Player Join Listener loaded for dedicated");

            } else {

                p.pm.registerEvents(new com.relicum.scb.listeners.mixed.PlayerJoin(), p);
                SkyApi.getCMsg().INFO("Player Join Listener loaded for mixed");
            }

            if (SkyApi.getSm().getLobbyConfig().getConfig().getBoolean("LOBBYSET")) {
                if (p.getConfig().getBoolean("dedicatedSSB")) {
                    p.pm.registerEvents(new DBlockBreakPlace(), p);
                    SkyApi.getCMsg().INFO("Dedicated mode block place and break listener activated");
                } else {

                    p.loadLobbyEvents();
                }
            }

            p.pm.registerEvents(new WorldListeners(), p);
            p.pm.registerEvents(new onBlockClick(), p);
            // p.pm.registerEvents(new PlayerJoin(p), p);
            p.pm.registerEvents(new PlayerQuit(p), p);
            // p.pm.registerEvents(new PlayerLoginNoPerm(p), p);
            // p.pm.registerEvents(new BlockDamage(), p);
            p.pm.registerEvents(new PlayerJoinLobby(), p);
            // p.pm.registerEvents(new ShopManager(p), p);
            p.pm.registerEvents(new SignChange(), p);
            p.pm.registerEvents(new PlayerInteract(), p);
            p.pm.registerEvents(new ShopManager(p), p);
            // List<String> wol = new ArrayList<>();

            // p.pm.registerEvents(new PlayerToggleFly(p),p);
            // p.pm.registerEvents(new Generator(),p);

            BroadcastManager.setup();
            GemShop gemShop = new GemShop(p);

            registerNewPerm("ssba.admin.breakblocks", "Allows  user to break blocks", "ssba.admin");
            registerNewPerm("ssba.admin.placeblocks", "Allow user to place blocks", "ssba.admin");
            registerNewPerm("ssba.admin.breakbypass", "Allow user to bypass breaking of blocks anywhere", "ssba.admin");
            registerNewPerm("ssba.admin.placebypass", "Allow user to bypass placing of blocks anywhere", "ssba.admin");

            registerNewPerm("ssba.admin.createsign", "Allows user to create signs", "ssba.admin");
            registerNewPerm("ssb.player.uselobbyjoin", "Allows user to use a lobby join sign", "ssb.player");
            registerNewPerm("ssb.player.uselobbyleave", "Allows user to use a lobby leave sign", "ssb.player");
            registerNewPerm("ssb.player.usearenajoin", "Allows user to use a arena leave sign", "ssb.player");
            registerNewPerm("ssb.player.usearenareturn", "Allows user to use a Arena lobby return to main lobby signs", "ssb.player");

            if (getConfig().getBoolean("storeCmds")) {
                saver.saveStoreToFile();
                PermissionSaver.saveAllPermsToFile();
            }

            if (SkyApi.getSm().isUseWorldManagement() && SkyApi.getSm().isGenerateDefaultWorld()) {
                SkyApi.getCMsg().INFO("Please restart the server as part of autosetup");
            }

        }

    }

    /**
     * Sets new debug.
     *
     * @param debug New value of debug.
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * Gets debug.
     *
     * @return Value of debug.
     */
    public boolean isDebug() {
        return debug;
    }
}
