package pekl.gasqueue.com.gasqueue;

import android.provider.Settings.Secure;
import java.util.ArrayList;

public class Customer implements User{

    private boolean orderSent;
    private int queuePosition;
    private boolean banned = false;
    private String clientID = Secure.ANDROID_ID;
    private ArrayList<Product> order = new ArrayList<Product>(); //Fel listtyp?
    public StopWatch timer = new StopWatch();
    public int Position;

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

    public int getTotalCost() {
        int totalCost = 0;

        for (Product value : order) {
            totalCost = totalCost + value.getPrice();
        }
        return totalCost;
    }
    //Removes all items and resets the order
    public void resetOrder(){
        order.clear();
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

    public int getQueuePosition() {
        return queuePosition;
    }

    public void decrementQueuePosition() {
            this.queuePosition--;
    }

    public void setQueuePosition(int queuePosition) {
        this.queuePosition = queuePosition;
    }

}