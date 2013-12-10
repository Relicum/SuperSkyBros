package com.relicum.scb.commands;

import com.relicum.scb.conversations.DefaultConversationFactory;
import com.relicum.scb.conversations.autosetup.SetAutoStart;
import com.relicum.scb.objects.signs.utils.Col;
import com.relicum.scb.types.SkyApi;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * SuperSkyBros
 * First Created 29/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class autosetup extends SubBase {

    public static ConversationFactory factory;

    private final String cHeader = "    \u00A72>\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
            "[\u00A7b\u00A7lSuper-Sky-Bros\u00A72]\u25AC*\u25AC*\u25AC*\u25AC*\u25AC" +
            "*\u25AC*\u25AC*\u25AC*\u25AC*\u25AC<\n";

    private StringBuilder sb;


    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {

        if (!SkyApi.getSm().isDedicated()) {
            player.sendMessage(Col.Dark_Red() + "You can only run this in Dedicated mode");
            return true;
        }

        factory = DefaultConversationFactory.getDefaultConversation();
        String[] strings1 = new String[6];
        for (int i = 0; i < 6; i++) {
            strings1[i] = "";
        }

        player.sendMessage(strings1);
        Map<Object, Object> data = new HashMap<>();
        data.put("pre", 2);
        factory.withInitialSessionData(data);
        factory.withFirstPrompt(new SetAutoStart());
        factory.buildConversation((Conversable) player).begin();
        /*player.sendMessage(strings1);
        player.sendMessage(this.cHeader);
        getPlugin().getConfig().set("generateDefaultWorld", true);
        getPlugin().saveConfig();
        player.sendMessage(ChatColor.GREEN + "The server will now shutdown you will need to stop and start the server");
        player.sendMessage("3 times to complete the setup the console will display details of progress.");
        player.sendMessage(ChatColor.GRAY + "----------------------------------------------");
        DelayedShutDown.shutDown();
        player.sendMessage(ChatColor.RED + "The server will now shutdown in 5 seconds");*/

        return true;

    }

    /**
     * Simplify set this function to set the field mNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = "autosetup";
    }

    /**
     * Simply set this to return the the number of arguments The command should receive
     *
     * @return Integer
     */
    @Override
    public Integer setNumArgs() {
        return 0;
    }

    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssbw.admin.autosetup";
    }

    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssbw autosetup";
    }

    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssbw autosetup";
    }

    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssbw autosetup";
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }


}
