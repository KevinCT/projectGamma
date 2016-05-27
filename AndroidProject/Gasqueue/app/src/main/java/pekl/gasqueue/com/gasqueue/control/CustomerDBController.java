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
import pekl.gasqueue.com.gasqueue.service.ChildChangeListener;
import pekl.gasqueue.com.gasqueue.service.FirebaseDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IChildChangeListener;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IValueChangeListener;
import pekl.gasqueue.com.gasqueue.service.SingleValueChangeListener;

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
        dbManagerCustomer = new FirebaseDatabaseManager(databaseReference); //Firebase ska ej vara här...
        customer = new Customer();
        updateBanState(databaseReference);
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

    public void updateBanState(String ref) {
        IChildChangeListener childBanListListener = new ChildChangeListener(ref + "/banList") { //Funkar det verkligen med /???

            @Override
            public void childAdded(DataSnapshot data, String s) {
                String id = data.getValue(String.class);
                if (id.equals(customer.getClientID())) {
                    customer.setBan(true);
                }
            }

            @Override
            public void childChanged(DataSnapshot data, String s) {

            }

            @Override
            public void childRemoved(DataSnapshot data) {
                String id = data.getValue(String.class);
                if (id.equals(customer.getClientID())) {
                    customer.setBan(false);
                }
            }

            @Override
            public void childMoved(DataSnapshot data, String s) {

            }
        };

    }

    public void updateQueueNumber() {

        IValueChangeListener<DataSnapshot> valueChangeListener = new SingleValueChangeListener(reference + "/queueNumber"){

            @Override
            public void dataChanged(DataSnapshot data) {
                if(queueNumber != null) {
                    queueNumber = data.getValue(Integer.class);
                    dbManagerCustomer.setValue("queueNumber", queueNumber + 1);
                    updateQueuePosition(); //Activate listeners
                }
                else {
                    queueNumber = 0;
                    dbManagerCustomer.setValue("queueNumber", queueNumber + 1);
                }
            }
        };

    }

    public void updateQueuePosition() {
        IChildChangeListener childBanListListener = new ChildChangeListener(reference + "/customerNumberServed") { //Funkar det verkligen med /???

            @Override
            public void childAdded(DataSnapshot data, String s) {

            }

            @Override
            public void childChanged(DataSnapshot data, String s) {
                if (customer.isOrderSent()) {
                    if (!(customer.getQueuePosition() == 0) || customer.getQueuePosition() == null) { //Or 0?
                        customer.setQueuePosition(queueNumber - data.getValue(Integer.class));
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
            public void childRemoved(DataSnapshot data) {

            }

            @Override
            public void childMoved(DataSnapshot data, String s) {

            }
        };

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
