package com.example.vlad.androidapp.Presenters;

import com.example.vlad.androidapp.Contracts.ProductDetailContract;
import com.example.vlad.androidapp.DatabaseHelper;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.ExApplication;
import com.squareup.picasso.Picasso;

public class ProductDetailPresenterImpl implements ProductDetailContract.ProductDetailPresenter,
        ProductDetailContract.GetProductInteractor.OnFinishedListener {

    private ProductDetailContract.ProductDetailView productDetailView;
    private ProductDetailContract.GetProductInteractor getProductInteractor;
    private DatabaseHelper mDatabaseHelper;
    private int id;

    public ProductDetailPresenterImpl(ProductDetailContract.ProductDetailView productDetailView,
                                      ProductDetailContract.GetProductInteractor getProductInteractor) {
        this.productDetailView = productDetailView;
        this.getProductInteractor = getProductInteractor;
        this.mDatabaseHelper = new DatabaseHelper(productDetailView.getNowActivity());
        this.id = ExApplication.getInstance().getProductId();
        loadProduct();
    }

    @Override
    public void onDestroy() {
        productDetailView = null;
    }

    private void loadProduct() {
        int id = ExApplication.getInstance().getProductId();
        getProductInteractor.getProduct(id,this);
    }

    @Override
    public void deleteFromFavorite(){
        mDatabaseHelper.deleteData(id);
        productDetailView.setButton(false);
    }

    @Override
    public void addToFavorite(){
        mDatabaseHelper.insertData(id);
        productDetailView.setButton(true);
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
            productDetailView.setButton(mDatabaseHelper.checkIsDataAlreadyInDb(id));
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (productDetailView!=null){
            productDetailView.onResponseFailure(t);
        }
    }
}
