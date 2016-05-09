package pekl.gasqueue.com.gasqueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Petros on 2016-05-09.
 */

/**
 * Vad behövs?
 * Bör kunna ta ner orders med lyssnare
 * ha en kö
 * skicka bekräftade orders
 * ge strikes
 * banna användare
 * skicka notifikationer
 */
public class Bar implements User {
    Map<String, Integer> strikeMap = new HashMap<>();


    public void addStrike(String clientID) {
        if(strikeMap.containsKey(clientID)) {
            if(strikeMap.get(clientID) == 2) {
                banUser(clientID);
            }
            strikeMap.put(clientID, strikeMap.get(clientID) + 1);
        } else {
            strikeMap.put(clientID, 1);
        }
    }

    private void banUser(String clientID) {

    }
}
