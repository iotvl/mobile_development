package com.example.vlad.androidapp.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vlad.androidapp.Adapters.ProductAdapter;
import com.example.vlad.androidapp.Contracts.FavoritesContract;
import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.Interactors.GetProductInteractorImpl;
import com.example.vlad.androidapp.NavigationManager;
import com.example.vlad.androidapp.Presenters.FavoritesPresenterImpl;
import com.example.vlad.androidapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends Fragment implements FavoritesContract.FavoritesView {
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    private FavoritesContract.FavoritesPresenter presenter;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_activity, container, false);
        ButterKnife.bind(this, view);
        presenter = new FavoritesPresenterImpl(this, new GetProductInteractorImpl());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productAdapter = new ProductAdapter();
        return view;
    }

    @Override
    public void showFavorites(List<Product> favoriteProducts) {
        productAdapter.setProducts(favoriteProducts);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public FragmentActivity getNowActivity() {
        return getActivity();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        throwable.getCause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
