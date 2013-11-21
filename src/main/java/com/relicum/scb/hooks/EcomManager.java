package com.relicum.scb.hooks;

import net.milkbowl.vault.economy.Economy;

/**
 * SuperSkyBros
 * First Created 21/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class EcomManager {
    public static Economy getEconomy() {
        return VaultManager.getEcon();
    }

}
