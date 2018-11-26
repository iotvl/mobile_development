package com.example.vlad.androidapp.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vlad.androidapp.DatabaseHelper;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.Entities.ProductResponse;
import com.example.vlad.androidapp.ExApplication;
import com.example.vlad.androidapp.R;
import com.example.vlad.androidapp.ServerUtilities.LCBOUtility;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
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

    DatabaseHelper mDatabaseHelper;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        ButterKnife.bind(this);
        id = ExApplication.getInstance().getProductId();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDatabaseHelper = new DatabaseHelper(this);
        if (mDatabaseHelper.checkIsDataAlreadyInDb(id)) {
            goneAddVisibleDeleteButtons();
        } else {
            goneDeleteVisibleAddButtons();
        }
        loadData();
    }

    @OnClick({R.id.add_button, R.id.delete_button, R.id.product_image})
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.add_button:
                mDatabaseHelper.insertData(id);
                goneAddVisibleDeleteButtons();
                break;
            case R.id.delete_button:
                mDatabaseHelper.deleteData(id);
                goneDeleteVisibleAddButtons();
                break;
            case R.id.product_image:
                fullScreen();
                break;
        }
    }

    public void goneDeleteVisibleAddButtons() {
        deleteButton.setVisibility(View.GONE);
        addButton.setVisibility(View.VISIBLE);
    }

    public void goneAddVisibleDeleteButtons() {
        addButton.setVisibility(View.GONE);
        deleteButton.setVisibility(View.VISIBLE);
    }

    public void loadData() {
        Call<ProductResponse> call = LCBOUtility.getInstance().getLCBOclient().getProduct(id);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Product product = response.body().getProduct();
                Picasso.get().load(product.getImageUrl()).into(imageView);
                titleTextView.setText("Name: " + product.getName());
                priceTextView.setText("Price: " + product.getPriceInCents() / 100.0 + "$");
                alcoholTextView.setText("Alcohol content: " + product.getAlcoholContent() / 100.0 + "%");
                packageTextView.setText("Package: " + product.getPackage());
                originTextView.setText("Origin: " + product.getOrigin());
                producerTextView.setText("Producer name: " + product.getProducerName());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                t.getCause();
            }
        });
    }

    public void fullScreen() {
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
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
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }
}
