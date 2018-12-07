package com.example.vlad.androidapp.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vlad.androidapp.Adapters.ProductAdapter;
import com.example.vlad.androidapp.Contracts.MainContract;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.Interactors.GetProductsInteractorImpl;
import com.example.vlad.androidapp.NavigationManager;
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
    @BindView(R.id.timer_button)
    protected Button timerButton;
    @BindView(R.id.favorites_button)
    protected Button favrites_button;

    private ProductAdapter productAdapter;
    private MainContract.MainPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        presenter = new MainPresenterImpl(this, new GetProductsInteractorImpl());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
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
            NavigationManager.getNavigationManager().startProductDetail();
        }
    };

    @OnClick({R.id.favorites_button, R.id.timer_button})
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.favorites_button:
                NavigationManager.getNavigationManager().startFavorites();
                break;
            case R.id.timer_button:
                NavigationManager.getNavigationManager().startTimer();
                break;
        }
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void showProductsView(List<Product> productsArrayList) {
        textNoData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        productAdapter.setProducts(productsArrayList);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        throwable.getCause();
    }

    @Override
    public void showNoDataView() {
        recyclerView.setVisibility(View.GONE);
        textNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
