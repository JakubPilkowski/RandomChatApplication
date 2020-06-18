package com.example.randomchatapplication.ui.create_profile.profile;

import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.CreateProfileFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class CreateProfileFragment extends BaseFragment<CreateProfileFragmentBinding, CreateProfileFragmentViewModel> implements Providers {

    public static final String TAG = "CreateProfileFragment";
    private int step;
    private int statusBarHeight;
    public static CreateProfileFragment newInstance(int step, int statusBarHeight) {

        CreateProfileFragment createProfileFragment = new CreateProfileFragment();
        createProfileFragment.setStep(step);
        createProfileFragment.setStatusBarHeight(statusBarHeight);
        return createProfileFragment;
    }

    public void setStatusBarHeight(int statusBarHeight) {
        this.statusBarHeight = statusBarHeight;
    }

    private void setStep(int step) {
        this.step = step;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.create_profile_fragment;
    }

    @Override
    public Class<CreateProfileFragmentViewModel> getViewModelClass() {
        return CreateProfileFragmentViewModel.class;
    }

    @Override
    public void bindData(CreateProfileFragmentBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init(step, statusBarHeight);

    }

    @Override
    public int getToolbarType() {
        return 0;
    }

    @Override
    public int getBackPressType() {
        return 2;
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
        return ((CreateProfileActivity)getActivity()).getBinding();
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
