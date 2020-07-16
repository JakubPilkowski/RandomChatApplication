package com.example.randomchatapplication.ui.profiles.profile_image_gallery;

import androidx.databinding.ViewDataBinding;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ProfileImageGalleryFragmentBinding;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.models.Photo;
import com.example.randomchatapplication.navigation.Navigator;

import java.util.ArrayList;

public class ProfileImageGalleryFragment extends BaseFragment<ProfileImageGalleryFragmentBinding,ProfileImageGalleryViewModel> implements Providers {

    public static final String TAG  = "ProfileImageGalleryFragment";
    private ArrayList<Photo> photos = new ArrayList<>();
    private Photo chosenPhoto;


    public static ProfileImageGalleryFragment newInstance(ArrayList<Photo>photos, Photo chosenPhoto) {
        ProfileImageGalleryFragment fragment = new ProfileImageGalleryFragment();
        fragment.setChosenPhoto(chosenPhoto);
        fragment.setPhotos(photos);
        return fragment;
    }

    private void setPhotos(ArrayList<Photo> photos) {
        this.photos.addAll(photos);
    }

    private void setChosenPhoto(Photo chosenPhoto) {
        this.chosenPhoto = chosenPhoto;
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

    @Override
    public int getLayoutRes() {
        return R.layout.profile_image_gallery_fragment;
    }

    @Override
    public Class<ProfileImageGalleryViewModel> getViewModelClass() {
        return ProfileImageGalleryViewModel.class;
    }

    @Override
    public void bindData(ProfileImageGalleryFragmentBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init(photos, chosenPhoto);
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
}
