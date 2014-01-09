package com.relicum.scb.listeners;

import com.relicum.scb.*;
import com.relicum.scb.classes.PlayerType;
import com.relicum.scb.configs.PlayerConfig;
import com.relicum.scb.events.PlayerJoinLobbyEvent;
import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.objects.inventory.SerializableInventory;
import com.relicum.scb.objects.inventory.StorePlayerSettings;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.PlayerSt;
import com.relicum.scb.utils.timers.StartTimer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
@Setter
@Getter
public class PlayerJoin implements Listener {

    private SCB plugin;
    private boolean firstTimeOverride;
    private PlayerSt status;
    private boolean mode;
    public PlayerSettings relicum;
    public StorePlayerSettings playerSettings;
    public PlayerConfig relfile;

    public PlayerJoin(SCB plugin) {
        this.plugin = plugin;
        firstTimeOverride = plugin.getConfig().getBoolean("firstJoinOverride");
        mode = plugin.getConfig().getBoolean("dedicatedSSB");
    }


    public void playJoin(PlayerJoinEvent e) {


        //Testing StorePlayerSettings
        Player p = e.getPlayer();
        playerSettings = new StorePlayerSettings();
        playerSettings.setGameMode(p.getGameMode());
        playerSettings.setInventory(new SerializableInventory(p.getInventory().getArmorContents(), p.getInventory().getContents()));
        playerSettings.setPlayerTime(p.getPlayerTime());
        playerSettings.setExhaustion(p.getExhaustion());
        playerSettings.setSaturation(p.getSaturation());
        playerSettings.setPlayerHealth(p.getHealth());
        playerSettings.setFoodLevel(p.getFoodLevel());
        playerSettings.setTotalExperience(p.getTotalExperience());
        playerSettings.setLevel(p.getLevel());
        playerSettings.setXp(p.getExp());
        playerSettings.setAllowedFlight(p.getAllowFlight());
        playerSettings.setFlying(p.isFlying());
        playerSettings.setFlySpeed(p.getFlySpeed());
        playerSettings.setWalkSpeed(p.getWalkSpeed());
        playerSettings.setPlayerName(p.getName());
        playerSettings.setDisplayName(p.getDisplayName());
        playerSettings.setFireTicks(p.getFireTicks());

        SkyApi.getSCB().getConfig().set(p.getName(), playerSettings);
        SkyApi.getSCB().saveConfig();

        //End test

        //Test Player Profiles
        if (PlayerLoginManager.hasProfile(e.getPlayer().getName())) {
            this.relicum = (PlayerSettings) relfile.getConfig().get("player." + e.getPlayer().getName());

            SkyApi.getCMsg().INFO("The player " + this.relicum.getPlayerName() + " has a profile");
            SkyApi.getCMsg().WARNING(Paths.get(PlayerLoginManager.path).resolve(e.getPlayer().getName() + ".yml").toString());
        } else {
            this.relfile = new PlayerConfig(PlayerLoginManager.profilePath(e.getPlayer().getName()));

            this.relicum = new PlayerSettings()
                    .setPlayerName(e.getPlayer().getName())
                    .setPlayerType(PlayerType.PLAYER)
                    .setInLobby(true);
            this.relfile.getConfig().set("player." + e.getPlayer().getName(), this.relicum);
            this.relfile.saveConfig();
            SkyApi.getCMsg().INFO("No profile found for player " + e.getPlayer().getName());
            SkyApi.getCMsg().WARNING(Paths.get(PlayerLoginManager.path).resolve(e.getPlayer().getName() + ".yml").toString());
        }
        //End test player profiles


        if (firstTimeOverride && !e.getPlayer().hasPlayedBefore() && !e.getPlayer().isOp()) {
            if (VaultManager.perms.has(e.getPlayer(), "ssb.player.join") && (!mode)) {
                e.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation().add(0.5, 0.5, 0.5));
            }

        }


        //CraftPlayer player1 = (CraftPlayer) e.getPlayer();
        // EntityPlayer player = player1.getHandle();
        //NBTTagCompound nbtTagCompound = new NBTTagCompound();
        // NBTTagCompound nbtTagCompound2 = new NBTTagCompound();
        //playerAbilities.a(nbtTagCompound);
        // player.abilities.isInvulnerable = false;
        // player.abilities.mayBuild = false;
        //  player.abilities.canFly = true;
        // player.abilities.canInstantlyBuild = false;
        // player.updateAbilities();

        //  player.b(nbtTagCompound2);
        List<String> st = Arrays.asList(
                "    " + "\u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<",
                ChatColor.GOLD +
                        e.getPlayer().getName() + " welcome to the server");

        if (SCB.getInstance().getConfig().getBoolean(SCB.DEDICATED_SSB)) {
            SmashPl pl = new SmashPl(e.getPlayer());
/*            if (VaultManager.perms.has(pl.getPlayer(), "ssba.admin") || pl.getPlayer().isOp()) {
                ChatColor b = ChatColor.BOLD;
                String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
                pl.sendMessage(
                        pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros Beta " +
                                SCB.getInstance().getDescription().getVersion() + " this should not be run on " +
                                "a live server be warned");

            }*/


            if (VaultManager.perms.has(pl.getPlayer(), "ssb.player.join") || pl.isOp()) {
                e.setJoinMessage("");

                pl.setpStatus(PlayerSt.JOINEDSERVER);
                pl.setMyLocation("JOINEDSERVER");
                PlayerJoinLobbyEvent event = new PlayerJoinLobbyEvent(pl, "JOINEDSERVER", SCB.getInstance().getConfig().getBoolean(SCB.DEDICATED_SSB));
                Bukkit.getServer().getPluginManager().callEvent(event);
                System.out.println("Gets to the dedicatedssb section");
                return;
            } else {
                e.setJoinMessage("");

                pl.kickPlayer(SCB.getMessageManager().getErrorMessage("system.noJoinPerm"));
                return;
            }


        }


        if (SettingsManager.getInstance().notWorlds().contains(e.getPlayer().getWorld().getName()) && !e.getPlayer().isOp()) {
            System.out.println("Player Join Returned right at the top");
            return;
        }
        if (SettingsManager.getInstance().notWorlds().contains(e.getPlayer().getWorld().getName()) && plugin.getConfig().getBoolean(SCB.DEDICATED_SSB) && !VaultManager.perms.has(
                e.getPlayer(), "ssba.admin")) {
            e.setJoinMessage("");
            System.out.println("Player Join Event has been thrown");
            e.getPlayer().kickPlayer(SCB.getMessageManager().getErrorMessage("system.kickJoinWorldBlacklisted"));
            System.out.println(
                    "Player " + e.getPlayer().getName() + " was kicked trying from the world " + e.getPlayer().getWorld().getName() + " which is on the black list you can change this in the config.yml");
            return;
        }


/*        if (VaultManager.perms.has(e.getPlayer(), "ssba.admin") || e.getPlayer().isOp()) {

            ChatColor b = ChatColor.BOLD;
            String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
            e.getPlayer().sendMessage(
                    pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros " +
                            "Beta " + SCB.getInstance().getDescription().getVersion() + " this " +
                            "should not be run on a live server be warned");


        }*/

        if (!SkyApi.getSm().getLobbyConfig().getConfig().contains("LOBBY.REGION")) {
            if (e.getPlayer().isOp()) {
                e.setJoinMessage(SCB.getMessageManager().getErrorMessage("system.opAutoJoinOverRide"));
                plugin.getLogger().severe("You need to set a lobby spawn or players can not join");
                return;

            } else {

                e.setJoinMessage("");
                e.getPlayer().kickPlayer(SCB.getMessageManager().getErrorMessage("system.noLobby"));
                plugin.getLogger().severe(
                        e.getPlayer().getName() + " has been kicked as you have not set a Lobby " +
                                "Spawn yet");
                return;
            }

        }


        //Display Special Message Advertising the plugin only to new players
        for (String s : plugin.getConfig().getStringList("rel.lines.line")) {
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
        }

        System.out.println("Player passed right through all Join event conditions ???");
    }


    protected int testTimer(int x, int y, int z) {

        StartTimer.makeTimer(1, 1, 31);
        return 1;
    }


    public boolean displayBetaMessage(PlayerJoinEvent e) {


        e.setJoinMessage(null);
        ChatColor b = ChatColor.BOLD;
        String pre = ChatColor.GRAY + "" + b + "[" + ChatColor.RED + "" + b + "SSB" + ChatColor.GRAY + "" + b + "]";
        e.getPlayer().sendMessage(
                pre + ChatColor.GREEN + "This server currently has installed Super Sky Bros Beta " + SCB.getInstance().getDescription().getVersion() + " this should not be " +
                        "run on a live server be warned");


        return true;
    }

}

