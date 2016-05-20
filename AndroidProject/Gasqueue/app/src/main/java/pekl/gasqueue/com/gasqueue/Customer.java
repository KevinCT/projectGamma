package pekl.gasqueue.com.gasqueue;

import android.provider.Settings.Secure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import pekl.gasqueue.com.gasqueue.control.BarDBController;
import pekl.gasqueue.com.gasqueue.control.CustomerDBController;

public class Customer implements User{
    private boolean orderSent;
    private Integer queuePosition;
    private boolean banned = false;
    private String clientID = Secure.ANDROID_ID;
    private Cart cart;
    public StopWatch timer = new StopWatch(); //Varför public???


    public Customer() {
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

    public HashMap<Product,Integer> getOrder() {
        return cart.getCart();
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
        timer.main(null);
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

    public void setQueuePosition(int queuePosition) {
        this.queuePosition = queuePosition;
    }

    public void resetOrder() {

    }

}