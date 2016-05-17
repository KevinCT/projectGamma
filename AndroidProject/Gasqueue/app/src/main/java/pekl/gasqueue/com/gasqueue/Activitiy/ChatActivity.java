package pekl.gasqueue.com.gasqueue.Activitiy;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;


import pekl.gasqueue.com.gasqueue.Activitiy.Fragments.UserDialogFragment;
import pekl.gasqueue.com.gasqueue.Message;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ChatController;

public class ChatActivity extends AppCompatActivity implements UserDialogFragment.Listener {
    private EditText messageInput;
    private Button sendBtn;
    private ChatController dbChatController;
    private UserDialogFragment userFragment;

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
                String text = messageInput.getText().toString();
                dbChatController.setMessage(text);
                dbChatController.sendMessage();

            }
        });
        FirebaseListAdapter<Message> adapter = new FirebaseListAdapter<Message>(this,Message.class,android.R.layout.simple_list_item_2,dbChatController.getMessage()) {
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
        showDialog();



    }
    public void showDialog(){
        FragmentManager manager = getFragmentManager();
        userFragment= new UserDialogFragment();
        userFragment.setCancelable(false);
        userFragment.show(manager,"hi");
        userFragment.setListener(this);

    }


    @Override
    public void onNameSet(String username) {
        dbChatController.setUserName(username);
        userFragment.dismiss();
    }
}
