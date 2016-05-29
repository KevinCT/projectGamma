package pekl.gasqueue.com.gasqueue.model;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Kotex on 09/05/2016.
 *
 * Cart represents a list of products with quantity.
 * It has a hashmap with product as key and quantity as value.
 *
 */
public class Cart {
    private static HashMap<Product,Integer> cart;
    private int total;

    public Cart()
    {
        total = 0;
        cart = new HashMap<Product,Integer>();
    }

    public void addProduct (Product product,int quantity) {
        if (cart.containsKey(product)) {
            int temp = cart.get(product);
            total = total - product.getPrice()*cart.get(product);
            temp = temp + quantity;

            if(temp > 99 ) { temp = 99; }
            else if (temp <0) { temp = 0; }

            cart.put(product,temp);
            total = total + product.getPrice()*temp;
        }
        else
        {
            cart.put(product,quantity);
            total = total + product.getPrice()*quantity;
        }
    }

    public HashMap<Product,Integer> getCart()
    {
        return this.cart;
    }

    public HashMap<String,Integer> productToString() {
        Set<Product> products = cart.keySet();
        HashMap<String,Integer> orderString = new HashMap<>();

        for (Product e:products) {
            orderString.put(e.getName(), cart.get(e));
        }
        return orderString;
    }

    public void removeProduct(Product product)
    {
        if(cart.containsKey(product))
        {
            total = total - product.getPrice()*cart.get(product);
            cart.remove(product);
        }
    }

    public int getTotal()
    {
        return total;
    }

    public int getTotalOfProduct(Product p)
    {
        if (!cart.containsKey(p))
        {
            return 0;
        }
        else {
            return p.getPrice()*cart.get(p);
        }
    }

    public void clearCar()
    {
        cart.clear();
    }
}
