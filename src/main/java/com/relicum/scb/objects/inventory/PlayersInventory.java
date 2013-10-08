package com.relicum.scb.objects.inventory;

import com.relicum.scb.objects.inventory.interfaces.IArmour;
import com.relicum.scb.objects.inventory.interfaces.IInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * SuperSkyBros First Created 02/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayersInventory implements IInventory, IArmour {

    private PlayerInventory inventory;

    private ItemStack[] armour = new ItemStack[4];


    public PlayersInventory(Player player) {

        setPlayerInventory(player);

    }


    @Override
    public ItemStack[] getArmour() {
        return this.armour;
    }


    /**
     * Gets inventory.
     *
     * @return the inventory as an ItemStack
     */
    @Override
    public PlayerInventory getInventory() {

        return this.inventory;
    }


    private void setPlayerInventory(Player player) {

        this.inventory = player.getInventory();
        player.closeInventory();

    }


    private void setArmourInit() {
        this.armour = this.getInventory().getArmorContents();
    }
}
