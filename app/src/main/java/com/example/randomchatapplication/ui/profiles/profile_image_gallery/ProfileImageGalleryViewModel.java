package com.example.randomchatapplication.ui.profiles.profile_image_gallery;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.viewpager2.widget.ViewPager2;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.adapters.profile_image_gallery.ProfileImageGalleryAdapter;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ProfileImageGalleryFragmentBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;
import com.example.randomchatapplication.models.Photo;

import java.util.ArrayList;

public class ProfileImageGalleryViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel

    private ProfileImageGalleryAdapter adapter;
    private ArrayList<Photo> photos = new ArrayList<>();
    private Photo chosenPhoto;
    public ObservableField<String> imageInfo = new ObservableField<>();
    public ObservableInt marginTop = new ObservableInt();
//    public ObservableField<ProfileImageGalleryAdapter> profileImageGalleryAdapter = new ObservableField<>();
    private ViewPager2 viewPager2;
    public ObservableBoolean toolbarExpanded = new ObservableBoolean(false);


    public void init(ArrayList<Photo> photos, Photo chosenPhoto){
        this.photos.addAll(photos);
        this.chosenPhoto = chosenPhoto;
        adapter = new ProfileImageGalleryAdapter();
        adapter.setItems(photos);
        adapter.setParentBinding((ProfileImageGalleryFragmentBinding) getBinding());
        imageInfo.set(photos.indexOf(chosenPhoto)+1+"/"+photos.size());
        marginTop.set(ScreenHelper.getStatusBarHeight(getActivity().getApplicationContext()));
        viewPager2 = ((ProfileImageGalleryFragmentBinding)getBinding()).profileImageGalleryViewPager;
        ConstraintSet imageGalleryConstraintSetStart = ((ProfileImageGalleryFragmentBinding)getBinding()).profileImageGalleryMotionLayout.getConstraintSet(R.id.profile_details_image_gallery_trans_start);
        ConstraintSet.Constraint imageGalleryToolbarStart = imageGalleryConstraintSetStart.getConstraint(R.id.profile_image_gallery_toolbar);
        imageGalleryToolbarStart.layout.topMargin = marginTop.get();

        ConstraintSet imageGalleryConstraintSetEnd = ((ProfileImageGalleryFragmentBinding)getBinding()).profileImageGalleryMotionLayout.getConstraintSet(R.id.profile_details_image_gallery_trans_end);
        ConstraintSet.Constraint imageGalleryToolbarEnd = imageGalleryConstraintSetEnd.getConstraint(R.id.profile_image_gallery_toolbar);
        imageGalleryToolbarEnd.layout.topMargin = marginTop.get();



//        profileImageGalleryAdapter.set(adapter);
//        viewPager2.setUserInputEnabled(false);
        viewPager2.setAdapter(adapter);
        viewPager2.setCurrentItem(photos.indexOf(chosenPhoto));
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                imageInfo.set(position+1+"/"+photos.size());
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    public void onBackPress(){
        getActivity().onBackPressed();
    }

}
