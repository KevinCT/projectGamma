package pekl.gasqueue.com.gasqueue.control;

import pekl.gasqueue.com.gasqueue.model.FormatDate;
import pekl.gasqueue.com.gasqueue.model.Message;
import pekl.gasqueue.com.gasqueue.service.FirebaseDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;

/**
 * Created by kevin on 15/05/2016.
 */
public class ChatController {
    private Message chatMessage;
    private IDatabaseManager dbManagerChat;
    public ChatController(String databaseRef){
        chatMessage = new Message();
        dbManagerChat = new FirebaseDatabaseManager(databaseRef);

    }
    public void sendMessage(){
        setTimeStamp();
        dbManagerChat.sendObject("Messages",chatMessage);
    }
    public void setMessage(String message){
        chatMessage.setMessage(message);


    }
    public void setUserName(String username){
        chatMessage.setName(username);
    }


    private void setTimeStamp(){
        FormatDate currentDate = new FormatDate();
        chatMessage.setTimeStamp(currentDate.getCurrentDate());
    }




}
