package pekl.gasqueue.com.gasqueue.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Kotex on 03/05/2016.
 */
public class Menu {
    private String barCode;
    private String barName;
    private static Map<String, Product> stringKey;
    private List<Product> allProducts;

    //The category user chooses in CategoryActivity
    public void downloadMenu() {
        stringKey = new HashMap<>();
        allProducts = new ArrayList<>();
        //allProducts.add()
    }

    public Menu()
    {
        stringKey = new HashMap<>();
        allProducts = new ArrayList<Product>();
        final Product vodka = new Product("vodka", Product.Category.DRINK,45);
        final Product whiskey = new Product("whiskey", Product.Category.DRINK,45);
        final Product chicken = new Product("kyckling", Product.Category.FOOD,30);
        final Product sommarby = new Product("sommarby", Product.Category.CIDER, 25);
        final Product vatten = new Product("vatten", Product.Category.NON_ALCOHOLIC,10);
        final Product falcon = new Product("falcon", Product.Category.BEER,20);
        final Product brookly = new Product("brookly", Product.Category.BEER,25);

        stringKey.put("vodka",vodka);
        stringKey.put("whiskey",whiskey);
        stringKey.put("kyckling",chicken);
        stringKey.put("sommarby",sommarby);
        stringKey.put("vatten",vatten);
        stringKey.put("falcon",falcon);
        stringKey.put("brookly",brookly);

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

    public static HashMap<Product, Integer> stringToProduct(HashMap<String, Integer> orderString) {
        Set<String> products = orderString.keySet();
        HashMap<Product,Integer> orderProduct = new HashMap<>();

        for (String e:products) {
            orderProduct.put(stringKey.get(e), orderString.get(e));
        }
        return orderProduct;
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
