package pekl.gasqueue.com.gasqueue.Activitiy.Fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pekl.gasqueue.com.gasqueue.R;


public class UserDialogFragment extends DialogFragment {
    public interface Listener{
        public void onNameSet(String username);
    }
    private Listener listener;

    public void setListener(Listener listener){
        this.listener=listener;
    }


    public UserDialogFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dialog, container, false);
        Button enterBtn =(Button)view.findViewById(R.id.enterBtn);
        final EditText usernameInput=(EditText)view.findViewById(R.id.usernameInput);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= usernameInput.getText().toString();
                listener.onNameSet(username);
            }
        });


        return view;
    }

}
