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

    public void setChosenProduct(Product temp)
    {
        chosenProduct = new Product(temp.getName(),temp.getCategory(),temp.getPrice());
    }

    public Product getChosenProduct()
    {
        Product temp = new Product();
        temp = chosenProduct;
        return temp;
    }

    public void setPrice(int temp)
    {
        price=temp;
    }
    public void setName(String temp)
    {
        name = temp;
    }
    public void setCategory(Category temp)
    {
        category = temp;
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
