package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class LobbyBlockPlace implements Listener, Cancellable {

	public SCB plugin;
	public Vector min;
	public Vector max;
	public String world;

	public LobbyBlockPlace(JavaPlugin pl) {
		this.plugin = (SCB) pl;
		this.min = plugin.LBS.getLobbyRegion().getMinVector();
		this.max = plugin.LBS.getLobbyRegion().getMaxVector();
		this.world = plugin.LBS.getLobbyRegion().getWorld().getName();
	}

	public LobbyBlockPlace() {
		//To change body of created methods use File | Settings | File Templates.
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void lobbyPlace(BlockPlaceEvent e) {

		Player player = e.getPlayer();
		String wo = player.getWorld().getName();

		//SCB.getInstance().LBS.getLobbyRegion().isAABB(player.getLocation().toVector());
		if (!player.hasPermission("ssba.admin.breakblocks") && !player.isOp() && this.world.equals(wo)) {
			if (SCB.getInstance().LBS.getLobbyRegion().isAABB(e.getBlock().getLocation().toVector())) {
				e.setCancelled(true);
				player.sendMessage(SCB.MM.getErrorMessage("listeners.blockplace.lobbyPlace"));


			}
		}
	/*	String ha = this.generateHash(e.getBlock());

		if (SCB.getInstance().LBS.getHashList().contains(ha)) {
			if (!player.hasPermission("ssba.admin.placeblocks") && !player.isOp()) {
				e.setCancelled(true);
				player.sendMessage(SCB.MM.getErrorMessage("listeners.blockplace.lobbyPlace"));
			}
		}*/


	}


	public String generateHash(Block block) {

		Integer mi = block.getChunk().getX();
		Integer ma = block.getChunk().getZ();
		Integer th = mi * ma;

		return Base64Coder.encodeString(th.toString() + block.getWorld().getName());
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public void setCancelled(boolean b) {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
