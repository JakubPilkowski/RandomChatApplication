package com.example.randomchatapplication.adapters.profile_images;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.custom_views.ArrayView;
import com.example.randomchatapplication.databinding.ProfileDetailsImageViewBinding;
import com.example.randomchatapplication.models.Photo;

import java.util.ArrayList;

public class ProfileImagesAdapter extends ArrayAdapter<Photo> {

    private ArrayList<Photo> photos = new ArrayList<>();
    private LayoutInflater mInflater;

    public ProfileImagesAdapter(@NonNull Context context, ArrayList<Photo> photos) {
        super(context, R.layout.profile_details_image_view);
        this.photos.clear();
        this.photos.addAll(photos);
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout linearLayout = (LinearLayout) convertView;
        if (linearLayout==null){
            linearLayout = (LinearLayout) mInflater.inflate(R.layout.profile_details_image_view,parent, false);
        }
        ImageView imageView = linearLayout.findViewById(R.id.profile_details_single_image_view);
        imageView.getLayoutParams().height = parent.getWidth()/2;
        ProfileDetailsImageViewBinding binding = ProfileDetailsImageViewBinding.bind(linearLayout);
        ProfileImagesAdapterViewModel viewModel = new ProfileImagesAdapterViewModel();
        binding.setViewModel(viewModel);
        viewModel.init(photos.get(position));
        return linearLayout;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Nullable
    @Override
    public Photo getItem(int position) {
        return photos.get(position);
    }

    @Override
    public int getPosition(@Nullable Photo item) {
        return photos.indexOf(item);
    }
}
