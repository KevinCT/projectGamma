package pekl.gasqueue.com.gasqueue;

/**
 * Created by kevin on 19/04/2016.
 */
public class Product {
    private String name;
    private String category;
    private int price;
    public Product(String name, String category, int price){
        this.name=name;
        this.category=category;
        this.price=price;
    }


    public int getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public String getCategory(){
        return category;
    }



}
