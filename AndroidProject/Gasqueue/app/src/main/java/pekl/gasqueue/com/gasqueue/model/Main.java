package pekl.gasqueue.com.gasqueue.model;

import pekl.gasqueue.com.gasqueue.control.QueueController;

/**
 * Created by kevin on 19/04/2016.
 */
public class Main {
    public static void main(String[] args) {
        Customer eric = new Customer();
        QueueController qc = new QueueController();

        qc.nextCustomer(eric);
        FormatDate date = new FormatDate();
        System.out.println(date.getCurrentDate());

    }

}

