package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

import pekl.gasqueue.com.gasqueue.Authenticator;
import pekl.gasqueue.com.gasqueue.service.DatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.ValueChangeListener;

/**
 * Created by Kevin on 2016-05-08.
 */
public class AuthenticatorController {
    private Authenticator authenticator;
    private List<Authenticator> authenticatorList;
    private IDatabaseManager<Firebase> dbManagerAuthenticator;
    ValueChangeListener valueListener;


    public AuthenticatorController(String databaseRef){
        dbManagerAuthenticator= new DatabaseManager(new Firebase(databaseRef));
        authenticator = new Authenticator();
        authenticatorList=new ArrayList<>();

    }

    public void sendBarReference(){
        dbManagerAuthenticator.sendObject("Authenticators",authenticator);

    }
    public boolean authenticate(String input) {
         valueListener = new ValueChangeListener(dbManagerAuthenticator.getReference().toString() + "/Authenticators") {

            @Override
            public void dataChanged(DataSnapshot data) {
                for (DataSnapshot authSnapshot : data.getChildren()) {
                    authenticatorList.add(authSnapshot.getValue(Authenticator.class));

                }
            }

        };
        return authenticator.authenticate(input,authenticatorList);
    }

    public void setPassword(String password){
        authenticator.setPassword(password);
    }

    public String getClientType(){
        return authenticator.getClientType();
    }







}
