package com.example.randomchatapplication.adapters.profile_hobbies;

import android.view.View;
import android.view.ViewGroup;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseRecyclerViewAdapter;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.databinding.ProfileHobbyViewBinding;
import com.example.randomchatapplication.models.Hobby;

import java.util.ArrayList;

public class ProfileHobbiesAdapter extends BaseRecyclerViewAdapter<Hobby, BaseViewHolder> {

    private ArrayList<ProfileHobbiesAdapterViewModel> viewModels = new ArrayList<>();


    @Override
    public int getItemLayoutRes() {
        return R.layout.profile_hobby_view;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType, View itemView) {
        ProfileHobbyViewBinding profileHobbyViewBinding = ProfileHobbyViewBinding.bind(itemView);
        return new BaseViewHolder(itemView, profileHobbyViewBinding);
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
//        ProfileHobbiesAdapterViewModel viewModel;
//        if(viewModels.size()<=position){
//            viewModel = new ProfileHobbiesAdapterViewModel();
//            viewModels.add(viewModel);
//            holder.setViewModel(viewModel);
//            ((ProfileHobbyViewBinding)holder.getBinding()).setViewModel(viewModel);
//            holder.setElement(items.get(position));
//        }
//        else {
//            viewModel = viewModels.get(position);
//            ((ProfileHobbyViewBinding)holder.getBinding()).setViewModel(viewModel);
//            holder.setViewModel(viewModel);
//        }
    }
}
