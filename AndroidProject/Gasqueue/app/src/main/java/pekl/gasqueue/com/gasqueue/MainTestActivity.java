package pekl.gasqueue.com.gasqueue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

import service.DatabaseManagerBar;

public class MainTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        Firebase.setAndroidContext(this);
        DatabaseManagerBar test = new DatabaseManagerBar();
        test.banUser("Victor");
    }
}
