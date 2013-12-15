package com.relicum.scb.types;

import com.relicum.scb.*;
import com.relicum.scb.commands.CommandManager;
import com.relicum.scb.hooks.ChatManager;
import com.relicum.scb.hooks.PermsManager;
import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.objects.inventory.InventoryManager;
import com.relicum.scb.utils.MessageManager;
import com.relicum.scb.utils.PropertiesManager;
import com.relicum.scb.we.WEManager;


/**
 * Central Class to access all managers without have multiple additions
 * of the same class being created. Is class uses the singleton patten to achieve this.
 *
 * @author Relicum
 * @version 0.3
 */
public class SkyApi {

    private static CommandManager commandManager = null;
    private static LobbyManager lobbyManager = null;
    private static SM sm = null;
    private static ArenaManager arenaManager = null;
    private static cMsg cmsg = null;
    private static InventoryManager inventoryManager = null;
    private static WorldManager worldManager = null;
    private static WEManager weManager = null;
    private static VaultManager vaultManager = null;
    private static SignManager signManager = null;
    private static PropertiesManager propertiesManager = null;
    private static ChatManager chatManager = null;
    private static MessageManager messageManager = null;
    private static PermsManager permsManager = null;
    private static SCB scb;


    /**
     * Get command manager.
     *
     * @return the command manager
     */
    public static CommandManager getCommandManager() {
        if (SkyApi.commandManager == null) {
            commandManager = new CommandManager();
        }
        return SkyApi.commandManager;
    }


    /**
     * Get lobby manager.
     *
     * @return the lobby manager
     */
    public static LobbyManager getLobbyManager() {
        if (SkyApi.lobbyManager == null) {
            lobbyManager = new LobbyManager();
        }
        return SkyApi.lobbyManager;
    }

    /**
     * Get settings manager.
     *
     * @return the settings manager
     */
    public static SM getSm() {
        if (SkyApi.sm == null) {
            sm = new SM();
        }
        return sm;
    }

    /**
     * Get arena manager.
     *
     * @return the arena manager
     */
    public static ArenaManager getArenaManager() {
        if (SkyApi.arenaManager == null) {
            arenaManager = new ArenaManager();
        }

        return SkyApi.arenaManager;
    }

    /**
     * Get the Colored Console Writer
     *
     * @return consoleColor the colored console manager
     */
    public static cMsg getCMsg() {
        if (SkyApi.cmsg == null) {
            cmsg = new cMsg();
        }
        return cmsg;
    }


    /**
     * Get sign manager.
     *
     * @return the sign manager
     */
    public static SignManager getSignManager() {
        if (SkyApi.signManager == null) {
            signManager = new SignManager();
        }
        return SkyApi.signManager;
    }


    /**
     * Get world edit manager.
     *
     * @return the world edit manager
     */
    public static WEManager getWorldEditManager() {
        if (SkyApi.weManager == null) {
            weManager = new WEManager();
        }
        return SkyApi.weManager;
    }


    /**
     * Get message manager.
     *
     * @return the message manager
     */
    public static MessageManager getMessageManager() {
        if (SkyApi.messageManager == null) {
            messageManager = new MessageManager();
        }
        return SkyApi.messageManager;

    }


    /**
     * Gets inventory manager.
     *
     * @return the inventory manager
     */
    public static InventoryManager getInventoryManager() {
        if (SkyApi.inventoryManager == null) {
            inventoryManager = new InventoryManager();
        }
        return SkyApi.inventoryManager;
    }

    /**
     * Get world manager.
     *
     * @return the world manager
     */
    public static WorldManager getWorldManager() {

        if (SkyApi.worldManager == null) {
            worldManager = new WorldManager();
        }
        return SkyApi.worldManager;
    }


    /**
     * Get perms manager.
     *
     * @return the perms manager
     */
    public static PermsManager getPermsManager() {
        if (SkyApi.permsManager == null) {
            permsManager = new PermsManager();
        }
        return SkyApi.permsManager;
    }


    /**
     * Get chat manager.
     *
     * @return the chat manager
     */
    public static ChatManager getChatManager() {
        if (SkyApi.chatManager == null) {
            chatManager = new ChatManager();
        }
        return SkyApi.chatManager;
    }


    /**
     * Gets properties manager.
     *
     * @return the properties manager
     */
    public static PropertiesManager getPropertiesManager() {
        if (SkyApi.propertiesManager == null) {
            propertiesManager = new PropertiesManager();
        }
        return SkyApi.propertiesManager;
    }

    private static class VaultManagerHolder {
        private static final VaultManager vaultManager = new VaultManager();
    }

    /**
     * Gets vault manager.
     *
     * @return the vault manager
     */
    public static VaultManager getVaultManager() {
        return VaultManagerHolder.vaultManager;
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
    public static void init(SCB sCb) {
        scb = sCb;


    }

    public static void loadManagers() {
        SkyApi.getMessageManager();
        SkyApi.getArenaManager();
        SkyApi.getVaultManager();
        SkyApi.getChatManager();
        SkyApi.getPermsManager();
        SkyApi.getCMsg();
        SkyApi.getCommandManager();
        SkyApi.getLobbyManager();
        SkyApi.getSignManager();
        SkyApi.getWorldEditManager();
        SkyApi.getPropertiesManager();
        SkyApi.getInventoryManager();
    }

}
