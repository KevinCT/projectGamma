package pekl.gasqueue.com.gasqueue.Activitiy.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pekl.gasqueue.com.gasqueue.Message;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.service.ValueChangeListener;

/**
 * Created by kevin on 19/05/2016.
 */
public class ChatMessageAdapter extends BaseAdapter {
    private List<Message> messageList= new ArrayList<>();
    ValueChangeListener listener;

    public ChatMessageAdapter(String databaseRef){
            listener = new ValueChangeListener(databaseRef) {
            @Override
            public void dataChanged(DataSnapshot dataSnapshot) {
                //clear list to avoid duplicate messages
                messageList.clear();
                for(DataSnapshot messageSnapshot:dataSnapshot.getChildren()){
                    messageList.add(messageSnapshot.getValue(Message.class));
                    notifyDataSetChanged();
                }
            }

        };

    }

    @Override

    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.indexOf(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView==null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout,parent,false);
        }
        else{
            view=convertView;
        }
        TextView messageView = (TextView)view.findViewById(R.id.messageView);
        messageView.setText(messageList.get(position).getMessage());
        TextView nameView = (TextView)view.findViewById(R.id.nameView);
        nameView.setText(messageList.get(position).getName());
        TextView timestampView =(TextView)view.findViewById(R.id.timestampView);
        timestampView.setText(messageList.get(position).getTimeStamp());

        return view;
    }
}
