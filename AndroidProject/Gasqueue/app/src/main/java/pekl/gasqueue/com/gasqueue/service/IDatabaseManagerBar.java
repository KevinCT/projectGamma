package pekl.gasqueue.com.gasqueue.service;

import java.util.List;

import pekl.gasqueue.com.gasqueue.Product;

/**
 * Created by Petros on 2016-04-27.
 */
public interface IDatabaseManagerBar extends IDatabaseManager {

    public List<Product> getOrder();

    public void sendNotifications();

    public void banUser(String clientID);
}
