package com.relicum.scb;

import com.relicum.scb.commands.CommandManager;
import com.relicum.scb.commands.CommandManagerFirstJoin;
import com.relicum.scb.commands.DebugManager;
import com.relicum.scb.configs.*;
import com.relicum.scb.listeners.*;
import com.relicum.scb.mini.SerializedLocation;
import com.relicum.scb.mini.SignLocationStore;
import com.relicum.scb.objects.inventory.InventoryManager;
import com.relicum.scb.types.SkyBrosApi;
import com.relicum.scb.utils.*;
import com.relicum.scb.we.WorldEditPlugin;
import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.*;
import org.bukkit.block.Block;
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
import java.util.logging.Logger;


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
    private static final Logger log = Logger.getLogger("Minecraft");
    private static final String FIRST_RUN_DONE = "firstRunDone";
    private static final String WORLD_GENERATOR = "worldGenerator";
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
    private static Economy econ = null;


    /**
     * The Vault Chat Interface
     */
    private static Chat chat = null;
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
    private final String NORMAL = "\033[m";
    private final String BLUE_TEXT = "\033[36m";  //Blue
    private final String RED_TEXT = "\033[31m";  //Red
    private final String GREEN_TEXT = "\033[32m";  //Green


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
        SkyBrosApi.init(this);
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        SkyBrosApi.getSettingsManager2();
        World theWorld = Bukkit.getWorld("world");

        //Method[] fields = World.class.getDeclaredMethods();
        //Field f2 = fields.getClass().getDeclaredField("generator");

        //System.out.println(theWorld.getClass().getDeclaredMethods().toString());

        BukkitInterface.setServer(this.getServer());

        SkyBrosApi.getVaultManager();

        if (p.getConfig().getBoolean("debugCommands")) {

            p.getCommand(DebugManager.V_LIST).setExecutor(new DebugManager(p));
            p.getCommand(DebugManager.V_LIST).setPermissionMessage("Only runs from console");


            System.out.println("Debug Commands installed");
        }

        if (SCB.getInstance().getConfig().getBoolean(FIRST_RUN)) {
            this.saveOnDisable = false;
            CommandExecutor cm = new CommandManagerFirstJoin(p);

            p.getCommand("ssba").setExecutor(cm);
            p.getCommand("ssba").setPermissionMessage("You do not have permission to run this command");
            p.pm.registerEvents(new FirstRun(this), this);
            FileUtils.createDirectory(getDataFolder().toString(), "players");
            FileUtils.createDirectory(getDataFolder().toString(), "worlds");
            StringBuilder pa = new StringBuilder();
            pa.append(getBLUE_TEXT()).append("[<]").append("[1]").append(getRED_TEXT()).append("Yml world gen value is ").append(pa).append(getBLUE_TEXT()).append("[2]").append("[>]").append(getNORMAL());

        } else {

            MM = new MessageManager();
            CommandExecutor cm = new CommandManager(p);
            p.getCommand("ssb").setExecutor(cm);
            p.getCommand("ssba").setExecutor(cm);
            p.getCommand("ssbw").setExecutor(cm);
            p.getCommand("ssb").setPermissionMessage(MM.getNoPerm());
            p.getCommand("ssba").setPermissionMessage(MM.getNoPerm());
            p.getCommand("ssbw").setPermissionMessage(MM.getNoPerm());
            //Debug Commands

            poolManager = new ScheduledManager(2);
            getServer().getScheduler().scheduleSyncDelayedTask(SCB.getInstance(), new Startup(), 15L);

            SkyBrosApi.getSettingsManager2().getSignConfig().getConfig().options().copyDefaults(true);
            SkyBrosApi.getSettingsManager2().getSignConfig().saveConfig();
            new SignLocationStore(p);
            StringBuilder pa = new StringBuilder();
            String paa = StringUtils.replaceUtf8Characters(String.valueOf(pa.append(getBLUE_TEXT()).append("[<]").append("[1]").append(getRED_TEXT()).append("Yml world gen value is ").append(pa).append(getBLUE_TEXT()).append("[2]").append("[>]").append(getNORMAL())));
            System.out.println(paa);
            //SkyBrosApi.getSettingsManager2().getWorldConfig().getConfig().options().copyDefaults(true);
            //SkyBrosApi.getSettingsManager2().getWorldConfig().saveConfig();
            //SkyBrosApi.getWorldManager();
        }


    }

    /**
     * On disable.
     */
    @Override
    public void onDisable() {

        if (this.getConfig().getBoolean("modeSet") == false || this.getConfig().getBoolean(FIRST_RUN)) {
            if (((this.getConfig().getBoolean(FIRST_RUN)) && (!this.getConfig().getBoolean(FIRST_RUN_DONE)))) {
                if (this.saveOnDisable) {
                    this.getConfig().set(FIRST_RUN, false);
                    this.getConfig().set(FIRST_RUN_DONE, true);
                }
                SkyBrosApi.getSettingsManager2().getLobbyConfig().saveConfig();
                SkyBrosApi.getSettingsManager2().getWorldConfig().saveConfig();
                SkyBrosApi.getSettingsManager2().getSignConfig().saveConfig();
                this.saveConfig();
            }
            return;
        } else {
            try {
                SkyBrosApi.getSettingsManager2().getLobbyConfig().saveConfig();
                SkyBrosApi.getSettingsManager2().getWorldConfig().saveConfig();
                SkyBrosApi.getSettingsManager2().getSignConfig().saveConfig();
                ARC.saveConfig();
                SPC.saveConfig();
                SFM.saveConfig();
                this.saveConfig();
                ScheduledManager.getScheduler().shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void loadLobbyEvents() {

        if (!this.getConfig().getBoolean(DEDICATED_SSB)) {
            System.out.println("Loading Lobby Events in SSB");
            p.pm.registerEvents(new LobbyBlockPlace(p), p);
            p.pm.registerEvents(new LobbyBlockBreak(p), p);

            // p.pm.registerEvents(new PlayerBlockDamage(), p);

        }

    }

    public void unloadLobbyEvents() {
        if (!this.getConfig().getBoolean(DEDICATED_SSB)) {
            LobbyBlockBreak bl = new LobbyBlockBreak(this);
            LobbyBlockPlace bp = new LobbyBlockPlace(this);

            System.out.println("UnLoading Lobby Events in SSB");
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

    private void registerNewPerm(String name, String des, String parent) {
        org.bukkit.permissions.Permission per = new org.bukkit.permissions.Permission(name);
        per.setDescription(des);
        per.addParent(parent, true);
        per.setDefault(PermissionDefault.OP);

        p.pm.addPermission(per);


    }

    class Startup implements Runnable {


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

            p.bWorlds = p.getConfig().getStringList(IGNORE_WORLDS);
            for (String w : p.bWorlds) {

                System.out.println("World " + w + " is in the blacklist");
            }
            p.INV = new InventoryManager();

            p.LBC = SkyBrosApi.getSettingsManager2().getLobbyConfig();


            p.SPC = new SpawnConfig("spawns.yml");
            p.SPC.getConfig().options().copyDefaults(true);
            p.SPC.saveConfig();

/*            p.SNC = new SignConfig("signs.yml");
            p.SNC.getConfig().options().copyDefaults(true);
            p.SNC.saveConfig();
            new SignLocationStore(p);*/

            p.ARC = new ArenaConfig("arena.yml");
            p.ARC.getConfig().options().copyDefaults(true);
            p.ARC.saveConfig();
            p.ARM = new ArenaManager();

            p.SFM = new SignFormat("signsText.yml");
            p.SFM.getConfig().options().copyDefaults(true);
            p.SFM.saveDefaultConfig();


            SettingsManager.getInstance().setup(p);
            //MM = new MessageManager(p);
            if (!p.getConfig().getBoolean(ENABLE)) {
                getLogger().info("SCB is being disabled due to it enable being false in config.yml");
                p.pm.disablePlugin(p);
                return;

            }

            p.LBS = SkyBrosApi.getLobbyManager();
            if (p.LBC.getConfig().getBoolean(LOBBYSET)) {
                if (p.getConfig().getBoolean(DEDICATED_SSB)) {
                    p.pm.registerEvents(new DBlockBreakPlace(p), p);
                } else {

                    p.loadLobbyEvents();
                }
            }

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


            //TODO Must refactor out this Helper Class
            Helper.getInstance().setup();

            registerNewPerm(SSBA_ADMIN_BREAKBLOCKS, "Allows  user to break blocks", SSBA_ADMIN);
            registerNewPerm(SSBA_ADMIN_PLACEBLOCKS, "Allow user to place blocks", SSBA_ADMIN);
            registerNewPerm(SSBA_ADMIN_CREATESIGN, "Allows user to create signs", SSBA_ADMIN);
            registerNewPerm("ssb.player.uselobbyjoin", "Allows user to use a lobby join sign", "ssb.player.*");

            applyWorldDefaultSettings("world");

        }

    }

    public String getGREEN_TEXT() {
        return GREEN_TEXT;
    }

    public String getRED_TEXT() {
        return RED_TEXT;
    }

    public String getBLUE_TEXT() {
        return BLUE_TEXT;
    }

    public String getNORMAL() {
        return NORMAL;
    }


    public void updateBukkitConfigs() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("bukkit.yml"));

        config.set("worlds.world.generator", "CleanroomGenerator:.");

        try {
            config.save(new File("bukkit.yml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.removeDefaultWorld("world");
        config.set("settings.allow-end", false);

        try {
            config.save(new File("bukkit.yml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertiesManager prop = new PropertiesManager();
        Map<String, Object> st = SkyBrosApi.getSettingsManager2().getWorldConfig().getConfig().getConfigurationSection("mainWorld").getValues(true);
        for (Map.Entry e : st.entrySet()) {
            prop.setPropertiesConfig((String) e.getKey(), e.getValue());
        }
        try {
            prop.savePropertiesConfig();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.removeDefaultWorld("world" + "_the_end");
        this.removeDefaultWorld("world" + "_nether");


        SkyBrosApi.getSettingsManager2().getWorldConfig().getConfig().set("mainWorld.level-name", "SSB");


        SkyBrosApi.getSettingsManager2().getWorldConfig().getConfig().set("mainWorld.level-name", SkyBrosApi.getSCB().getConfig().getString("world"));


        System.out.println(RED_TEXT + "The end set to false" + NORMAL);


    }

    public boolean removeDefaultWorld(String path) {


        FileUtils.clear(new File(path));
        try {
            FileUtils.clear(new File(path));
        } catch (Exception e) {
            SkyBrosApi.getSCB().getLogger().severe(e.getMessage());
            return false;
        }

        SkyBrosApi.getSCB().getLogger().info("Successfully deleted " + path + " folder the server will now restart");

        return true;
    }

    public World applyWorldDefaultSettings(String name) {

        World world;

        try {
            world = Bukkit.getServer().getWorld("world");
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        world.loadChunk(0, 0, true);
        world.setSpawnLocation(0, 32, 0);
        world.setKeepSpawnInMemory(true);

        world.getSpawnLocation().getWorld().getChunkAt(world.getSpawnLocation()).load();
        Block block = world.getBlockAt(0, 31, 0);

        block.getState().getBlock().setType(Material.AIR);

        block.getState().update(true);

        block.getState().setType(Material.GOLD_BLOCK);
        block.getState().update(true);

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


        System.out.println("Settings applied for  " + name + " has been successful");
        return world;
    }


}
