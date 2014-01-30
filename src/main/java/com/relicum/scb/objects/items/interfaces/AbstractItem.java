/**
 * Name: AbstractItem.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.objects.items.interfaces;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AbstractItem implements ILore, IDisplayName {

    protected Material material;
    protected ItemMeta itemMeta;

    public AbstractItem(Material material) {
        setMaterial(material);
        setItemMeta(getMaterial());

    }

    /**
     * Gets display name.
     * 
     * @return the display name
     */
    @Override
    public String getDisplayName() {

        return getItemMeta().getDisplayName();

    }

    /**
     * Sets display name.
     * 
     * @param name the name
     */
    @Override
    public void setDisplayName(String name) {

        getItemMeta().setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

    }

    /**
     * Get the item as a single {@link org.bukkit.inventory.ItemStack}
     * 
     * @return the {@link org.bukkit.inventory.ItemStack}
     */
    public ItemStack asItemStack() {
        ItemStack itemStack = new ItemStack(getMaterial(), 1);
        itemStack.setItemMeta(getItemMeta());
        return itemStack;
    }

    /**
     * Get the item as a multi stack {@link org.bukkit.inventory.ItemStack}
     * 
     * @param num the number of items in the stack between 1-64
     * @return the {@link org.bukkit.inventory.ItemStack}
     */
    public ItemStack asItemStack(int num) {
        if (num > 64)
            num = 64;
        ItemStack itemStack = new ItemStack(getMaterial(), num);
        itemStack.setItemMeta(getItemMeta());
        return itemStack;
    }

    /**
     * Gets lore.
     * 
     * @return the lore
     */
    @Override
    public List<String> getLore() {
        return getItemMeta().getLore();
    }

    /**
     * Sets the lore for the item as a {@link java.util.List} of {@link String}
     * 
     * @param lore for the item passed as {@link java.util.List}
     */
    @Override
    public void setLore(List<String> lore) {

        this.getItemMeta().setLore(stripColor(lore));
    }

    /**
     * Gets the items {@link org.bukkit.inventory.meta.ItemMeta} connected to it
     * 
     * @return the items {@link org.bukkit.inventory.meta.ItemMeta}
     */
    @Override
    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    /**
     * Sets the items {@link org.bukkit.inventory.meta.ItemMeta}
     * 
     * @param material which is used to set the
     *        {@link org.bukkit.inventory.meta.ItemMeta}
     */
    @Override
    public void setItemMeta(Material material) {
        this.itemMeta = Bukkit.getItemFactory().getItemMeta(getMaterial());

    }

    /**
     * Gets material. The {@link org.bukkit.Material} the item is from
     * 
     * @return the material {@link org.bukkit.Material} of the item
     */
    @Override
    public Material getMaterial() {
        return this.material;
    }

    /**
     * Set the Items {@link org.bukkit.Material}
     * 
     * @param material the material of the item
     */
    @Override
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Strip color.
     * 
     * @param list the list
     * @return the list
     */
    private List<String> stripColor(List<String> list) {
        List<String> l = new ArrayList<>(list.size());

        for (String s : list) {
            l.add(ChatColor.translateAlternateColorCodes('&', s));

        }
        return l;
    }
}
