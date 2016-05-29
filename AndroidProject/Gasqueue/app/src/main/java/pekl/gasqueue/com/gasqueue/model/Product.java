package pekl.gasqueue.com.gasqueue.model;

/**
 * Created by kevin on 19/04/2016.
 *
 * Product represents a product found in a menu and sold by a bar.
 * A product includes a name, a price and belongs to a category.
 *
 */
public class Product {
    private String name;
    private int price;
    private Category category;

    //There are only 5 different categories
    public enum Category{
        BEER, DRINK, NON_ALCOHOLIC, FOOD, CIDER
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
