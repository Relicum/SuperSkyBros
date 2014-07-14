package com.relicum.scb.objects.inventory;

import com.relicum.scb.types.SkyApi;
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
        StorePlayerSettings store = SkyApi.getInventoryManager().getPlayersStore(player.getName());
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
        // TODO need to iterate through these to use materials
        player.getInventory().setContents(store.getInventory().getContents());
        player.getInventory().setArmorContents(store.getInventory().getArmour());

        UpdateInv(player);

        return player;
    }

    public static void UpdateInv(final Player p) {

        SkyApi.getSCB().getServer().getScheduler().runTask(SkyApi.getSCB(), new Runnable() {

            @Override
            public void run() {
                p.updateInventory();

            }
        });
    }

}
