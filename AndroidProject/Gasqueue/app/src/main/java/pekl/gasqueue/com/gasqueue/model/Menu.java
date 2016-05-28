package pekl.gasqueue.com.gasqueue.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Kotex on 03/05/2016.
 */
public class Menu {
    private String bartenderCode;
    private String customerCode;
    private String barName;
    private static HashMap<String, Product> stringKey = new HashMap<>();;
    private List<Product> allProducts;

    private static final Product vodka = new Product("vodka", Product.Category.DRINK,45);
    private static final Product whiskey = new Product("whiskey", Product.Category.DRINK,45);
    private static final Product chicken = new Product("kyckling", Product.Category.FOOD,30);
    private static final Product sommarby = new Product("sommarby", Product.Category.CIDER, 25);
    private static final Product vatten = new Product("vatten", Product.Category.NON_ALCOHOLIC,10);
    private static final Product falcon = new Product("falcon", Product.Category.BEER,20);
    private static final Product brookly = new Product("brookly", Product.Category.BEER,25);


    //The category user chooses in CategoryActivity
    public void downloadMenu() {
        allProducts = new ArrayList<>();
        //allProducts.add()
    }

    public Menu(String barName,String barCode, String guestCode)
    {
        this.barName = barName;
        bartenderCode = barCode;
        customerCode = guestCode;
        allProducts = new ArrayList<>();
    }

    public Menu()
    {
        allProducts = new ArrayList<Product>();
        bartenderCode = "0000";
        customerCode = "1234";
    }

    public static HashMap<Product, Integer> stringToProduct(HashMap<String, Integer> orderString) {
        stringKey.put("vodka",vodka);
        stringKey.put("whiskey",whiskey);
        stringKey.put("kyckling",chicken);
        stringKey.put("sommarby",sommarby);
        stringKey.put("vatten",vatten);
        stringKey.put("falcon",falcon);
        stringKey.put("brookly",brookly);

        Set<String> products = orderString.keySet();
        HashMap<Product,Integer> orderProduct = new HashMap<>();
        System.out.println(orderString.size());
        System.out.println(products.size());

        for (String e:products) {
            orderProduct.put( stringKey.get(e), (Integer) orderString.get(e));
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

    public List<Product> getProducts()
    {
        return allProducts;
    }

    public List<Product> getProductsSameCategory(Product.Category c){
        List<Product> productsSameCategory = new ArrayList<Product>();
        if (allProducts.size() >0) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getCategory().equals(c))
                    productsSameCategory.add(allProducts.get(i));
            }
        }
        return productsSameCategory;
    }
}
