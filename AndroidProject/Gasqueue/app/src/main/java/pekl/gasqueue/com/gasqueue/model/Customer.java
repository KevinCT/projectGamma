package pekl.gasqueue.com.gasqueue.model;

import android.provider.Settings.Secure;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import pekl.gasqueue.com.gasqueue.Activitiy.PickupActivity;

public class Customer extends Observable implements User {
    private boolean orderSent;
    private Integer queuePosition=12; //change this back to nothing after test
    private boolean banned = false;
    private String clientID = Secure.ANDROID_ID;
    private Cart cart;
    public StopWatch timer = new StopWatch();
    private List<Observer> observers = new ArrayList<Observer>();

    public Customer() {
        cart = new Cart();
    }
    public void attach(PickupActivity pickupActivity){
        observers.add(pickupActivity);
    }
    public void detach(PickupActivity pickupActivity){
        observers.remove(pickupActivity);
    }
    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update(this, getQueuePosition());
            System.out.println(queuePosition);
        }
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

    public void setQueuePosition(int queuePosition) {
        this.queuePosition = queuePosition;
    }

    public void resetOrder() {

    }
}

