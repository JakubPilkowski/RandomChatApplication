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
import com.example.randomchatapplication.models.ListenerContainer;
import com.example.randomchatapplication.navigation.Navigator;

public class SearchViewActivity extends BaseActivity<ActivitySearchViewBinding,SearchViewActivityViewModel> implements Providers {
    private HobbyInterface hobbyInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent =getIntent();
        ListenerContainer container = intent.getParcelableExtra("listener");
        Log.d("onCreate", container.getHobbyInterface().toString());
        setHobbyInterface(container.getHobbyInterface());
        super.onCreate(savedInstanceState);
    }

    public void setHobbyInterface(HobbyInterface hobbyInterface) {
        this.hobbyInterface = hobbyInterface;
    }

    @Override
    protected void initActivity(ActivitySearchViewBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init(hobbyInterface);
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
