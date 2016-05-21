
package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import pekl.gasqueue.com.gasqueue.Authenticator;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.AuthenticatorController;

public class WelcomeActivity extends AppCompatActivity {
    private EditText passwordText;
    private Button loginBtn;
    private AuthenticatorController authController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Firebase.setAndroidContext(this);
        authController = new AuthenticatorController("https://dazzling-torch-9680.firebaseio.com/");
        passwordText = (EditText) findViewById(R.id.inputCodeField);
        loginBtn = (Button) findViewById(R.id.logInBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(authController.authenticate(passwordText.getText().toString()) && authController.getClientType().equals("Customer")) {
                    Intent temp = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(temp);
                }
                else if (authController.authenticate(passwordText.getText().toString()) && authController.getClientType().equals("Bar")){
                    Intent temp = new Intent(WelcomeActivity.this, CreateBarACtivity.class);
                    startActivity(temp);
                }

            }
        });
    }
}
