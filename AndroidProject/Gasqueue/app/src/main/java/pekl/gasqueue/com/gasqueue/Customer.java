package pekl.gasqueue.com.gasqueue;

/**
 * Created by kevin on 19/04/2016.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class Customer {

    public String name;
    public List<Product>order = new ArrayList<Product>();
    //Constructor that sets name of customer
    public Customer(String name){

        this.name = name;

    }
    //Returns the name of the customer
    public String getName(){

        return name;

    }
    //Adds an item to customer's order
    public void addItem(Product item){
        order.add(item);
        System.out.println("Added "+item.getName()+" for "+name);
    }
    //Removes an item from customer's order specified by order element index
    public void removeItem(Product item){
        order.remove(order.indexOf(item));
        System.out.println("Removed "+item.getName()+" for "+name);
    }
    public void showOrder(){
        int totalCost=0;
        System.out.println(name+": ");
        for (Product value :order) {
            System.out.println(value.getName());
            totalCost=totalCost+value.getPrice();
        }
        System.out.println("Amount to pay:"+ totalCost +"kr");
    }
    //Removes all items and resets the order
    public void resetOrder(){
        order.clear();
        System.out.println(name+"'s order cleared");
    }
    //creates a 60 second timer
    public void timer(){
        StopWatch timer = new StopWatch();

    }
    //sends in the current order to the bar
    public void sendOrder(){
        System.out.println(name+"'s order sent!");
    }



}

