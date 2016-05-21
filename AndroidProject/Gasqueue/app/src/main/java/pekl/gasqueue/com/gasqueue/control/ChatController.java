package pekl.gasqueue.com.gasqueue.control;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import pekl.gasqueue.com.gasqueue.FormatDate;
import pekl.gasqueue.com.gasqueue.Message;
import pekl.gasqueue.com.gasqueue.service.DatabaseManager;

/**
 * Created by kevin on 15/05/2016.
 */
public class ChatController {
    private Message chatMessage;
    private DatabaseManager dbManagerChat;
    public ChatController(String databaseRef){
        chatMessage = new Message();
        dbManagerChat = new DatabaseManager(new Firebase(databaseRef));

    }
    public void sendMessage(){
        FormatDate currentDate = new FormatDate();
        chatMessage.setTimeStamp(currentDate.getCurrentDate());
        dbManagerChat.createChildReference("Messages").push().setValue(chatMessage);
    }
    public void setMessage(String message){
        chatMessage.setMessage(message);


    }
    public void setUserName(String username){
        chatMessage.setName(username);
    }

    public Query getMessageRef(){
        return dbManagerChat.createChildReference("Messages");


    }




}
