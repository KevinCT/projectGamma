

package pekl.gasqueue.com.gasqueue;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pekl.gasqueue.com.gasqueue.model.Menu;
import pekl.gasqueue.com.gasqueue.model.Product;

/**
 * Created by Kotex on 29/05/2016.
 */
public class MenuTest {
    private Menu menu;
    private Product product1;
    private Product product2;
    private List<Product> list;

    public MenuTest()
    {
        menu = new Menu();
        product1 = new Product("product1",Product.Category.BEER,30);
        product2 = new Product("product2", Product.Category.DRINK,60);
        list = new ArrayList<Product>();
        list.add(product1);
        list.add(product2);
    }

    @Test
    public void addProductTest()
    {
        menu.addProduct(product1);
        int size = menu.getProducts().size();
        Assert.assertEquals(1,size);
        menu.addProduct(product2);
        size = menu.getProducts().size();
        Assert.assertEquals(2,size);
    }

    @Test
    public void removeProductTest()
    {
        menu.setProduct(list);
        menu.removeProduct(product1);
        int size = menu.getProducts().size();
        Assert.assertEquals(1,size);
        menu.removeProduct(product2);
        size = menu.getProducts().size();
        Assert.assertEquals(0,size);
    }

    @Test
    public void getProductSameCategoryTest()
    {
        menu.setProduct(list);
        List<Product> tempList = new ArrayList<Product>();
        tempList = menu.getProductsSameCategory(Product.Category.BEER);
        boolean containProduct = tempList.contains(product1);
        Assert.assertTrue(containProduct);
        tempList = menu.getProductsSameCategory(Product.Category.DRINK);
        containProduct = tempList.contains(product2);
        Assert.assertTrue(containProduct);
        tempList = menu.getProductsSameCategory(Product.Category.FOOD);
        containProduct = (tempList.size()>0);
        Assert.assertFalse(containProduct);
    }

}
