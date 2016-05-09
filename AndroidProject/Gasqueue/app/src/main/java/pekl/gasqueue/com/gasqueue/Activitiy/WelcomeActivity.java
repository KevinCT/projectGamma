
package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pekl.gasqueue.com.gasqueue.R;

public class WelcomeActivity extends AppCompatActivity {
    private EditText passwordText;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        passwordText = (EditText) findViewById(R.id.inputCodeField);
        loginBtn = (Button) findViewById(R.id.logInBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //local atm, change later
                if(passwordText.getText().toString().equals("0000")) {
                    Intent temp = new Intent(WelcomeActivity.this,CreateBarACtivity.class);
                    startActivity(temp);
                }
                else if (passwordText.getText().toString().equals("1234")){
                    Intent temp = new Intent(WelcomeActivity.this, CategoryActivity.class);
                    startActivity(temp);
                }
            }
        });
    }
}
