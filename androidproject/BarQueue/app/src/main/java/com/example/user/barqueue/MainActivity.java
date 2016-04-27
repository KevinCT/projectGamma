package com.example.user.barqueue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://blinding-heat-4643.firebaseio.com/ericshao";
    private Firebase FirebaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

    }

    protected void onStart(){
        super.onStart();
        Button mvodkaButton = (Button) findViewById(R.id.vodkaButton);
        Button mwhiskeyButton = (Button) findViewById(R.id.whiskeyButton);
        final TextView mTextChoose = (TextView) findViewById(R.id.chooseTitle);
        final TextView mDrinkChoice = (TextView) findViewById(R.id.drinkChoice);

        FirebaseRef = new Firebase(FIREBASE_URL);

        FirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("You ordered a "+ dataSnapshot.getValue());
                String drinkOrder = (String) dataSnapshot.getValue();
                mDrinkChoice.setText(drinkOrder);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read messed up: "+firebaseError.getMessage());
            }
        });

        mvodkaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseRef.setValue("Vodka");
            }
        });

        mwhiskeyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseRef.setValue("Whiskey");
            }
        });
    }

}
