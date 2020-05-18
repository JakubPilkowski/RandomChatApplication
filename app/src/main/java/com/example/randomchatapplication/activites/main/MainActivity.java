package com.example.randomchatapplication.activites.main;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.authentication.AuthActivity;
import com.example.randomchatapplication.api.LoginConnection;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivityMainBinding;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.helpers.UserPreferences;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> implements Providers {


    @Override
    protected void initActivity(ActivityMainBinding binding) {
        binding.setViewModel(viewModel);
        viewModel.setProviders(this);
        viewModel.init();
//        viewModel.refreshToolbar();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        UserPreferences.initInstance(getApplicationContext());
        MockyConnection.init();
        //ProgressDialogManager.init(getApplicationContext());
//        LoginConnection.init();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        if(!UserPreferences.getINSTANCE().hasUser())
        {
            finish();
            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
        }
        super.onResume();
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
