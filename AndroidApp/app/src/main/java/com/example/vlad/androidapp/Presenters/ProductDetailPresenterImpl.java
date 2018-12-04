package com.example.vlad.androidapp.Presenters;

import android.content.Context;

import com.example.vlad.androidapp.Contracts.ProductDetailContract;
import com.example.vlad.androidapp.DatabaseHelper;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.ExApplication;
import com.squareup.picasso.Picasso;

public class ProductDetailPresenterImpl implements ProductDetailContract.ProductDetailPresenter,
        ProductDetailContract.GetProductIntractor.OnFinishedListener {

    private ProductDetailContract.ProductDetailView productDetailView;
    private ProductDetailContract.GetProductIntractor getProductIntractor;
    private DatabaseHelper mDatabaseHelper;
    private int id;

    public ProductDetailPresenterImpl(ProductDetailContract.ProductDetailView productDetailView,
                                      ProductDetailContract.GetProductIntractor getProductIntractor) {
        this.productDetailView = productDetailView;
        this.getProductIntractor = getProductIntractor;
        this.mDatabaseHelper = new DatabaseHelper(productDetailView.getNowActivity());
        this.id = ExApplication.getInstance().getProductId();
    }

    @Override
    public void onDestroy() {
        productDetailView = null;
    }

    @Override
    public void requestDataFromServer() {
        getProductIntractor.getProduct(this);
    }

    @Override
    public void chooseDeleteOrAddButtonBasedOnDbData(){
        if (mDatabaseHelper.checkIsDataAlreadyInDb(id)){
            productDetailView.goneAddVisibleDeleteButtons();
        } else {
            productDetailView.goneDeleteVisibleAddButtons();
        }
    }

    @Override
    public void deleteDataFromDb(){
        mDatabaseHelper.deleteData(id);
        productDetailView.goneDeleteVisibleAddButtons();
    }

    @Override
    public void addDataToDb(){
        mDatabaseHelper.insertData(id);
        productDetailView.goneAddVisibleDeleteButtons();
    }

    @Override
    public void onFinished(Product product) {
        if (productDetailView!=null){
            productDetailView.setImageView(Picasso.get().load(product.getImageUrl()));
            productDetailView.setTitleText(product.getName());
            productDetailView.setPriceText(product.getPriceInCents()/100.0);
            productDetailView.setAlcoholText(product.getAlcoholContent() / 100.0);
            productDetailView.setPackageText(product.getPackage());
            productDetailView.setOriginText(product.getOrigin());
            productDetailView.setProducerText(product.getProducerName());
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (productDetailView!=null){
            productDetailView.onResponseFailure(t);
        }
    }
}
