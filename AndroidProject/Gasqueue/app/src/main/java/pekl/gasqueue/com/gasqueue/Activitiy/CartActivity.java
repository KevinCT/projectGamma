package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import pekl.gasqueue.com.gasqueue.Activitiy.Adapter.HashMapAdapter;
import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.R;

public class CartActivity extends AppCompatActivity {
    private Cart cart;
    private HashMapAdapter adapter;
    private ListView listView;
    private TextView totalTV;
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
    }
}
