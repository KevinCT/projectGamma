package pekl.gasqueue.com.gasqueue.Activitiy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pekl.gasqueue.com.gasqueue.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button menuBtn;
    private Button cartBtn;
    private Button chatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menuBtn=(Button) findViewById(R.id.menuBtn);
        cartBtn=(Button) findViewById(R.id.cartBtn);
        chatBtn=(Button) findViewById(R.id.chatBtn);
        menuBtn.setOnClickListener(this);
        cartBtn.setOnClickListener(this);
        chatBtn.setOnClickListener(this);





    }
    @Override
    public void onClick(View v) {
         Class activity=HomeActivity.class;
        switch (v.getId())
        {
            case R.id.menuBtn:
                activity=CategoryActivity.class;
                break;
            case R.id.chatBtn:
                activity=ChatActivity.class;
                break;
            case R.id.cartBtn:
                activity=ChatActivity.class;
                break;

        }
        Intent temp = new Intent(this, activity);
        startActivity(temp);

    }


}
