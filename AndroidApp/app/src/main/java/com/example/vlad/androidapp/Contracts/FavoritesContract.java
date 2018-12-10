package com.example.vlad.androidapp.Contracts;

import android.support.v4.app.FragmentActivity;

import com.example.vlad.androidapp.Entities.Product;

import java.util.List;

public interface FavoritesContract {
    interface FavoritesPresenter {
        void onDestroy();
    }

    interface FavoritesView {
        FragmentActivity getNowActivity();

        void showFavorites(List<Product> favoriteProducts);
        void onResponseFailure(Throwable throwable);
    }
}
