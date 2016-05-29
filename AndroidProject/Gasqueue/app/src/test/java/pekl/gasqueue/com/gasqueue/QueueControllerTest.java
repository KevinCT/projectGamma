package pekl.gasqueue.com.gasqueue;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by User on 5/29/2016.
 */
import pekl.gasqueue.com.gasqueue.control.QueueController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class QueueControllerTest {
    QueueController qc;
    String kevin = "kevin";
    String paul = "paul";
    public QueueControllerTest(){
        qc = new QueueController();
    }

    @Test
    public void getFirstInQueueTest(){
        
    }

    @Test
    public void addCustomerTest(){
        qc.addCustomer(kevin);
        qc.addCustomer(paul);
        assertTrue(qc.getQueueSize() == 2);
    }

    @Test
    public void isCustomerServedTest(){

    }

    @Test
    public void pushQueueTest(){

    }

    @Test
    public void returnGuestIDTest(){

    }

    @Test
    public void nextCustomerTest(){

    }

    @Test
    public void cancelGuestTest(){

    }

    @Test
    public void getQueueSizeTest(){

    }



}
