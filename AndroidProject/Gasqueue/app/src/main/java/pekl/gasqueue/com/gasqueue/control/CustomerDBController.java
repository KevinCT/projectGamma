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

    public CustomerDBController(String databaseReference) {
        dbManagerCustomer = new DatabaseManager(new Firebase(databaseReference));
        customer = new Customer();
        updateBanState();
    }

    public void sendOrder(){
        if(!customer.isBanned() && !customer.isOrderSent()) {
            Map<String, List<Product>> map = new HashMap<>();
            map.put(customer.getClientID(), customer.getOrder());
            updateQueueNumber();
            dbManagerCustomer.saveMap("Orders", map);
            customer.setOrderStatus(true);

        }
    }

    public void updateBanState() {
        Firebase ref = dbManagerCustomer.createChildReference("banList");

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
        Firebase ref = dbManagerCustomer.createChildReference("queueNumber");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                customer.setQueueNumber(snapshot.getValue(Integer.class));
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

    public void addItem(Product product) {
        customer.addItem(product);
    }

}