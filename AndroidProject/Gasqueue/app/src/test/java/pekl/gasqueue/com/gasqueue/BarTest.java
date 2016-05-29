package pekl.gasqueue.com.gasqueue;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import pekl.gasqueue.com.gasqueue.model.Bar;
import pekl.gasqueue.com.gasqueue.model.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by User on 5/29/2016.
 */
public class BarTest {
    Bar bar;
    String user = "kevin";
    Product vodka = new Product();
    public BarTest(){
        bar = new Bar();
    }

    @Test
    public void pushTest(){
        System.out.println("Running pushTest");
        bar.push();
        assertTrue(bar.getCustomerNumberServed()==1);
    }
    @Test
    public void getCustomerNumberServedTest(){
        System.out.println("Running getCustomerNumberServedTest");
        Bar bar2 = new Bar();
        bar2.push();
        bar2.push();
        assertTrue(bar2.getCustomerNumberServed()==2);
    }
    @Test
    public void addStrikeTest(){
        System.out.println("Running addStrikeTest");
        user = "android_5X_96";
        System.out.println("First strike!");
        bar.addStrike(user);
        System.out.println("Second strike!");
        bar.addStrike(user);
        System.out.println("Third strike!");
        bar.addStrike(user);

    }
    @Test
    public void banUserTest(){
        System.out.println("Running banUserTest");
        //cannot run as in TestClass
    }
   // @Test
    /*public void addOrderTest(){
        System.out.println("Running addOrderTest");
        bar.getOrder(user).put(vodka, 12);
        bar.addOrder(user, bar.getOrder(user));
        assertTrue(bar.getTotalOrders()==1);
    }*/
    @Test
    public void getTotalOrdersTest(){
        System.out.println("Running getTotalOrdersTest");
        bar.addOrder(user, bar.getOrder(user));
        bar.addOrder(user, bar.getOrder(user));
        assertTrue(bar.getTotalOrders()==2);

    }
    @Test
    public void getOrderTest(){
        System.out.println("Running getOrderTest");
        assertTrue(bar.getOrder(user) == bar.getOrder(user));

        bar.addOrder(user, bar.getOrder(user));
        assertTrue(bar.getOrder(user) == bar.getOrder(user));

    }
    @Test
    public void removeOrder(){
        System.out.println("Running removeOrder");
        bar.removeOrder(user);


    }
}
