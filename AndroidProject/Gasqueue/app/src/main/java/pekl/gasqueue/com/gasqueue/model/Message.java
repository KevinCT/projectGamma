package pekl.gasqueue.com.gasqueue.model;



/**
 * Created by kevin on 09/05/2016.
 * Class representing a message used in the chat.
 */
public class Message {
    private String message;
    private String name;
    private String timeStamp;

    //empty constructor needed by firebase
    public Message() {

    }

    public Message(String message, String name, String timeStamp) {
        this.message = message;
        this.name = name;
        this.timeStamp=timeStamp;

    }
    public void setName(String name){
        this.name=name;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public void setTimeStamp(String timeStamp){
        this.timeStamp=timeStamp;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }


}
