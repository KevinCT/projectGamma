import java.util.Collection;
import java.util.List;
public class Customer {

        public String name;
        public List<MenuItem>order;
        //Constructor that sets name of customer
        public Customer(String name){

            this.name = name;

        }
        //Returns the name of the customer
        public String getName(){

            return name;

        }
        //Adds an item to customer's order
        public void addItem(MenuItem item){
            order.add(item);
        }
        //Removes an item from customer's order specified by order element index
        public void removeItem(int index){
            order.remove(index);
        }
        //Removes all items and resets the order
        public void resetOrder(){
            order.clear();
        }
        //creates a 60 second timer
        public void timer(){
            StopWatch timer = new StopWatch();

        }
        //sends in the current order to the bar
        public void sendOrder(){
            System.out.println("Order sent!");
        }



}
