package com.example.vlad.androidapp.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vlad.androidapp.NavigationManager;
import com.example.vlad.androidapp.R;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationManager.getNavigationManager().init(getSupportFragmentManager());
        NavigationManager.getNavigationManager().startMain();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }
        else {
            NavigationManager.getNavigationManager().navigateBack();
        }
    }
}