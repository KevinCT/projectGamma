package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import pekl.gasqueue.com.gasqueue.Customer;
import pekl.gasqueue.com.gasqueue.Product;
import pekl.gasqueue.com.gasqueue.R;

public class ProductActivity extends AppCompatActivity {
    private Customer customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_product);
        Button vodkaBtn = (Button) findViewById(R.id.vodkaBtn);
        Button whiskeyBtn = (Button) findViewById(R.id.whiskeyBtn);
        Button sendOrderBtn=(Button) findViewById(R.id.sendOrderBtn);
        customer=new Customer();
        final Product vodka = new Product("vodka","alcohol",20);
        final Product whiskey = new Product("whiskey","alcohol",20);




        vodkaBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                customer.addItem(vodka);
            }
        });
        whiskeyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                customer.addItem(whiskey);
            }
        });

        sendOrderBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                customer.sendOrder();
            }
        });


    }
}
