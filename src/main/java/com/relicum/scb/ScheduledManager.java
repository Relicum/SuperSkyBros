package com.relicum.scb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * SuperSkyBros First Created 24/10/13 Used to Managed the Scheduled Thread Pool
 *
 * @author Relicum
 * @version 0.1
 */
public class ScheduledManager {

    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;
    static int SCHED_NUM_THREADS;
    static int LOGIN_NUM_THREADS;
    private static ScheduledExecutorService scheduler;
    private static ExecutorService loginExecutor = null;

    public ScheduledManager(int num) {

        SCHED_NUM_THREADS = num;
        scheduler = Executors.newScheduledThreadPool(SCHED_NUM_THREADS);

    }


    public static ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    public static boolean loginServiceForShutDown() {
        if (loginExecutor != null) {
            try {
                loginExecutor.shutdown();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (loginExecutor == null)
            return true;

        return false;
    }

    /**
     * Login ExecutorService.
     *
     * @param num the num of threads used 0 is cached service
     * @return the executor service Fixed or Cached
     */
    public static ExecutorService loginService(int num) {
        //if num = 0 we will use newCachedThreadPool


        if (ScheduledManager.loginExecutor == null) {
            if (num == 0) {
                loginExecutor = Executors.newCachedThreadPool();
            } else if (num >= 1) {
                LOGIN_NUM_THREADS = num;
                loginExecutor = Executors.newFixedThreadPool(LOGIN_NUM_THREADS);
            }
        }

        return loginExecutor;

    }


    /**
     * Gets scheduled threads.
     *
     * @return the scheduled threads the number of thread allocated to the timer
     */
    public static int getScheduledThreads() {
        return SCHED_NUM_THREADS;
    }

    /**
     * Gets login threads.
     *
     * @return the login threads number of threads allocated to login Service 0 is cached service
     */
    public static int getLoginThreads() {
        return LOGIN_NUM_THREADS;
    }

    private static void log(String aMsg) {
        System.out.println(aMsg);
    }
}
