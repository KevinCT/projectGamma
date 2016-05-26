package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;

import pekl.gasqueue.com.gasqueue.model.Bar;
import pekl.gasqueue.com.gasqueue.model.Menu;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.service.FirebaseDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;

/**
 * Created by Petros on 2016-05-09.
 */
public class BarDBController {

    private Bar bar;
    private IDatabaseManager dbManagerBar;
    private String databaseReference;
    private QueueController queueController;

    public BarDBController(String databaseReference) {
        dbManagerBar = new FirebaseDatabaseManager(new Firebase(databaseReference)); //Skapa ny managerklass för Baren?
        this.bar = new Bar();
        queueController = new QueueController();
        updateOrders();
    }

    public void updateOrders() {
        Firebase ref1 = (Firebase) dbManagerBar.createChildReference("Orders"); //Temporary solution to avoid errors
        ref1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap<String, Integer> order = (HashMap<String, Integer>) dataSnapshot.getValue(HashMap.class); //Ska bara finns ett element i hashmappen
                String onlyKey = dataSnapshot.getKey();
                System.out.println(onlyKey + " = onlyKey + size = " + order.keySet().toArray()[0]);

                HashMap<Product, Integer> orderProduct =  Menu.stringToProduct(order);
                newCustomer(onlyKey, orderProduct);
                bar.addOrder(onlyKey, orderProduct);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                HashMap<String, HashMap<Product, Integer>> order = (HashMap<String, HashMap<Product,Integer>>) dataSnapshot.getValue(); //Ska bara finns ett element i hashmappen
                bar.removeOrder((String) order.keySet().toArray()[0]);
                //queueController.RemoveGuestOrder (not CURRENT guest counting down, any guest in the queue)
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Firebase ref0 = (Firebase) dbManagerBar.createChildReference("cancelOrder"); //Temporary solution to avoid errors
        ref0.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String clientID = dataSnapshot.getValue(String.class);
                bar.removeOrder(clientID);
                //queueController.RemoveGuestOrder (not CURRENT guest counting down, any guest in the queue)
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void newCustomer(String clientID, HashMap<Product, Integer> order) {
        bar.addOrder(clientID, order);
        queueController.addCustomer(clientID);

        dbManagerBar.setValue("totalOrders",bar.getTotalOrders());

    }

    public QueueController getQueueController() {
        return queueController;
    }

    public HashMap<Product, Integer> getOrder(String clientID) {
        return bar.getOrder(clientID);
    }

    //Update queue as well
    public void orderDone() {
        bar.push(); //ökar int för completed orders med 1
        queueController.pushQueue(); //pushar fysiska kön i order framåt
        bar.removeOrder((String) queueController.returnGuestID()); //tar bort ordern som var just gjort från orders hashmappen

        dbManagerBar.setValue("customerNumberServed", bar.getCustomerNumberServed());
    }
}