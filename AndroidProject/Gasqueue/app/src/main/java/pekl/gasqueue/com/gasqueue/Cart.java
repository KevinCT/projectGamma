package pekl.gasqueue.com.gasqueue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kotex on 09/05/2016.
 */
public class Cart {
    private HashMap<Product,Integer> cart = new HashMap<>();

    public void addProduct (Product product,int quantity) {
        if (cart.containsKey(product)) {
            int temp = cart.get(product);
            temp = temp + quantity;
            cart.remove(product);
            cart.put(product,temp);
        }
        else cart.put(product,quantity);

        System.out.println(product.getName() + " : " + cart.get(product).toString());
    }

    public HashMap<Product,Integer> getCart()
    {
        return this.cart;
    }

    public void removeProduct(Product product)
    {
        if(cart.containsKey(product))
        {
            cart.remove(product);
        }
        else System.out.println("Product finns inte i kundvagn!");
    }
}
