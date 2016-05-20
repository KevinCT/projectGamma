package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.Firebase;

import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Product;

/**
 * Created by Petros on 2016-04-27.
 */
public interface IDatabaseManager {

    public void saveMap(String address, Map<String, Map<Product, Integer>> map);

    public void addToMap(String address, String clientID, List<Product> list);

    public void saveStringList(String address, List<String> list);

    public void sendObject(String address, Object object);

    public Object createChildReference(String childReference);

    //public void addUpdateListener(String address);

    public boolean checkCode(String barCode);

}
