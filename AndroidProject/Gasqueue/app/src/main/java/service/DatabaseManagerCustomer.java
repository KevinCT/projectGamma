package service;

import com.firebase.client.Firebase;

/**
 * Created by Petros on 2016-04-27.
 */
public class DatabaseManagerCustomer implements IDatabaseManagerCustomer {

    private static Firebase databaseReference = new Firebase("https://dazzling-torch-9680.firebaseio.com/");

    public DatabaseManagerCustomer() {

    }

    //@Override
    //public void placeOrder(Order order) {}

    //@Override
    //public void cancelOrder(Order order) {}

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
