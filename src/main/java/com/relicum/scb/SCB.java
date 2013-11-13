package com.relicum.scb;

import com.relicum.scb.commands.CommandManager;
import com.relicum.scb.commands.CommandManagerFirstJoin;
import com.relicum.scb.commands.DebugManager;
import com.relicum.scb.configs.*;
import com.relicum.scb.listeners.*;
import com.relicum.scb.objects.inventory.InventoryManager;
import com.relicum.scb.objects.world.Generator;
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
import org.bukkit.generator.ChunkGenerator;
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

    @Setter
    public InventoryManager INV;

    protected ArrayList<Permission> plist = new ArrayList<>();

    protected PluginManager pm = Bukkit.getServer().getPluginManager();

    public ScheduledManager poolManager;

    public WorldConfig WCF;

    public WorldManager worldManager;


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

    @Getter
    public boolean isUpdatesEnabled = true;


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
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);


        if (p.getConfig().getBoolean("worldGenerator")) {
            p.WCF = new WorldConfig("worlds.yml");
            p.WCF.getConfig().options().copyDefaults(true);
            p.WCF.saveConfig();
            p.worldManager = new WorldManager(this, this.WCF);
        }
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
            boolean f1 = new File(getDataFolder() + "/worlds").exists();
            if (!f1) {
                boolean fi1 = new File(getDataFolder() + "/worlds").mkdirs();

                if (fi1)
                    System.out.println("New Directory created at " + getDataFolder() + "/worlds");
                else
                    System.out.println("Error: Failed to create players directory at " + getDataFolder() + "/worlds");
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

        if (p.getConfig().getBoolean("worldGenerator")) {
            p.WCF.saveConfig();
            p.getLogger().info("World Settings have been saved");
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
            //p.pm.registerEvents(new BlockDamage(p), p);
            p.pm.registerEvents(new PlayerJoinLobby(), p);
            p.pm.registerEvents(new WorldLoad(p), p);
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
            Helper.getInstance().setup(p);

            registerNewPerm("ssba.admin.breakblocks", "Allows  user to break blocks", "ssba.admin.*");
            registerNewPerm("ssba.admin.placeblocks", "Allow user to place blocks", "ssba.admin.*");
            registerNewPerm("ssba.admin.createsign", "Allows user to create signs", "ssba.admin.*");
            registerNewPerm("ssb.player.uselobbyjoin", "Allows user to use a lobby join sign", "ssb.player.*");



/*            Set<org.bukkit.permissions.Permission> per = p.pm.getPermissions();
            Iterator<org.bukkit.permissions.Permission> it = per.iterator();
            while(it.hasNext()){
                org.bukkit.permissions.Permission st = it.next();
                  if(st.getName().contains("bukkit")) {
                 System.out.println(st.getName() + ": Default " + st.getDefault().toString() + " Description : " + st.getDescription());
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
        }
        catch ( Exception e ) {
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


    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new Generator();
    }


}
