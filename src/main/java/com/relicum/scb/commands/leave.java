package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import org.bukkit.entity.Player;

/**
 * SuperSkyBros
 *
 * @author Relicum
 * @version 0.1
 */
public class leave extends SubBase {
	/**
	 * @param player Player
	 * @param args   String[]
	 * @return boolean
	 */
	@Override
	public boolean onCommand(Player player, String[] args) {

		if (SCB.getInstance().LBS.isInLobby(player.getName())) {
			if (SCB.getInstance().getConfig().getBoolean("dedicatedSSB")) {
				player.sendMessage("Can not remove you from lobby no where to go");
				return true;
			}
			SCB.getInstance().LBS.removePlayer(player.getName());
			player.teleport(SCB.getInstance().LBS.getLobbyRegion().getWorld().getSpawnLocation());
			player.sendMessage("Removed from lobby and Teleported to World Spawn in Lobby World");
		} else {

			player.sendMessage("Can not leave, you are not in SSB game or Lobby to start with");
		}

		return true;
	}

	/**
	 * Simplify set this function to set the field mNode with the commands description will come from in the messages.yml
	 * file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
	 * command.description.createarena you only need to set this to createarena
	 */
	@Override
	public void setmDescription() {
		mNode = "leave";
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
		return "ssb.player.leave";
	}

	/**
	 * Simply set this to return the clist Usage
	 *
	 * @return String
	 */
	@Override
	public String setUsage() {
		return "/ssb leave";
	}

	/**
	 * Set this to the label of the command
	 *
	 * @return String
	 */
	@Override
	public String setLabel() {
		return "ssb leave";
	}

	/**
	 * Set com
	 *
	 * @return String
	 */
	@Override
	public String setCmd() {
		return "ssb leave";
	}
}
