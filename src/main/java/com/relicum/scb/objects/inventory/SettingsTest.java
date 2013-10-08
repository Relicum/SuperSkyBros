package com.relicum.scb.objects.inventory;

import com.thoughtworks.xstream.XStream;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * SuperSkyBros First Created 02/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SettingsTest {

    public static Settings getsettings() {

        Settings settings = new Settings();
        settings.setPlayerName("Relicum");
        settings.setAllowedFlight(true);
        settings.setPlayerDamage(20.0);
        settings.setPlayerHealth(15);
        settings.setTotalExperience(5478);
        settings.setAllowedFlight(false);
        settings.setInv();
        settings.setIt(new ItemStack(Material.APPLE));
        settings.setMyl("Hide and Seek");
        settings.setMyl("Spleef");
        settings.setMyl("SuperSkyBros");
        return settings;
    }
}
