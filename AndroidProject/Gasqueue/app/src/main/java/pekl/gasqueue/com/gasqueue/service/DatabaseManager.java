package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Petros on 2016-05-02.
 */
public class DatabaseManager implements IDatabaseManager {

    Firebase reference;

    public DatabaseManager(Firebase reference) {
        this.reference = reference;
    }

    @Override
    public void saveMap(String childReference, Map<String, Object> map) {
        Firebase targetReference = reference.child("childReference");
        targetReference.setValue(map);
    }

    @Override
    public void addToMap(String childReference, String key, Object object) {
        Firebase targetReference = reference.child("childReference");
        Map<String, Object> addedObject = new HashMap<>();
        addedObject.put(key, object);
        targetReference.updateChildren(addedObject);
    }
}
