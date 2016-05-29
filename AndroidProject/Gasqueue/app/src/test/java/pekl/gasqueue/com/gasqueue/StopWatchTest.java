package pekl.gasqueue.com.gasqueue;
import org.junit.Assert;
import org.junit.Test;

import pekl.gasqueue.com.gasqueue.model.StopWatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Eric on 5/29/2016.
 */
    public class StopWatchTest {
    private StopWatch stopWatch;

    public StopWatchTest() {
        stopWatch = new StopWatch();
    }

    @Test
    public void runTimerTest() {
        System.out.println("Running runTimerTest");
        stopWatch.runTimer();
        assertTrue(stopWatch.isRunning());
    }

    @Test
    public void setIntervalTest() {
        System.out.println("Running setIntervalTest");
        stopWatch = new StopWatch();
        assertTrue(stopWatch.setInterval()==59);
    }

    @Test
    public void cancelTimerTest() {
        System.out.println("Running cancelTimerTest");
        stopWatch.runTimer();
        stopWatch.cancelTimer();
        assertTrue(stopWatch.getInterval()==0);
    }

    @Test
    public void isRunningTest() {
        System.out.println("Running isRunningTest");
        stopWatch.runTimer();
        assertTrue(stopWatch.isRunning());
    }

    @Test
    public void getCurrentTime() {
        System.out.println("Running getCurrentTimeTest");
        assertTrue(stopWatch.getCurrentTime() == 60);
    }
}