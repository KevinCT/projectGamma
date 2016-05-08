package pekl.gasqueue.com.gasqueue;

/**
 * Created by kevin on 19/04/2016.
 */

import android.provider.Settings.Secure;
import java.util.ArrayList;

public class Customer implements User{

    private boolean orderSent;
    private boolean banned;
    private String clientID = Secure.ANDROID_ID;
    private ArrayList<Product> order = new ArrayList<Product>(); //Fel listtyp?
    StopWatch timer = new StopWatch();

    public Customer() {

    }

    //Adds an item to customer's order
    public void addItem(Product item){
        order.add(item);
    }

    public String getClientID() {
        return clientID;
    }

    public void setBan(Boolean banState) {
        this.banned = banState;
    }

    public boolean isBanned() {
        return banned;
    }

    public ArrayList<Product> getOrder() {
        return (ArrayList<Product>) order.clone(); //Rätt?
    }

    //Removes an item from customer's order specified by order element index
    public void removeItem(Product item){
        order.remove(order.indexOf(item));
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
    public void startTimer(){
        timer.run();
    }

    public void setOrderStatus(Boolean orderSent) {
        this.orderSent = orderSent;
    }

    public boolean isOrderSent() {
        return orderSent;
    }

}