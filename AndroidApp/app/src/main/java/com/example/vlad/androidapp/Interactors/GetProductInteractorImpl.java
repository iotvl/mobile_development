package com.example.vlad.androidapp.Interactors;

import com.example.vlad.androidapp.Contracts.ProductDetailContract;
import com.example.vlad.androidapp.Entities.ProductResponse;
import com.example.vlad.androidapp.ExApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductInteractorImpl implements ProductDetailContract.GetProductInteractor {
    @Override
    public void getProduct(final int id, final OnFinishedListener onFinishedListener) {
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
