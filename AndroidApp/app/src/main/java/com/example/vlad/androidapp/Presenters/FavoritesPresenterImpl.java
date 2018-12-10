package com.example.vlad.androidapp.Presenters;

import android.database.Cursor;

import com.example.vlad.androidapp.Contracts.FavoritesContract;
import com.example.vlad.androidapp.Contracts.ProductDetailContract;
import com.example.vlad.androidapp.DatabaseHelper;
import com.example.vlad.androidapp.Entities.Product;

import java.util.ArrayList;
import java.util.List;

public class FavoritesPresenterImpl implements FavoritesContract.FavoritesPresenter,
        ProductDetailContract.GetProductInteractor.OnFinishedListener{

    private FavoritesContract.FavoritesView favoritesView;
    private ProductDetailContract.GetProductInteractor getProductInteractor;
    private DatabaseHelper mDatabaseHelper;
    private List<Product> favoriteProductsList;

    public FavoritesPresenterImpl(FavoritesContract.FavoritesView favoritesView,
                                  ProductDetailContract.GetProductInteractor getProductInteractor){
        this.favoritesView = favoritesView;
        this.getProductInteractor = getProductInteractor;
        this.mDatabaseHelper = new DatabaseHelper(favoritesView.getNowActivity());
        this.favoriteProductsList = new ArrayList<>();
        loadFavorites();
    }

    @Override
    public void onDestroy() {
        favoritesView = null;
    }

    private void loadFavorites() {
        favoriteProductsList.clear();
        Cursor data = mDatabaseHelper.getData();
        while (data.moveToNext()) {
            getProductInteractor.getProduct(data.getInt(0), this);
        }
    }

    @Override
    public void onFinished(Product product) {
        if (favoritesView!=null) {
            favoriteProductsList.add(product);
            favoritesView.showFavorites(favoriteProductsList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (favoritesView!=null){
            favoritesView.onResponseFailure(t);
        }
    }
}
