package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.model.StopWatch;


/**
 * Created by Eric on 5/11/2016.
 */
public class PickupActivity extends AppCompatActivity {
    Context context = this;
    private StopWatch sw = new StopWatch();
    private int pos = 10; //customer.getQueuePosition
    public PickupActivity(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pickup);

    }
    protected void onStart() {
        super.onStart();
        Button mcancelButton = (Button) findViewById(R.id.pushButton);
        Button mviewOrderButton = (Button) findViewById(R.id.viewOrderButton);
        Button mpushButton = (Button) findViewById(R.id.pushButton);
        final TextView mstatusView = (TextView) findViewById(R.id.statusView);
        final TextView mpositionView = (TextView) findViewById(R.id.positionView);
        final TextView mtimeView = (TextView) findViewById(R.id.timeView);
        final Timer timer = new Timer();
        final int delay = 1000;
        final int period = 1000;
        mstatusView.setText("Your current position");
        mpositionView.setText(Integer.toString(pos));




        assert mcancelButton != null;
        mcancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //qc.queue.removeItem(customer);
                final AlertDialog.Builder okaybruh = new AlertDialog.Builder(context);
                okaybruh.setMessage("okay bruh");
                okaybruh.setCancelable(true);
                okaybruh.setPositiveButton(
                        "thanks mate",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                dialog.cancel();
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

        assert mviewOrderButton != null;
        mviewOrderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //insert switch to cart view
            }


        });

        assert mpushButton != null;
        System.out.println(mpushButton.toString());

        mpushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos--;
                mpositionView.setText(Integer.toString(pos));
                if (pos == 0) {

                    sw.runTimer();
                    mstatusView.setText("Time left to pay and pick up drink");


                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            while (sw.getCurrentTime() != 0) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    System.out.println("got interrupted!");
                                }


                                mpositionView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (sw.getCurrentTime() == 0) {
                                            mpositionView.setText("You're too slow!");
                                        } else {
                                            mpositionView.setText(Integer.toString(sw.getCurrentTime()));
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
        });
    }
}

