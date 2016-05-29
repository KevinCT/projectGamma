package pekl.gasqueue.com.gasqueue;
import org.junit.Assert;
import org.junit.Test;

import pekl.gasqueue.com.gasqueue.model.Message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by User on 5/29/2016.
 */
public class MessageTest {
    Message message;
    public MessageTest(){
        message = new Message("hello","kevin","12:00");
    }

    @Test
    public void setNameTest(){
        System.out.println("Running setNameTest");
        message.setName("paul");
        assertTrue(message.getName()=="paul");
    }
    @Test
    public void setMessageTest(){
        System.out.println("Running setMessageTest");
        message.setMessage("bye");
        assertTrue(message.getMessage() == "bye");
    }
    @Test
    public void setTimeStampTest(){
        System.out.println("Running setTimeStampTest");
        message.setTimeStamp("13:00");
        assertTrue(message.getTimeStamp() == "13:00");
    }
    @Test
    public void getNameTest(){
        System.out.println("Running getNameTest");
        assertTrue(message.getName()=="kevin");
    }
    @Test
    public void getMessageTest(){
        System.out.println("Running getMessageTest");
        assertTrue(message.getMessage() == "hello");
    }
    @Test
    public void getTimeStampTest(){
        System.out.println("Running getTimeStampTest");
        assertTrue(message.getTimeStamp() == "12:00");
    }
}
