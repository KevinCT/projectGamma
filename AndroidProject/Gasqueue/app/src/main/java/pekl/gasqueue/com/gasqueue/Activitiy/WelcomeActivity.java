
package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.AuthenticatorController;

public class WelcomeActivity extends AppCompatActivity {
    private EditText passwordText;
    private Button loginBtn;
    private AuthenticatorController authController;
    private Class activity;
    private String clientType;
    private TextView errorLbl;

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
        errorLbl =(TextView) findViewById(R.id.codeErrorLbl);
        errorLbl.setVisibility(View.INVISIBLE);
    }
    private void authenticate(){
        clientType=authController.authenticate(passwordText.getText().toString());
        if(clientType.equals("customer")) {

            activity=MainActivity.class;

        }
        else if (clientType.equals("bar")){

            activity=WelcomeBarActivity.class;
        }
        else {
            activity=null;
            errorLbl.setVisibility(View.VISIBLE);
        }
        nextActivity(activity);
    }
    private void nextActivity(Class activity){
        if(activity!=null) {
            Intent intentActivity = new Intent(this, activity);
            handleData(intentActivity);
            startActivity(intentActivity);
        }

    }
    private void initListener(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();

            }
        });

    }
    private void handleData(Intent intentActivity){
        intentActivity.putExtra("barPassword",authController.getBarPassword());
        intentActivity.putExtra("customerPassword",authController.getCustomerPassword());
        intentActivity.putExtra("clientType",true);
    }
}
