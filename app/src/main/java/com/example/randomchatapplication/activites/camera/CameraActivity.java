package com.example.randomchatapplication.activites.camera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.os.Bundle;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivityCameraBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class CameraActivity extends BaseActivity<ActivityCameraBinding, CameraViewModel> implements Providers {


    @Override
    public boolean lightStatusBar() {
        return true;
    }

    @Override
    protected void initActivity(ActivityCameraBinding binding) {
        binding.setViewModel(viewModel);
        viewModel.setProviders(this);
        viewModel.init();
    }

    @Override
    protected Class<CameraViewModel> getViewModel() {
        return CameraViewModel.class;
    }

    @Override
    public int getIdFragmentContainer() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_camera;
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
        return null;
    }
}
