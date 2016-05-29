package pekl.gasqueue.com.gasqueue;
import org.junit.Assert;
import org.junit.Test;

import pekl.gasqueue.com.gasqueue.model.Queue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by User on 5/29/2016.
 */
public class QueueTest {
    Queue queue;
    public QueueTest(){
        queue = new Queue();
    }

    @Test
    public void enqueueTest(){
        System.out.println("Running enqueueTest");
        queue.enqueue(1);
        assertTrue(queue.getSize() == 1);
    }

    @Test
    public void dequeueTest(){
        System.out.println("Running dequeueTest");
        queue.enqueue(1);
        queue.enqueue(1);
        queue.dequeue();
        assertTrue(queue.getSize() == 1);
    }

    @Test
    public void peekTest(){
        System.out.println("Running peekTest");
        queue.enqueue(1);
        assertTrue((Integer)queue.peek() == 1);
    }

    @Test
    public void getSizeTest(){
        System.out.println("Running getSizeTest");

        assertTrue(queue.getSize()==0);
    }

    @Test
    public void resetDeletedCustomerTest(){
        System.out.println("Running resestDeletedCustomerTest");
        queue.resetDeletedCustomer();
        assertTrue(queue.deletedGuest == null);
    }
}
