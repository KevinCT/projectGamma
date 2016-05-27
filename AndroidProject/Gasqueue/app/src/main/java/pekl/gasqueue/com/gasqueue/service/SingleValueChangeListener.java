package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Petros on 2016-05-27.
 */
public abstract class SingleValueChangeListener implements IValueChangeListener<DataSnapshot> {

    IDatabaseManager<Firebase> databaseManager;


    public SingleValueChangeListener(String reference) {
        databaseManager = new FirebaseDatabaseManager(reference);
        Firebase ref = databaseManager.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChanged(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public abstract void dataChanged(DataSnapshot data);
}
