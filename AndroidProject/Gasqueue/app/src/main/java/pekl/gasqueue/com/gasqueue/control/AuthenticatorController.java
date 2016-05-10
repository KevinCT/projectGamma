package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Authenticator;

/**
 * Created by Kevin on 2016-05-08.
 */
public class AuthenticatorController {
    private Authenticator authenticator;
    private Firebase databaseReference= new Firebase("https://dazzling-torch-9680.firebaseio.com/");
    private Firebase targetReference;

    public AuthenticatorController(){
        targetReference= databaseReference.child("Authenticator");
        authenticator = new Authenticator();

    }
    //avoid conflicts
    public void sendBarReference(){
        Map<String, String> map = new HashMap<>();
        map.put(authenticator.getPassword(),"test");
        targetReference.setValue(map);
    }
    public String Authenticate(String password){
        return null;


    }
    public void setPassword(String password){
        authenticator.setPassword(password);
    }

}
