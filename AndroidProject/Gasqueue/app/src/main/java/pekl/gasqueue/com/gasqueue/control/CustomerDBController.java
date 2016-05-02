package pekl.gasqueue.com.gasqueue.control;

import pekl.gasqueue.com.gasqueue.Customer;
import pekl.gasqueue.com.gasqueue.Product;
import pekl.gasqueue.com.gasqueue.service.DatabaseManagerCustomer;

/**
 * Created by Petros on 2016-04-28.
 */
public class CustomerDBController {

    private Customer customer;
    private DatabaseManagerCustomer dbManagerCustomer = new DatabaseManagerCustomer();

    public CustomerDBController() {
        customer= new Customer();

    }

    public void sendOrder(){
        dbManagerCustomer.placeOrder(customer.getClientID(), customer.getOrder());
    }

    public void cancelOrder() {
        customer.resetOrder();
        dbManagerCustomer.placeOrder(customer.getClientID(), customer.getOrder());
    }

    public void addItem(Product product) {
        customer.addItem(product);
    }

}
