package com.example.vlad.androidapp.Contracts;

import com.example.vlad.androidapp.Entities.Product;

import java.util.List;

public interface MainContract {

    interface MainPresenter {
        void onDestroy();
        void onRefreshListener();
        void requestDataFromServer();
    }

    interface MainView {
        void setDataToRecyclerView(List<Product> productsArrayList);
        void onResponseFailure(Throwable throwable);
        void hideTextNoData();
        void showTextNoData();
        void hideRecyclerView();
        void showRecyclerView();
    }

    interface GetProductsIntractor{
        interface OnFinishedListener {
            void onFinished(List<Product> productsArrayList);
            void onFailure(Throwable t);
        }

        void getProductsArrayList(OnFinishedListener onFinishedListener);

    }
}
