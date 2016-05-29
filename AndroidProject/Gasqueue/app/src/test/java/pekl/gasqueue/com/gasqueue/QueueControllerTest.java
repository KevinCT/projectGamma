package pekl.gasqueue.com.gasqueue;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by User on 5/29/2016.
 */
import pekl.gasqueue.com.gasqueue.control.QueueController;
import pekl.gasqueue.com.gasqueue.model.Customer;

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
        System.out.println("Running getFirstInQueueTest");
        QueueController qc2 = new QueueController();
        qc2.addCustomer(kevin);
        assertTrue(qc2.getFirstInQueue() == "kevin");
    }

    @Test
    public void addCustomerTest(){
        System.out.println("Running addCustomerTest");
        qc.addCustomer(kevin);
        qc.addCustomer(paul);
        assertTrue(qc.getQueueSize() == 2);
    }

    @Test
    public void isCustomerServedTest(){
        System.out.println("Running isCustomerServedTest");
    }

    @Test
    public void pushQueueTest(){
        System.out.println("Running pushQueueTest");
        QueueController qc3 = new QueueController();
        qc3.addCustomer(kevin);
        qc3.addCustomer(paul);
        qc3.pushQueue();
        assertTrue(qc3.getQueueSize()==1);
    }

    @Test
    public void returnGuestIDTest(){
        System.out.println("Running returnGuestIDTest");
    }

    @Test
    public void nextCustomerTest(){
        System.out.println("Running nextCustomerTest");
        Customer customer = new Customer();
        QueueController qc5 = new QueueController();
        qc5.nextCustomer(customer);
        
    }

    @Test
    public void cancelGuestTest(){
        System.out.println("Running cancelGuestTest");
    }

    @Test
    public void getQueueSizeTest(){
        System.out.println("Running getQueueSizeTest");
    }



}
