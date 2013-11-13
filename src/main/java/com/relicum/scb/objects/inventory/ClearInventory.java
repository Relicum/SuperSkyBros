package com.relicum.scb.objects.inventory;

import com.relicum.scb.SCB;
import com.relicum.scb.objects.signs.utils.Col;
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
 * SuperSkyBros First Created 03/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ClearInventory {

    public static Player Clear(Player player) {

        player.setFlying(false);
        player.setAllowFlight(false);
        player.setMaximumNoDamageTicks(0);
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        player.setFireTicks(0);
        player.setExp(0.0f);
        player.setLevel(0);
        player.setFlySpeed(0.1f);
        player.setWalkSpeed(0.2f);
        player.setDisplayName(player.getName());
        Collection<PotionEffect> po = player.getActivePotionEffects();
        for ( PotionEffect potionEffect : po ) {
            player.removePotionEffect(potionEffect.getType());
        }
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[4]);
        UpdateInv(player);


        return player;
    }


    public static Player applyLobbyInv(Player player) {


        ClearInventory.Clear(player);
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
        player.setMaximumNoDamageTicks(12000);
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setAllowFlight(true);
        player.setLevel(0);
        player.setExp(0.0F);
        player.getInventory().setItem(0, bo);
        player.getInventory().setItem(8, em);
        if (player.getName().equalsIgnoreCase("Relicum")) {
            player.setDisplayName(Col.Dark_Purple() + "Relicum" + Col.Reset());
            player.setPlayerListName(Col.Dark_Purple() + "Relicum" + Col.Reset());
        } else {

            String pn = player.getName();
            int l = pn.length();
            String spn;
            if (l > 14) {
                spn = pn.substring(0, 13);

            } else {
                spn = pn;
            }
            player.setPlayerListName(Col.Grey() + spn + Col.Reset());
            player.setDisplayName(Col.Grey() + spn + Col.Reset());
        }
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
