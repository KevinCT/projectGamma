package pekl.gasqueue.com.gasqueue;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pekl.gasqueue.com.gasqueue.model.Authenticator;

/**
 * Created by kevin on 29/05/2016.
 */
public class AuthenticatorTest {
    private Authenticator authenticator;
    private List<Authenticator> authenticatorList;
    public AuthenticatorTest(){
        authenticator=new Authenticator();
        authenticator.setBarPassword("0000");
        authenticator.setCustomerPassword("1111");
        authenticatorList=new ArrayList<>();
        authenticatorList.add(authenticator);
    }

    @Test
    public void authenticateTest() {
        String actual =authenticator.authenticate("0000", authenticatorList);
        Assert.assertEquals("bar",actual);
        String actual2= authenticator.authenticate("1111",authenticatorList);
        Assert.assertEquals("customer",actual2);
        String actual3=authenticator.authenticate("1543",authenticatorList);
        Assert.assertEquals("empty",actual3);

    }

}
