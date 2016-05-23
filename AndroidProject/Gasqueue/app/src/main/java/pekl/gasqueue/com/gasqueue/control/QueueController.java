package pekl.gasqueue.com.gasqueue.control;

import pekl.gasqueue.com.gasqueue.model.Customer;
import pekl.gasqueue.com.gasqueue.model.Queue;

/**
 * Created by User on 5/3/2016.
 */
public class QueueController {
    private Queue queue;

    public QueueController(){

    }

    public String getFirstInQueue() {
        return (String) queue.peek();
    }

    public void addCustomer(String clientID) {
        queue.enqueue(clientID);
    }

    public void removeServedCustomer() {
        queue.dequeue();
    }

    public boolean isCustomerServed(Customer customer) {
        return customer.timer.isRunning() == true;
    }

    public void pushQueue(){
        queue.dequeue(); //fysiska queue som är i order


    }

    public Object returnGuestID(){
        return queue.deletedGuest;
    }

    public void nextCustomer(Customer customer){
        if(customer.timer.isRunning() == true){
            System.out.println("There is already an order waiting to be picked up!");
        }
        else {
            try {
                System.out.println(queue.dequeue().toString() + ", please pick up your drink in 60 seconds.");
                customer.timer.runTimer();
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

    /*public void viewQueue(){
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
    }*/
}
