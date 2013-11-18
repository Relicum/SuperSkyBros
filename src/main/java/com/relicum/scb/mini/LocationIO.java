package com.relicum.scb.mini;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * SuperSkyBros First Created 14/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class LocationIO {

    private static final int NUM_THREADS = 2;

    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    private static LocationIO instance = null;

    private static ExecutorService executor = null;


    private LocationIO() {

    }


    public static LocationIO getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new LocationIO();
            startExecutors();
            return instance;

        }
    }


    public static void startExecutors() {
        if (executor == null) {
            executor = Executors.newFixedThreadPool(NUM_THREADS);
        }
    }


    public static void saveSigns(List<SerializedLocation> locations, String path) {

        Future future = executor.submit(new signSave(path, locations));


    }


    public static void fullyShutdown() {

        executor.shutdown();
        System.out.println("Locations thread pool is been set to shutdown after all tasks have completed");
        if (executor.isShutdown())
            System.out.println("Locations thread pool successfully shutdown");
        executor = null;
    }


    /**
     * The Save all sign locations.
     */
    private static final class signSave implements Runnable {

        final String path;

        final List<SerializedLocation> serializedLocation;


        signSave(final String path, final List<SerializedLocation> serializedLocation) {
            this.serializedLocation = serializedLocation;
            this.path = path;
        }


        @Override
        public void run() {
            XStream xStream = new XStream(new Xpp3Driver());
            xStream.alias("location", SerializedLocation.class);

            FileOutputStream fs = XStreamWriter.getFos(path);
            ObjectOutputStream out = null;
            try {
                out = xStream.createObjectOutputStream(fs);
                int tot = serializedLocation.size();
                for ( int i = 0; i < tot; i++ ) {
                    out.writeObject(serializedLocation.get(i));
                    out.flush();
                    out.close();
                    fs.close();
                }
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }

        }
    }

}
