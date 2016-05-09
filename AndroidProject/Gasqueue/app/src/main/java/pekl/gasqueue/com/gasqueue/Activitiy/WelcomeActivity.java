
package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pekl.gasqueue.com.gasqueue.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        EditText codeInput= (EditText) findViewById(R.id.inputCodeField);

        Button logInBtn = (Button) findViewById(R.id.logInBtn);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp = new Intent(WelcomeActivity.this,CategoryActivity.class);
                startActivity(temp);
            }
        });
    }
}
