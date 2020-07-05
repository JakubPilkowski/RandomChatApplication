package com.example.randomchatapplication.adapters.colorPicker;

import android.view.View;
import android.view.ViewGroup;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseRecyclerViewAdapter;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.databinding.CustomColorPickerItemBinding;
import com.example.randomchatapplication.interfaces.ColorPickerListener;

import java.util.ArrayList;
import java.util.List;

public class ColorPickerAdapter extends BaseRecyclerViewAdapter<Integer, BaseViewHolder>  {
    private List<ColorPickerAdapterViewModel> viewModels = new ArrayList<>();
    private ColorPickerListener listener;

    @Override
    public int getItemLayoutRes() {
        return R.layout.custom_color_picker_item;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType, View itemView) {
        CustomColorPickerItemBinding binding = CustomColorPickerItemBinding.bind(itemView);
        return new BaseViewHolder(itemView, binding);
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
        ColorPickerAdapterViewModel viewModel;
        if(viewModels.size()<=position){
            viewModel = new ColorPickerAdapterViewModel();
            viewModels.add(viewModel);
            holder.setViewModel(viewModel);
            ((CustomColorPickerItemBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setElement(items.get(position), listener);
        }
        else {
            viewModel = viewModels.get(position);
            ((CustomColorPickerItemBinding)holder.getBinding()).setViewModel(viewModel);
            holder.setViewModel(viewModel);
        }
    }

    public void setListener(ColorPickerListener listener) {
        this.listener = listener;
    }
}
