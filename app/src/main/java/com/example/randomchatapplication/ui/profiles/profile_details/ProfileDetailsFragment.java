package com.example.randomchatapplication.ui.profiles.profile_details;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.ViewDataBinding;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ProfileDetailsFragmentBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.models.Profile;
import com.example.randomchatapplication.navigation.Navigator;

public class ProfileDetailsFragment extends BaseFragment<ProfileDetailsFragmentBinding, ProfileDetailsViewModel> implements Providers {

    public static final String TAG = "ProfileDetailsFragment";
    private Profile profile;

    public static ProfileDetailsFragment newInstance(Profile profile, Context context) {
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
        postponeEnterTransition();
        ImageView imageView = binding.profileImage;
        Context context = imageView.getContext();
        Glide.with(context)
                .load(profile.getPhotos().get(0).getPhoto())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        startPostponedEnterTransition();
                        ConstraintSet swipeConstraintEnd = binding.profileDetailsMotionLayout.getConstraintSet(R.id.profile_details_main_swipe_end);
                        ConstraintSet.Constraint profileImageEnd = swipeConstraintEnd.getConstraint(R.id.profile_image);
                        profileImageEnd.layout.mHeight = (int) (ScreenHelper.getStatusBarHeight(getContext()) + DimensionsHelper.convertDpToPixel(56, getContext()));

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
                        return false;
                    }

                })
                .into(imageView);
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
