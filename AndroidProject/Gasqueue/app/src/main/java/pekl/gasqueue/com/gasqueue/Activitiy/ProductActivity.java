package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import pekl.gasqueue.com.gasqueue.R;

public class ProductActivity extends AppCompatActivity {
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Firebase.setAndroidContext(this);
        Button vodkaBtn = (Button) findViewById(R.id.vodkaBtn);
        Button whiskeyBtn = (Button) findViewById(R.id.whiskeyBtn);



        mRef=new Firebase("https://dazzling-torch-9680.firebaseio.com/");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        vodkaBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mRef.setValue("Vodka");
            }
        });
        whiskeyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mRef.setValue("Whiskey");
            }
        });

    }
}
