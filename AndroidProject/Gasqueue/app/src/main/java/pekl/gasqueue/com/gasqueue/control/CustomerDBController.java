package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.model.Customer;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.service.FirebaseDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;

/**
 * Created by Petros on 2016-04-28.
 */
public class CustomerDBController {
    private static CustomerDBController customerDBInstance = null;

    private Customer customer;
    private IDatabaseManager dbManagerCustomer;
    private Integer queueNumber;
    private static String reference = "https://dazzling-torch-9680.firebaseio.com/";

    private CustomerDBController(String databaseReference) {
        dbManagerCustomer = new FirebaseDatabaseManager(new Firebase(databaseReference)); //Firebase ska ej vara här...
        customer = new Customer();
        updateBanState();
    }

    public static CustomerDBController getInstance() {
        if (customerDBInstance == null) {
            customerDBInstance = new CustomerDBController(reference);
        }
        return customerDBInstance;
    }

    public static void setReference(String databaseReference) {
        reference = databaseReference;
    }

    public void sendOrder(){
        if(!customer.isBanned() && !customer.isOrderSent()) {
            Map<String, Map<String, Integer>> map = new HashMap<>();
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
                if(queueNumber != null) {
                    queueNumber = snapshot.getValue(Integer.class);
                    dbManagerCustomer.setValue("queueNumber", queueNumber);
                    updateQueuePosition(); //Activate listeners
                }
                else {
                    queueNumber = 0;
                    dbManagerCustomer.setValue("queueNumber", queueNumber);
                }
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
                if (customer.isOrderSent()) {
                    if (!(customer.getQueuePosition() == 0) || customer.getQueuePosition() == null) { //Or 0?
                        customer.setQueuePosition(queueNumber - dataSnapshot.getValue(Integer.class));
                        customer.notifyObservers(); //double-check this
                    } else {
                        //Your turn to be served, send notifications etc.
                        //orderSent = false,  etc
                        customer.setOrderStatus(false);
                        //customer.startTimer();
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

    public int getQueuePosition() {

        return customer.getQueuePosition();
    }

    public void cancelOrder() {
        customer.resetOrder();
        if (customer.isOrderSent()) {
            dbManagerCustomer.sendObject("cancelOrder", customer.getClientID());
            customer.setOrderStatus(false);
        }
    }

    public void addToCart(Product product, int quantity) {
        customer.addItem(product, quantity);
    }

    public int itemAmountInCart(Product product) {
        return customer.getOrder().get(product);
    }

    public Customer returnCustomer() {
        return customer;
    }
}
