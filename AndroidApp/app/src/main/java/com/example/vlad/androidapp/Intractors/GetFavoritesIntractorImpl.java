package com.example.vlad.androidapp.Intractors;

import com.example.vlad.androidapp.Contracts.FavoritesContract;
import com.example.vlad.androidapp.Contracts.MainContract;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.Entities.ProductResponse;
import com.example.vlad.androidapp.ExApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFavoritesIntractorImpl implements FavoritesContract.GetFavoritesProductIntractor {
    @Override
    public void getProduct(int id, final FavoritesContract.GetFavoritesProductIntractor.OnFinishedListener onFinishedListener) {
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

