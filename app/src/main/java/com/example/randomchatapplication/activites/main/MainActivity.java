package com.example.randomchatapplication.activites.main;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.authentication.AuthActivity;
import com.example.randomchatapplication.api.LoginConnection;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivityMainBinding;
import com.example.randomchatapplication.helpers.MenuHelper;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.helpers.UserPreferences;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;
import com.example.randomchatapplication.ui.profiles.ProfilesFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> implements Providers {

    private DrawerLayout drawerLayout;


    @Override
    protected void initActivity(ActivityMainBinding binding) {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigator.attach(new ProfilesFragment(), ProfilesFragment.TAG);
        binding.setViewModel(viewModel);
        viewModel.setProviders(this);
        viewModel.init();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.MainTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean lightStatusBar() {
        return true;
    }

    @Override
    protected void onResume() {
        if (!UserPreferences.getINSTANCE().hasUser()) {
            finish();
            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
        } else {
            refreshToolbar();
            UserPreferences.getINSTANCE().clear();
        }
        super.onResume();
    }

    public void menuClick(View v) {
        MenuHelper.clickedMenu(this, Integer.parseInt(v.getTag().toString()));
    }

    public void closeDrawer() {
        drawerLayout.closeDrawers();
    }

    public void refreshToolbar(){
        viewModel.refreshToolbar();
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onSupportNavigateUp() {
        drawerLayout.openDrawer(Gravity.START);
        return true;
    }

    @Override
    protected Class<MainActivityViewModel> getViewModel() {
        return MainActivityViewModel.class;
    }

    @Override
    public int getIdFragmentContainer() {
        return R.id.main_activity_container;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Navigator getNavigator() {
        return navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return getCurrentFragment().getBinding();
    }

    @Override
    public BaseFragment getFragment() {
        return getCurrentFragment();
    }
}
