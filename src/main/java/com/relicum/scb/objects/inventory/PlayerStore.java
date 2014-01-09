package com.relicum.scb.objects.inventory;

import org.bukkit.entity.Player;


/**
 * SuperSkyBros First Created 03/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerStore {

    public PlayerStore() {


    }


    public static StorePlayerSettings storePlayer(Player player) {
        StorePlayerSettings ps = new StorePlayerSettings();
        ps.setInventory(new SerializableInventory(player.getInventory().getContents(), player.getInventory().getArmorContents()));
        ps.setAllowedFlight(player.getAllowFlight());
        ps.setPlayerHealth(player.getHealth());
        ps.setPlayerDamage(player.getExhaustion());
        ps.setTotalExperience(player.getTotalExperience());
        ps.setFoodLevel(player.getFoodLevel());
        ps.setPlayerName(player.getName());
        ps.setGameMode(player.getGameMode());
        ps.setXp(player.getExp());
        ps.setLevel(player.getLevel());
        ps.setDisplayName(player.getDisplayName());
        ps.setFlySpeed(player.getFlySpeed());
        ps.setWalkSpeed(player.getWalkSpeed());

        return ps;

    }

}
