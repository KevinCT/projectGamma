package pekl.gasqueue.com.gasqueue;

import android.util.Log;

import java.util.List;

/**
 * Created by Kevin on 2016-05-08.
 */
// might be useless.. // maybe create a client and bar password in same.
public class Authenticator {
    private String password;
    private Bar bar;
    private String clientType;


    public Authenticator(){


    }
    public void setClientType(String client){
        this.clientType=client;
    }
    public String getClientType(){
        return this.clientType;
    }

    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
    public Bar getBar(){
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public boolean authenticate(String input, List<Authenticator> authenticatorList){
        for(Authenticator authPassword:authenticatorList) {
            if (input.equals(authPassword.getPassword())) {
                clientType=authPassword.getClientType();
                Log.v("test",getClientType());
                return true;
            }
        }
        return false;
    }

}
