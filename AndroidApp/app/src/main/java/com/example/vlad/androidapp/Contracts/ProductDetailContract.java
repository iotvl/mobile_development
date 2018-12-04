package com.example.vlad.androidapp.Contracts;

import android.support.v4.app.FragmentActivity;

import com.example.vlad.androidapp.Entities.Product;
import com.squareup.picasso.RequestCreator;

public interface ProductDetailContract {
    interface ProductDetailPresenter {
        void onDestroy();
        void requestDataFromServer();

        void chooseDeleteOrAddButtonBasedOnDbData();

        void deleteDataFromDb();

        void addDataToDb();
    }

    interface ProductDetailView {
        void onResponseFailure(Throwable throwable);
        void goneDeleteVisibleAddButtons();
        void goneAddVisibleDeleteButtons();
        void setImageView(RequestCreator load);
        void setTitleText(String title);
        void setPriceText(double price);
        void setAlcoholText(double alcohol);
        void setPackageText(String mPackage);
        void setOriginText(String origin);
        void setProducerText(String producer);

        FragmentActivity getNowActivity();
    }

    interface GetProductIntractor{
        interface OnFinishedListener {
            void onFinished(Product product);
            void onFailure(Throwable t);
        }

        void getProduct(ProductDetailContract.GetProductIntractor.OnFinishedListener onFinishedListener);

    }
}
