package com.example.vlad.androidapp.Activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vlad.androidapp.Adapters.ProductAdapter;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.Entities.Products;
import com.example.vlad.androidapp.R;
import com.example.vlad.androidapp.ServerUtilities.LCBOUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.textNoData)
    protected TextView textNoData;
    @BindView(R.id.pullToRefresh)
    protected SwipeRefreshLayout pullToRefresh;

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        productAdapter = new ProductAdapter();
        loadData();
        recyclerView.setAdapter(productAdapter);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                pullToRefresh.setRefreshing(false);
            }
        });
    }

    @OnClick(R.id.favorites_button)
    public void onClick(View view){
        Intent openFavoritesActivity = new Intent(view.getContext(), FavoritesActivity.class);
        view.getContext().startActivity(openFavoritesActivity);
    }

    public void loadData() {
        Call<Products> call = LCBOUtility.getInstance().getLCBOclient().allProducts();

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                textNoData.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                List<Product> products = response.body().getResult();
                productAdapter.setProducts(products);

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                textNoData.setVisibility(View.VISIBLE);
                t.getCause();
            }
        });
    }

}