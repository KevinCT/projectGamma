package pekl.gasqueue.com.gasqueue.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Petros on 2016-05-09.
 */

/**
 * Vad behövs?
 * Bör kunna ta ner orders med lyssnare
 * ha en kö
 * skicka bekräftade orders
 * ge strikes
 * banna användare
 * skicka notifikationer
 */
public class Bar implements User {
    private HashMap<String, HashMap<Product, Integer>> orders;
    private Map<String, Integer> strikeMap = new HashMap<>();

    private int totalOrders = 0;
    private int customerNumberServed = 0;

    public Bar() {
        this.orders = new HashMap<>();
    }

    public void push(){
        customerNumberServed++;

    }

    public int getCustomerNumberServed() {
        return customerNumberServed;
    }

    public void addStrike(String clientID) {
        if(strikeMap.containsKey(clientID)) {
            if(strikeMap.get(clientID) == 2) {
                banUser(clientID);
            }
            strikeMap.put(clientID, strikeMap.get(clientID) + 1);
        } else {
            strikeMap.put(clientID, 1);
        }
    }

    private void banUser(String clientID) {

    }

    public void addOrder(String clientID, HashMap<Product, Integer> order) { //Får läggas till en kö senare istället, eller så räcker det med att lägga in clientID i kön
        this.orders.put(clientID, order);
        totalOrders++;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public HashMap<Product, Integer> getOrder(String clientID) {

        System.out.println("getOrder clientID: " + clientID);
        if(orders.get(clientID) != null) {
            Set<Product> products = orders.get(clientID).keySet();
            for (Product p : products) {
                System.out.println(p.getName() + " " + orders.get(clientID).get(p));
            }
        } else {
            System.out.println("orders is null");
        }

        return orders.get(clientID);
    }

    public void removeOrder(String clientID) {
        this.orders.remove(clientID);
    }

}
