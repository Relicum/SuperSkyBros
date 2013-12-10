package com.relicum.scb.hooks;

import com.relicum.scb.SCB;
import com.relicum.scb.types.SkyApi;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class VaultManager {

    private static Economy econ = null;

    public static Permission perms = null;

    private static Chat chat = null;

    public VaultManager() {

        setupPermissions();
        setupChat();
        setupEconomy();
    }


    /**
     * Gets econ.
     *
     * @return the econ
     */
    public static Economy getEcon() {
        return econ;
    }


    /**
     * Gets chat.
     *
     * @return the chat
     */
    public static Chat getChat() {
        return chat;
    }


    /**
     * Gets perms.
     *
     * @return the perms
     */
    public static Permission getPerms() {
        return perms;
    }


    private void setupEconomy() {

        RegisteredServiceProvider<Economy> rsp = SkyApi.getSCB().getServer().getServicesManager().getRegistration(
                net.milkbowl.vault.economy.Economy
                        .class);

        if (rsp != null) {
            econ = rsp.getProvider();
            SkyApi.getCMsg().INFO(SCB.SUCCESSFULLY_HOOKED_INTO_ECONOMY_PLUGIN);
        } else {
            SkyApi.getCMsg().WARNING(SCB.VAULT_COULD_NOT_HOOK_INTO_ECONOMY_PLUGIN);
        }

    }


    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = SkyApi.getSCB().getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
            chat = rsp.getProvider();
            SkyApi.getCMsg().INFO(SCB.SUCCESSFULLY_HOOKED_INTO_CHAT_PLUGIN);
        } else {
            SkyApi.getCMsg().WARNING(SCB.VAULT_COULD_NOT_HOOK_INTO_CHAT_PLUGIN);
        }


    }


    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = SkyApi.getSCB().getServer().getServicesManager().getRegistration(Permission.class);


        if (rsp != null) {
            perms = rsp.getProvider();
            SkyApi.getCMsg().INFO(SCB.SUCCESSFULLY_HOOKED_INTO_PERMISSIONS_PLUGIN);
        } else {
            SkyApi.getCMsg().WARNING(SCB.VAULT_COULD_NOT_HOOK_INTO_PERMISSIONS_PLUGIN);
        }

    }

}
