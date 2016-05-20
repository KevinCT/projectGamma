package pekl.gasqueue.com.gasqueue.Activitiy.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pekl.gasqueue.com.gasqueue.Activitiy.ChatActivity;
import pekl.gasqueue.com.gasqueue.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
   /* public interface Listener{
        public void onNameSet(String username);
    }
    private Listener listener;

    public void setListener(Listener listener){
        this.listener=listener;
    }

*/

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        Button confirmationBtn = (Button)view.findViewById(R.id.okBtn);
        final EditText nameInput = (EditText)view.findViewById(R.id.nameInput);
        confirmationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // listener.onNameSet(nameInput.getText().toString());
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("username",nameInput.getText().toString());
                startActivity(intent);

            }
        });
        return view;
    }

}
