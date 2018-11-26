package com.example.vlad.androidapp.ServerUtilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LCBOUtility {

    private static LCBOUtility instance = null;
    private static final String BASE_URL = "http://lcboapi.com";

    private LCBOClient LCBOclient;

    public static LCBOUtility getInstance() {
        if (instance == null) {
            instance = new LCBOUtility();
        }
        return instance;
    }

    private LCBOUtility() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.LCBOclient = retrofit.create(LCBOClient.class);
    }

    public LCBOClient getLCBOclient() {
        return this.LCBOclient;
    }
}
