package pekl.gasqueue.com.gasqueue.Activitiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import pekl.gasqueue.com.gasqueue.Activitiy.Adapter.HostListViewAdapter;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.BarDBController;
import pekl.gasqueue.com.gasqueue.control.CustomerDBController;
import pekl.gasqueue.com.gasqueue.control.QueueController;
import pekl.gasqueue.com.gasqueue.model.Product;


/**
 * Created by User on 5/11/2016.
 */
public class HostActivity extends AppCompatActivity {
    private BarDBController barController;
    private QueueController queueController;
    private HashMap<Product,Integer> order;
    private String firstInQueue;
    private HostListViewAdapter orderAdapter;
    private CustomerDBController customerDBController;

    private TextView currentGuestView;
    private TextView detailsView;
    private TextView nameView;
    private TextView timerTextView;
    private TextView totalPriceTextView;
    private ListView orderListView;
    private Button pushButton;
    private Button viewQueueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        barController = new BarDBController("https://dazzling-torch-9680.firebaseio.com/"); //kan sättas dynamiskt om det behövs
        queueController = barController.getQueueController();
        if(queueController.getQueueSize() > 0) {
            firstInQueue = queueController.getFirstInQueue();
        }
    }

    protected void onStart() {
        super.onStart();
        initializeViews();
        orderAdapter = new HostListViewAdapter(order);
        orderListView.setAdapter(orderAdapter);

        assert pushButton != null;
        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(queueController.getQueueSize() > 0) {
                    barController.orderDone();
                    firstInQueue = queueController.getFirstInQueue();
                    updateView();

                }
            }
        });
    }

    private void initializeViews() {
        currentGuestView = (TextView) findViewById(R.id.currentGuestView);
        detailsView = (TextView) findViewById(R.id.detailsView);
        nameView = (TextView) findViewById(R.id.nameView);
        pushButton = (Button) findViewById(R.id.pushButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        totalPriceTextView = (TextView) findViewById(R.id.totalPriceTextView);
        orderListView = (ListView) findViewById(R.id.orderListView);
        viewQueueButton = (Button) findViewById(R.id.viewQueueButton);
        updateView();

    }

    private void updateView() {
        this.nameView.setText(firstInQueue);
        //Ändra till notifyDataChanged senare
        order = barController.getOrder(firstInQueue);
        orderAdapter = new HostListViewAdapter(order);
        orderListView.setAdapter(orderAdapter);
        orderListView.setAdapter(orderAdapter);
    }
}

