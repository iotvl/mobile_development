package com.example.vlad.androidapp.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vlad.androidapp.Adapters.ProductAdapter;
import com.example.vlad.androidapp.Contracts.MainContract;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.Intractors.GetProductsIntractorImpl;
import com.example.vlad.androidapp.Presenters.MainPresenterImpl;
import com.example.vlad.androidapp.R;
import com.example.vlad.androidapp.RecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment implements MainContract.MainView {
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.textNoData)
    protected TextView textNoData;
    @BindView(R.id.pullToRefresh)
    protected SwipeRefreshLayout pullToRefresh;

    private ProductAdapter productAdapter;
    private MainContract.MainPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        presenter = new MainPresenterImpl(this, new GetProductsIntractorImpl());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        presenter.requestDataFromServer();
        productAdapter = new ProductAdapter(recyclerItemClickListener);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefreshListener();
                pullToRefresh.setRefreshing(false);
            }
        });
        return view;
    }

    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Product product) {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new ProductDetailFragment())
                    .addToBackStack(null)
                    .commit();
        }
    };

    @OnClick(R.id.favorites_button)
    public void onClick(){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new FavoritesFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setDataToRecyclerView(List<Product> productsArrayList) {
        productAdapter.setProducts(productsArrayList);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        throwable.getCause();
    }

    @Override
    public void hideTextNoData() {
        textNoData.setVisibility(View.GONE);
    }

    @Override
    public void showTextNoData() {
        textNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRecyclerView() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
