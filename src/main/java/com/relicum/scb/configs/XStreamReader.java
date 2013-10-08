package com.relicum.scb.configs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * SuperSkyBros First Created 03/10/13 Used to read from XStream serialized files
 *
 * @author Relicum
 * @version 0.1
 */
public class XStreamReader {

    XStreamReader() {

    }


    public static FileInputStream getFi(String filePath) {
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
            }
            catch ( IOException e ) {

                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    private static FileInputStream getFile(String filePath) {

        FileInputStream fi = null;
        try {
            fi = new FileInputStream(filePath);
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();

        }
        return fi;
    }

}
