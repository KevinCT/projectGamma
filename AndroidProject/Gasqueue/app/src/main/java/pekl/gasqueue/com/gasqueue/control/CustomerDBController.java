package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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
        dbManagerCustomer = new DatabaseManager(new Firebase(databaseReference)); //Referensen ska kunnas sättas dynamiskt
        customer = new Customer();
    }

    public void sendOrder(){
        if(!customer.isBanned()) {
            Map<String, List<Product>> map = new HashMap<>();
            map.put(customer.getClientID(), customer.getOrder());
            dbManagerCustomer.saveMap("Orders", map);
        }
        //customer.orderSent(true);
        //Lägg till metod senare.
    }

    public void updateBanState() {

        Firebase ref = new Firebase("Referencevärde till bannlistan");

        ref.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to the database
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
                String id = snapshot.getValue(String.class);
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

    public void cancelOrder() {
        customer.resetOrder();
        //dbManagerCustomer.addToMap("Orders",customer.getClientID(), customer.getOrder());
    }

    public void addItem(Product product) {
        customer.addItem(product);
    }

}