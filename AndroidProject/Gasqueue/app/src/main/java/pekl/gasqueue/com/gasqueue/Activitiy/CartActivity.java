package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import pekl.gasqueue.com.gasqueue.Activitiy.Adapter.HashMapAdapter;
import pekl.gasqueue.com.gasqueue.control.CustomerDBController;
import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.R;

public class CartActivity extends AppCompatActivity {
    private Cart cart;
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
        totalTV.setText(cart.getTotal() + " kr");
    }

    private void initializeViews()
    {
        cart = new Cart();
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
                customerDB.sendOrder();
            }
        });
    }

}
