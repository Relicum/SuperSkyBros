package com.relicum.scb.objects.inventory;

import com.relicum.scb.objects.inventory.interfaces.IArmour;
import com.relicum.scb.objects.inventory.interfaces.IInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * SuperSkyBros First Created 02/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayersInventory implements IInventory, IArmour {

    private ItemStack[] inventory = new ItemStack[36];

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
    public ItemStack[] getInventory() {

        return this.inventory;
    }


    private void setPlayerInventory(Player player) {

        this.inventory = player.getInventory().getContents();
        this.setArmourInit(player.getInventory().getArmorContents());
        player.closeInventory();

    }


    private void setArmourInit(ItemStack[] armour) {
        this.armour = armour;
    }
}
