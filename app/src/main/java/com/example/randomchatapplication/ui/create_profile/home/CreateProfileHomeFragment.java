package com.example.randomchatapplication.ui.create_profile.home;

import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.CreateProfileHomeFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class CreateProfileHomeFragment extends BaseFragment<CreateProfileHomeFragmentBinding, CreateProfileHomeFragmentViewModel> implements Providers {

    public static final String TAG = "CreateProfileHomeFragment";

    public static CreateProfileHomeFragment newInstance() {
        return new CreateProfileHomeFragment();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.create_profile_home_fragment;
    }

    @Override
    public Class<CreateProfileHomeFragmentViewModel> getViewModelClass() {
        return CreateProfileHomeFragmentViewModel.class;
    }

    @Override
    public void bindData(CreateProfileHomeFragmentBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init();
    }

    @Override
    public int getToolbarType() {
        return 0;
    }

    @Override
    public int getBackPressType() {
        return 0;
    }

    @Override
    public String getToolbarName() {
        return null;
    }

    @Override
    public float getToolbarFontSize() {
        return 0;
    }

    @Override
    public Navigator getNavigator() {
        return ((CreateProfileActivity)getActivity()).navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return ((CreateProfileActivity)getActivity()).binding;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
