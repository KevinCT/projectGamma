package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Product;

/**
 * Created by Petros on 2016-05-02.
 */
public class DatabaseManager implements IDatabaseManager {

    Firebase reference;

    public DatabaseManager(Firebase reference) {
        this.reference = reference;
    }

    @Override
    public void saveMap(String childReference1, Map<String, List<Product>> map) {
        Firebase targetReference = reference.child(childReference1);
        targetReference.setValue(map);
    }

    @Override
    public void addToMap(String childReference, String key, List<Product> list) { //Behövs ej tror jag
        /**
        Firebase targetReference = reference.child("childReference");
        Map<String, List<Product>> addedObject = new HashMap<>();
        addedObject.put(key, list);
        targetReference.updateChildren(addedObject);
         */
    }

    @Override
    public boolean checkCode(String barCode) {
        return false;
    }
}
