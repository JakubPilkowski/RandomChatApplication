package com.example.randomchatapplication.ui.profiles.profile_details;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.Scene;
import androidx.transition.TransitionInflater;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

import android.view.LayoutInflater;
import android.view.MotionEvent;
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
        binding.profileDetailsMainMotionLayout.setTransition(R.id.profile_details_main_scene_start, R.id.profile_details_main_scene_end);
        binding.profileDetailsMainMotionLayout.transitionToEnd();
        binding.profileDetailsMainMotionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                binding.profileDetailsButtonsMotionLayout.setTransition(R.id.profile_details_buttons_scene_start, R.id.profile_details_buttons_scene_end);
                binding.profileDetailsButtonsMotionLayout.transitionToEnd();
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });
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
