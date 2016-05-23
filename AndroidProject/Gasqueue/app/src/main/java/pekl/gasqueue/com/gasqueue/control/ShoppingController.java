package pekl.gasqueue.com.gasqueue.control;

import java.util.ArrayList;
import java.util.List;

import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.model.Menu;
import pekl.gasqueue.com.gasqueue.model.Product;

/**
 * Created by Kotex on 18/05/2016.
 */
public class ShoppingController {
    private List<Product> productsSameCategory = new ArrayList<>();
    private Menu tempMenu = new Menu();
    private static Product chosenProduct = new Product();
    private static Product.Category chosenCategory;
    private List<Product> allProducts = new ArrayList<>();
    private Cart cart = new Cart();

    public ShoppingController()
    {
        allProducts = tempMenu.getMenu();
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
        chosenProduct = new Product(p.getName(),p.getCategory(),p.getPrice());
    }

    public Product.Category getChosenCategory()
    {
        return chosenCategory;
    }

    public void setChosenCategory(Product.Category c)
    {
        chosenCategory = c;
    }

    public void addProduct(Product p , int g)
    {
        cart.addProduct(p,g);
    }

    public int getProductQuantity()
    {
        return cart.getQuantity(chosenProduct);
    }
}
