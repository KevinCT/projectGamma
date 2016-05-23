package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import pekl.gasqueue.com.gasqueue.Activitiy.Adapter.HashMapAdapter;
import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.R;

public class CartActivity extends AppCompatActivity {
    private Cart cart = new Cart();
    private HashMapAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new HashMapAdapter(cart.getCart());
        listView.setAdapter(adapter);
    }

}
