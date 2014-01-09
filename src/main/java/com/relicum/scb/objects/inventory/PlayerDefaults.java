package com.relicum.scb.objects.inventory;

import com.relicum.scb.objects.signs.utils.Col;
import com.relicum.scb.types.SkyApi;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * The type Player defaults.
 */
public class PlayerDefaults {

    /**
     * Apply default settings. Including removing potion effects
     *
     * @param player the player
     * @return the player
     */
    public static Player applyDefaultSettings(Player player) {

        player.setFlying(false);
        player.setAllowFlight(false);
        player.setMaximumNoDamageTicks(0);
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.ADVENTURE);
        player.setFireTicks(0);
        player.setExp(0.0f);
        player.setLevel(0);
        player.setFlySpeed(0.1f);
        player.setWalkSpeed(0.2f);
        player.setExhaustion(0.0f);
        player.setSaturation(5.0f);

        Collection<PotionEffect> po = player.getActivePotionEffects();
        for (PotionEffect potionEffect : po) {
            player.removePotionEffect(potionEffect.getType());
        }

        return player;
    }

    /**
     * Clears a players inventory
     *
     * @param player the player
     * @return the player
     */
    @SuppressWarnings("deprecation")
    public static Player clearInventory(final Player player) {

        player.getInventory().setArmorContents(new ItemStack[4]);
        player.getInventory().setContents(new ItemStack[36]);
        player.closeInventory();

        Bukkit.getServer().getScheduler().runTask(SkyApi.getSCB(), new Runnable() {
            @Override
            public void run() {
                player.updateInventory();
            }
        });

        return player;
    }

    /**
     * Apply lobby inventory.
     *
     * @param player the player
     */
    @SuppressWarnings("deprecation")
    public static void applyLobbyInventory(final Player player) {

        ItemStack[] contents = new ItemStack[36];
        ItemStack[] armor = new ItemStack[4];

        ItemStack bo = new ItemStack(Material.WRITTEN_BOOK);

        BookMeta im = (BookMeta) bo.getItemMeta();
        im.setDisplayName(Col.Green() + "Welcome To Super Sky Bros");
        im.setAuthor(Col.Gold() + "Relicum");
        im.setTitle(Col.Aqua() + "Super Sky Bros");
        List<String> page = new ArrayList<>();
        page.add(Col.Blue() + "Welcome to SuperSkyBros");
        page.add(Col.Blue() + "Please read for more details");
        String[] strpage = new String[page.size()];
        page.toArray(strpage);
        im.addPage(page.toArray(strpage));
        bo.setItemMeta(im);

        ItemStack em = new ItemStack(Material.EMERALD);
        ItemMeta iem = em.getItemMeta();
        iem.setDisplayName(Col.Green() + "Class Shop");
        List<String> lo = new ArrayList<>();
        lo.add(Col.Light_Purple() + "Not Currently Open");
        lo.add(Col.Light_Purple() + "Hope to be open soon");
        iem.setLore(lo);
        em.setItemMeta(iem);

        contents[0] = bo;
        contents[8] = em;

        player.getInventory().setContents(contents);
        player.getInventory().setArmorContents(armor);

        player.closeInventory();

        Bukkit.getServer().getScheduler().runTask(SkyApi.getSCB(), new Runnable() {

            @Override
            public void run() {
                player.updateInventory();
            }
        });


    }
}
