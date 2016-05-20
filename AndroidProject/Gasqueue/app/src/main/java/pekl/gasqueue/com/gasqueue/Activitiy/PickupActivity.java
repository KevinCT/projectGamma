package pekl.gasqueue.com.gasqueue.Activitiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.StopWatch;


/**
 * Created by User on 5/11/2016.
 */
public class PickupActivity extends AppCompatActivity {

    public StopWatch sw = new StopWatch();
    public int pos = 10;

    public PickupActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pickup);

    }
    protected void onStart() {
        super.onStart();
        Button mcancelButton = (Button) findViewById(R.id.cancelButton);
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
                            while (sw.currentTime != 0) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    System.out.println("got interrupted!");
                                }


                                mpositionView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (sw.currentTime == 0) {
                                            mpositionView.setText("You're too slow!");
                                        } else {
                                            mpositionView.setText(Integer.toString(sw.currentTime));
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

