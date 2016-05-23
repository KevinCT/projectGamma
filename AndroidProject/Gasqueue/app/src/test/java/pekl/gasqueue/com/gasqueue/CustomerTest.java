package pekl.gasqueue.com.gasqueue;

import org.junit.Test;

import pekl.gasqueue.com.gasqueue.model.Customer;
import pekl.gasqueue.com.gasqueue.model.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Kevin on 2016-04-28.
 */
public class CustomerTest {
    private Customer customer=new Customer();
    private Product product1 =new Product("name1",Product.Category.BEER,20);
    private Product product2 =new Product("name2",Product.Category.CIDER,20);

    @Test
    public void addItemTest(){
        System.out.println("Running addItemTest");
        customer.addItem(product1);
        assertEquals(1, customer.getOrder().size(), 0);


    }
    @Test
    public void removeItemTest(){
        System.out.println("Running removeItemTest");
        customer.addItem(product1);
        assertEquals(1,customer.getOrder().size(),0);
        customer.removeItem(product1);
        assertEquals(0,customer.getOrder().size(),0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void badIndexTest(){
        System.out.println("Running badIndexTest");
        customer.getOrder().get(-1);
    }
    @Test
    public void totalCostTest(){
        System.out.println("Running totalcostTest");
        customer.addItem(product1);
        customer.addItem(product2);
        int totalCost =customer.getTotalCost();
        assertEquals(40,totalCost,0);
    }
}
