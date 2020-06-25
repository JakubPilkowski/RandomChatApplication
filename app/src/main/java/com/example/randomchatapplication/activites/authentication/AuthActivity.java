package com.example.randomchatapplication.activites.authentication;

import androidx.databinding.ViewDataBinding;

import android.app.Activity;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.api.LoginConnection;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivityAuthBinding;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.helpers.UserPreferences;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;
import com.example.randomchatapplication.ui.auth.login.LoginFragment;

public class AuthActivity extends BaseActivity<ActivityAuthBinding, AuthActivityViewModel> implements Providers {


    @Override
    public boolean lightStatusBar() {
        return true;
    }

    @Override
    protected void initActivity(ActivityAuthBinding binding) {
        binding.setViewModel(viewModel);
        viewModel.setProviders(this);
        viewModel.init();
        navigator.attach(LoginFragment.newInstance(),LoginFragment.TAG);
    }

    @Override
    protected void onResume() {
        UserPreferences.initInstance(this);
        MockyConnection.init();
        LoginConnection.init();
        ProgressDialogManager.init(this);
        super.onResume();
    }
    @Override
    protected Class<AuthActivityViewModel> getViewModel() {
        return AuthActivityViewModel.class;
    }

    @Override
    public int getIdFragmentContainer() {
        return R.id.auth_fragment_container;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_auth;
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
        return getCurrentFragment().binding;
    }

    @Override
    public BaseFragment getFragment() {
        return getCurrentFragment();
    }
}
