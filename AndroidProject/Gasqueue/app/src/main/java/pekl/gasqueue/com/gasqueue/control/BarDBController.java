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
    public QueueController qc;
    public CustomerDBController customerDBC;
    public int Total = 0;
    public int SentOrders = 0;
    private Firebase total = new Firebase("https://dazzling-torch-9680.firebaseio.com/").child("Total");
    private Firebase sentOrders = new Firebase("https://dazzling-torch-9680.firebaseio.com/").child("sentOrders");
    public BarDBController(String databaseReference) {
        dbManagerBar = new DatabaseManager(new Firebase(databaseReference)); //Skapa ny managerklass för Baren?
        this.bar = new Bar();
        total.setValue(Total);
        updateOrders();
    }

    public void push(){
       // qc.nextCustomer();
        Total++;
        total.setValue(Total);

    }



    public void updateOrders() {
        dbManagerBar.createChildReference("Orders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap<String, HashMap<Product, Integer>> order = (HashMap<String, HashMap<Product,Integer>>) dataSnapshot.getValue(); //Ska bara finns ett element i hashmappen
                String onlyKey = (String) order.keySet().toArray()[0];
                bar.addOrder(onlyKey,(HashMap<Product, Integer>) order.get(onlyKey));
                qc.queue.enqueue(onlyKey);
                SentOrders++;
                sentOrders.setValue(SentOrders);
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
        dbManagerBar.createChildReference("cancelOrder").addChildEventListener(new ChildEventListener() {
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

}
