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
        //prevent returning nulll
        clientType="empty";
        for (Authenticator authPassword : authenticatorList) {
            if (input.equals(authPassword.getBarPassword())) {
                barPassword=authPassword.getBarPassword();
                customerPassword=authPassword.getCustomerPassword();
                clientType = "bar";
                break;
            } else if (input.equals(authPassword.getCustomerPassword())) {
                clientType = "customer";
                break;
            }
        }

            return clientType;


    }
}
