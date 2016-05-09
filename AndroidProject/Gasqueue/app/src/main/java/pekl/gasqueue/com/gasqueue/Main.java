package pekl.gasqueue.com.gasqueue;

import pekl.gasqueue.com.gasqueue.control.QueueController;

/**
 * Created by kevin on 19/04/2016.
 */
public class Main {
    public static void main(String[] args) {
        Customer eric = new Customer();
        QueueController qc = new QueueController();

        qc.nextCustomer(eric);
    }
}

