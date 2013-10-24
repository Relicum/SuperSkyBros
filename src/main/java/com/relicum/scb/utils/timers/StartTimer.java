package com.relicum.scb.utils.timers;

import com.relicum.scb.ScheduledManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * SuperSkyBros First Created 24/10/13 Start Countdown. Multiple Instances can be run at the same time Uses a
 * ScheduledThreadPool Service
 *
 * @author Relicum
 * @version 0.1
 */
public class StartTimer {

    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    private final long fInitialDelay;

    private final long fDelayPeriod;

    private final long fShutDownAfter;

    private static ScheduledExecutorService scheduler = ScheduledManager.getScheduler();

    public Integer timeleft;


    public StartTimer(long initial, long period, Integer tl) {
        System.out.println("Current Thread is: " + Thread.currentThread().getName());
        fInitialDelay = initial;
        fDelayPeriod = period;
        timeleft = tl;
        fShutDownAfter = tl.longValue();
        StartTimerAndStop();
    }


    private static void log(String aMsg) {
        System.out.println(aMsg);
    }


    StartTimer(long initial, long period, Integer tl, String d) {
        System.out.println("Current Thread is: " + Thread.currentThread().getName());
        fInitialDelay = initial;
        fDelayPeriod = period;
        timeleft = tl;
        fShutDownAfter = tl.longValue();
        StartTimerAndStop();
    }


    /**
     * Make timer. Used to create a new instance of Start Game Countdown
     *
     * @param initial the initial delay before starting - seconds
     * @param period  the period between executions - seconds
     * @param tl      the max time the timer will run before being shutdown - seconds
     */
    public static void makeTimer(long initial, long period, Integer tl) {
        new StartTimer(initial, period, tl);
    }


    /**
     * Basically Initialise the object
     */
    void StartTimerAndStop() {

        Runnable GameStartTask = new StartGameTimerTask((int) fShutDownAfter);
        ScheduledFuture<?> StartGameFuture = scheduler.scheduleAtFixedRate(GameStartTask, fInitialDelay, fDelayPeriod, TimeUnit.SECONDS);
        Runnable stopStartGameTask = new StopGameStartTask(StartGameFuture);
        scheduler.schedule(stopStartGameTask, fShutDownAfter, TimeUnit.SECONDS);
    }


    /**
     * The actual Task for the Game Timer
     */
    private static final class StartGameTimerTask implements Runnable {

        private Integer timeleft;


        StartGameTimerTask(int i) {
            timeleft = i;


        }


        @Override
        public void run() {


            --timeleft;

            Bukkit.broadcastMessage(ChatColor.GREEN + "Time till game is " + timeleft);
            //MCBouncer.sendMessage("Relicum");
            if (this.timeleft.equals(30)) {
                System.out.println("The game is about to start in " + this.timeleft + " Seconds");
            } else if (this.timeleft.equals(25)) {
                System.out.println("The game will start in " + this.timeleft + " seconds");
            } else if (this.timeleft.equals(20)) {
                System.out.println("The game will start in " + this.timeleft + " seconds");
            } else if (this.timeleft.equals(15)) {
                System.out.println("The game will start in " + this.timeleft + " seconds");
            } else if (this.timeleft.equals(10)) {
                System.out.println("The game will start in " + this.timeleft + " seconds");
            } else if (timeleft < 10 && timeleft > 0) {
                System.out.println("Get Ready " + this.timeleft);
            } else if (timeleft == 0) {
                System.out.println("GO GO GO Game Started");
                Bukkit.broadcastMessage(ChatColor.GREEN + "GO GO GO Game Started");
            }


        }
    }


    /**
     * The Task for the future eg ending of the task.
     */
    private final class StopGameStartTask implements Runnable {

        StopGameStartTask(ScheduledFuture<?> aSchedFeature) {

            fScheduledFuture = aSchedFeature;

        }


        @Override
        public void run() {
            log("Stopping the Game Start Timer");

            fScheduledFuture.cancel(DONT_INTERRUPT_IF_RUNNING);

            /**
             Note that this Task also performs cleanup, by asking the
             scheduler to shutdown gracefully. How sweet lol
             */

            //scheduler.shutdown();
        }


        private ScheduledFuture<?> fScheduledFuture;
    }
}
