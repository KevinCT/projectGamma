package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;

import pekl.gasqueue.com.gasqueue.Adapter.ChatMessageAdapter;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ChatController;

public class ChatActivity extends AppCompatActivity  {
    private EditText messageInput;
    private Button sendBtn;
    private ChatController dbChatController;
    private ChatMessageAdapter adapter;
    private ListView chatView;
    private Bundle fragmentData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Firebase.setAndroidContext(this);
        dbChatController = new ChatController("https://dazzling-torch-9680.firebaseio.com/");
        initView();
        initAdapter();
        initUserName();
        initListener();
    }
    private void initView(){
        sendBtn=(Button) findViewById(R.id.sendBtn);
        messageInput=(EditText)findViewById(R.id.messageTextField);
        chatView = (ListView)findViewById(R.id.chatView);

    }
    private void initUserName(){
        fragmentData = getIntent().getExtras();
        if (fragmentData != null) {
            dbChatController.setUserName(fragmentData.getString("username"));
        }

    }
    private void initAdapter(){
        adapter = new ChatMessageAdapter("https://dazzling-torch-9680.firebaseio.com/Messages");
        chatView.setAdapter(adapter);

    }
    private void sendBtnClicked(){
        if(!messageInput.getText().toString().equals("")) {
            String text = messageInput.getText().toString();
            dbChatController.setMessage(text);
            dbChatController.sendMessage();
            messageInput.setText("");
        }

    }
    private void initListener(){
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBtnClicked();

            }
        });

    }




}
