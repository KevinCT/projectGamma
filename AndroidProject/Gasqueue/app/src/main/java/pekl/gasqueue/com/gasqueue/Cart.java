package pekl.gasqueue.com.gasqueue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Kotex on 09/05/2016.
 */
public class Cart {
    private static HashMap<Product,Integer> cart = new HashMap<Product,Integer>();
    private static int total = 0;

    public void addProduct (Product product,int quantity) {
        if (cart.containsKey(product)) {
            int temp = cart.get(product);
            total = total - product.getPrice()*cart.get(product);
            temp = temp + quantity;
            if(temp > 99 )
            {
                temp = 99;
            }
            else if (temp <0)
            {
                temp = 0;
            }
            cart.put(product,temp);
            total = total + product.getPrice()*temp;
        }
        else
        {
            cart.put(product,quantity);
            total = total + product.getPrice()*quantity;
        }

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
            total = total - product.getPrice()*cart.get(product);
            cart.remove(product);
        }
        else System.out.println("Product finns inte i kundvagn!");
    }

    public int getTotal()
    {
        return total;
    }

    public int getQuantity(Product product)
    {
        if (cart.containsKey(product))
        {
            return cart.get(product);
        }
        else{
            System.out.println("Product finns inte i kundvagn!");
            return 0;
        }
    }

    public void incQuantity(Product product)
    {
        addProduct(product,1);
    }

    public void decQuantity(Product product)
    {
        addProduct(product,-1);
    }
}
