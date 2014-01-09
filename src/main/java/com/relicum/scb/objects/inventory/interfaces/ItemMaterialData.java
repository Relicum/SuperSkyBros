package com.relicum.scb.objects.inventory.interfaces;

/**
 * Data Model of Material Data
 * This is required for saving inventories
 * As XStream will take the ItemStack which includes Magic ID's
 * First Created 19/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ItemMaterialData {

    private static ItemMaterialData instance;

    private String material;
    private Integer amount;
    private Integer meta;
    private Integer slot;


    public ItemMaterialData() {
    }

    public static ItemMaterialData getInstance() {
        return new ItemMaterialData();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }
}
