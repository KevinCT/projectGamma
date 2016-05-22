package pekl.gasqueue.com.gasqueue.Activitiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.BarDBController;


/**
 * Created by User on 5/11/2016.
 */
public class HostActivity extends AppCompatActivity {
    private BarDBController bdbc;
    public HostActivity(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

    }
    protected void onStart() {
        super.onStart();
        final TextView mcurrentGuestView = (TextView) findViewById(R.id.currentGuestView);
        final TextView mdetailsView = (TextView) findViewById(R.id.detailsView);
        final TextView mnameView = (TextView) findViewById(R.id.nameView);
        Button mpushButton = (Button) findViewById(R.id.pushButton);
        Button mviewQueueButton = (Button) findViewById(R.id.viewQueueButton);

        assert mpushButton != null;
        mpushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bdbc.orderDone();
            }
        });

    }
}

