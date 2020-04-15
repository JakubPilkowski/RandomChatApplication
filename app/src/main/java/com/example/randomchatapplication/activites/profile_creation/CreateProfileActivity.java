package com.example.randomchatapplication.activites.profile_creation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.os.Bundle;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class CreateProfileActivity extends BaseActivity<ActivityCreateProfileBinding,CreateProfileViewModel> implements Providers {


    @Override
    protected void initActivity(ActivityCreateProfileBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init();
    }

    @Override
    protected Class<CreateProfileViewModel> getViewModel() {
        return CreateProfileViewModel.class;
    }

    @Override
    public int getIdFragmentContainer() {
        return R.id.create_profile_container;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_create_profile;
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
