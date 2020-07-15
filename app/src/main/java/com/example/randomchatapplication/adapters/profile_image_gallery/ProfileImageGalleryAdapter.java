package com.example.randomchatapplication.adapters.profile_image_gallery;

import android.view.View;
import android.view.ViewGroup;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseRecyclerViewAdapter;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.databinding.ProfileImageGalleryFragmentBinding;
import com.example.randomchatapplication.databinding.ProfileImageGallerySingleImageBinding;
import com.example.randomchatapplication.models.Photo;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class ProfileImageGalleryAdapter extends BaseRecyclerViewAdapter<Photo, BaseViewHolder> {
    private ArrayList<ProfileImageGalleryAdapterViewModel> viewModels = new ArrayList<>();
    private ProfileImageGalleryFragmentBinding parentBinding;

    @Override
    public int getItemLayoutRes() {
        return R.layout.profile_image_gallery_single_image;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType, View itemView) {
        ProfileImageGallerySingleImageBinding binding = ProfileImageGallerySingleImageBinding.bind(itemView);
        return new BaseViewHolder(itemView, binding);
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
        ProfileImageGalleryAdapterViewModel viewModel;
        if(viewModels.size()<=position){
            viewModel = new ProfileImageGalleryAdapterViewModel();
            viewModels.add(viewModel);
            holder.setViewModel(viewModel);
            ((ProfileImageGallerySingleImageBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setElement(items.get(position), holder.getBinding(), parentBinding);
        }
        else {
            viewModel = viewModels.get(position);
            ((ProfileImageGallerySingleImageBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setViewModel(viewModel);
        }
    }

    public void setParentBinding(ProfileImageGalleryFragmentBinding parentBinding) {
        this.parentBinding = parentBinding;
    }
}
