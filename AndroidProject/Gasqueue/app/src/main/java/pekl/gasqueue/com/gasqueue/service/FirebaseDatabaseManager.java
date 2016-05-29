package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.Firebase;

import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.model.Menu;
import pekl.gasqueue.com.gasqueue.model.Product;

/**
 * Created by Petros on 2016-05-02.
 */
public class FirebaseDatabaseManager implements IDatabaseManager<Firebase> {

    private Firebase reference;

    public FirebaseDatabaseManager(String ref) {
        reference = new Firebase(ref);
    }

    @Override
    public void saveMap(String childReference, Map<String, Map<String, Integer>> map) {
        Firebase targetReference = createChildReference(childReference);
        targetReference.setValue(map);
    }

    @Override
    public void saveStringList(String address, List<String> list) {
        Firebase targetReference = createChildReference(address);
        targetReference.setValue(list);
    }

    @Override
    public void sendObject(String address, Object object) {
        Firebase targetReference = createChildReference(address);
        targetReference.push().setValue(object);
    }

    @Override
    public void setValue(String address, Object object) {
        Firebase targetReference = createChildReference(address);
        targetReference.setValue(object);
    }

    @Override
    public Firebase getReference() {
        return reference;
    }


    @Override
    public Firebase createChildReference(String childReference) {
        Firebase ref = reference.child(childReference);
        return ref;
    }


    @Override
    public boolean checkCode(String barCode) {
        return false;
    }

}
