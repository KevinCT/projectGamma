package pekl.gasqueue.com.gasqueue.service;

import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Product;

/**
 * Created by Petros on 2016-04-27.
 */
public interface IDatabaseManager {

    public void saveMap(String address, Map<String, List<Product>> map);

    public void addToMap(String address, String clientID, List<Product> list);

    public boolean checkCode(String barCode);

}
