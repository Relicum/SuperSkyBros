package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.events.NoFallTPDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.IOException;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class arenatp extends SubBase {
	/**
	 * @param player Player
	 * @param args   String[]
	 * @return boolean
	 */
	@Override
	public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {
		Integer id = null;
		Location tpto = null;
		Chunk ch = null;
		try {
			Integer s = SCB.getInstance().ARM.total;
			id = Integer.parseInt(args[0]);
			tpto = SCB.getInstance().ARM.getArenaById(id).getAreg().getadminSpawn();
			ch = tpto.getChunk();
			player.sendMessage(SCB.getMessageManager().getAdminMessage("command.message.arenatpLoading"));
			if (!ch.load()) {
				player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.arenatpLoadingFail"));
			} else {

				teleportToLobby(player, tpto);
				Bukkit.getServer().getPluginManager().callEvent(new NoFallTPDamageEvent(player, EntityDamageEvent.DamageCause.FALL, 0.10D));
			}
		} catch (Exception e) {
			String me = SCB.getMessageManager().getErrorMessage("command.message.arenatpDoesNotExist");
			String tmp = me.replace("%ID%", args[0]);
			player.sendMessage(tmp);
			e.printStackTrace();

		}


		return true;
	}

	/**
	 * Teleports the player to the given location
	 *
	 * @param p Player
	 * @param l Location
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	public boolean teleportToLobby(final Player p, final Location l) {

		SCB.getInstance().getServer().getScheduler().runTaskLater(SCB.getInstance(), new Runnable() {
			@Override
			public void run() {
				Bukkit.getServer().getPluginManager().callEvent(new NoFallTPDamageEvent(p, EntityDamageEvent.DamageCause.FALL, 0.10D));
				if (!p.teleport(l)) {
					System.out.println("Error teleporting player to lobby");
				}
			}
		}, 10L);

		return true;
	}


	/**
	 * Simplify set this function to set the field mNode with the commands description will come from in the messages.yml
	 * file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
	 * command.description.createarena you only need to set this to createarena
	 */
	@Override
	public void setmDescription() {
		mNode = "arenatp";
	}

	/**
	 * Simply set this to return the the number of arguments The command should receive
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
		return "ssba.admin.arenatp";
	}

	/**
	 * Simply set this to return the clist Usage
	 *
	 * @return String
	 */
	@Override
	public String setUsage() {
		return "/ssba arenatp [id]";
	}

	/**
	 * Set this to the label of the command
	 *
	 * @return String
	 */
	@Override
	public String setLabel() {
		return "ssba arenatp";
	}

	/**
	 * Set com
	 *
	 * @return String
	 */
	@Override
	public String setCmd() {
		return "ssba arenatp";
	}
}
