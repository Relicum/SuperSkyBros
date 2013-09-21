package com.relicum.scb.classes.utils;

import com.relicum.scb.SCB;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class Armour extends IClassArmour {

    public ItemStack[] itms = new ItemStack[4];

    private Color col = null;

    private ArmourEnum aenum = null;


    public Armour() {


    }


    private ItemStack processArmour(Material m) {

        return new ItemStack(m);

    }


    public ItemStack getArmourItem(ArmourEnum em, ArmourType ty) {

        ItemStack it = new ItemStack(buildMaterialString(em, ty));
        if (this.col != null) {

            LeatherArmorMeta itm = (LeatherArmorMeta) it.getItemMeta();
            itm.setColor(this.col);
            it.setItemMeta(itm);

        }
        Integer l = this.itms.length;
        if (l == null) {
            this.itms[1 + 1] = it;
        } else {
            this.itms[0] = it;
        }
        return it;
    }


    @Override

    public void setArmourColor(Color c) {

        if (c != null) {
            this.col = c;
        }
    }


    @Override
    public void setArmourMaterial(ArmourEnum mat) {
        if (mat != null) {
            this.aenum = mat;
        }
    }


    @Override
    public ItemStack[] getAllArmour(ArmourEnum em) {

        ItemStack[] itss = new ItemStack[4];
        try {

            itss[0] = getHelmet(em);
            itss[1] = getChestPlate(em);
            itss[2] = getLeggings(em);
            itss[3] = getBoots(em);
            return itss;
        }
        catch ( NullPointerException e ) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        catch ( Exception e ) {
            SCB.getInstance().getLogger().severe("You can only color leather armour");
        }

        return itss;


    }


    @Override
    public ItemStack getHelmet(ArmourEnum em) {

        return getArmourItem(em, ArmourType.HELMET);
    }


    @Override
    public ItemStack getChestPlate(ArmourEnum em) {
        return getArmourItem(em, ArmourType.CHESTPLATE);
    }


    @Override
    public ItemStack getLeggings(ArmourEnum em) {

        return getArmourItem(em, ArmourType.LEGGINGS);
    }


    @Override
    public ItemStack getBoots(ArmourEnum em) {

        return getArmourItem(em, ArmourType.BOOTS);
    }


    @Override
    public ItemStack getSetArmor(Integer i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public Material buildMaterialString(ArmourEnum em, ArmourType ty) {

        String mat = em.name().toUpperCase();
        String typ = ty.name().toUpperCase();
        String matt = mat + "_" + typ;
        return Material.valueOf(matt);
    }
}
