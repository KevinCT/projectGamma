package pekl.gasqueue.com.gasqueue;
import org.junit.Assert;
import org.junit.Test;

import pekl.gasqueue.com.gasqueue.model.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kevin on 2016-04-27.
 */

public class ProductTest {
    //Creating product to be tested
    private Product product1;
    private Product product2;
    public ProductTest()
    {
        product1 = new Product("product1",Product.Category.BEER,25);
        product2 = new Product();
    }
    @Test
    public void getNameTest(){
        String name = product1.getName();
        Assert.assertEquals("product1",name);
    }
    @Test
    public void getCategoryTest(){
        Product.Category category =product1.getCategory();
        Assert.assertEquals(Product.Category.BEER,category);
    }
    @Test
    public void getPriceTest(){
        int price =product1.getPrice();
        Assert.assertEquals(25,price);
    }

    @Test
    public void setChangesTest(){
        product2.setChanges("product2",Product.Category.CIDER,20);
        Assert.assertTrue(product2.getName().equals("product2"));
        Assert.assertTrue(product2.getCategory().equals(Product.Category.CIDER));
        Assert.assertTrue(product2.getPrice() == 20);

    }






}
