package com.example.vlad.androidapp.Intractors;

import com.example.vlad.androidapp.Contracts.ProductDetailContract;
import com.example.vlad.androidapp.Entities.ProductResponse;
import com.example.vlad.androidapp.Entities.Products;
import com.example.vlad.androidapp.ExApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductIntractorImpl implements ProductDetailContract.GetProductIntractor {
    @Override
    public void getProduct(final OnFinishedListener onFinishedListener) {
        int id = ExApplication.getInstance().getProductId();
        Call<ProductResponse> call = ExApplication.getInstance().getLCBOclient().getProduct(id);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                onFinishedListener.onFinished(response.body().getProduct());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
