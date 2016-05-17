package pekl.gasqueue.com.gasqueue.control;





import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import pekl.gasqueue.com.gasqueue.Authenticator;
import pekl.gasqueue.com.gasqueue.Bar;
import pekl.gasqueue.com.gasqueue.service.DatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;

/**
 * Created by Kevin on 2016-05-08.
 */
public class AuthenticatorController {
    private Authenticator authenticator;
    private List<Authenticator> authenticatorList;
    private IDatabaseManager dbManagerAuthenticator;


    public AuthenticatorController(String databaseRef){
        dbManagerAuthenticator= new DatabaseManager(new Firebase(databaseRef));
        authenticator = new Authenticator();
        authenticatorList=new ArrayList<>();

    }

    public void sendBarReference(){
        dbManagerAuthenticator.createChildReference("Authenticators").push().setValue(authenticator);
    }
    public boolean authenticate(String input){
        Firebase passwordRef = dbManagerAuthenticator.createChildReference("Authenticators");
        passwordRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot authSnapshot:dataSnapshot.getChildren()){
                    authenticatorList.add(authSnapshot.getValue(Authenticator.class));

                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return authenticator.authenticate(input,authenticatorList);

    }
    public void setPassword(String password){
        authenticator.setPassword(password);
    }

    public String getClientType(){
        return authenticator.getClientType();
    }







}
