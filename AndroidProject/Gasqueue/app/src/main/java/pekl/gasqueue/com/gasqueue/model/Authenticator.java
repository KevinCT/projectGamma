package pekl.gasqueue.com.gasqueue.model;

import android.util.Log;

import java.util.List;

/**
 * Created by Kevin on 2016-05-08.
 */
// might be useless.. // maybe create a client and bar password in same.
public class Authenticator {
    private String barPassword;
    private String customerPassword;
    private String clientType;



    public Authenticator(){


    }
   /* public void setClientType(String client){
        this.clientType=client;
    }
    public String getClientType(){
        return this.clientType;
    }*/

    public void setBarPassword(String barPassword){
        this.barPassword=barPassword;
    }
    public String getBarPassword(){
        return this.barPassword;
    }
    public void setCustomerPassword(String customerPassword){
        this.customerPassword=customerPassword;
    }
    public String getCustomerPassword(){
        return customerPassword;
    }


    public String authenticate(String input, List<Authenticator> authenticatorList){
        clientType="hi";
        for(Authenticator authPassword:authenticatorList) {
            if (input.equals(authPassword.getBarPassword())) {
                clientType= "bar";
            }
            else if(input.equals(authPassword.getCustomerPassword())){
                clientType= "customer";
            }
        }
        return clientType;
    }

}
