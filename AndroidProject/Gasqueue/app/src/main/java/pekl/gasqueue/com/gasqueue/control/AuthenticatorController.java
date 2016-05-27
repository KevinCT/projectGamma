package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

import pekl.gasqueue.com.gasqueue.model.Authenticator;
import pekl.gasqueue.com.gasqueue.service.FirebaseDatabaseManager;
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
        dbManagerAuthenticator= new FirebaseDatabaseManager(databaseRef);
        authenticator = new Authenticator();
        authenticatorList=new ArrayList<>();
        initListener();

    }

    public void sendBarReference(){
        dbManagerAuthenticator.sendObject("Authenticators",authenticator);

    }
    public String authenticate(String input) {


        return authenticator.authenticate(input,authenticatorList);
    }
    private void initListener(){
        valueListener = new ValueChangeListener(dbManagerAuthenticator.getReference().toString() + "/Authenticators") {

            @Override
            public void dataChanged(DataSnapshot data) {
                for (DataSnapshot authSnapshot : data.getChildren()) {
                    authenticatorList.add(authSnapshot.getValue(Authenticator.class));

                }
            }

        };

    }
    public void setBarPassword(String barPassword){
        authenticator.setBarPassword(barPassword);
    }

    public void setCustomerPassword(String customerPassword){
        authenticator.setCustomerPassword(customerPassword);
    }

    public String getBarPassword(){
        return authenticator.getBarPassword();
    }
    public String getCustomerPassword(){
        return authenticator.getCustomerPassword();
    }









}
