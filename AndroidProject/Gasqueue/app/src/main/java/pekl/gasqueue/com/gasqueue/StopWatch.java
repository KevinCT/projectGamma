package pekl.gasqueue.com.gasqueue;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class StopWatch {
    private static int interval;
    static Timer timer;
    private static int currentTime = 60;

    public StopWatch(){

    }

    public void runTimer() {
        interval = 60;
        Scanner sc = new Scanner(System.in);
        int delay = 1000;
        int period = 1000;
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {currentTime = setInterval();}}, delay, period);

    }

    public static final int setInterval() {

        if (interval == 0)
            timer.cancel();

        return --interval;
    }

    public void cancelTimer(){
        timer.cancel();
        interval = 0;
    }

    public boolean isRunning(){
        if (interval == 0){
            return false;
        }
        else{return true;}
    }

    public int getCurrentTime(){
        return currentTime;
    }

}
