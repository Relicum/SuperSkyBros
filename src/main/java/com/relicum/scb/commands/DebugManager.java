package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.SmashPlayer;
import com.relicum.scb.types.SkyApi;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * SuperSkyBros First Created 20/09/13 Used to Execute Debug Commands from Console only
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
            System.out.println("vList must pass type of list options are: Lobby, Lobbyname,Perms,wsettings");
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
            if (SCB.getInstance().LBS.getPlayersInLobby().size() == 0) {
                System.out.println("There are no players in Lobby List");
                return;
            }
            for (SmashPlayer sm : SCB.getInstance().LBS.getPlayersInLobby()) {

                System.out.println("Player " + sm.getName() + " is in the Lobby List");
            }
        }

        if (list.equalsIgnoreCase("lobbyname")) {
            if (SCB.getInstance().LBS.getPlayerNamesInLobby().size() == 0) {
                System.out.println("There are no player names in Lobby name list");
                return;
            }
            for (String s : SCB.getInstance().LBS.getPlayerNamesInLobby()) {
                System.out.println("Player name " + s + " is in the Lobby name List");
            }

        }

        if (list.equalsIgnoreCase("uuid")) {
            if (SCB.getInstance().LBS.getPlayersInUUIDList().size() == 0) {
                System.out.println("There are no player names in UUID name list");
                return;
            }
            for (UUID s : SCB.getInstance().LBS.getPlayersInUUIDList()) {
                System.out.println("Player name " + s.toString() + " is in the Lobby name List");
            }

        }

        if (list.equalsIgnoreCase("perms")) {

            Set<Permission> per = plugin.getServer().getPluginManager().getPermissions();
            for (Permission k : per) {
                if (k.getName().startsWith("ssb")) {
                    if (k.getName().equalsIgnoreCase("ssba.admin")) {
                        System.out.println("Parent Node: ssba.admin");
                        System.out.println("Has the Child Perms");
                        Permission ad = plugin.getServer().getPluginManager().getPermission("ssba.admin");
                        Map<String, Boolean> adc = ad.getChildren();
                        for (String ak : adc.keySet()) {
                            System.out.println(ak);
                        }
                    }
                    if (k.getName().equalsIgnoreCase("ssb.player")) {
                        System.out.println("Parent Node: ssb.player");
                        System.out.println("Has the Child Perms");
                        Permission ad = plugin.getServer().getPluginManager().getPermission("ssb.player");
                        Map<String, Boolean> adc = ad.getChildren();
                        for (String ak : adc.keySet()) {
                            System.out.println(ak);
                        }
                    }
                }
            }

        }
        if (list.equalsIgnoreCase("wsettings")) {

            SkyApi.getWorldManager().applyWorldDefaultSettings("scb");
            System.out.println("World Settings have tried to be applied");

        }

        return;
    }


}
