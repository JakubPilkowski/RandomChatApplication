package com.example.randomchatapplication.adapters.hobbies.hobbiesAdapter;

import android.view.View;
import android.view.ViewGroup;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.adapters.hobbies.hobbiesSearchViewAdapter.HobbySearchViewAdapterViewModel;
import com.example.randomchatapplication.base.BaseRecyclerViewAdapter;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.databinding.HobbyItemBinding;
import com.example.randomchatapplication.databinding.SearchHobbyItemBinding;
import com.example.randomchatapplication.models.Hobby;

import java.util.ArrayList;
import java.util.List;

public class HobbiesAdapter extends BaseRecyclerViewAdapter<Hobby, BaseViewHolder> {

    private List<HobbiesAdapterViewModel> viewModels = new ArrayList<>();

    @Override
    public void setItems(List<Hobby> items) {
        viewModels.clear();
        super.setItems(items);
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.hobby_item;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType, View itemView) {
        HobbyItemBinding binding = HobbyItemBinding.bind(itemView);
        return new BaseViewHolder(itemView, binding);
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
        HobbiesAdapterViewModel viewModel;
        if (viewModels.size() <= position) {
            viewModel = new HobbiesAdapterViewModel();
            viewModels.add(viewModel);
            holder.setViewModel(viewModel);
            ((HobbyItemBinding) holder.getBinding()).setViewModel(viewModel);
            holder.setElement(items.get(position), this);
        } else {
            viewModel = viewModels.get(position);
            ((HobbyItemBinding) holder.getBinding()).setViewModel(viewModel);
            holder.setViewModel(viewModel);
        }
    }
    void onRemoveItem(Hobby item){
        items.remove(item);
        notifyDataSetChanged();
    }
}
