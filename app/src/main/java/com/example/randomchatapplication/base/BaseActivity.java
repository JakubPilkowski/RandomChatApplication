package com.example.randomchatapplication.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import androidx.lifecycle.ViewModelProviders;

import com.example.randomchatapplication.navigation.Navigator;


public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    public Navigator navigator = new Navigator();
    public VM viewModel;
    public B binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.setActivity(this);
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        viewModel = ViewModelProviders.of(this).get(getViewModel());
        initActivity(binding);
    }

    public BaseFragment getCurrentFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentById(getIdFragmentContainer());
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1)
            finish();
        else {
            BaseFragment fragment = getCurrentFragment();
                switch (fragment.getBackPressType()) {
                    case 0:
                        super.onBackPressed();
                        break;
                    case 1:
                        navigator.clearBackStack();
                        break;
                }

        }
    }

    protected abstract void initActivity(B binding);

    protected abstract Class<VM> getViewModel();

    public abstract int getIdFragmentContainer();

    @LayoutRes
    public abstract int getLayoutRes();

}
