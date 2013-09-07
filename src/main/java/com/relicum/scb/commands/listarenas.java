package com.relicum.scb.commands;

import com.relicum.scb.ArenaManager;
import com.relicum.scb.SCB;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class listarenas extends SubBase {
	/**
	 * @param player Player
	 * @param args   String[]
	 * @return boolean
	 */
	@Override
	public boolean onCommand(Player player, String[] args) {

		ArenaManager aman = SCB.getInstance().ARM;
		ArrayList<String> arl = aman.getListMessages();

		for (String m : arl) {
			player.sendMessage(m);
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
		mNode = "listarenas";
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
		return "ssba.admin.listarenas";
	}

	/**
	 * Simply set this to return the clist Usage
	 *
	 * @return String
	 */
	@Override
	public String setUsage() {
		return "/ssba listarenas";
	}

	/**
	 * Set this to the label of the command
	 *
	 * @return String
	 */
	@Override
	public String setLabel() {
		return "ssba listarenas";
	}

	/**
	 * Set com
	 *
	 * @return String
	 */
	@Override
	public String setCmd() {
		return "ssba listarenas";
	}
}
