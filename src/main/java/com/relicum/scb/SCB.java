package com.relicum.scb;

import com.relicum.scb.commands.CommandManager;
import com.relicum.scb.commands.CommandManagerFirstJoin;
import com.relicum.scb.commands.DebugManager;
import com.relicum.scb.configs.*;
import com.relicum.scb.listeners.*;
import com.relicum.scb.mini.SerializedLocation;
import com.relicum.scb.mini.SignLocationStore;
import com.relicum.scb.objects.inventory.InventoryManager;
import com.relicum.scb.objects.world.WorldConfigurator;
import com.relicum.scb.types.SkyBrosApi;
import com.relicum.scb.utils.FileUtils;
import com.relicum.scb.utils.GemShop;
import com.relicum.scb.utils.Helper;
import com.relicum.scb.utils.MessageManager;
import com.relicum.scb.we.WorldEditPlugin;
import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * Main SSB Class
 *
 * @author Relicum
 * @version 0.9
 */

public class SCB extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static final String FIRST_RUN_DONE = "firstRunDone";
    public static final String DEDICATED_SSB = "dedicatedSSB";
    public static final String WORLD_EDIT = "WorldEdit";
    private static final String WORLD_GENERATOR = "worldGenerator";
    private static final String FIRST_RUN = "firstRun";
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
    public static Permission perms = null;
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
    /**
     * The SignManager
     */
    private SignManager SNM;
    /**
     * The BaseSign Formatter Config.
     */
    private SignFormat SFM;
    public boolean saveOnDisable = true;
    @Setter
    public InventoryManager INV;
    private ScheduledManager poolManager;
    private WorldConfig WCF;
    private WorldManager worldManager;
    @Getter
    public boolean isUpdatesEnabled = true;
    protected ArrayList<Permission> plist = new ArrayList<>();
    private final PluginManager pm = Bukkit.getServer().getPluginManager();
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
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        SkyBrosApi.getSettingsManager2();
        if (SkyBrosApi.getSettingsManager2().isUseWorldManagement()) {
            WorldConfigurator cs = new WorldConfigurator();
            cs.bukkitConfig.getConfig().options().copyDefaults(true);
            File f = new File(SkyBrosApi.getSCB().getDataFolder().getAbsoluteFile().getParentFile().getParent().toString() + "/bukkit.yml");
            if (!f.exists()) {
                System.out.println("File doesn't exist");
            } else {
                if (f.isFile()) System.out.println("It is a file");

                if (f.isDirectory()) System.out.println("It is a directory");

                if (f.canRead()) System.out.println("File can be read");

                if (!f.canWrite()) {
                    System.out.println("File not writable");
                    if (f.setWritable(true)) System.out.println("I have set the file to writable");

                    if (!f.canWrite()) System.out.println("File Still mot writable");

                }

            }

            //File f1 = new File(SkyBrosApi.getSCB().getDataFolder().getAbsoluteFile().getParentFile().getParent().toString() + "/bukkit.yml");
        /*
        System.out.println(SkyBrosApi.getSCB().getDataFolder().getAbsoluteFile().getParentFile().getParent().toString() + "/bukkit.yml");


        if (p.getConfig().getBoolean(WORLD_GENERATOR)) {
            p.pm.registerEvents(new WorldLoad(), p);


            p.worldManager = SkyBrosApi.getWorldManager();
            worldManager.setMainProperties();
        }*/
        }


        BukkitInterface.setServer(this.getServer());


        setupPermissions();
        setupChat();
        setupEconomy();

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

        } else {

            MM = new MessageManager();
            CommandExecutor cm = new CommandManager(p);
            p.getCommand("ssb").setExecutor(cm);
            p.getCommand("ssba").setExecutor(cm);
            p.getCommand("ssb").setPermissionMessage(MM.getNoPerm());
            p.getCommand("ssba").setPermissionMessage(MM.getNoPerm());
            //Debug Commands

            poolManager = new ScheduledManager(2);
            getServer().getScheduler().scheduleSyncDelayedTask(SCB.getInstance(), new Startup(), 15L);

            SkyBrosApi.getSettingsManager2().getSignConfig().getConfig().options().copyDefaults(true);
            SkyBrosApi.getSettingsManager2().getSignConfig().saveConfig();
            new SignLocationStore(p);
            //SkyBrosApi.getSettingsManager2().getWorldConfig().getConfig().options().copyDefaults(true);
            //SkyBrosApi.getSettingsManager2().getWorldConfig().saveConfig();
            //SkyBrosApi.getWorldManager();
        }


    }

    /**
     * On disable.
     */

    public void onDisable() {

        if (((this.getConfig().getBoolean(FIRST_RUN)) && (!this.getConfig().getBoolean(FIRST_RUN_DONE)))) {
            if (this.saveOnDisable) {
                this.getConfig().set(FIRST_RUN, false);
                this.getConfig().set(FIRST_RUN_DONE, true);
            }
            SkyBrosApi.getSettingsManager2().getLobbyConfig().saveConfig();
            //SkyBrosApi.getSettingsManager2().getWorldConfig().saveConfig();
            SkyBrosApi.getSettingsManager2().getSignConfig().saveConfig();
            this.saveConfig();

        } else {


            try {
                SkyBrosApi.getSettingsManager2().getLobbyConfig().saveConfig();
                //SkyBrosApi.getSettingsManager2().getWorldConfig().saveConfig();
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

    class Startup implements Runnable {


        final SCB p = SCB.getInstance();

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

            /*WorldCreator wc = new WorldCreator("template");

            wc.environment(Environment.NORMAL);
            wc.type(WorldType.FLAT);
            wc.generator("CleanroomGenerator:.");
            wc.generateStructures(false);
            System.out.println("About to start new world creation");
            World world = wc.createWorld();
            System.out.println("End of world creation");
            Location below = new Location(world, 0, 64, 0);
            Block b = below.getBlock();
            b.setType(Material.GLASS);
            world.save();
            world.setAutoSave(true);
            world.setSpawnLocation(0,65,0);
            world.setKeepSpawnInMemory(true);
            world.setDifficulty(Difficulty.EASY);
            world.setSpawnFlags(false,false);
            world.save();*/
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
            //p.pm.registerEvents(new ArenaChangeStatus(p), p);
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



/*            Set<org.bukkit.permissions.Permission> per = p.pm.getPermissions();
            Iterator<org.bukkit.permissions.Permission> it = per.iterator();
            while(it.hasNext()){
                org.bukkit.permissions.Permission st = it.next();
                  if(st.getName().contains("bukkit")) {
                 System.out.println(st.getName() + ": Default " + st.getDefault().toString() + " Description : " + st
                 .getDescription());
                System.out.println(""); }
            }*/
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

    private void setupEconomy() {

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(
                net.milkbowl.vault.economy.Economy
                        .class);

        if (rsp != null) {
            // SkyBrosApi. = rsp.getProvider();
            log.info(SUCCESSFULLY_HOOKED_INTO_ECONOMY_PLUGIN);
        } else {
            log.warning(VAULT_COULD_NOT_HOOK_INTO_ECONOMY_PLUGIN);
        }

    }

    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
            chat = rsp.getProvider();
            log.info(SUCCESSFULLY_HOOKED_INTO_CHAT_PLUGIN);
        } else {
            log.warning(VAULT_COULD_NOT_HOOK_INTO_CHAT_PLUGIN);
        }


    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);


        if (rsp != null) {
            perms = rsp.getProvider();
            log.info(SUCCESSFULLY_HOOKED_INTO_PERMISSIONS_PLUGIN);
        } else {
            log.warning(VAULT_COULD_NOT_HOOK_INTO_PERMISSIONS_PLUGIN);
        }

    }


}
