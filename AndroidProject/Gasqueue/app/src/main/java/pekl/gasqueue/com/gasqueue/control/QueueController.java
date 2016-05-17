package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.Firebase;

import java.util.HashMap;

import pekl.gasqueue.com.gasqueue.Customer;
import pekl.gasqueue.com.gasqueue.Queue;
import pekl.gasqueue.com.gasqueue.service.DatabaseManager;

/**
 * Created by User on 5/3/2016.
 */
public class QueueController {
    public Queue queue;

    public QueueController(){

    }

    public void addCustomer(String clientID) {
        queue.enqueue(clientID);
    }

    public boolean isCustomerServed(Customer customer) {
        return customer.timer.isRunning() == true;
    }

    public void nextCustomer(Customer customer){
        if(customer.timer.isRunning() == true){
            System.out.println("There is already an order waiting to be picked up!");
        }
        else {
            try {
                System.out.println(queue.dequeue().toString() + ", please pick up your drink in 60 seconds.");
                customer.timer.main(null);
            } catch (IndexOutOfBoundsException noGuests) {
                System.out.println("There are no guests in the current queue.");
            }
        }

    }


    public void cancelGuest(Customer customer){
        try{
            System.out.println(queue.deletedGuest.toString()+"'s order was cancelled!");
            customer.timer.cancelTimer();
            queue.resetDeletedCustomer();
        } catch (NullPointerException noOrder){
            System.out.println("There is nothing to cancel.");
        }
    }

    public void viewQueue(){
        if(queue.isEmpty()==true){
            System.out.println("Queue is empty!");
        }
        else{
            System.out.println("Current queue size: "+queue.getSize());
            for(int i = 0; i<queue.getSize(); i++) {
                int j = i + 1;
                System.out.println("Position " + j + ": " + queue.getCustomer(i).toString());
            }
        }
    }

    public void givePosition(Customer customer, int Total, int position){
        customer.Position = position-Total;
    }
}
