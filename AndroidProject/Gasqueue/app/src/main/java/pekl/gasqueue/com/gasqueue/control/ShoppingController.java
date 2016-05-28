package pekl.gasqueue.com.gasqueue.control;

import android.provider.ContactsContract;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.model.Menu;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.service.FirebaseDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IValueChangeListener;
import pekl.gasqueue.com.gasqueue.service.ValueChangeListener;

/**
 * Created by Kotex on 18/05/2016.
 */
public class ShoppingController {
    private List<Product> productsSameCategory;
    private Menu menu;
    private static Product chosenProduct;
    private static Product.Category chosenCategory;
    private List<Product> allProducts;
    private Cart cart;
    private static boolean isBartender = false;
    private static boolean isCreatingProduct;
    private IValueChangeListener listener;

    public ShoppingController()
    {
        downloadMenu();
        menu = new Menu();
        allProducts = menu.getMenu();
        cart = new Cart();
        productsSameCategory = new ArrayList<Product>();
    }

    public List<Product> getProductSameCategory()
    {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory().equals(chosenCategory))
                productsSameCategory.add(allProducts.get(i));
        }
        return productsSameCategory;
    }

    public Product getChosenProduct()
    {
        return chosenProduct;
    }

    public void setChosenProduct(Product p)
    {
        chosenProduct = p;
        System.out.println(chosenProduct.getName() + "");
    }

    public Product.Category getChosenCategory()
    {
        return chosenCategory;
    }

    public void setChosenCategory(Product.Category c)
    {
        chosenCategory = c;
    }

    public void incQuantity(Product p)
    {
        cart.addProduct(p,1);
    }

    public void decQuantity(Product p)
    {
        cart.addProduct(p,-1);
    }

    public void addProductToCart(Product p , int g)
    {
        cart.addProduct(p,g);
    }

    public void removeProductFromCart(Product p)
    {
        cart.removeProduct(p);
    }

    public HashMap<Product,Integer> getCart()
    {
        return cart.getCart();
    }

    public int getTotalOfProduct(Product p)
    {
        return cart.getTotalOfProduct(p);
    }

    public int getQuantityOfProduct(Product p) {
        if (cart.getCart().containsKey(p)) {
            return cart.getCart().get(p);
        }
        else return 0;
    }

    public void setTypeOfUser(boolean isBartender)
    {
        this.isBartender = isBartender;
    }

    public boolean getTypeOfUser()
    {
        return isBartender;
    }

    public void setIntention(boolean isCreatingProduct)
    {
        this.isCreatingProduct = isCreatingProduct;
    }

    public boolean getIntention()
    {
        return isCreatingProduct;
    }
    public void addProductToMenu(String n, Product.Category c, int p)
    {
        menu.addProduct(new Product(n,c,p));
    }

    public void changeProduct(String name, Product.Category category, int price)
    {
        chosenProduct.setChanges(name,category,price);
    }

    public void createProduct(String name, Product.Category category, int price)
    {
        Product temp = new Product(name,category,price);
        allProducts.add(temp);
    }
    //code for testing might be removed
    public Menu getMenu(){

        return menu;
        }
    private void downloadMenu(){
        listener = new ValueChangeListener("https://dazzling-torch-9680.firebaseio.com/Menus") {
            @Override
            public void dataChanged(DataSnapshot data) {
                HashMap<String,Menu> hash= data.getValue(HashMap.class);
                menu=hash.get("0000");


            }


        };

    }


}
