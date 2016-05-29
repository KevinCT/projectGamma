package pekl.gasqueue.com.gasqueue;

import org.junit.Assert;
import org.junit.Test;

import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.model.Product;

/**
 * Created by Kotex on 29/05/2016.
 */
public class CartTest {
    private Cart cart;
    private Product product1;
    private Product product2;

    public CartTest()
    {
        cart = new Cart();
        product1 = new Product("product1", Product.Category.BEER,25);
        product2 = new Product("product2", Product.Category.CIDER,30);
    }

    @Test
    public void getCartTest()
    {
        int size = cart.getCart().size();
        Assert.assertEquals(0,size);
    }

    @Test
    public void addProductTest()
    {
        cart.addProduct(product1,1);
        int size = cart.getCart().size();
        Assert.assertEquals(1,size);

        cart.addProduct(product2,3);
        size = cart.getCart().size();
        Assert.assertEquals(2,size);
    }

    @Test
    public void removeProductTest()
    {
        cart.addProduct(product1,2);
        cart.addProduct(product2,4);
        cart.removeProduct(product2);
        int size = cart.getCart().size();
        Assert.assertEquals(1,size);
        int total = cart.getTotal();
        Assert.assertEquals(50,total);
    }

    @Test
    public void clearCartTest()
    {
        cart.addProduct(product1,5);
        cart.clearCar();
        int size = cart.getCart().size();
        Assert.assertEquals(0,size);
    }

    @Test
    public void getTotalTest()
    {
        cart.addProduct(product1,1);
        cart.addProduct(product2,2);
        int total = cart.getTotal();
        Assert.assertEquals(85,total);
    }

    @Test
    public void getTotalOfProductTest()
    {
        cart.addProduct(product2,2);
        int total = cart.getTotalOfProduct(product2);
        Assert.assertEquals(60,total);
        cart.addProduct(product1,1);
        total = cart.getTotalOfProduct(product1);
        Assert.assertEquals(25,total);
    }

}
