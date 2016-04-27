package pekl.gasqueue.com.gasqueue;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Kevin on 2016-04-28.
 */
public class CustomerTest {
    private Customer customer=new Customer();
    private Product product =new Product("name","category",20);
    @Test
    public void addItemTest(){
        customer.addItem(product);




    }
}
