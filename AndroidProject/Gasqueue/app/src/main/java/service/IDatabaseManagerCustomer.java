package service;

import java.util.List;

import pekl.gasqueue.com.gasqueue.Product;

/**
 * Created by Petros on 2016-04-27.
 */
public interface IDatabaseManagerCustomer extends IDatabaseManager {

    public void placeOrder(String clientID, List<Product> order);

    public void cancelOrder(String clientID);

    public int getPosition(String clientID);

    public boolean isBarCodeCorrect(String barCode);

    public boolean isNotBanned(String clientID);
}
