package pekl.gasqueue.com.gasqueue.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotex on 03/05/2016.
 */
public class Menu {
    private String barCode;
    private String barName;
    private List<Product> allProducts;

    //The category user chooses in CategoryActivity
    public void downloadMenu() {
        allProducts = new ArrayList<>();
        //allProducts.add()
    }

    public Menu()
    {
        allProducts = new ArrayList<Product>();
        final Product vodka = new Product("vodka", Product.Category.DRINK,45);
        final Product whiskey = new Product("whiskey", Product.Category.DRINK,45);
        final Product chicken = new Product("kyckling", Product.Category.FOOD,30);
        final Product sommarby = new Product("sommarby", Product.Category.CIDER, 25);
        final Product vatten = new Product("vatten", Product.Category.NON_ALCOHOLIC,10);
        final Product falcon = new Product("falcon", Product.Category.BEER,20);
        final Product brookly = new Product("brookly", Product.Category.BEER,25);
        allProducts.add(vodka);
        allProducts.add(whiskey);
        allProducts.add(chicken);
        allProducts.add(sommarby);
        allProducts.add(vatten);
        allProducts.add(falcon);
        allProducts.add(brookly);
    }

    public Menu(String barCode, String barName)
    {
        allProducts = new ArrayList<>();
        this.barCode = barCode;
        this.barName = barName;
    }

    public void addProduct(Product p)
    {
        allProducts.add(p);
    }

    public void removeProduct(Product p)
    {
        if (allProducts.contains(p)) {
            allProducts.remove(p);
        }
    }

    public List<Product> getMenu()
    {
        return allProducts;
    }
}
