package com.relicum.scb.types;

import com.relicum.scb.*;
import com.relicum.scb.commands.CommandManager;
import com.relicum.scb.hooks.ChatManager;
import com.relicum.scb.hooks.PermsManager;
import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.SM;
import com.relicum.scb.objects.inventory.InventoryManager;
import com.relicum.scb.utils.MessageManager;
import com.relicum.scb.utils.PropertiesManager;
import com.relicum.scb.we.WEManager;

/**
 * The type SkyApi.
 */
public class SkyApi {

    private static SCB scb;

    private static class CommandManagerHolder {
        private static final CommandManager commandManager = new CommandManager();
    }

    /**
     * Get command manager.
     *
     * @return the command manager
     */
    public static CommandManager getCommandManager() {
        return CommandManagerHolder.commandManager;
    }


    private static class LobbyManagerHolder {
        private static final LobbyManager lobbyManager = new LobbyManager();
    }

    /**
     * Get lobby manager.
     *
     * @return the lobby manager
     */
    public static LobbyManager getLobbyManager() {
        return LobbyManagerHolder.lobbyManager;
    }


    private static class SMHolder {
        private static final SM sm = new SM();
    }

    /**
     * Get settings manager.
     *
     * @return the settings manager
     */
    public static SM getSm() {
        return SMHolder.sm;
    }


    private static class ArenaManagerHolder {
        private static final ArenaManager arenaManager = new ArenaManager();
    }

    /**
     * Get arena manager.
     *
     * @return the arena manager
     */
    public static ArenaManager getArenaManager() {
        return ArenaManagerHolder.arenaManager;
    }

    private static class cMsgHolder {
        private static final cMsg consoleColor = new cMsg(scb);
    }

    /**
     * Get the Colored Console Writer
     *
     * @return consoleColor the colored console manager
     */
    public static cMsg getCMsg() {
        return cMsgHolder.consoleColor;
    }

    private static class SignManagerHolder {
        private static final SignManager signManager = new SignManager();
    }

    /**
     * Get sign manager.
     *
     * @return the sign manager
     */
    public static SignManager getSignManager() {
        return SignManagerHolder.signManager;
    }


    private static class WEManagerHolder {
        private static final WEManager worldEditManager = new WEManager();
    }

    /**
     * Get world edit manager.
     *
     * @return the world edit manager
     */
    public static WEManager getWorldEditManager() {
        return WEManagerHolder.worldEditManager;
    }


    private static class MessageManagerHolder {
        private static final MessageManager messageManager = new MessageManager();
    }

    /**
     * Get message manager.
     *
     * @return the message manager
     */
    public static MessageManager getMessageManager() {
        return MessageManagerHolder.messageManager;
    }


    private static class InventoryManagerHolder {
        private static final InventoryManager inventoryManager = new InventoryManager();
    }

    /**
     * Gets inventory manager.
     *
     * @return the inventory manager
     */
    public static InventoryManager getInventoryManager() {
        return InventoryManagerHolder.inventoryManager;
    }


    private static class WorldManagerHolder {
        private static final WorldManager worldManager = new WorldManager();
    }

    /**
     * Get world manager.
     *
     * @return the world manager
     */
    public static WorldManager getWorldManager() {
        return WorldManagerHolder.worldManager;
    }


    private static class PermsManagerHolder {
        private static final PermsManager permsManager = new PermsManager();
    }

    /**
     * Get perms manager.
     *
     * @return the perms manager
     */
    public static PermsManager getPermsManager() {
        return PermsManagerHolder.permsManager;
    }


    private static class ChatManagerHolder {
        private static final ChatManager chatManager = new ChatManager();
    }

    /**
     * Get chat manager.
     *
     * @return the chat manager
     */
    public static ChatManager getChatManager() {
        return ChatManagerHolder.chatManager;
    }


    private static class PropertiesManagerHolder {
        private static final PropertiesManager propertiesManager = new PropertiesManager();
    }

    /**
     * Gets properties manager.
     *
     * @return the properties manager
     */
    public static PropertiesManager getPropertiesManager() {
        return PropertiesManagerHolder.propertiesManager;
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

}
