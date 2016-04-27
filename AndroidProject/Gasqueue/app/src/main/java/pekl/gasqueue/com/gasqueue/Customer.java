package pekl.gasqueue.com.gasqueue;

/**
 * Created by kevin on 19/04/2016.
 */


import android.provider.Settings.Secure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import service.DatabaseManagerCustomer;

public class Customer {
    private String clientID = "APA";
    private List<Product> order = new ArrayList<Product>();
    private DatabaseManagerCustomer dbManager;

    public Customer() {
        this.dbManager = new DatabaseManagerCustomer();
    }

    //Adds an item to customer's order
    public void addItem(Product item){
        order.add(item);;
    }
    //Removes an item from customer's order specified by order element index
    public void removeItem(Product item){
        order.remove(order.indexOf(item));;
    }
    public void showOrder(){
        int totalCost=0;

        for (Product value :order) {
            System.out.println(value.getName());
            totalCost=totalCost+value.getPrice();
        }
        System.out.println("Amount to pay:"+ totalCost +"kr");
    }
    //Removes all items and resets the order
    public void resetOrder(){
        order.clear();
    }
    //creates a 60 second timer
    public void timer(){
        StopWatch timer = new StopWatch();

    }
    //sends in the current order to the bar
    public void sendOrder(){
        dbManager.placeOrder(clientID, order);
    }



}

