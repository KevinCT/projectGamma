package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.Firebase;

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
        Map<String, List<Product>> map = new HashMap<>();
        map.put(customer.getClientID(),customer.getOrder());
        dbManagerCustomer.saveMap("Orders", map);
        //customer.orderSent(true);
        //Lägg till metod senare.
    }

    public void cancelOrder() {
        customer.resetOrder();
        //dbManagerCustomer.addToMap("Orders",customer.getClientID(), customer.getOrder());
    }

    public void addItem(Product product) {
        customer.addItem(product);
    }

}