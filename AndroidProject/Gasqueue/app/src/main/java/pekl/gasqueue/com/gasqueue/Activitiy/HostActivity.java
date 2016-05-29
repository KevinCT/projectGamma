package pekl.gasqueue.com.gasqueue.Activitiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Set;

import pekl.gasqueue.com.gasqueue.Adapter.HostListViewAdapter;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.BarDBController;
import pekl.gasqueue.com.gasqueue.control.QueueController;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.model.StopWatch;


/**
 * Created by User on 5/11/2016.
 */
public class HostActivity extends AppCompatActivity {
    private BarDBController barController;
    private QueueController queueController;
    private HashMap<Product,Integer> order;
    private String firstInQueue;
    private StopWatch stopWatch;

    private HostListViewAdapter orderAdapter;
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

        stopWatch = new StopWatch();

        if(queueController.getQueueSize() > 0) {
            firstInQueue = queueController.getFirstInQueue();
        }
    }

    protected void onStart() {
        super.onStart();
        initializeViews();

        assert pushButton != null;
        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(queueController.getQueueSize() > 0) {
                    firstInQueue = queueController.getFirstInQueue();
                    barController.orderDone();
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

        order = barController.getOrder(firstInQueue);

        if(!(order == null)) {
            totalPriceTextView.setText(totalPrice() + "");


            orderAdapter = new HostListViewAdapter(order);

            orderListView.setAdapter(orderAdapter);
            stopWatch.runTimer();
            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {
                    while (stopWatch.getCurrentTime() != 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }

                        timerTextView.post(new Runnable() {

                            @Override
                            public void run() {
                                if (stopWatch.getCurrentTime() == 0) {
                                } else {
                                    timerTextView.setText(Integer.toString(stopWatch.getCurrentTime()));
                                }
                            }
                        });
                    }
                    stopWatch = new StopWatch();

                }
            };
            Thread myThread = new Thread(myRunnable);
            myThread.start();
        }

    }

    private int totalPrice() {
        int total = 0;
        Set<Product> products = order.keySet();
        for(Product product: products) {
            total = total + product.getPrice()*order.get(product);
        }
        return total;
    }
}

