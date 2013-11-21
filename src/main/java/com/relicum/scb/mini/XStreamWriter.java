package com.relicum.scb.mini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * SuperSkyBros First Created 14/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class XStreamWriter {

    public XStreamWriter() {

    }


    public static FileOutputStream getFos(String filePath) {
        preChecks(filePath);
        return getFile(filePath);

    }


    private static boolean preChecks(String filePath) {

        File f = new File(filePath);

        if (!f.exists()) {
            try {
                if (!f.createNewFile()) {
                    System.out.println("Error creating file " + filePath);
                    return false;
                }
                if (!f.canWrite()) {
                    System.out.println("Error file is not writable" + filePath);
                    return false;
                }
            } catch (IOException e) {

                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    private static FileOutputStream getFile(String filePath) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return fos;
    }
}
