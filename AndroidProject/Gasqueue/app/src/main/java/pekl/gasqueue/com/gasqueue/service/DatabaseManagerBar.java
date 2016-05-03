package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Product;

/**
 * Created by Petros on 2016-04-27.
 */
public class DatabaseManagerBar implements IDatabaseManagerBar {


    private static Firebase databaseReference = new Firebase("https://dazzling-torch-9680.firebaseio.com/");
    private Firebase banListReference = databaseReference.child("banned Users");

    public DatabaseManagerBar() {
    }

    @Override
    public List<Product> getOrder() {
        return null;
    }

    @Override
    public void sendNotifications() {

    }

    @Override
    public void banUser(String clientID) {
        List<String> bannedUser = new ArrayList<String>();
        bannedUser.add(clientID);
        banListReference.push().setValue(bannedUser);
    }

    @Override
    public void saveMap(String address, Map<String, Object> map) {

    }

    @Override
    public void addToMap(String address, String clientID, Object object) {

    }
}
