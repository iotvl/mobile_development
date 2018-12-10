package com.example.vlad.androidapp.Presenters;

import com.example.vlad.androidapp.Contracts.MainContract;
import com.example.vlad.androidapp.Entities.Product;

import java.util.List;

public class MainPresenterImpl implements MainContract.MainPresenter,
        MainContract.GetProductsInteractor.OnFinishedListener{

    private MainContract.MainView mainView;
    private MainContract.GetProductsInteractor getProductsInteractor;

    public MainPresenterImpl(MainContract.MainView mainView,
                             MainContract.GetProductsInteractor getProductsInteractor){
        this.mainView = mainView;
        this.getProductsInteractor = getProductsInteractor;
        getProductsInteractor.getProductsArrayList(this);
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onRefreshListener() {
        getProductsInteractor.getProductsArrayList(this);
    }

    @Override
    public void onFinished(List<Product> productsArrayList) {
        if(mainView!=null){
            mainView.showProductsView(productsArrayList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView!=null){
            mainView.onResponseFailure(t);
            mainView.showNoDataView();
        }
    }

}
