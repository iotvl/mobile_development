package com.example.vlad.androidapp.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.vlad.androidapp.R;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new MainFragment())
                .addToBackStack(null)
                .commit();
    }
}