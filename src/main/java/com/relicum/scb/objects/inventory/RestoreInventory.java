package com.relicum.scb.objects.inventory;

import com.relicum.scb.SCB;
import org.bukkit.entity.Player;

/**
 * SuperSkyBros First Created 03/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class RestoreInventory {

    public static Player restore(Player player) {

        ClearInventory.Clear(player);
        StorePlayerSettings store = SCB.getInstance().INV.getPlayersStore(player.getName());
        player.setExp(store.getXp());
        player.setTotalExperience(store.getTotalExperience());
        player.setLevel(store.getLevel());
        player.setAllowFlight(store.getAllowedFlight());
        player.setHealth(store.getPlayerHealth());
        player.setFoodLevel(store.getFoodLevel());
        player.setGameMode(store.getGameMode());
        player.setDisplayName(store.getDisplayName());
        player.setFlying(false);
        player.setFlySpeed(store.getFlySpeed());
        player.setWalkSpeed(store.getWalkSpeed());
        player.getInventory().setContents(store.getInv());
        player.getInventory().setArmorContents(store.getArmour());
        UpdateInv(player);

        return player;
    }


    public static void UpdateInv(final Player p) {

        SCB.getInstance().getServer().getScheduler().runTask(SCB.getInstance(), new Runnable() {

            @Override
            public void run() {
                p.updateInventory();

            }
        });
    }

}
