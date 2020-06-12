package com.subrata.runtimefragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class HomeFragment extends Fragment {

    private Button buttonToNavigateFirstFragment;
    private EditText ed_message;
    private Button btn_send;

    OnMessageReadListener messageReadListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     *
     *Creating an Interface for the message to read
     */
    public interface OnMessageReadListener
    {
        public void onMessageRead(String message);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        buttonToNavigateFirstFragment = view.findViewById(R.id.b1);
        ed_message = view.findViewById(R.id.ed_message);
        btn_send = view.findViewById(R.id.btn_send);

        //Setting on click listener
        buttonToNavigateFirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new FirstFragment()).addToBackStack(null).commit();
            }
        });

        //When user clicks on send button
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = ed_message.getText().toString();
                messageReadListener.onMessageRead(message);
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        try {
            messageReadListener = (OnMessageReadListener) activity;
        }catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must override onMessageRead");
        }
    }
}