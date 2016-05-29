package pekl.gasqueue.com.gasqueue.control;

import pekl.gasqueue.com.gasqueue.model.Customer;
import pekl.gasqueue.com.gasqueue.model.Queue;

/**
 * Created by User on 5/3/2016.
 */
public class QueueController {
    private Queue queue;

    public QueueController(){
        queue = new Queue();
    }

    public String getFirstInQueue() {
        return (String) queue.peek();
    }

    public void addCustomer(String clientID) {
        queue.enqueue(clientID);
    }

    public boolean isCustomerServed(Customer customer) {
        return customer.getTimer().isRunning() == true;
    }

    public void pushQueue(){
        queue.dequeue(); //fysiska queue som är i order


    }

    public Object returnGuestID(){
        return queue.deletedGuest;
    }

    public void nextCustomer(Customer customer){
        if(customer.getTimer().isRunning() == true){
            System.out.println("There is already an order waiting to be picked up!");
        }
        else {
            try {
                System.out.println(queue.dequeue().toString() + ", please pick up your drink in 60 seconds.");
                customer.getTimer().runTimer();
            } catch (IndexOutOfBoundsException noGuests) {
                System.out.println("There are no guests in the current queue.");
            }
        }

    }


    public void cancelGuest(Customer customer){
        try{
            System.out.println(queue.deletedGuest.toString()+"'s order was cancelled!");
            customer.getTimer().cancelTimer();
            queue.resetDeletedCustomer();
        } catch (NullPointerException noOrder){
            System.out.println("There is nothing to cancel.");
        }
    }

    public int getQueueSize() {
        return queue.getSize();
    }
}
