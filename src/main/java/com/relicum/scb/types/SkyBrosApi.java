package com.relicum.scb.types;

import com.relicum.scb.ArenaManager;
import com.relicum.scb.LobbyManager;
import com.relicum.scb.SCB;
import com.relicum.scb.SignManager;
import com.relicum.scb.commands.CommandManager;
import com.relicum.scb.mini.SettingsManager2;
import com.relicum.scb.objects.inventory.InventoryManager;
import com.relicum.scb.utils.MessageManager;
import com.relicum.scb.we.WEManager;

/**
 * The type SkyBrosApi.
 */
public class SkyBrosApi {

    private static CommandManager commandManager;

    private static LobbyManager lobbyManager;

    private static SCB scb;

    private static SettingsManager2 settingsManager2;

    private static ArenaManager arenaManager;

    private static SignManager signManager;

    private static WEManager worldEditManager;

    private static MessageManager messageManager;

    private static InventoryManager inventoryManager;


    /**
     * Get command manager.
     *
     * @return the command manager
     */
    public static CommandManager getCommandManager() {
        if (commandManager == null)
            commandManager = new CommandManager(SCB.getInstance());
        return commandManager;
    }


    /**
     * Get lobby manager.
     *
     * @return the lobby manager
     */
    public static LobbyManager getLobbyManager() {
        if (lobbyManager == null)
            lobbyManager = new LobbyManager();
        return lobbyManager;
    }


    /**
     * Get settings manager.
     *
     * @return the settings manager
     */
    public static SettingsManager2 getSettingsManager2() {
        if (settingsManager2 == null)
            settingsManager2 = new SettingsManager2();
        return settingsManager2;
    }


    /**
     * Get arena manager.
     *
     * @return the arena manager
     */
    public static ArenaManager getArenaManager() {
        if (arenaManager == null)
            arenaManager = new ArenaManager();
        return arenaManager;
    }


    /**
     * Get sign manager.
     *
     * @return the sign manager
     */
    public static SignManager getSignManager() {
        if (signManager == null)
            signManager = new SignManager();
        return signManager;
    }


    /**
     * Get world edit manager.
     *
     * @return the world edit manager
     */
    public static WEManager getWorldEditManager() {
        if (worldEditManager == null)
            worldEditManager = new WEManager();
        return worldEditManager;
    }


    /**
     * Get message manager.
     *
     * @return the message manager
     */
    public static MessageManager getMessageManager() {
        if (messageManager == null)
            messageManager = new MessageManager();
        return messageManager;
    }


    /**
     * Gets inventory manager.
     *
     * @return the inventory manager
     */
    public static InventoryManager getInventoryManager() {
        if (inventoryManager == null)
            inventoryManager = new InventoryManager();
        return inventoryManager;
    }


    /**
     * Get main plugin.
     *
     * @return the SCB
     */
    public static SCB getSCB() {
        return scb;
    }


    /**
     * Init void.
     *
     * @param scb the scb
     */
    public static void init(SCB scb) {
        scb = scb;
    }

}
