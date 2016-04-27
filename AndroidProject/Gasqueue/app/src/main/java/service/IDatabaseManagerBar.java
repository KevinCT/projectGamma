package service;

/**
 * Created by Petros on 2016-04-27.
 */
public interface IDatabaseManagerBar extends IDatabaseManager {

    //public Order getOrder();

    public void sendNotifications();

    public void banUser(String clientID);
}
