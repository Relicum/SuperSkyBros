package com.relicum.scb;

import com.relicum.scb.commands.CommandManagerFirstJoin;
import com.relicum.scb.commands.DebugManager;
import com.relicum.scb.configs.*;
import com.relicum.scb.listeners.*;
import com.relicum.scb.mini.SerializedLocation;
import com.relicum.scb.mini.SignLocationStore;
import com.relicum.scb.objects.inventory.InventoryManager;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.*;
import com.relicum.scb.we.WorldEditPlugin;
import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Main SSB Class
 *
 * @author Relicum
 * @version 0.9
 */

public class SCB extends JavaPlugin implements Listener {

    public static final String DEDICATED_SSB = "dedicatedSSB";
    public static final String WORLD_EDIT = "WorldEdit";
    public static final String SUCCESSFULLY_HOOKED_INTO_ECONOMY_PLUGIN = "Successfully Hooked into Economy Plugin";
    public static final String VAULT_COULD_NOT_HOOK_INTO_ECONOMY_PLUGIN = "Vault could not hook into Economy Plugin";
    public static final String SUCCESSFULLY_HOOKED_INTO_CHAT_PLUGIN = "Successfully Hooked into Chat Plugin";
    public static final String VAULT_COULD_NOT_HOOK_INTO_CHAT_PLUGIN = "Vault could not hook into Chat Plugin";
    public static final String SUCCESSFULLY_HOOKED_INTO_PERMISSIONS_PLUGIN = "Successfully Hooked into Permissions Plugin";
    public static final String VAULT_COULD_NOT_HOOK_INTO_PERMISSIONS_PLUGIN = "Vault could not hook into Permissions Plugin";
    public static final String LOBBYSET = "LOBBYSET";
    public static final String SSBA_ADMIN = "ssba.admin.*";
    public static final String SSBA_ADMIN_BREAKBLOCKS = "ssba.admin.breakblocks";
    public static final String SSBA_ADMIN_PLACEBLOCKS = "ssba.admin.placeblocks";
    public static final String SSBA_ADMIN_CREATESIGN = "ssba.admin.createsign";
    public static final String IGNORE_WORLDS = "ignoreWorlds";
    public static final String ENABLE = "enable";
    private static final String FIRST_RUN_DONE = "firstRunDone";
    private static final String FIRST_RUN = "firstRun";
    /**
     * The constant MM.
     */
    public static MessageManager MM;
    /**
     * Holds a static p of itself as a JavaPlugin object
     */
    @SuppressWarnings("StaticVariableOfConcreteClass")
    private static SCB p;

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
    public boolean saveOnDisable = true;
    @Setter
    public InventoryManager INV;
    @Getter
    public boolean isUpdatesEnabled = true;
    protected ArrayList<Permission> plist = new ArrayList<>();
    /**
     * The SignManager
     */
    private SignManager SNM;
    /**
     * The BaseSign Formatter Config.
     */
    private SignFormat SFM;
    private ScheduledManager poolManager;
    private WorldConfig WCF;
    private WorldManager worldManager;
    private PluginManager pm = Bukkit.getServer().getPluginManager();
    private List<String> bWorlds = new ArrayList<>();


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

    /**
     * Gets instance of WorldEdit to use
     *
     * @return the WorldEdit plugin api
     */
    public static WorldEditPlugin getWorldEdit() {

        Plugin WE = BukkitInterface.getServer().getPluginManager().getPlugin(WORLD_EDIT);

        if ((WE instanceof WorldEditPlugin)) {
            return (WorldEditPlugin) WE;

        }
        return null;
    }

    public List<String> getBlackList() {
        return bWorlds;
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
        ConfigurationSerialization.registerClass(SerializedLocation.class);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        SkyApi.init(this);

        saveResource("messages.properties", true);
        //SkyApi.getCMsg().INFO("New Message Properties file saved");

        BukkitInterface.setServer(this.getServer());

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

        if (getConfig().getBoolean(FIRST_RUN) && (!getConfig().getBoolean("modeSet"))) {
            this.saveOnDisable = false;
            CommandExecutor cm = new CommandManagerFirstJoin(p);

            p.getCommand("ssba").setExecutor(cm);
            p.getCommand("ssba").setPermissionMessage("You do not have permission to run this command");
            p.pm.registerEvents(new FirstRun(this), this);
            FileUtils.createDirectory(getDataFolder().toString(), "players");
            FileUtils.createDirectory(getDataFolder().toString(), "worlds");

        } else {

            MM = new MessageManager();
            CommandExecutor cm = SkyApi.getCommandManager();
            p.getCommand("ssb").setExecutor(cm);
            p.getCommand("ssba").setExecutor(cm);
            p.getCommand("ssbw").setExecutor(cm);
            p.getCommand("ssb").setPermissionMessage(MM.getNoPerm());
            p.getCommand("ssba").setPermissionMessage(MM.getNoPerm());
            p.getCommand("ssbw").setPermissionMessage(MM.getNoPerm());
            //Debug Commands

            poolManager = new ScheduledManager(2);
            getServer().getScheduler().scheduleSyncDelayedTask(SCB.getInstance(), new Startup(), 15L);

        }


    }

    /**
     * On disable.
     */
    @Override
    public void onDisable() {


        if ((this.getConfig().getBoolean(FIRST_RUN)) && (!this.getConfig().getBoolean(FIRST_RUN_DONE))) {
            if (this.saveOnDisable) {
                this.getConfig().set(FIRST_RUN, false);
                this.getConfig().set(FIRST_RUN_DONE, true);
            }
            SkyApi.getSm().getLobbyConfig().saveConfig();
            SkyApi.getSm().getWorldConfig().saveConfig();
            SkyApi.getSm().getSignConfig().saveConfig();
            SkyApi.getSm().getSignFormatConfig().saveConfig();
            this.saveConfig();


        } else {
            try {
                SkyApi.getSm().getLobbyConfig().saveConfig();
                SkyApi.getSm().getWorldConfig().saveConfig();
                SkyApi.getSm().getSignConfig().saveConfig();
                SkyApi.getSm().getSignFormatConfig().saveConfig();
                ARC.saveConfig();
                SPC.saveConfig();
                this.saveConfig();
                ScheduledManager.getScheduler().shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void loadLobbyEvents() {

        if (!this.getConfig().getBoolean(DEDICATED_SSB)) {
            SkyApi.getCMsg().INFO("Loading Lobby Events in SSB");
            p.pm.registerEvents(new LobbyBlockPlace(p), p);
            p.pm.registerEvents(new LobbyBlockBreak(p), p);

            // p.pm.registerEvents(new PlayerBlockDamage(), p);

        }

    }

    public void unloadLobbyEvents() {
        if (!this.getConfig().getBoolean(DEDICATED_SSB)) {
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

    class Startup implements Runnable {


        SCB p = SkyApi.getSCB();

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


            p.bWorlds = p.getConfig().getStringList(IGNORE_WORLDS);
            for (String w : p.bWorlds) {

                System.out.println("World " + w + " is in the blacklist");
            }

            //SkyApi.getSm().setSsbWorlds();
            p.INV = new InventoryManager();

            p.LBC = SkyApi.getSm().getLobbyConfig();

            new SignLocationStore(p);

            p.SPC = new SpawnConfig("spawns.yml");
            p.SPC.getConfig().options().copyDefaults(true);
            p.SPC.saveConfig();


            p.ARC = new ArenaConfig("arena.yml");
            p.ARC.getConfig().options().copyDefaults(true);
            p.ARC.saveConfig();
            p.ARM = new ArenaManager();


            SettingsManager.getInstance().setup(p);
            //MM = new MessageManager(p);
            if (!p.getConfig().getBoolean(ENABLE)) {
                SkyApi.getCMsg().INFO("SCB is being disabled due to it enable being false in config.yml");
                p.pm.disablePlugin(p);
                return;

            }

            p.LBS = SkyApi.getLobbyManager();
            if (p.LBC.getConfig().getBoolean(LOBBYSET)) {
                if (p.getConfig().getBoolean(DEDICATED_SSB)) {
                    p.pm.registerEvents(new DBlockBreakPlace(p), p);
                } else {

                    p.loadLobbyEvents();
                }
            }

            p.pm.registerEvents(new WorldListeners(), p);
            p.pm.registerEvents(new onBlockClick(p), p);
            p.pm.registerEvents(new PlayerJoin(p), p);
            p.pm.registerEvents(new PlayerQuit(p), p);
            p.pm.registerEvents(new PlayerLoginNoPerm(p), p);
            //p.pm.registerEvents(new BlockDamage(p), p);
            p.pm.registerEvents(new PlayerJoinLobby(), p);

            p.pm.registerEvents(new SignChange(p), p);
            p.pm.registerEvents(new PlayerInteract(p), p);
            p.pm.registerEvents(new ShopManager(p), p);
            //p.pm.registerEvents(new ArenaChangeStatusOld(p), p);
            // List<String> wol = new ArrayList<>();
            //wol.add("world_the_end");
            // p.pm.registerEvents(new PlayerToggleFly(p),p);
            // p.pm.registerEvents(new Generator(),p);

            BroadcastManager.setup();
            GemShop gemShop = new GemShop(p);
            p.SNM = new SignManager();


            registerNewPerm(SSBA_ADMIN_BREAKBLOCKS, "Allows  user to break blocks", SSBA_ADMIN);
            registerNewPerm(SSBA_ADMIN_PLACEBLOCKS, "Allow user to place blocks", SSBA_ADMIN);
            registerNewPerm(SSBA_ADMIN_CREATESIGN, "Allows user to create signs", SSBA_ADMIN);
            registerNewPerm("ssb.player.uselobbyjoin", "Allows user to use a lobby join sign", "ssb.player.*");


        }

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

        config.set("worlds.world.generator", "CleanroomGenerator:.");

        try {
            config.save(new File("bukkit.yml"));
            SkyApi.getCMsg().INFO("CleanroomGenerator has been set to world in bukkit.yml");

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

        SkyApi.getCMsg().INFO("Settings applied for  " + name + " has been successful");
        SkyApi.getCMsg().INFO("You can now login enjoy !!");
        return world;
    }


}
