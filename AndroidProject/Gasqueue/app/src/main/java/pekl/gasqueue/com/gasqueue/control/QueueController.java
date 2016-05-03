package pekl.gasqueue.com.gasqueue.control;

import pekl.gasqueue.com.gasqueue.Customer;

/**
 * Created by User on 5/3/2016.
 */
public class QueueController {
    public QueueController(){

    }

    public void nextCustomer(Customer customer){
        customer.startTimer();

    }
}
