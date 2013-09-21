package com.relicum.scb.channels;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * GameMessageListener Used to Listen to messages and send them only to the Players in the game the message came from.
 * First Created 12/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class GameMessageListener implements PluginMessageListener {

    /**
     * A method that will be thrown when a PluginMessageSource sends a plugin message on a registered channel.
     *
     * @param channel Channel that the message was sent through.
     * @param player  Source of the message.
     * @param message The raw message that was sent.
     */
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
