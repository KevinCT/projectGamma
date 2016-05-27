package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.gson.Gson;

import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.AuthenticatorController;
import pekl.gasqueue.com.gasqueue.model.Product;

public class CreateBarActivity extends AppCompatActivity {
    private Button createBarBtn;
    private EditText barPasswordInput;
    private EditText customerPasswordInput;
    private String customerPassword;
    private String barPassword;
    private TextView errorLabel;
    private TextView errorLabel2;
    private AuthenticatorController authenticatorController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bar_activity);
        Firebase.setAndroidContext(this);
        authenticatorController = new AuthenticatorController("https://dazzling-torch-9680.firebaseio.com/");
        initView();
        initListener();


    }
    private void initView(){
        createBarBtn = (Button) findViewById(R.id.createBarBtn);
        barPasswordInput = (EditText) findViewById(R.id.barPWInput);
        customerPasswordInput=(EditText)findViewById(R.id.customerPWInput);
        errorLabel=(TextView)findViewById(R.id.errorLabel);
        errorLabel.setVisibility(View.INVISIBLE);
        errorLabel2=(TextView)findViewById(R.id.errorLabel2);
        errorLabel2.setVisibility(View.INVISIBLE);


    }
    private void checkInput(){
        customerPassword=customerPasswordInput.getText().toString();
        barPassword=barPasswordInput.getText().toString();
        if(barPassword.length()==4 && customerPassword.length()==4 && !barPassword.equals(customerPassword)){
            setPassword(barPassword,customerPassword);
            authenticatorController.sendBarReference();
            nextActivity();
        }
        else{
            errorLabel.setVisibility(View.VISIBLE);
            errorLabel2.setVisibility(View.VISIBLE);
        }

    }
    private void nextActivity(){
        Intent temp = new Intent(CreateBarActivity.this,EditCategoryActivity.class);
        temp.putExtra("barPassword",barPassword);
        temp.putExtra("customerPassword",customerPassword);
        temp.putExtra("clientType",true);
        startActivity(temp);
    }
    private void initListener(){
        createBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });

    }
    private void setPassword(String barPassword, String customerPassword){
        authenticatorController.setBarPassword(barPassword);
        authenticatorController.setCustomerPassword(customerPassword);
    }

}
