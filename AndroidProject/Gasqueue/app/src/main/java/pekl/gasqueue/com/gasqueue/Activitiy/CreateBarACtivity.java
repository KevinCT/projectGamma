package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import pekl.gasqueue.com.gasqueue.Bar;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.AuthenticatorController;

public class CreateBarACtivity extends AppCompatActivity {
    private AuthenticatorController AuthController;
    private Button createBarBtn;
    private EditText passwordText;

    //private Bar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bar_activity);
        Firebase.setAndroidContext(this);
  //      bar= new Bar();
        this.AuthController=new AuthenticatorController("https://dazzling-torch-9680.firebaseio.com/");
        createBarBtn = (Button) findViewById(R.id.createBarBtn);
        passwordText = (EditText) findViewById(R.id.passwordTextField);
        createBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthController.setPassword(passwordText.getText().toString());
                AuthController.sendBarReference();
            }
        });


    }
}
