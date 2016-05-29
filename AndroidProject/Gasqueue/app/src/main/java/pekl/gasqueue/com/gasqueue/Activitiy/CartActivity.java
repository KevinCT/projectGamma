package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import pekl.gasqueue.com.gasqueue.Adapter.HashMapAdapter;
import pekl.gasqueue.com.gasqueue.control.CustomerDBController;
import pekl.gasqueue.com.gasqueue.control.ShoppingController;
import pekl.gasqueue.com.gasqueue.R;

public class CartActivity extends AppCompatActivity {
    private ShoppingController shoppingController;
    private HashMapAdapter adapter;
    private ListView listView;
    private TextView totalTV;
    private CustomerDBController customerDB = CustomerDBController.getInstance();
    private Button orderButton;


    public CartActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initializeViews();

        listView.setAdapter(adapter);
        totalTV.setText(shoppingController.getTotalOfCart() + " kr");
    }

    private void initializeViews()
    {
        shoppingController = ShoppingController.getInstance();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new HashMapAdapter();
        totalTV = (TextView) findViewById(R.id.totalTV);
        orderButton = (Button) findViewById(R.id.orderButton);
    }
    protected void onStart() {
        super.onStart();

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("here","here");
                customerDB.sendOrder();
                // move later
                Intent activity = new Intent(getApplicationContext(),PickupActivity.class);
                startActivity(activity);
            }
        });
    }

}
