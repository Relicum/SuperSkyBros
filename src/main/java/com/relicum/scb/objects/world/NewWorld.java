package com.relicum.scb.objects.world;

/**
 * SuperSkyBros First Created 29/10/13 This class is used to create new worlds They then get save as there own object.
 *
 * @author Relicum
 * @version 0.1
 */
public class NewWorld {


    private worldSettings ws;


    private String filePath;


    // public NewWorld(String filePath) {
    //    this.filePath = filePath;
    //    this.ws = new worldSettings();

    // }


    public void load() {
        this.ws = XStreamReader.load(this.filePath);
        System.out.println("worldsettings loaded");
    }


    public void save() {
        if (XStreamWriter.save(this.filePath, this.ws)) System.out.println("Worldsettings file saved successfully");
        else System.out.println("There was an error saving worldsettings");
    }


    public worldSettings getWs() {
        return this.ws;
    }

}
