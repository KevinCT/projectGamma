package pekl.gasqueue.com.gasqueue.Activitiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

import pekl.gasqueue.com.gasqueue.Customer;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.BarDBController;
import pekl.gasqueue.com.gasqueue.control.QueueController;

/**
 * Created by User on 5/11/2016.
 */
public class PickupActivity extends AppCompatActivity {
    /*
    public QueueController qc;
    public Customer customer;
    public BarDBController bdbc;

    public PickupActivity(){

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pickup);

    }
    protected void onStart() {
        super.onStart();
        Button mcancelButton = (Button) findViewById(R.id.cancelButton);
        Button mviewOrderButton = (Button) findViewById(R.id.viewOrderButton);
        TextView mstatusView = (TextView) findViewById(R.id.statusView);
        TextView mpositionView = (TextView) findViewById(R.id.positionView);
        TextView mtimeView = (TextView) findViewById(R.id.timeView);

        assert mcancelButton != null;
        mcancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            qc.queue.removeItem(customer);

            }
        });

        assert mviewOrderButton != null;
        mviewOrderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //insert switch to cart view
            }


         });

        //mpositionView.setText(Integer.toString((bdbc.SentOrders - bdbc.Total)));
        if(mpositionView.getText() == "0"){
            customer.startTimer();
            mpositionView.setText("");
            while(customer.timer.isRunning()==true){
                mtimeView.setText(customer.timer.currentTime);
            }

        }
        //create code that displays the STATUS and POSITION of the guest and order

    }*/
}
