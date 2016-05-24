package pekl.gasqueue.com.gasqueue.model;

import android.util.Log;

import java.util.List;

/**
 * Created by Kevin on 2016-05-08.
 */
public class Authenticator {
    private String barPassword;
    private String customerPassword;
    private String clientType;


    public Authenticator() {


    }

    public void setBarPassword(String barPassword) {
        this.barPassword = barPassword;
    }

    public String getBarPassword() {
        return this.barPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }


    public String authenticate(String input, List<Authenticator> authenticatorList) {
        for (Authenticator authPassword : authenticatorList) {
            if (input.equals(authPassword.getBarPassword())) {
                clientType = "bar";
            } else if (input.equals(authPassword.getCustomerPassword())) {
                clientType = "customer";
            } else clientType = "empty";
        }
        if (clientType != null)
            return clientType;


    }
}
