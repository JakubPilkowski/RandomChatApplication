package com.example.randomchatapplication.ui.profiles.profile_details;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ProfileDetailsFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.models.Profile;
import com.example.randomchatapplication.navigation.Navigator;

public class ProfileDetailsFragment extends BaseFragment<ProfileDetailsFragmentBinding,ProfileDetailsViewModel> implements Providers {

    public static final String TAG = "ProfileDetailsFragment";
    private Profile profile;

    public static ProfileDetailsFragment newInstance(Profile profile) {
        ProfileDetailsFragment profileDetailsFragment = new ProfileDetailsFragment();
        profileDetailsFragment.setProfile(profile);
        return profileDetailsFragment;
    }



    private void setProfile(Profile profile) {
        this.profile = profile;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.profile_details_fragment;
    }

    @Override
    public Class<ProfileDetailsViewModel> getViewModelClass() {
        return ProfileDetailsViewModel.class;
    }

    @Override
    public void bindData(ProfileDetailsFragmentBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init(profile);
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
        return ((MainActivity)getActivity()).navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return ((MainActivity)getActivity()).binding;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
