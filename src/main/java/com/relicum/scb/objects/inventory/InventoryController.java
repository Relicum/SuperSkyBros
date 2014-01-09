package com.relicum.scb.objects.inventory;

/**
 * The type Inventory controller.
 */
public class InventoryController {

    /**
     * The Mode the server is in
     */
    private boolean isMixed;

    /**
     * Instantiates a new Inventory controller.
     *
     * @param mode the mode
     */
    public InventoryController(boolean mode) {
        this.isMixed = mode;

    }

    /**
     * Is mixed mode if returns true. False if its dedicated mode
     *
     * @return the boolean
     */
    public boolean isMixed() {
        return isMixed;
    }

    /**
     * Sets mixed.
     *
     * @param isMixed the is mixed
     */
    public void setMixed(boolean isMixed) {
        this.isMixed = isMixed;
    }
}
