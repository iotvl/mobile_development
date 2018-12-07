package com.example.vlad.androidapp.Contracts;

import com.example.vlad.androidapp.Entities.Product;

import java.util.List;

public interface MainContract {

    interface MainPresenter {
        void onDestroy();
        void onRefreshListener();
    }

    interface MainView {
        void showProductsView(List<Product> productsArrayList);
        void onResponseFailure(Throwable throwable);
        void showNoDataView();
    }

    interface GetProductsInteractor {
        interface OnFinishedListener {
            void onFinished(List<Product> productsArrayList);
            void onFailure(Throwable t);
        }

        void getProductsArrayList(OnFinishedListener onFinishedListener);

    }
}
