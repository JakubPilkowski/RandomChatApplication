package com.example.randomchatapplication.adapters.profiles;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.base.BaseRecyclerViewAdapter;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.databinding.SingleProfileItemBinding;
import com.example.randomchatapplication.models.Profile;

import java.util.ArrayList;

public class ProfilesAdapter extends BaseRecyclerViewAdapter<Profile, BaseViewHolder> {

    private ArrayList<ProfilesAdapterViewModel> viewModels = new ArrayList<>();
    private Activity activity;

    @Override
    public int getItemLayoutRes() {
        return R.layout.single_profile_item;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType, View itemView) {
        SingleProfileItemBinding binding = SingleProfileItemBinding.bind(itemView);
        return new BaseViewHolder(itemView, binding);
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
        ProfilesAdapterViewModel viewModel;
        if(viewModels.size()<=position){
            viewModel = new ProfilesAdapterViewModel();
            viewModels.add(viewModel);
            holder.setViewModel(viewModel);
            ((SingleProfileItemBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setElement(items.get(position), activity, holder.getBinding());
        }
        else {
            viewModel = viewModels.get(position);
            ((SingleProfileItemBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setViewModel(viewModel);
        }
    }

    public void removeItem(Profile profile) {
        int position = items.indexOf(profile);
        viewModels.remove(position);
        items.remove(position);
        notifyItemRemoved(position);
    }
    public BaseAdapterViewModel getViewModel(int index){
        return viewModels.get(index);
    }
}
