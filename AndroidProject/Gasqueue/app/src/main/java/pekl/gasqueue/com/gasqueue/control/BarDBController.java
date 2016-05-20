package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;

import pekl.gasqueue.com.gasqueue.Bar;
import pekl.gasqueue.com.gasqueue.Product;
import pekl.gasqueue.com.gasqueue.User;
import pekl.gasqueue.com.gasqueue.service.DatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;

/**
 * Created by Petros on 2016-05-09.
 */
public class BarDBController {

    private Bar bar;
    private IDatabaseManager dbManagerBar;
    private String databaseReference;
    private QueueController qc;

    public BarDBController(String databaseReference) {
        dbManagerBar = new DatabaseManager(new Firebase(databaseReference)); //Skapa ny managerklass för Baren?
        this.bar = new Bar();
        updateOrders();
    }

    public void updateOrders() {
        Firebase ref1 = (Firebase) dbManagerBar.createChildReference("Orders"); //Temporary solution to avoid errors
        ref1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap<String, HashMap<Product, Integer>> order = (HashMap<String, HashMap<Product,Integer>>) dataSnapshot.getValue(); //Ska bara finns ett element i hashmappen
                String onlyKey = (String) order.keySet().toArray()[0];
                newCustomer(onlyKey,(HashMap<Product, Integer>) order.get(onlyKey));
                bar.addOrder(onlyKey,(HashMap<Product, Integer>) order.get(onlyKey));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                HashMap<String, HashMap<Product, Integer>> order = (HashMap<String, HashMap<Product,Integer>>) dataSnapshot.getValue(); //Ska bara finns ett element i hashmappen
                bar.removeOrder((String) order.keySet().toArray()[0]);
                //qc.RemoveGuestOrder (not CURRENT guest counting down, any guest in the queue)
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
                //qc.RemoveGuestOrder (not CURRENT guest counting down, any guest in the queue)
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
        qc.addCustomer(clientID);
        Firebase ref = (Firebase) dbManagerBar.createChildReference("totalOrders"); //Ska verkligen Firebase finnas här?
        ref.setValue(bar.getTotalOrders()); //Fel metod används..... använd db manager

    }

    //Update queue as well
    public void orderDone() {
        bar.push();
        Firebase ref = (Firebase) dbManagerBar.createChildReference("customerNumberServed"); //Ska verkligen Firebase finnas här?
        ref.setValue(bar.getCustomerNumberServed()); //Fel metod används..... använd db manager
        //ta bort från kön
    }
}