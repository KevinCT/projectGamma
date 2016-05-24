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
    private CustomerDBController customerDB = CustomerDBController.getInstance();
    private Cart cart = new Cart();
    private HashMapAdapter adapter;
    private Button orderButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ListView listView = (ListView) findViewById(R.id.listView);
        Button orderButton = (Button) findViewById(R.id.orderButton);
        adapter = new HashMapAdapter();
        listView.setAdapter(adapter);
        TextView totalTV = (TextView) findViewById(R.id.totalTV);
        totalTV.setText(cart.getTotal() + " kr");
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
