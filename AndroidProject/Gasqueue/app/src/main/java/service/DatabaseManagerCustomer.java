package service;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Product;

/**
 * Created by Petros on 2016-04-27.
 */
public class DatabaseManagerCustomer implements IDatabaseManagerCustomer {

    private static Firebase databaseReference = new Firebase("https://dazzling-torch-9680.firebaseio.com/");
    private static Firebase ordersReference = databaseReference.child("Orders");

    public DatabaseManagerCustomer() {

    }

    @Override
    public void placeOrder(String clientID, List<Product> order) {
        Map<String, List<Product>> newOrder = new HashMap<String, List<Product>>();
        newOrder.put(clientID, order);
        ordersReference.setValue(newOrder);
    }

    @Override
    public void cancelOrder(String clientID) {}

    @Override
    public int getPosition(String clientID) {
        return 0;
    }

    @Override
    public boolean isBarCodeCorrect(String barCode) {
        return false;
    }

    @Override
    public boolean isNotBanned(String clientID) {
        return false;
    }
}
