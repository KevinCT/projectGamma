package pekl.gasqueue.com.gasqueue;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by User on 5/29/2016.
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pekl.gasqueue.com.gasqueue.model.FormatDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class FormatDateTest {
    FormatDate formatDate;
    public FormatDateTest(){
        formatDate = new FormatDate();
    }

    @Test
    public void getCurrentDateTest(){
        System.out.println("Running getCurrentDateTest");
        System.out.println(formatDate.getCurrentDate());
        System.out.println(new SimpleDateFormat("yyyy.MM.dd  HH:mm ").format(Calendar.getInstance().getTime()));
        String a = formatDate.getCurrentDate();
        String b = new SimpleDateFormat("yyyy.MM.dd  HH:mm ").format(Calendar.getInstance().getTime());


        assertTrue(a == b);
    }
}
