package pekl.gasqueue.com.gasqueue.service;

import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.model.Product;

/**
 * Created by Petros on 2016-04-27.
 */
public interface IDatabaseManager<T> {

    public void saveMap(String address, Map<String, Map<Product, Integer>> map);

    public void addToMap(String address, String clientID, List<Product> list);

    public void saveStringList(String address, List<String> list);

    public void sendObject(String address, Object object);

    public T getReference();

    public void setValue(String address, Object object);

    public T createChildReference(String childReference);

    //public void addUpdateListener(String address);

    public boolean checkCode(String barCode);

}
