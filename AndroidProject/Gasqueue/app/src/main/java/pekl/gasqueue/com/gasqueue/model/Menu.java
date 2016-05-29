package pekl.gasqueue.com.gasqueue.model;

import android.util.Log;

import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pekl.gasqueue.com.gasqueue.service.IValueChangeListener;
import pekl.gasqueue.com.gasqueue.service.ValueChangeListener;

/**
 * Created by Kotex on 03/05/2016.
 *
 * Menu represents a list of products found in a bar.
 * It will be downloaded from Firebase when the user enters the right password.
 * Menu can be changed by host or bartender.
 *
 *
 */
public class Menu {
    private HashMap<String, Product> stringKey = new HashMap<>();;
    private List<Product> allProducts;

    public Menu(List<Product> list){
        allProducts=new ArrayList<>();
        allProducts=list;
    }

    public Menu()
    {
        allProducts = new ArrayList<Product>();
       }


    public HashMap<Product, Integer> stringToProduct(HashMap<String, Integer> orderString) {
        for(Product product: allProducts) {
            stringKey.put(product.getName(), product);
        }

        Set<String> products = orderString.keySet();
        HashMap<Product,Integer> orderProduct = new HashMap<>();

        for (String e:products) {
            orderProduct.put( stringKey.get(e), (Integer) orderString.get(e));
        }
        return orderProduct;
    }

    public void setProduct(List<Product> list)
    {
        allProducts = list;
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
