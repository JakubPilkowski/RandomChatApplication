package com.example.randomchatapplication.ui.profiles;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ProfilesFragmentBinding;
import com.example.randomchatapplication.helpers.CubeAnimation;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.navigation.Navigator;

public class ProfilesFragment extends BaseFragment<ProfilesFragmentBinding, ProfilesViewModel> implements Providers {

    public static final String TAG = "ProfilesFragment";

    public static ProfilesFragment newInstance() {
        return new ProfilesFragment();
    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (!enter)
            return CubeAnimation.create(CubeAnimation.RIGHT, false, 500);
        else
            return super.onCreateAnimation(transit, true, nextAnim);
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
