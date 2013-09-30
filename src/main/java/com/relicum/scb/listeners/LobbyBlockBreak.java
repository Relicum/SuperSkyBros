package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

/**
 * The type LobbyBlockBreak.
 *
 * @author Relicum
 * @version 0.2
 */
public class LobbyBlockBreak implements Listener, Cancellable {

    /**
     * The Plugin.
     */
    public SCB plugin;

    public Vector min;

    public Vector max;

    public String world;


    /**
     * Instantiates a new Lobby block break.
     *
     * @param pl the pl
     */
    public LobbyBlockBreak(JavaPlugin pl) {
        this.plugin = (SCB) pl;
        this.min = plugin.LBS.getLobbyRegion().getMinVector();
        this.max = plugin.LBS.getLobbyRegion().getMaxVector();
        this.world = plugin.LBS.getLobbyRegion().getWorld().getName();

    }


    /**
     * Lobby break.
     *
     * @param BlockBreakEvent the event
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void lobbyBreak(BlockBreakEvent e) {


        Player player = e.getPlayer();
        String wo = player.getWorld().getName();

        if (!SCB.perms.has(player, "ssba.admins.breakblocks") && !player.isOp() && this.world.equals(wo)) {
            if (SCB.getInstance().LBS.getLobbyRegion().isAABB(e.getBlock().getLocation().toVector())) {
                e.setCancelled(true);
                player.sendMessage(SCB.MM.getErrorMessage("listeners.blockbreak.lobbyBreak"));
            }
        }

		/*String ha = this.generateHash(e.getBlock());
        if (SCB.getInstance().LBS.getHashList().contains(ha)) {
			if (!player.hasPermission("ssba.admin.breakblocks") && !player.isOp()) {
				e.setCancelled(true);
				player.sendMessage(SCB.MM.getErrorMessage("listeners.blockbreak.lobbyBreak"));
			}
		}*/


    }


    /**
     * Is cancelled.
     *
     * @return the boolean
     */
    @Override
    public boolean isCancelled() {
        return false;
    }


    /**
     * Sets cancelled.
     *
     * @param b the b
     */
    @Override
    public void setCancelled(boolean b) {

    }


    /**
     * Generate hash.
     *
     * @param block the block
     * @return the string
     */
    public String generateHash(Block block) {

        Integer mi = block.getChunk().getX();
        Integer ma = block.getChunk().getZ();
        Integer th = mi * ma;

        return Base64Coder.encodeString(th.toString() + block.getWorld().getName());
    }
}
