package com.example.vlad.androidapp.Presenters;

import android.content.Context;
import android.database.Cursor;

import com.example.vlad.androidapp.Contracts.FavoritesContract;
import com.example.vlad.androidapp.DatabaseHelper;
import com.example.vlad.androidapp.Entities.Product;

import java.util.ArrayList;
import java.util.List;

public class FavoritesPresenterImpl implements FavoritesContract.FavoritesPresenter,
        FavoritesContract.GetFavoritesProductIntractor.OnFinishedListener{

    private FavoritesContract.FavoritesView favoritesView;
    private FavoritesContract.GetFavoritesProductIntractor getFavoritesProductIntractor;
    private DatabaseHelper mDatabaseHelper;
    private List<Product> productList;

    public FavoritesPresenterImpl(FavoritesContract.FavoritesView favoritesView,
                                  FavoritesContract.GetFavoritesProductIntractor getFavoritesProductIntractor){
        this.favoritesView = favoritesView;
        this.getFavoritesProductIntractor = getFavoritesProductIntractor;
        this.mDatabaseHelper = new DatabaseHelper(favoritesView.getNowActivity());
        this.productList = new ArrayList<>();
    }

    @Override
    public void onDestroy() {
        favoritesView = null;
    }

    @Override
    public void requestDataFromServer() {
        productList.clear();
        Cursor data = mDatabaseHelper.getData();
        while (data.moveToNext()) {
            getFavoritesProductIntractor.getProduct(data.getInt(0), this);
        }
    }

    @Override
    public void onFinished(Product product) {
        if (favoritesView!=null) {
            productList.add(product);
            favoritesView.setDataToRecyclerView(productList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (favoritesView!=null){
            favoritesView.onResponseFailure(t);
        }
    }
}
