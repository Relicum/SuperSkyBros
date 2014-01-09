package com.relicum.scb.objects.inventory;

import lombok.ToString;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * SerializableInventory
 * First Created 27/12/13
 *
 * @author Relicum
 * @version 0.1
 */
@ToString
@SerializableAs(value = "SerializableInventory")
public class SerializableInventory implements ConfigurationSerializable {

    private final ItemStack[] armour;
    private final ItemStack[] contents;


    /**
     * Instantiates a new Serializable inventory.
     *
     * @param armour   the armour
     * @param contents the contents
     */
    public SerializableInventory(ItemStack[] armour, ItemStack[] contents) {
        this.armour = armour;
        this.contents = contents;
    }

    /**
     * Deserialize serializable contents.
     *
     * @param map the map
     * @return the serializable contents
     */
    @SuppressWarnings("ConstantConditions")
    public static SerializableInventory deserialize(Map<String, Object> map) {

        Object AObject = map.get("armour"),
                IObject = map.get("contents");

        if (AObject == null || IObject == null || !(AObject instanceof ItemStack) || !(IObject instanceof ItemStack)) {
            return null;
        }

        ItemStack[] a = (ItemStack[]) AObject;
        ItemStack[] i = (ItemStack[]) IObject;

        return new SerializableInventory(a, i);
    }

    /**
     * Creates a Map representation of this class.
     * <p/>
     * This class must provide a method to restore this class, as defined in
     * the {@link org.bukkit.configuration.serialization.ConfigurationSerializable} interface javadocs.
     *
     * @return Map containing the current state of this class
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("armour", this.armour);
        map.put("contents", this.contents);

        return map;
    }

    /**
     * Get armour.
     *
     * @return the itemstack[] of armour
     */
    public ItemStack[] getArmour() {
        return armour;
    }

    /**
     * Get contents.
     *
     * @return the itemstack[]
     */
    public ItemStack[] getContents() {
        return contents;
    }
}
