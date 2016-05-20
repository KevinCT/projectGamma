package pekl.gasqueue.com.gasqueue.Activitiy;


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

import pekl.gasqueue.com.gasqueue.Queue;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.StopWatch;
import pekl.gasqueue.com.gasqueue.control.QueueController;

public class HostActivity extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://dazzling-torch-9680.firebaseio.com/";
    private Firebase fbQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_host);

    }

    protected void onStart(){
        super.onStart();
        Button mpushButton = (Button) findViewById(R.id.pushButton);
        Button mcancelButton = (Button) findViewById(R.id.cancelButton);
        Button mviewQueueButton = (Button) findViewById(R.id.viewButton);

        final QueueController qc = new QueueController();
        final StopWatch timer = new StopWatch();

        fbQueue = new Firebase(FIREBASE_URL).child("Queue");

        /*FirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("You ordered a "+ dataSnapshot.getValue());
                String drinkOrder = (String) dataSnapshot.getValue();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read messed up: "+firebaseError.getMessage());
            }
        });*/

        assert mviewQueueButton != null;
        mviewQueueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(qc.queue.isEmpty()==true){
                    System.out.println("Queue is empty!");
                }
                else{
                    System.out.println("Current queue size: "+qc.queue.getSize());
                    for(int i = 0; i<qc.queue.getSize(); i++) {
                        int j = i + 1;
                        System.out.println("Position " + j + ": " + qc.queue.getCustomer(i).toString());
                    }
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
                        System.out.println(qc.queue.dequeue().toString() + ", please pick up your drink in 60 seconds.");

                        timer.runTimer();
                        fbQueue.setValue(qc.queue.list);
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
                    System.out.println(qc.queue.deletedGuest.toString()+"'s order was cancelled!");
                    timer.cancelTimer();
                    qc.queue.resetDeletedCustomer();
                } catch (NullPointerException noOrder){
                    System.out.println("There is nothing to cancel.");
                }


            }
        });


    }

}
