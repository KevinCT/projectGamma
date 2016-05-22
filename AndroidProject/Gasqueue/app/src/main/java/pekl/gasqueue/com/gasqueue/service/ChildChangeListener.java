package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Petros on 2016-05-22.
 */
public abstract class ChildChangeListener implements IChildChangeListener<DataSnapshot> {

    IDatabaseManager<Firebase> databaseManager;

    public ChildChangeListener(String reference) {
        databaseManager = new DatabaseManager(new Firebase(reference));
        Firebase ref = databaseManager.getReference();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                onChildAdded(dataSnapshot, s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                onChildChanged(dataSnapshot, s);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                onChildRemoved(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                onChildMoved(dataSnapshot, s);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public abstract void onChildAdded(DataSnapshot data, String s);

    public abstract void onChildChanged(DataSnapshot data, String s);

    public abstract void onChildRemoved(DataSnapshot data);

    public abstract void onChildMoved(DataSnapshot data, String s);
}
