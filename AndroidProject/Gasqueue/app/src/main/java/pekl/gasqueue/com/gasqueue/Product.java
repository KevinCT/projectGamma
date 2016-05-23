package pekl.gasqueue.com.gasqueue;

/**
 * Created by kevin on 19/04/2016.
 */
public class Product {
    private String name;
    private int price;

    //chosenProduct is product user chooses in CategoryActivity
    private static Product chosenProduct;

    //There are only 5 different categories
    public enum Category{
        BEER, DRINK, NON_ALCOHOLIC, FOOD, CIDER
    }
    private Category category;

    public void createProduct(String name, Category category, int price)
    {
        Product vara = new Product(name,category,price);
        //TODO: add this product to server

    }
    public Product()
    {
        this.name = null;
        this.category = null;
        this.price = 0;
    }
    public Product(String name, Category category, int price){
        this.name=name;
        this.category=category;
        this.price=price;
    }

    public void setChanges(String newName, Category newCategory, int newPrice )
    {
        name = newName;
        category = newCategory;
        price = newPrice;
    }

    public int getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public Category getCategory(){
        return category;
    }
}
