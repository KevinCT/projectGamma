package pekl.gasqueue.com.gasqueue.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import pekl.gasqueue.com.gasqueue.model.Message;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.service.ValueChangeListener;

/**
 * Created by kevin on 19/05/2016.
 */
public class ChatMessageAdapter extends BaseAdapter {
    private List<Message> messageList;
    private ValueChangeListener listener;
    private View view;
    private TextView messageView;
    private TextView nameView;
    private TextView timestampView;

    public ChatMessageAdapter(String databaseRef){
        messageList=new ArrayList<>();
        initListener(databaseRef);
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
        if(convertView==null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout,parent,false);
        }
        else{
            view=convertView;
        }
        messageView = (TextView)view.findViewById(R.id.messageView);
        nameView = (TextView)view.findViewById(R.id.nameView);
        timestampView =(TextView)view.findViewById(R.id.timestampView);
        messageView.setText(messageList.get(position).getMessage());
        nameView.setText(messageList.get(position).getName());
        timestampView.setText(messageList.get(position).getTimeStamp());

        return view;
    }
    private void initListener(String databaseRef){
        listener = new ValueChangeListener(databaseRef) {
            @Override
            public void dataChanged(DataSnapshot dataSnapshot) {
                messageList.clear();
                for(DataSnapshot messageSnapshot:dataSnapshot.getChildren()){
                    messageList.add(messageSnapshot.getValue(Message.class));
                    notifyDataSetChanged();
                }
            }

        };
    }


}
