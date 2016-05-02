package pekl.gasqueue.com.gasqueue.service;

import java.util.Map;

/**
 * Created by Petros on 2016-04-27.
 */
public interface IDatabaseManager {

    public void saveMap(String address, Map<String, Object> map);

    public void addToMap(String address, String clientID, Object object);
}
