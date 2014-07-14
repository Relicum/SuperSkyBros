package com.relicum.scb.commands;

import com.relicum.scb.types.SkyApi;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SuperSkyBros First Created 08/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class adminmode extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {

        if (args[0].equalsIgnoreCase("on")) {
            if (!SkyApi.getSm().getAdminMode().contains(player.getName())) {
                SkyApi.getSm().setAdminMode(player.getName());
                player.setDisplayName(ChatColor.DARK_RED + "[AM]" + player.getName());
            }
            player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.adminmodeToggle").replace("%TOGGLE%", "ON"));
            return true;
        } else if (args[0].equalsIgnoreCase("off")) {
            SkyApi.getSm().getAdminMode().remove(player.getName());
            player.setDisplayName(player.getName());
            player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.adminmodeToggle").replace("%TOGGLE%", "OFF"));
            return true;
        }

        return false;
    }

    /**
     * Simplify set this function to set the field mNode with the commands
     * description will come from in the messages.yml file You do not need to
     * enter the full node as it will be prefixed for you. Eg is the full node
     * is command.description.createarena you only need to set this to
     * createarena
     */
    @Override
    public void setmDescription() {
        mNode = this.getClass().getSimpleName();
    }

    /**
     * Simply set this to return the the number of arguments The command should
     * receive
     *
     * @return Integer
     */
    @Override
    public Integer setNumArgs() {
        return 1;
    }

    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssba.admin.adminmode";
    }

    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {

        return "/<command> [on|off]";
    }

    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba adminmode";
    }

    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba adminmode";
    }

    public List<String> onTabComplete(CommandSender sender, String alias, String[] strings) {
        System.out.println(strings.toString());
        System.out.println(alias + " length is " + strings.length);
        List<String> ops = Arrays.asList("on", "off");
        if (sender instanceof Player) {

            if (strings.length == 2) {
                System.out.println("trying to tab");
                return StringUtil.copyPartialMatches(strings[1], ops, new ArrayList<String>(ops.size()));
            }

        }

        return Arrays.asList("help");
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
