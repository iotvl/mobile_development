package com.example.vlad.androidapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.vlad.androidapp.Views.FavoritesFragment;
import com.example.vlad.androidapp.Views.MainFragment;
import com.example.vlad.androidapp.Views.ProductDetailFragment;

public class NavigationManager {

    private static NavigationManager navigationManager = null;
    private FragmentManager fragmentManager;

    public void init(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static NavigationManager getNavigationManager(){
        if (navigationManager==null){
            navigationManager = new NavigationManager();
        }
        return navigationManager;
    }

    public void navigateBack(){
        fragmentManager.popBackStack();
    }

    public void startMain() {
        Fragment fragment = new MainFragment();
        open(fragment);
    }

    public void startProductDetail() {
        Fragment fragment = new ProductDetailFragment();
        open(fragment);
    }

    public void startFavorites() {
        Fragment fragment = new FavoritesFragment();
        open(fragment);
    }

    private void open(Fragment fragment) {
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(fragment.toString())
                    .commit();
        }
    }
}