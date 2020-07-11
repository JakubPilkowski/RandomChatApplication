package com.example.randomchatapplication.adapters.profile_images;

import android.content.Context;
import android.media.MediaCodec;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseRecyclerViewAdapter;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.custom_views.ArrayView;
import com.example.randomchatapplication.databinding.ProfileDetailsFragmentBinding;
import com.example.randomchatapplication.databinding.ProfileDetailsImageViewBinding;
import com.example.randomchatapplication.models.Photo;

import java.util.ArrayList;

public class ProfileImagesAdapter extends BaseRecyclerViewAdapter<Photo, BaseViewHolder> {

    private ArrayList<ProfileImagesAdapterViewModel> viewModels = new ArrayList<>();

    @Override
    public int getItemLayoutRes() {
        return R.layout.profile_details_image_view;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType, View itemView) {
        ProfileDetailsImageViewBinding binding = ProfileDetailsImageViewBinding.bind(itemView);
        return new BaseViewHolder(itemView, binding);
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
        ProfileImagesAdapterViewModel viewModel;
        if(viewModels.size()<=position){
            viewModel = new ProfileImagesAdapterViewModel();
            viewModels.add(viewModel);
            holder.setViewModel(viewModel);
            ((ProfileDetailsImageViewBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setElement(items.get(position));
        }
        else {
            viewModel = viewModels.get(position);
            ((ProfileDetailsImageViewBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setViewModel(viewModel);
        }
    }
}
