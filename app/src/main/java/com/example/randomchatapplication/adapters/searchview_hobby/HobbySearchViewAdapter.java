package com.example.randomchatapplication.adapters.searchview_hobby;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseRecyclerViewAdapter;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.databinding.SearchHobbyItemBinding;
import com.example.randomchatapplication.models.Hobby;

import java.util.ArrayList;
import java.util.List;

public class HobbySearchViewAdapter extends BaseRecyclerViewAdapter<Hobby, BaseViewHolder> implements Filterable {

    private HobbiesFilter filter;
    private List<Hobby> itemsBase = new ArrayList<>();

    @Override
    public void setItems(List<Hobby> items) {
        super.setItems(items);
        itemsBase.addAll(items);
    }

    private List<HobbySearchViewAdapterViewModel> viewModels = new ArrayList<>();

    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new HobbiesFilter();
        return filter;
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.search_hobby_item;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType, View itemView) {
        SearchHobbyItemBinding binding = SearchHobbyItemBinding.bind(itemView);
        return new BaseViewHolder(itemView, binding);
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
        HobbySearchViewAdapterViewModel viewModel;
        if (viewModels.size() <= position) {
            viewModel = new HobbySearchViewAdapterViewModel();
            viewModels.add(viewModel);
            holder.setViewModel(viewModel);
            ((SearchHobbyItemBinding) holder.getBinding()).setViewModel(viewModel);
            holder.setElement(items.get(position));
        } else {
            viewModel = viewModels.get(position);
            ((SearchHobbyItemBinding) holder.getBinding()).setViewModel(viewModel);
            holder.setViewModel(viewModel);
        }
    }

    private class HobbiesFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d("performFiltering", String.valueOf(itemsBase.size()));
            FilterResults results = new FilterResults();
            if (constraint.length() > 0) {
                List<Hobby> hobbies = new ArrayList<>();
                for (int i = 0; i < getItemCount(); i++) {
                    if (itemsBase.get(i).getValue().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        hobbies.add(itemsBase.get(i));
                    }
                }
                results.count = hobbies.size();
                results.values = hobbies;
            } else {
                results.count = getItemCount();
                results.values = itemsBase;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            viewModels.clear();
            items.clear();
            items.addAll((ArrayList<Hobby>) results.values);
            notifyDataSetChanged();
        }
    }
}
