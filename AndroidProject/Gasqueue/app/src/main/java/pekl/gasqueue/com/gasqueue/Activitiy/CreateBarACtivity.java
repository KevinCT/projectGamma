package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pekl.gasqueue.com.gasqueue.R;

public class CreateBarActivity extends AppCompatActivity {
    private Button createBarBtn;
    private EditText barPasswordInput;
    private EditText customerPasswordInput;
    private String customerPassword;
    private String barPassword;
    private TextView errorLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bar_activity);
        initView();
        createBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               checkInput();
            }
        });


    }
    private void initView(){
        createBarBtn = (Button) findViewById(R.id.createBarBtn);
        barPasswordInput = (EditText) findViewById(R.id.barPWInput);
        customerPasswordInput=(EditText)findViewById(R.id.customerPWInput);
        errorLabel=(TextView)findViewById(R.id.errorLabel);
        errorLabel.setVisibility(View.GONE);


    }
    private void checkInput(){
        customerPassword=customerPasswordInput.getText().toString();
        barPassword=barPasswordInput.getText().toString();
        if(barPassword.length()==4 && customerPassword.length()==4 && !barPassword.equals(customerPassword)){
            nextActivity();
        }
        else{
            errorLabel.setVisibility(View.VISIBLE);
        }

    }
    private void nextActivity(){
        Intent temp = new Intent(CreateBarActivity.this, WelcomeActivity.class);
        temp.putExtra("barPassword",barPasswordInput.getText().toString());
        temp.putExtra("customerPassword",customerPasswordInput.getText().toString());
        startActivity(temp);
    }
}
