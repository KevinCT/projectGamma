package pekl.gasqueue.com.gasqueue.Activitiy;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import pekl.gasqueue.com.gasqueue.FormatDate;
import pekl.gasqueue.com.gasqueue.Message;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.service.DatabaseManager;

public class ChatActivity extends AppCompatActivity {
    private EditText messageInput;
    private Button sendBtn;
    private DatabaseManager chatDBManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        chatDBManager = new DatabaseManager(new Firebase("https://dazzling-torch-9680.firebaseio.com/"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageInput =(EditText) findViewById(R.id.messageTextField);
        sendBtn=(Button) findViewById(R.id.sendBtn);
        final Firebase messageRef =chatDBManager.createChildReference("Messages");

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormatDate currentDate = new FormatDate();
                String text = messageInput.getText().toString();
                Message message = new Message(text, Settings.Secure.ANDROID_ID,currentDate.getCurrentDate());
                messageRef.push().setValue(message);

            }
        });
        FirebaseListAdapter<Message> adapter = new FirebaseListAdapter<Message>(this,Message.class,android.R.layout.simple_list_item_2,messageRef) {
           @Override
           protected void populateView(View view, Message message, int i) {
               TextView text =(TextView)view.findViewById(android.R.id.text1);
               text.setText(message.getName());
               TextView text2 = (TextView)view.findViewById(android.R.id.text2);
               //create own xml later
               text2.setText(message.getMessage() +"                    "+ message.getTimeStamp());


           }
       };

        ListView chatView = (ListView)findViewById(R.id.chatView);
        chatView.setAdapter(adapter);


    }

}
