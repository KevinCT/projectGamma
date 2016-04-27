package pekl.gasqueue.com.gasqueue;
import org.junit.Test;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kevin on 2016-04-27.
 */
public class ProductTest {
    //Creating product to be tested
        private Product product = new Product("name","cateogry",20);
    @Test
    public void getNameTest(){
        String name =product.getName();
        assertEquals(name,name);
    }
    @Test
    public void getCategoryTest(){
        String category =product.getCategory();
        assertEquals(category,category);
    }
    @Test
    public void getPriceTest(){
        int price =product.getPrice();
        assertEquals(20,20,0);

    }






}
