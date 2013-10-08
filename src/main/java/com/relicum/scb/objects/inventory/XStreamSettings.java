package com.relicum.scb.objects.inventory;

import com.relicum.scb.SCB;
import com.relicum.scb.configs.XStreamReader;
import com.relicum.scb.configs.XStreamWriter;
import com.thoughtworks.xstream.XStream;
import org.bukkit.Material;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.SimplePluginManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * SuperSkyBros First Created 02/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class XStreamSettings {

    public XStream xstream;


    public XStreamSettings() {

  /*      String path =SCB.getInstance().getDataFolder() + "/players/joe.xml";
        FileOutputStream fo = XStreamWriter.getFos(path);
        FileInputStream fi = XStreamReader.getFi(path);

        XStream xstream = new XStream();
        xstream.processAnnotations(SimplePluginManager.class);
        SimplePluginManager joe = new SimplePluginManager(SCB.getInstance().getServer(), new SimpleCommandMap(SCB.getInstance().getServer()));

        xstream.toXML(joe, fo);
        String xml = xstream.toXML(joe);
        System.out.println(xml);*/
/*
        Settings newJoe = (Settings)xstream.fromXML(fi);

        newJoe.setPlayerName("John");
        newJoe.getIt().setDurability((short) 3);
        ItemMeta im = newJoe.getIt().getItemMeta();
        List<String> lo = new ArrayList<>();
        lo.add("New Lore");
        lo.add("Second Lore");
        im.setLore(lo);
        newJoe.getIt().setItemMeta(im);
        xstream.toXML(newJoe,fo);
        String xml2= xstream.toXML(newJoe);
        System.out.println(xml2);

        System.out.println(newJoe.getMyl().get(1));*/


    }


}
