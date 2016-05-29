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
    private Intent intentActivity;
    private Button confirmationBtn;
    private EditText nameInput;
    private View view;


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        initView();
        initListener();
        return view;
    }
    private void initView(){
        confirmationBtn = (Button)view.findViewById(R.id.okBtn);
        nameInput = (EditText)view.findViewById(R.id.nameInput);

    }
    private void nextActivity(){
        intentActivity = new Intent(getActivity(), ChatActivity.class);
        intentActivity.putExtra("username",nameInput.getText().toString());
        startActivity(intentActivity);

    }
    private void initListener(){
        confirmationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });

    }


}
