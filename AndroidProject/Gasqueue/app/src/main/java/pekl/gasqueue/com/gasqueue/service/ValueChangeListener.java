package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Petros on 2016-05-19.
 */
public abstract class ValueChangeListener implements IValueChangeListener<DataSnapshot> {

    IDatabaseManager<Firebase> databaseManager;

    public ValueChangeListener(String reference) {
        databaseManager = new DatabaseManager(new Firebase(reference));
        Firebase ref = databaseManager.getReference();
        ref.addValueEventListener(new ValueEventListener() {
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
