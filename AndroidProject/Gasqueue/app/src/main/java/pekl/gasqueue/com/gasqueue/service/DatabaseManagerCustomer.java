package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Product;
import pekl.gasqueue.com.gasqueue.Queue;
import pekl.gasqueue.com.gasqueue.control.QueueController;

/**
 * Created by Petros on 2016-04-27.
 */
public class DatabaseManagerCustomer implements IDatabaseManagerCustomer {

    private final static Firebase databaseReference = new Firebase("https://dazzling-torch-9680.firebaseio.com/");
    private Firebase ordersReference = databaseReference.child("Orders");
    public Queue queue;
    public Firebase Queue = databaseReference.child("Queue");

    public DatabaseManagerCustomer() {

    }

    @Override
    public void placeOrder(String clientID, List<Product> order) {
        Map<String, List<Product>> newOrder = new HashMap<String, List<Product>>();//borde flyttas upp så ny inte skapas hela tiden
        newOrder.put(clientID, order);
        queue.enqueue(newOrder);
        Queue.setValue(queue.list); //Tar vi bort den tidigare ordern här?
    }

    @Override
    public void cancelOrder(String clientID) {
        try{
        System.out.println(queue.deletedGuest.toString()+"'s order was cancelled!");
        //timer.cancelTimer();
        queue.resetDeletedCustomer();
        } catch (NullPointerException noOrder){
        System.out.println("There is nothing to cancel.");
        }
    }

    @Override
    public int getPosition(String clientID) {
        return 0;
    }

    @Override
    public boolean isBarCodeCorrect(String barCode) {
        return false;
    }

    @Override
    public boolean isNotBanned(String clientID) {
        return false;
    }

    @Override
    public void saveMap(String address, Map<String, List<Product>> map) {

    }

    @Override
    public void addToMap(String address, String clientID, List<Product> list) {

    }

    @Override
    public void saveStringList(String address, List<String> list) {

    }

    @Override
    public void addToStringList(String address, String clientID) {

    }

    @Override
    public boolean checkCode(String barCode) {
        return false;
    }

}
