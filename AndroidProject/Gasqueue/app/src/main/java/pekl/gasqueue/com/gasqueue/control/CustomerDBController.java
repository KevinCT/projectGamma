package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Customer;
import pekl.gasqueue.com.gasqueue.Product;
import pekl.gasqueue.com.gasqueue.service.DatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;

/**
 * Created by Petros on 2016-04-28.
 */
public class CustomerDBController {

    private Customer customer;
    private IDatabaseManager dbManagerCustomer;
    private int queueNumber;

    public CustomerDBController(String databaseReference) {
        dbManagerCustomer = new DatabaseManager(new Firebase(databaseReference)); //Firebase ska ej vara här...
        customer = new Customer();
        updateBanState();
    }

    public void sendOrder(){
        if(!customer.isBanned() && !customer.isOrderSent()) {
            Map<String, Map<Product, Integer>> map = new HashMap<>();
            map.put(customer.getClientID(), customer.getOrder());
            updateQueueNumber();
            dbManagerCustomer.saveMap("Orders", map);
            customer.setOrderStatus(true);

        }
    }

    public void updateBanState() {
        Firebase ref = (Firebase) dbManagerCustomer.createChildReference("banList"); //Temporary solution to avoid errors

        ref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                String id = snapshot.getValue(String.class);
                if (id.equals(customer.getClientID())) {
                    customer.setBan(true);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String id = dataSnapshot.getValue(String.class);
                if (id.equals(customer.getClientID())) {
                    customer.setBan(false);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void updateQueueNumber() {
        Firebase ref = (Firebase) dbManagerCustomer.createChildReference("queueNumber"); //Temporary solution to avoid errors

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                queueNumber = snapshot.getValue(Integer.class);
                updateQueuePosition(); //Activate listeners
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

    }

    public void updateQueuePosition() {
        Firebase ref = (Firebase) dbManagerCustomer.createChildReference("customerNumberServed"); //Temporary solution to avoid errors

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if(customer.isOrderSent()) {
                    if( ! (customer.getQueuePosition() == 0) ||  customer.getQueuePosition() == null) { //Or 0?
                        customer.setQueuePosition(queueNumber - dataSnapshot.getValue(Integer.class));
                    } else {
                        //Your turn to be served, send notifications etc.
                        //orderSent = false,  etc
                        customer.setOrderStatus(false);
                        customer.startTimer();
                    }
                }
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

    public void cancelOrder() {
        customer.resetOrder();
        if(customer.isOrderSent()) {
            dbManagerCustomer.sendObject("cancelOrder", customer.getClientID());
            customer.setOrderStatus(false);
        }
    }

    public void addToCart(Product product, int quantity) {
        customer.addItem(product, quantity);
    }



}