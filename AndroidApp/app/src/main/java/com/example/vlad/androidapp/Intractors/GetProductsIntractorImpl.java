package com.example.vlad.androidapp.Intractors;

import android.view.View;

import com.example.vlad.androidapp.Contracts.MainContract;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.Entities.Products;
import com.example.vlad.androidapp.ExApplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductsIntractorImpl implements MainContract.GetProductsIntractor {
    @Override
    public void getProductsArrayList(final OnFinishedListener onFinishedListener) {
        Call<Products> call = ExApplication.getInstance().getLCBOclient().allProducts();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                onFinishedListener.onFinished(response.body().getResult());
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
