package com.example.randomchatapplication.activites.search_view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivitySearchViewBinding;
import com.example.randomchatapplication.interfaces.HobbyInterface;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class SearchViewActivity extends BaseActivity<ActivitySearchViewBinding,SearchViewActivityViewModel> implements Providers {

    @Override
    protected void initActivity(ActivitySearchViewBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init();
    }

    @Override
    protected Class<SearchViewActivityViewModel> getViewModel() {
        return SearchViewActivityViewModel.class;
    }

    @Override
    public int getIdFragmentContainer() {
        return 0;
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_search_view;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Navigator getNavigator() {
        return null;
    }

    @Override
    public ViewDataBinding getBinding() {
        return null;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return binding;
    }

    @Override
    public BaseFragment getFragment() {
        return null;
    }
}
