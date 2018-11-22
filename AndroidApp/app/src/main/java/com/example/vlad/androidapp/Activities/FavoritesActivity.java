package com.example.vlad.androidapp.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vlad.androidapp.Adapters.ProductAdapter;
import com.example.vlad.androidapp.DatabaseHelper;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.Entities.ProductResponse;
import com.example.vlad.androidapp.R;
import com.example.vlad.androidapp.ServerUtilities.LCBOUtility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    DatabaseHelper mDatabaseHelper;
    private ProductAdapter productAdapter;
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDatabaseHelper = new DatabaseHelper(this);
        productAdapter = new ProductAdapter();
        getDbData();
        recyclerView.setAdapter(productAdapter);
    }

    public void getDbData() {
        Cursor data = mDatabaseHelper.getData();
        while (data.moveToNext()) {
            loadData(data.getInt(0));
        }
    }

    public void loadData(int id) {
        products = new ArrayList<>();
        Call<ProductResponse> call = LCBOUtility.getInstance().getLCBOclient().getProduct(id);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Product product = response.body().getProduct();
                products.add(product);
                productAdapter.setProducts(products);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                t.getCause();
            }
        });
    }
}
