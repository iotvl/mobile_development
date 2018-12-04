package com.example.vlad.androidapp.Presenters;

import com.example.vlad.androidapp.Contracts.MainContract;
import com.example.vlad.androidapp.Entities.Product;

import java.util.List;

public class MainPresenterImpl implements MainContract.MainPresenter,
        MainContract.GetProductsIntractor.OnFinishedListener{

    private MainContract.MainView mainView;
    private MainContract.GetProductsIntractor getProductsIntractor;

    public MainPresenterImpl(MainContract.MainView mainView,
                             MainContract.GetProductsIntractor getProductsIntractor){
        this.mainView = mainView;
        this.getProductsIntractor = getProductsIntractor;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onRefreshListener() {
        getProductsIntractor.getProductsArrayList(this);
    }

    @Override
    public void requestDataFromServer() {
        getProductsIntractor.getProductsArrayList(this);
    }

    @Override
    public void onFinished(List<Product> productsArrayList) {
        if(mainView!=null){
            mainView.hideTextNoData();
            mainView.showRecyclerView();
            mainView.setDataToRecyclerView(productsArrayList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView!=null){
            mainView.onResponseFailure(t);
            mainView.hideRecyclerView();
            mainView.showTextNoData();
        }
    }

}
