package pekl.gasqueue.com.gasqueue.service;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Petros on 2016-05-19.
 */
public abstract class ValueChangeListener implements IValueChangeListener {

    IDatabaseManager test;

    public ValueChangeListener(String ref) {
        Firebase reference = new Firebase(ref);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public abstract void dataChanged();
}
