import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class Customer {
        public StopWatch timer;
        public String name;
        public List<Product>order = new ArrayList<Product>();
        //Constructor that sets name of customer
        public Customer(String name){
            this.timer = new StopWatch();
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

            System.out.println("Customer "+name+": ");


                int totalCost = 0;
                System.out.println(name + ": ");

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

        public void startTimer(){
            String[] arguments = new String[] {"123"};
            timer.main(arguments);

        }
        //sends in the current order to the bar
        public void sendOrder(){
            main.q.enqueue(name);
            System.out.println(name+"'s order sent!");
        }



}
