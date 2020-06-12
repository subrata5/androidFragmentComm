package com.subrata.runtimefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnMessageReadListener {

    public static FragmentManager fragmentManager;
    private TextView txt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_container) != null){

            //Activity resumed. If not given would lead to overload of fragment
            if(savedInstanceState != null)
            {
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.fragment_container, homeFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onMessageRead(String message) {
            txt_message =  findViewById(R.id.txt_meesgae);
            txt_message.setText(message);
    }
}