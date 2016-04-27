package service;

/**
 * Created by Petros on 2016-04-27.
 */
public interface IDatabaseManagerCustomer extends IDatabaseManager {

    public void placeOrder(Order order);

    public void cancelOrder(Order order);

    public int getPosition(String clientID);

    public boolean isBarCodeCorrect(String barCode);

    public boolean isNotBanned(String clientID);
}
