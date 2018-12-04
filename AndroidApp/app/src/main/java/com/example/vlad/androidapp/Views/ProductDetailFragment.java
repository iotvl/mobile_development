package com.example.vlad.androidapp.Views;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vlad.androidapp.Contracts.ProductDetailContract;
import com.example.vlad.androidapp.Intractors.GetProductIntractorImpl;
import com.example.vlad.androidapp.Presenters.ProductDetailPresenterImpl;
import com.example.vlad.androidapp.R;
import com.squareup.picasso.RequestCreator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailFragment extends Fragment implements ProductDetailContract.ProductDetailView {
    @BindView(R.id.product_image)
    ImageView imageView;
    @BindView(R.id.product_title)
    protected TextView titleTextView;
    @BindView(R.id.product_price)
    protected TextView priceTextView;
    @BindView(R.id.product_alcohol)
    protected TextView alcoholTextView;
    @BindView(R.id.product_package)
    protected TextView packageTextView;
    @BindView(R.id.product_origin)
    protected TextView originTextView;
    @BindView(R.id.product_producer)
    protected TextView producerTextView;
    @BindView(R.id.add_button)
    protected Button addButton;
    @BindView(R.id.delete_button)
    protected Button deleteButton;

    private ProductDetailContract.ProductDetailPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detail, container, false);
        ButterKnife.bind(this, view);
        presenter = new ProductDetailPresenterImpl(this, new GetProductIntractorImpl());
        presenter.chooseDeleteOrAddButtonBasedOnDbData();
        presenter.requestDataFromServer();
        return view;
    }

    @OnClick({R.id.add_button, R.id.delete_button, R.id.product_image})
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.add_button:
                presenter.addDataToDb();
                break;
            case R.id.delete_button:
                presenter.deleteDataFromDb();
                break;
            case R.id.product_image:
                fullScreen();
                break;
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        throwable.getCause();
    }

    @Override
    public void goneDeleteVisibleAddButtons() {
        deleteButton.setVisibility(View.GONE);
        addButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneAddVisibleDeleteButtons() {
        addButton.setVisibility(View.GONE);
        deleteButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void setImageView(RequestCreator load) {
        load.into(imageView);
    }

    @Override
    public void setTitleText(String title) {
        titleTextView.setText("Name: " + title);
    }

    @Override
    public void setPriceText(double price) {
        priceTextView.setText("Price: " + price + "$");
    }

    @Override
    public void setAlcoholText(double alcohol) {
        alcoholTextView.setText("Alcohol content: " + alcohol + "%");
    }

    @Override
    public void setPackageText(String mPackage) {
        packageTextView.setText("Package: " + mPackage);
    }

    @Override
    public void setOriginText(String origin) {
        originTextView.setText("Origin: " + origin);
    }

    @Override
    public void setProducerText(String producer) {
        producerTextView.setText("Producer name: " + producer);
    }

    @Override
    public FragmentActivity getNowActivity() {
        return getActivity();
    }

    private void fullScreen() {
        int uiOptions = getActivity().getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }
        getActivity().getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
