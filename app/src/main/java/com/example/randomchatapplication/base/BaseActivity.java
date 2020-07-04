package com.example.randomchatapplication.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import androidx.lifecycle.ViewModelProviders;

import com.example.randomchatapplication.api.LoginConnection;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.helpers.UserPreferences;
import com.example.randomchatapplication.navigation.Navigator;


public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    public Navigator navigator = new Navigator();
    public VM viewModel;
    public B binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserPreferences.initInstance(this);
        MockyConnection.init();
        LoginConnection.init();
        ProgressDialogManager.init(this);
//        setStatusBarColor();
        navigator.setActivity(this);
        navigator.setFragmentContainer(getIdFragmentContainer());
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        viewModel = ViewModelProviders.of(this).get(getViewModel());
        initActivity(binding);
    }

    public abstract boolean lightStatusBar();

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (!lightStatusBar()) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(0);
            }
        }
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
                    case 2:
                        finish();
                        break;
                }

        }
    }

    @Override
    protected void onResume() {
        hideKeyboard();
        super.onResume();
    }

    @Override
    protected void onPause() {
        hideKeyboard();
        super.onPause();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    protected abstract void initActivity(B binding);

    protected abstract Class<VM> getViewModel();

    public abstract int getIdFragmentContainer();

    @LayoutRes
    public abstract int getLayoutRes();

}
