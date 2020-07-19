package com.example.randomchatapplication.ui.profiles.profile;

import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ProfilesFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class ProfilesFragment extends BaseFragment<ProfilesFragmentBinding, ProfilesViewModel> implements Providers {

    public static final String TAG = "ProfilesFragment";

    public static ProfilesFragment newInstance() {
        return new ProfilesFragment();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.profiles_fragment;
    }

    @Override
    public Class<ProfilesViewModel> getViewModelClass() {
        return ProfilesViewModel.class;
    }

    @Override
    public void bindData(ProfilesFragmentBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init();
        viewModel.setProfiles(((MainActivity)getActivity()).viewModel.getProfiles());
    }

    @Override
    public int getToolbarType() {
        return 1;
    }

    @Override
    public int getBackPressType() {
        return 0;
    }

    @Override
    public String getToolbarName() {
        return "Profile";
    }

    @Override
    public float getToolbarFontSize() {
        return 0;
    }

    @Override
    public Navigator getNavigator() {
        return ((MainActivity) getActivity()).navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return ((MainActivity) getActivity()).binding;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
