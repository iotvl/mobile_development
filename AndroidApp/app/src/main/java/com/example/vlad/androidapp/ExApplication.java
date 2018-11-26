package com.example.vlad.androidapp;

import android.app.Application;


public class ExApplication extends Application {
    private static ExApplication instance = null;
    private int productId;

    public static ExApplication getInstance() {
        if (instance == null) {
            instance = new ExApplication();
        }
        return instance;
    }

    public void setProductId(int id){
        this.productId = id;
    }

    public int getProductId(){
        return this.productId;
    }
}
