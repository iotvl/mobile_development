package com.example.vlad.androidapp;

import android.app.Application;

import com.example.vlad.androidapp.ServerUtilities.LCBOClient;
import com.example.vlad.androidapp.Views.ProductDetailFragment;

import retrofit2.converter.gson.GsonConverterFactory;


public class ExApplication extends Application {
    private static ExApplication instance = null;
    private static final String BASE_URL = "http://lcboapi.com";
    private LCBOClient LCBOclient;
    private int productId;

    public static ExApplication getInstance() {
        if (instance == null) {
            instance = new ExApplication();
            instance.buildRetrofit();
        }
        return instance;
    }

    public void setProductId(int id){
        this.productId = id;
    }

    public int getProductId() {
        return this.productId;
    }

    private void buildRetrofit() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.LCBOclient = retrofit.create(LCBOClient.class);
    }

    public LCBOClient getLCBOclient() {
        return this.LCBOclient;
    }

}
