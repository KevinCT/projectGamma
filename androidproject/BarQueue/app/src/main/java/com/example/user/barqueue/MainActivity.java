package com.example.user.barqueue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://blinding-heat-4643.firebaseio.com/";
    private Firebase FirebaseRef;
    private Firebase c1;
    private Firebase c2;
    private Firebase c3;
    private Firebase Queue;
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
        Button msubmitButton = (Button) findViewById(R.id.submitButton);
        Button mviewQueueButton = (Button) findViewById(R.id.viewQueueButton);
        Button mpushButton = (Button) findViewById(R.id.pushButton);
        Button mcancelButton = (Button) findViewById(R.id.cancelButton);

        final TextView mchosenView = (TextView) findViewById(R.id.chosenView);
        final EditText mnameText = (EditText) findViewById(R.id.nameText);
        final Queue queue = new Queue();

        final StopWatch timer = new StopWatch();

        FirebaseRef = new Firebase(FIREBASE_URL).child("Users").child("Customers").child("TestCustomer");
        c1 = new Firebase(FIREBASE_URL).child("Users").child("Customers").child("C1");
        c2 = new Firebase(FIREBASE_URL).child("Users").child("Customers").child("C2");
        c3 = new Firebase(FIREBASE_URL).child("Users").child("Customers").child("C3");
        Queue = new Firebase(FIREBASE_URL).child("Queue");

        FirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("You ordered a "+ dataSnapshot.getValue());
                String drinkOrder = (String) dataSnapshot.getValue();
                mchosenView.setText(drinkOrder);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read messed up: "+firebaseError.getMessage());
            }
        });

        assert mviewQueueButton != null;
        mviewQueueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(queue.isEmpty()==true){
                    System.out.println("Queue is empty!");
                }
                else{
                System.out.println("Current queue size: "+queue.getSize());
                for(int i = 0; i<queue.getSize(); i++) {
                    int j = i + 1;
                    System.out.println("Position " + j + ": " + queue.getCustomer(i).toString());
                    }
                }

            }
        });


        assert msubmitButton != null;
        msubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                if(mnameText.toString().isEmpty()){
                    System.out.println("Type your name please!");
                }
                else{
                    String name = mnameText.getText().toString();
                queue.enqueue(name);
                System.out.println("Added "+name+" to the queue!");
                mnameText.setText("");
                Queue.setValue(queue.list);

                }
            }
        });

        assert mpushButton != null;
        mpushButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            if(timer.isRunning() == true){
                System.out.println("There is already an order waiting to be picked up!");
            }
            else {
                try {
                    System.out.println(queue.dequeue().toString() + ", please pick up your drink in 60 seconds.");

                    timer.main(null);
                    Queue.setValue(queue.list);
                } catch (IndexOutOfBoundsException noGuests) {
                    System.out.println("There are no guests in the current queue.");
                    }
                }
            }
        });

        assert mcancelButton != null;
        mcancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    System.out.println(queue.deletedGuest.toString()+"'s order was cancelled!");
                    timer.cancelTimer();
                    queue.resetDeletedCustomer();
                } catch (NullPointerException noOrder){
                    System.out.println("There is nothing to cancel.");
                }


            }
        });

        assert mvodkaButton != null;
        mvodkaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseRef.setValue("Vodka");
            }
        });

        assert mwhiskeyButton != null;
        mwhiskeyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseRef.setValue("Whiskey");
            }
        });
    }

}
