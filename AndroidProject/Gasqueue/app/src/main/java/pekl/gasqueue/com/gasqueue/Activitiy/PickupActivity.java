package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.CustomerDBController;
import pekl.gasqueue.com.gasqueue.model.Cart;
import pekl.gasqueue.com.gasqueue.model.StopWatch;
import pekl.gasqueue.com.gasqueue.service.ChildChangeListener;
import pekl.gasqueue.com.gasqueue.service.IChildChangeListener;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

/**
 * Created by Eric on 5/11/2016.
 */
public class PickupActivity extends AppCompatActivity {
    private Button cancelButton;
    private Button viewOrderButton;
    private TextView statusView;
    private TextView positionView;
    public Cart cart = new Cart(); //wtf
    Context context = this;
    private StopWatch sw = new StopWatch();
    private CustomerDBController cdbc;
    private Integer pos;
    public PickupActivity(){


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        Firebase.setAndroidContext(this);
        cdbc=CustomerDBController.getInstance();
    }
    protected void onStart() {
        super.onStart();
        cancelButton = (Button) findViewById(R.id.cancelButton);
        viewOrderButton = (Button) findViewById(R.id.viewOrderButton);
        statusView = (TextView) findViewById(R.id.statusView);
        positionView = (TextView) findViewById(R.id.positionView);
        statusView.setText("Your current position");
        try{
            pos = cdbc.getQueuePosition();
            System.out.println(pos.toString() + " wolowolowolowolo");
            updateView(pos);
        }catch(NullPointerException e){
              System.out.println("Error");
        }
        //positionView.setText(pos.toString() + "");

        assert cancelButton != null;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //qc.queue.removeItem(customer);
                final AlertDialog.Builder okaybruh = new AlertDialog.Builder(context);
                okaybruh.setMessage("Your order has been canceled.");
                okaybruh.setCancelable(true);
                okaybruh.setPositiveButton(
                        "Thanks!",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                dialog.cancel();
                                backToMain();
                            }
                        });

                AlertDialog.Builder areYouSure = new AlertDialog.Builder(context);
                areYouSure.setMessage("Are you sure you want to cancel your current order?");
                areYouSure.setCancelable(true);
                areYouSure.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                dialog.cancel();
                                cart.clearCar();// <-empties your cart and removes order form queue
                                cdbc.cancelOrder();
                                AlertDialog bruh = okaybruh.create();
                                bruh.show();
                                }
                        });
                areYouSure.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = areYouSure.create();
                alert.show();
                }
        });

        assert viewOrderButton != null;
        viewOrderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pos--; //IM GONNA FIX THIS BRUHHHH -shao
                updateView(pos);
                checkPosition();
            }
        });
    }

    public void backToMain(){
        Intent temp = new Intent(this, MainActivity.class);
        startActivity(temp);
    }

    public void updateView(Integer position){
        positionView.setText(Integer.toString(position));
    }

    public void checkPosition(){
        if (pos == 0) {
            sw.runTimer();
            statusView.setText("Time left to pay and pick up drink");

            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {
                    while (sw.getCurrentTime() != 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println("got interrupted!");
                        }

                        positionView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (sw.getCurrentTime() == 0) {
                                    positionView.setText("You're too slow!");
                                } else {
                                    positionView.setText(Integer.toString(sw.getCurrentTime()));
                                }
                            }
                        });
                    }
                }
            };
            Thread myThread = new Thread(myRunnable);
            myThread.start();
        }
    }

    private void updateQueuePosition() {
        IChildChangeListener childBanListListener = new ChildChangeListener("https://dazzling-torch-9680.firebaseio.com/customerNumberServed") {

            @Override
            public void childAdded(DataSnapshot data, String s) {

            }

            @Override
            public void childChanged(DataSnapshot data, String s) {
                updateView(CustomerDBController.getInstance().getQueuePosition());
                checkPosition();
            }

            @Override
            public void childRemoved(DataSnapshot data) {

            }

            @Override
            public void childMoved(DataSnapshot data, String s) {

            }
        };

    }
}

