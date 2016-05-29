package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.DataSnapshot;

import java.util.HashMap;
import java.util.Set;

import pekl.gasqueue.com.gasqueue.model.Bar;
import pekl.gasqueue.com.gasqueue.model.Menu;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.service.ChildChangeListener;
import pekl.gasqueue.com.gasqueue.service.FirebaseDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IChildChangeListener;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;

/**
 * Created by Petros on 2016-05-09.
 */
public class BarDBController {

    private Bar bar;
    private IDatabaseManager dbManagerBar;
    private QueueController queueController;
    private ShoppingController shoppingController;

    public BarDBController(String databaseReference) {
        dbManagerBar = new FirebaseDatabaseManager(databaseReference); //Skapa ny managerklass för Baren?
        this.bar = new Bar();
        queueController = new QueueController();
        shoppingController = ShoppingController.getInstance();

        updateOrders(databaseReference);
    }

    public void updateOrders(String ref) {
        IChildChangeListener childOrderListener = new ChildChangeListener(ref + "/" + "Orders") { //Funkar det verkligen med /???

            @Override
            public void childAdded(DataSnapshot data, String s) {
                HashMap<String, Integer> order = (HashMap<String, Integer>) data.getValue(HashMap.class); //Ska bara finns ett element i hashmappen
                String onlyKey = data.getKey();

                HashMap<Product, Integer> orderProduct =  shoppingController.convertStringToProduct(order);
                Set<Product> products = orderProduct.keySet();
                for(Product p:products) {
                }

                newCustomer(onlyKey, orderProduct);

            }

            @Override
            public void childChanged(DataSnapshot data, String s) {

            }

            @Override
            public void childRemoved(DataSnapshot data) {
                HashMap<String, HashMap<Product, Integer>> order = (HashMap<String, HashMap<Product,Integer>>) data.getValue(); //Ska bara finns ett element i hashmappen
                //bar.removeOrder((String) order.keySet().toArray()[0]);
                //queueController.RemoveGuestOrder (not CURRENT guest counting down, any guest in the queue)
            }

            @Override
            public void childMoved(DataSnapshot data, String s) {

            }
        };

        IChildChangeListener childCancelListener = new ChildChangeListener(ref + "/" + "cancelOrder") { //Funkar det verkligen med /???
            @Override
            public void childAdded(DataSnapshot data, String s) {
                String clientID = data.getValue(String.class);
                //bar.removeOrder(clientID);
                //queueController.RemoveGuestOrder (not CURRENT guest counting down, any guest in the queue)
            }

            @Override
            public void childChanged(DataSnapshot data, String s) {

            }

            @Override
            public void childRemoved(DataSnapshot data) {

            }

            @Override
            public void childMoved(DataSnapshot data, String s) {

            }
        };

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
        //bar.removeOrder((String) queueController.returnGuestID()); //tar bort ordern som var just gjort från orders hashmappen
        dbManagerBar.setValue("customerNumberServed", bar.getCustomerNumberServed());
                        //updates the customers phone position TextView with live position
    }
}