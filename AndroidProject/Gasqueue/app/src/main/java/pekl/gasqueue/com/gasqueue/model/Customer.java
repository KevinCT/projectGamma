package pekl.gasqueue.com.gasqueue.model;

import android.provider.Settings.Secure;

import java.util.HashMap;
import java.util.Set;

/**
 * Customer
 *
 * Class that represents a customer. Keeps track of the users order via the instance
 * of cart, its ID and its queue position once the order has been placed.
 *
 */

public class Customer {
    private boolean orderSent;
    private Integer queuePosition; //change this back to nothing after test
    private boolean banned = false;
    private String clientID = Secure.ANDROID_ID;

    private Cart cart;
    private StopWatch timer = new StopWatch();

    public Customer() {
        queuePosition = 1000;
        cart = new Cart();
    }

    //Adds an item to customer's order
    public void addItem(Product item, int quantity){
        cart.addProduct(item, quantity);
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

    public HashMap<String,Integer> getOrder() {
        return cart.productToString();
    }

    //Removes an item from customer's order specified by order element index
    public void removeItem(Product item){
        cart.removeProduct(item);
    }

    public int getTotalCost() {
        int totalCost = 0;



        Set<Product> products = cart.getCart().keySet();

        for (Product value : products) {
            totalCost = totalCost + value.getPrice() * cart.getCart().get(value);
        }
        return totalCost;
    }

    //creates a 60 second timer
    public void startTimer(){
        timer.runTimer();
    }

    public void setOrderStatus(Boolean orderSent) {
        this.orderSent = orderSent;
    }

    public boolean isOrderSent() {
        return orderSent;
    }

    public Integer getQueuePosition() {
        return queuePosition;
    }

    public void decrementQueuePosition() {
            this.queuePosition--;
    }

    public void setQueuePosition(Integer queuePosition) {
        this.queuePosition = queuePosition;
        System.out.println("Min köposition: " + queuePosition);
    }

    public void resetOrder() {

    }

    public StopWatch getTimer() {
        return timer;
    }
}

