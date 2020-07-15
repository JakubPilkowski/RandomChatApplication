package com.example.randomchatapplication.adapters.profile_image_gallery;

import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.custom_views.ZoomableImageView;
import com.example.randomchatapplication.databinding.ProfileImageGalleryFragmentBinding;
import com.example.randomchatapplication.databinding.ProfileImageGallerySingleImageBinding;
import com.example.randomchatapplication.models.Photo;
import com.example.randomchatapplication.ui.profiles.profile_image_gallery.ProfileImageGalleryViewModel;

public class ProfileImageGalleryAdapterViewModel extends BaseAdapterViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    private Photo photo;
    private ProfileImageGallerySingleImageBinding binding;
    private ProfileImageGalleryFragmentBinding fragmentBinding;

    @Override
    public void init(Object[] values) {
        photo = (Photo) values[0];
        binding = (ProfileImageGallerySingleImageBinding) values[1];
        fragmentBinding = (ProfileImageGalleryFragmentBinding) values[2];
        imageUrl.set(photo.getPhoto());
        binding.imageGallerySingleImageView.setGestureListener(new ZoomableImageView.GestureListener() {
            @Override
            public void onMove(MotionEvent event, float deltaX, float deltaY) {
                if (deltaX == -0.0)
                    fragmentBinding.profileImageGalleryViewPager.setUserInputEnabled(true);
                else
                    fragmentBinding.profileImageGalleryViewPager.setUserInputEnabled(false);
            }

            @Override
            public void onDoubleTap(MotionEvent event) {
                    fragmentBinding.profileImageGalleryViewPager.setUserInputEnabled(true);
            }

            @Override
            public void onScaleStart(ScaleGestureDetector event) {
                    fragmentBinding.profileImageGalleryViewPager.setUserInputEnabled(false);
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector, float saveScale) {
                if (saveScale == 1.0)
                    fragmentBinding.profileImageGalleryViewPager.setUserInputEnabled(true);
            }

            @Override
            public void onClick(MotionEvent event) {
                ProfileImageGalleryViewModel viewModel = fragmentBinding.getViewModel();
                if(viewModel.toolbarExpanded.get())
                    fragmentBinding.profileImageGalleryMotionLayout.transitionToStart();
                else
                    fragmentBinding.profileImageGalleryMotionLayout.transitionToEnd();
                viewModel.toolbarExpanded.set(!viewModel.toolbarExpanded.get());
                Log.d("ZoomableImageView", "onClick: ");
            }

        });
    }
}
