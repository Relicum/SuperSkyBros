package com.relicum.scb.utils;

import java.io.File;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class FileUtils {

    /**
     * Create directory a new directory at the specified path
     *
     * @param path the path
     * @param name the name
     */
    public static void createDirectory(String path, String name) {
        boolean f = new File(path + "/" + name).exists();
        if (!f) {
            boolean fi = new File(path + "/" + name).mkdirs();

            if (fi) System.out.println("New Directory created at " + path + "/" + name);
            else System.out.println("Error: Failed to directory at " + path + "/" + name);
        }
    }

    /**
     * Clear and delete a directory
     *
     * @param file the directory path as a File
     */
    public static void clear(File file) {
        if (!file.exists()) return;
        if (file.isFile()) {
            file.delete();
        } else {
            for (File f : file.listFiles())
                clear(f);
            file.delete();
        }
    }


}
