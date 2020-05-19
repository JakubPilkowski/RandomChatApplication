package com.example.randomchatapplication.models;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

public class ViewInfo {
    private ViewDataBinding binding;
    private ViewModel viewModel;
    private View view;

    public ViewInfo(ViewDataBinding binding, ViewModel viewModel, View view) {
        this.binding = binding;
        this.viewModel = viewModel;
        this.view = view;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public View getView() {
        return view;
    }
}
