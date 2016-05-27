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
        databaseManager = new FirebaseDatabaseManager(reference);
        Firebase ref = databaseManager.getReference();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                childAdded(dataSnapshot, s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                childChanged(dataSnapshot, s);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                childRemoved(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                childMoved(dataSnapshot, s);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public abstract void childAdded(DataSnapshot data, String s);

    public abstract void childChanged(DataSnapshot data, String s);

    public abstract void childRemoved(DataSnapshot data);

    public abstract void childMoved(DataSnapshot data, String s);
}
