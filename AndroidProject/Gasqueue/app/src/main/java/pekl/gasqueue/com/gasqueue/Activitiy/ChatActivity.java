package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;

import pekl.gasqueue.com.gasqueue.Activitiy.Adapter.ChatMessageAdapter;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ChatController;

public class ChatActivity extends AppCompatActivity  {
    private EditText messageInput;
    private Button sendBtn;
    private ChatController dbChatController;
    ChatMessageAdapter adapter;
    ListView chatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Firebase.setAndroidContext(this);
        dbChatController = new ChatController("https://dazzling-torch-9680.firebaseio.com/");
        messageInput =(EditText) findViewById(R.id.messageTextField);
        sendBtn=(Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!messageInput.getText().toString().equals("")) {
                    String text = messageInput.getText().toString();
                    dbChatController.setMessage(text);
                    dbChatController.sendMessage();
                    messageInput.setText("");
                }

            }
        });

        adapter = new ChatMessageAdapter("https://dazzling-torch-9680.firebaseio.com/Messages");
        chatView = (ListView)findViewById(R.id.chatView);
        chatView.setAdapter(adapter);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("username");
            dbChatController.setUserName(name);
        }



    }


}
