package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.PermissionSaver;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

/**
 * SuperSkyBros First Created 20/09/13 Used to Execute Debug Commands from
 * Console only
 *
 * @author Relicum
 * @version 0.1
 */
public class DebugManager implements CommandExecutor {

    public static final String V_LIST = "vList";
    private SCB plugin;


    public DebugManager(SCB pl) {
        plugin = pl;
    }


    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {
        if (strings == null || strings.length < 1) {
            System.out.println("vList must pass type of list options are: Lobby, Lobbyname, Perms, wsettings, blacklist, cmds");
            return true;
        }

        if (sender instanceof Player) {
            System.out.println("Player can not run this command console only");
            return false;


        }

        if (command.getName().equalsIgnoreCase(V_LIST)) {


            vList(strings[0]);
        }

        return true;
    }


    public void vList(String list) {

        if (list.equalsIgnoreCase("lobby")) {
            if (SkyApi.getLobbyManager().getPlayersInLobby().size() == 0) {
                System.out.println("There are no players in Lobby List");
                return;
            }
            for (Player sm : SkyApi.getLobbyManager().getPlayersInLobby()) {

                System.out.println("Player " + sm.getName() + " is in the Lobby List");
            }
        }

        if (list.equalsIgnoreCase("lobbyname")) {
            if (SkyApi.getLobbyManager().getPlayerNamesInLobby().size() == 0) {
                System.out.println("There are no player names in Lobby name list");
                return;
            }
            for (String s : SkyApi.getLobbyManager().getPlayerNamesInLobby()) {
                System.out.println("Player name " + s + " is in the Lobby name List");
            }

        }

        if (list.equalsIgnoreCase("uuid")) {
            if (SkyApi.getLobbyManager().getPlayersInUUIDList().size() == 0) {
                System.out.println("There are no player names in UUID name list");
                return;
            }
            for (UUID s : SkyApi.getLobbyManager().getPlayersInUUIDList()) {
                System.out.println("Player name " + s.toString() + " is in the Lobby name List");
            }

        }

        if (list.equalsIgnoreCase("perms")) {

            PermissionSaver.saveAllPermsToFile();
            return;
            /*
             * Set<Permission> per =
             * plugin.getServer().getPluginManager().getPermissions(); for
             * (Permission k : per) { if (k.getName().startsWith("ssb")) { if
             * (k.getName().equalsIgnoreCase("ssbadmin")) {
             * System.out.println("Parent Node: ssbadmin");
             * System.out.println("Has the Child Perms"); Permission ad =
             * plugin.getServer().getPluginManager().getPermission("ssbadmin");
             * Map<String, Boolean> adc = ad.getChildren(); for (String ak :
             * adc.keySet()) { System.out.println(ak); }
             * 
             * } if (k.getName().equalsIgnoreCase("ssba.admin.*")) {
             * System.out.println("Parent Node: ssba.admin.*");
             * System.out.println("Has the Child Perms"); Permission ad =
             * plugin.
             * getServer().getPluginManager().getPermission("ssba.admin.*");
             * Map<String, Boolean> adc = getChild(ad); if(adc.size() > 1){
             * for(String p: adc.keySet()){ System.out.println("Child node: " +
             * p); Permission np =
             * plugin.getServer().getPluginManager().getPermission(p);
             * Map<String, Boolean> npc = getChild(np);
             * System.out.println("Has the children :"); for (String nk :
             * npc.keySet()) { System.out.println(nk); } }
             * 
             * } else{ for (String ak : adc.keySet()) { System.out.println(ak);
             * } }
             * 
             * 
             * } if (k.getName().equalsIgnoreCase("ssb.player")) {
             * System.out.println("Parent Node: ssb.player");
             * System.out.println("Has the Child Perms"); Permission ad =
             * plugin.
             * getServer().getPluginManager().getPermission("ssb.player");
             * Map<String, Boolean> adc = ad.getChildren(); for (String ak :
             * adc.keySet()) { System.out.println(ak); } } if
             * (k.getName().equalsIgnoreCase("ssbw.admin")) {
             * System.out.println("Parent Node: ssbw.admin");
             * System.out.println("Has the Child Perms"); Permission ad =
             * plugin.
             * getServer().getPluginManager().getPermission("ssbw.admin");
             * Map<String, Boolean> adc = ad.getChildren(); for (String ak :
             * adc.keySet()) { System.out.println(ak); } } } }
             */

        }
        if (list.equalsIgnoreCase("wsettings")) {

            SkyApi.getWorldManager().applyWorldDefaultSettings("scb");
            System.out.println("World Settings have tried to be applied");

        }

        if (list.equalsIgnoreCase("blacklist")) {
            System.out.println("Currently in black and whitelist are");
            System.out.println("In the black list there is: ");
            for (String s : plugin.getConfig().getStringList("ignoreWorlds")) {
                System.out.println(s);
            }
            System.out.println("In the whitelist there are: ");
            for (String s : plugin.getConfig().getStringList("dedicatedSSBWorlds")) {
                System.out.println(s);
            }

        }

        if (list.equalsIgnoreCase("cmds")) {
            if (plugin.getConfig().getBoolean("storeCmds")) {
                plugin.getSaver().saveStoreToFile();
                return;
            }
            System.out.println("You need to set storeCmds to true in config.yml");
            System.out.println("This is automatically set to false after each run");

        }

        return;
    }


    public Map<String, Boolean> getChild(Permission perm) {

        return perm.getChildren();

    }

    /**
     * Returns an instance of CommandMap which Can then be used to correctly
     * register the command and details with Bukkit
     *
     * @return SimpleCommandMap
     */
    public SimpleCommandMap getCommandMap() {
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

        return (SimpleCommandMap) commandMap;
    }
}
