package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pekl.gasqueue.com.gasqueue.R;

public class WelcomeBarActivity extends AppCompatActivity implements View.OnClickListener {
    private Button editBarBtn;
    private Button manageBarBtn;
    private Button createBarBtn;
    private Class activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_bar);
        initView();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.editBarBtn:
                activity=EditCategoryActivity.class;
                break;
            case R.id.createBarBtn:
                activity = CreateBarActivity.class;
                break;
            case R.id.mangeBarBtn:
                activity=HostActivity.class;


        }
        nextActivity(activity);
    }
    private void initView(){
        editBarBtn = (Button) findViewById(R.id.editBarBtn);
        editBarBtn.setOnClickListener(this);
        manageBarBtn = (Button) findViewById(R.id.mangeBarBtn);
        manageBarBtn.setOnClickListener(this);
        createBarBtn = (Button) findViewById(R.id.createBarBtn);
        createBarBtn.setOnClickListener(this);

    }
    private void nextActivity(Class activity){
        Intent intentActivity= new Intent(this,activity);
        //clean code later only send this if its edit bar not createbar
        Bundle data;
        data=getIntent().getExtras();
        intentActivity.putExtras(data);
        startActivity(intentActivity);
    }

}
