package com.relicum.scb;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * SuperSkyBros First Created 24/10/13 Used to Managed the Scheduled Thread Pool
 *
 * @author Relicum
 * @version 0.1
 */
public class ScheduledManager {

    static int NUM_THREADS;

    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    private static ScheduledExecutorService scheduler;


    public ScheduledManager(int num) {

        NUM_THREADS = num;
        scheduler = Executors.newScheduledThreadPool(NUM_THREADS);

    }


    public static ScheduledExecutorService getScheduler() {
        return scheduler;
    }


    private static void log(String aMsg) {
        System.out.println(aMsg);
    }
}
