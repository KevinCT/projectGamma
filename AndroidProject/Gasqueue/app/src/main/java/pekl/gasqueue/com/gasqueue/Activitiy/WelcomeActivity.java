
package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.AuthenticatorController;

public class WelcomeActivity extends AppCompatActivity {
    private EditText passwordText;
    private Button loginBtn;
    private AuthenticatorController authController;
    private Class activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Firebase.setAndroidContext(this);
        authController = new AuthenticatorController("https://dazzling-torch-9680.firebaseio.com/");


    }

    @Override
    protected void onStart(){
        super.onStart();
        initView();
        initListener();


    }
    private void initView(){
        passwordText=(EditText)findViewById(R.id.inputCodeField);
        loginBtn = (Button) findViewById(R.id.logInBtn);
    }
    private void authenticate(){
        if(authController.authenticate(passwordText.getText().toString()).equals("customer")) {
            activity=MainActivity.class;

        }
        else if (authController.authenticate(passwordText.getText().toString()).equals("bar")){
            activity=WelcomeBarActivity.class;
        }
        else if(authController.authenticate(passwordText.getText().toString()).equals("empty")){
            activity=WelcomeActivity.class;
        }
        if(activity!=null)
        nextActivity(activity);
    }
    private void nextActivity(Class activity){
        Intent intentActivity= new Intent(this,activity);
        startActivity(intentActivity);

    }
    private void initListener(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();

            }
        });

    }
}
