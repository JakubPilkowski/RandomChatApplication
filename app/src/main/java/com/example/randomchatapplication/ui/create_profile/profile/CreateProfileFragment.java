package com.example.randomchatapplication.ui.create_profile.profile;

import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.CreateProfileFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class CreateProfileFragment extends BaseFragment<CreateProfileFragmentBinding, CreateProfileFragmentViewModel> implements Providers {




    public static CreateProfileFragment newInstance() {
        return new CreateProfileFragment();
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
        return ((CreateProfileActivity)getActivity()).getBinding();
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
