import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatch {
    static int interval;
    static Timer timer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = 60;
        System.out.println(Integer.toString(interval));
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                System.out.println(setInterval());

            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
}